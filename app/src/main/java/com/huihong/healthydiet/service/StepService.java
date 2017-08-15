package com.huihong.healthydiet.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.mInterface.UpdateStepCallBack;
import com.huihong.healthydiet.mInterface.UpdateTimeCallBack;
import com.huihong.healthydiet.stepcount.accelerometer.StepCount;
import com.huihong.healthydiet.stepcount.accelerometer.StepValuePassListener;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;


/**
 * 这个服务用来计步
 */
public class StepService extends Service {
    private String TAG = "StepService";


    //////////////
    private com.huihong.healthydiet.model.mybean.StepCount stepCount;//步数信息
    private int CURRENT_STEP;//当前所走的步数
    private int STEP_RUN_TIME;//运动总共的时长
    private float STEP_RUN_DISTANCE;//运动的距离


    private SensorManager sensorManager;//传感器管理对象
    private BroadcastReceiver mBatInfoReceiver;//广播接受者

    //服务开启时间
    private int OPEN_TIME = 0;
    //服务开启时候走过的步数
    private int OPEN_STEP = 0;


    /**
     * 通知管理对象
     */
    private NotificationManager mNotificationManager;
    /**
     * 加速度传感器中获取的步数
     */
    private StepCount mStepCount;
    /**
     * IBinder对象，向Activity传递数据的桥梁
     */
    private StepBinder stepBinder = new StepBinder();
    /**
     * 通知构建者
     */
    private NotificationCompat.Builder mBuilder;

    private Thread stepThread;


    private UpdateStepCallBack updateStepCallBack;


    public void setUpdateStepCallBack(UpdateStepCallBack updateStepCallBack) {
        this.updateStepCallBack = updateStepCallBack;
    }


    private UpdateTimeCallBack updateTimeCallBack;

    public void setUpdateTimeCallBack(UpdateTimeCallBack updateTimeCallBack) {
        this.updateTimeCallBack = updateTimeCallBack;
    }


    //服务第一次创建的时候会调用onCreate
    @Override
    public void onCreate() {
        super.onCreate();
        initBroadcastReceiver();
        stepThread = new Thread(new Runnable() {//开启一个线程接收从传感器获得过来的数据
            public void run() {
                startStepDetector();
            }
        });
        stepThread.start();
    }


    /**
     * 每一次绑定服务都会调用这个方法在这里初始化
     */

    @Override
    public IBinder onBind(Intent intent) {
        //从缓存中去拿步数信息
        stepCount = CacheUtils.getStepCount(this);
        CURRENT_STEP = stepCount.getStepCount();
        STEP_RUN_TIME = stepCount.getTime();
        STEP_RUN_DISTANCE = stepCount.getDistance();
        return stepBinder;
    }


    /**
     * 获取当天日期
     *
     * @return
     */
    private String getTodayDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 初始化通知栏
     */
    private void initNotification() {
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle(getResources().getString(R.string.app_name))
                .setContentText("今日步数" + CURRENT_STEP + " 步")
                .setContentIntent(getDefalutIntent(Notification.FLAG_ONGOING_EVENT))
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                .setAutoCancel(false)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(true)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setSmallIcon(R.mipmap.ic_launcher);
        Notification notification = mBuilder.build();
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        startForeground(notifyId_Step, notification);//开启前台进程
    }


    /**
     * 注册广播
     */
    private void initBroadcastReceiver() {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);

        mBatInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                String action = intent.getAction();
                if (Intent.ACTION_TIME_TICK.equals(action)) {
                    //系统每分钟会发出该广播
                    STEP_RUN_TIME++;//服务运行的时间+1
                    OPEN_TIME++;
                    //每分钟去保存一次当前的状态
                    if (updateTimeCallBack != null) {
                        updateTimeCallBack.updateTime(STEP_RUN_TIME);
                    }
                    //当天运动的步数每分钟保存本地一次
                    saveStepCount();
                    //当天运动的步数每15分钟提交一次
                    submitStepCount();
                    //没分钟去判断一次是否为新的一天
                    isNewDay();
                    //没分钟去判断一次是否获得积分
                    canGetIntegral();
                }
            }
        };
        registerReceiver(mBatInfoReceiver, filter);
    }

    /**
     * 是否能获得积分
     */
    private void canGetIntegral() {
        //如果计时30分钟 步数大于3000步则调接口
        LogUtil.i("此次计步器服务开启时间" + OPEN_TIME + "s  此时运动的步数" + OPEN_STEP);
        if (OPEN_TIME % 30 == 0) {
            if (OPEN_STEP >= 3000) {

                OkHttpUtils
                        .post()
                        .url(AppUrl.ADD_SCORE_RECORD)
                        .addParams("UserId", CacheUtils.getUserId(this))
                        .addParams("ScoreType", "Sport")
                        .addParams("token",CacheUtils.getToken(this))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                LogUtil.i("在运动服务中调接口 获得运动积分" + e);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                LogUtil.i("在运动服务中调接口 获得运动积分" + response);

                            }
                        });

            }
            OPEN_STEP = 0;//没30分钟清0
        }
    }


    /**
     * 监听晚上0点变化初始化数据
     * 这里到了0点之后需要关闭计步器服务
     * 并且提交当天的计步数据
     * 提交完成之后需要关闭手机的计步器状态
     * 并且把缓存中的数据清空
     */
    private void isNewDay() {
        //每天11点的时候去提交这次数据
        String nowTime = new SimpleDateFormat("HH:mm").format(new Date()) + "";//这里就取分钟
        if (nowTime.equals("00:00")) {
            //关闭服务 并清空数据
            stepCount.setStepCount(0);
            stepCount.setTime(0);
            CacheUtils.putStepCount(this, stepCount);
            CacheUtils.setRunState(this, "OFF");
            stopSelf();//关闭服务
        }
    }

    /**
     * 向服务器提交数据
     */
    private int LAST_SUBMIT_STEP = 0;//最近一次提交的数据

    private void submitStepCount() {
        //这里定时向服务器去定时提交最新的运动记录
        String nowTime = new SimpleDateFormat("mm").format(new Date()) + "";//这里就取分钟
        if (nowTime.equals("15") | nowTime.equals("30") | nowTime.equals("45") | nowTime.equals("00")) {
            //向服务器提交数据
            //如果上次提交的数据小于当前的数据那么去提交
            if (LAST_SUBMIT_STEP < CURRENT_STEP) {
                LAST_SUBMIT_STEP = CURRENT_STEP;
                OkHttpUtils
                        .post()
                        .url(AppUrl.UPLOAD_SPORT_INFO)
                        .addParams("UserId", CacheUtils.getUserId(this))
                        .addParams("steps", CURRENT_STEP + "")
                        .addParams("token",CacheUtils.getToken(this))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                LogUtil.i("在运动服务中调接口" + e);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                LogUtil.i("在运动服务中调接口" + response);

                            }
                        });
            }
        }
    }

    /**
     * 更新步数通知
     * 在通知栏通知
     */
    private void updateNotification() {
        //设置点击跳转
        Intent hangIntent = new Intent(this, MainActivity.class);
        PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = mBuilder.setContentTitle(getResources().getString(R.string.app_name))
                .setContentText("今日步数" + CURRENT_STEP + " 步")
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setContentIntent(hangPendingIntent)
                .build();
        mNotificationManager.notify(notifyId_Step, notification);
//        if (mCallback != null) {
////            mCallback.updateUi(CURRENT_STEP);
//        }
        Log.d(TAG, "updateNotification()");
    }

    /**
     * 记步Notification的ID
     */
    int notifyId_Step = 100;


    /**
     * @获取默认的pendingIntent,为了防止2.3及以下版本报错
     * @flags属性: 在顶部常驻:Notification.FLAG_ONGOING_EVENT
     * 点击去除： Notification.FLAG_AUTO_CANCEL
     */
    public PendingIntent getDefalutIntent(int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(), flags);
        return pendingIntent;
    }


    /**
     * 向Activity传递数据的纽带
     */
    public class StepBinder extends Binder {
        public StepService getService() {
            return StepService.this;
        }
    }


    /**
     * 判断使用哪种计步器传感器
     * 获取传感器实例
     */
    private void startStepDetector() {
        if (sensorManager != null) {
            sensorManager = null;
        }
        LogUtil.i(TAG, "开启线程判断传感器");
        // 获取传感器管理器的实例
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        if (Build.VERSION.SDK_INT >= 19) {
            LogUtil.i(TAG, "API大于19 判断有无计步器");
            //API大于19的可能会有计步器
            addCountStepListener();
        } else {
            LogUtil.i(TAG, "API小于19 开启重力传感器");
            addBasePedometerListener();
        }
    }


    /**
     * 如果设备支持计步器传感器
     * 那么使用计步器传感器
     */
    private Sensor detectorSensor;//计步器传感器
    private SensorEventListener mSensorEventListener;//计步器传感器监听

    private void addCountStepListener() {

        //获取计步器传感器
        detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (detectorSensor != null) {

            LogUtil.i(TAG, "有计步器传感器 使用计步器传感器");
            mSensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    if (event.values[0] == 1.0f) {
                        CURRENT_STEP++;
                        OPEN_STEP++;
                        LogUtil.i(TAG, "计步器传感器计步" + CURRENT_STEP);
                        if (updateStepCallBack != null) {
                            //计算时间差
                            updateStepCallBack.updateStep(CURRENT_STEP, STEP_RUN_TIME);
                        }
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };


            sensorManager.registerListener(mSensorEventListener, detectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            LogUtil.i(TAG, "没有计步器传感器 使用重力传感器");
            addBasePedometerListener();
        }
    }

    /**
     * 如果设备不支持计步器传感器
     * 那么使用重力传感器来代替
     */
    private Sensor sensor;//重力传感器

    private void addBasePedometerListener() {

        mStepCount = new StepCount();
        mStepCount.setSteps(0);//设置开始值
        //获取重力传感器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //注册重力传感器
        boolean isAvailable = sensorManager.registerListener(mStepCount.getStepDetector(), sensor, SensorManager.SENSOR_DELAY_UI);
        mStepCount.initListener(new StepValuePassListener() {
            @Override
            public void stepChanged(int steps) {
                LogUtil.i(TAG, "重力传感器计步器" + steps);//返回的值是累加的
                CURRENT_STEP++;
                OPEN_STEP++;
                if (updateStepCallBack != null) {
                    updateStepCallBack.updateStep(CURRENT_STEP, STEP_RUN_TIME);
                }
            }
        });
        if (isAvailable) {
            LogUtil.i(TAG, "加速度传感器可以使用");
        } else {
            LogUtil.i(TAG, "加速度传感器无法使用");
        }
    }


    /**
     * 保存当前步数的信息
     */
    private void saveStepCount() {
        stepCount.setStepCount(CURRENT_STEP);
        stepCount.setTime(STEP_RUN_TIME);
        CacheUtils.putStepCount(this, stepCount);
    }


    //计步器服务销毁的时候调用 关闭计步器线程
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);//取消前台进程
        unregisterReceiver(mBatInfoReceiver);
        //重力传感器解除
        if (mStepCount != null && sensor != null) {
            sensorManager.unregisterListener(mStepCount.getStepDetector(), sensor);
        }
        //计步器传感器解除
        if (detectorSensor != null && mSensorEventListener != null) {
            sensorManager.unregisterListener(mSensorEventListener, detectorSensor);
        }
    }

    //解除绑定服务的时候调用
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
