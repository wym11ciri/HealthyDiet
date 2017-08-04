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
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.mInterface.UpdateStepCallBack;
import com.huihong.healthydiet.stepcount.accelerometer.StepCount;
import com.huihong.healthydiet.stepcount.accelerometer.StepValuePassListener;
import com.huihong.healthydiet.utils.common.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 这个服务用来计步
 */
public class StepService extends Service {
    private String TAG = "StepService";

    private static int DURATION = 30 * 1000;//设置多久去存储一下数据
    private static String CURRENT_DATE = "";//当前的日期
    private SensorManager sensorManager;//传感器管理对象
    private BroadcastReceiver mBatInfoReceiver;//广播接受者
    /**
     * 保存记步计时器
     */
    private TimeCount time;
    private int CURRENT_STEP;//当前所走的步数
    /**
     * 计步传感器类型  Sensor.TYPE_STEP_COUNTER或者Sensor.TYPE_STEP_DETECTOR
     */
    private static int stepSensorType = -1;
    /**
     * 每次第一次启动记步服务时是否从系统中获取了已有的步数记录
     */
    private boolean hasRecord = false;
    /**
     * 系统中获取到的已有的步数
     */
    private int hasStepCount = 0;
    /**
     * 上一次的步数
     */
    private int previousStepCount = 0;
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

    private long startTime;
    //服务第一次创建的时候会调用onCreate
    @Override
    public void onCreate() {
        super.onCreate();
        //获得计步器开始的时间
        startTime=System.currentTimeMillis();


//        initNotification();
//        initTodayData();
        initBroadcastReceiver();


        stepThread = new Thread(new Runnable() {//开启一个线程接收从传感器获得过来的数据
            public void run() {
                startStepDetector();
            }
        });
        stepThread.start();


        startTimeCount();

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


//    /**
//     * 初始化当天的步数
//     */
//    private void initTodayData() {
//        CURRENT_DATE = getTodayDate();
//        DbUtils.createDb(this, "DylanStepCount");
//        DbUtils.getLiteOrm().setDebugged(false);
//        //获取当天的数据，用于展示
//        List<StepData> list = DbUtils.getQueryByWhere(StepData.class, "today", new String[]{CURRENT_DATE});
//        if (list.size() == 0 || list.isEmpty()) {
//            CURRENT_STEP = 0;
//        } else if (list.size() == 1) {
//            Log.v(TAG, "StepData=" + list.get(0).toString());
//            CURRENT_STEP = Integer.parseInt(list.get(0).getStep());
//        } else {
//            Log.v(TAG, "出错了！");
//        }
//        if (mStepCount != null) {
//            mStepCount.setSteps(CURRENT_STEP);
//        }
//        updateNotification();
//    }

    /**
     * 注册广播
     */
    private void initBroadcastReceiver() {
        final IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_SCREEN_OFF);// 屏幕灭屏广播
        filter.addAction(Intent.ACTION_SHUTDOWN); //关机广播
        filter.addAction(Intent.ACTION_SCREEN_ON);// 屏幕亮屏广播
        filter.addAction(Intent.ACTION_USER_PRESENT); // 屏幕解锁广播

        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);//按下Home建
        //监听日期变化
        filter.addAction(Intent.ACTION_DATE_CHANGED);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        filter.addAction(Intent.ACTION_TIME_TICK);

        mBatInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                String action = intent.getAction();
                if (Intent.ACTION_SCREEN_ON.equals(action)) {
                    Log.d(TAG, "screen on");
                } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                    Log.d(TAG, "screen off");
                } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
                    Log.d(TAG, "screen unlock");
                } else if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(intent.getAction())) {
                    Log.i(TAG, " receive Intent.ACTION_CLOSE_SYSTEM_DIALOGS");//点击Home键按钮
                    //保存一次
                    save();
                } else if (Intent.ACTION_SHUTDOWN.equals(intent.getAction())) {
                    Log.i(TAG, " receive ACTION_SHUTDOWN");
                    save();
                } else if (Intent.ACTION_DATE_CHANGED.equals(action)) {//日期变化步数重置为0
//                    Logger.d("重置步数" + StepDcretor.CURRENT_STEP);
                    save();
                    isNewDay();
                } else if (Intent.ACTION_TIME_CHANGED.equals(action)) {
                    //时间变化步数重置为0

                    save();
                    isNewDay();
                } else if (Intent.ACTION_TIME_TICK.equals(action)) {//日期变化步数重置为0

//                    Logger.d("重置步数" + StepDcretor.CURRENT_STEP);
                    save();
                    isNewDay();
                }
            }
        };
        registerReceiver(mBatInfoReceiver, filter);
    }


    /**
     * 监听晚上0点变化初始化数据
     */
    private void isNewDay() {
        String time = "00:00";
        if (time.equals(new SimpleDateFormat("HH:mm").format(new Date())) || !CURRENT_DATE.equals(getTodayDate())) {
            //这里要重置步数并关闭
            //并关闭几部线程
            stopSelf();
        }
    }


    /**
     * 开始保存记步数据
     */
    private void startTimeCount() {
        if (time == null) {
            time = new TimeCount(DURATION, 1000);
        }
        time.start();
    }

    /**
     * 更新步数通知
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
     * UI监听器对象
     */
//    private UpdateUiCallBack mCallback;

    /**
     * 注册UI更新监听
     *
     * @param paramICallback
     */
//    public void registerCallback(UpdateUiCallBack paramICallback) {
//        this.mCallback = paramICallback;
//    }

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

    @Override
    public IBinder onBind(Intent intent) {
        return stepBinder;
    }

    /**
     * 向Activity传递数据的纽带
     */
    public class StepBinder extends Binder {

        /**
         * 获取当前service对象
         *
         * @return StepService
         */
        public StepService getService() {
            return StepService.this;
        }
    }

    /**
     * 获取当前步数
     *
     * @return
     */
    public int getStepCount() {
        return CURRENT_STEP;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i("服务onStartCommand");
        return START_STICKY;
    }

    /**
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


    private int PEDOMETER_STEP_COUNT = 0;//使用计步器传感器的步数值
    private Sensor detectorSensor;//计步器传感器
    private SensorEventListener mSensorEventListener;//计步器传感器监听

    private void addCountStepListener() {

        //获取计步器传感器
        detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (detectorSensor != null) {
            isUseGravitySensor = true;
            stepSensorType = Sensor.TYPE_STEP_DETECTOR;
            LogUtil.i(TAG, "有计步器传感器 使用计步器传感器");
            mSensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    if (event.values[0] == 1.0f) {
                        LogUtil.i(TAG, "计步啦2");
                        PEDOMETER_STEP_COUNT++;
                        if (updateStepCallBack != null) {
                            //计算时间差
                         int min= (int) ((System.currentTimeMillis()-startTime)/(1000 * 60));
                            updateStepCallBack.updateStep(PEDOMETER_STEP_COUNT,min);
                        }
//                        updateNotification();
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
    private int GRAVITY_SENSOR_STEP_COUNT = 0;//使用重力传感器计步器数据
    private boolean isUseGravitySensor = false;//是否使用重力传感器来计步的

    private void addBasePedometerListener() {
        isUseGravitySensor = true;
        mStepCount = new StepCount();
        mStepCount.setSteps(GRAVITY_SENSOR_STEP_COUNT);//设置开始值
        //获取重力传感器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //注册重力传感器
        boolean isAvailable = sensorManager.registerListener(mStepCount.getStepDetector(), sensor, SensorManager.SENSOR_DELAY_UI);
        mStepCount.initListener(new StepValuePassListener() {
            @Override
            public void stepChanged(int steps) {
                LogUtil.i(TAG, "重力传感器计步器" + steps);//返回的值是累加的
                GRAVITY_SENSOR_STEP_COUNT = steps;
//                updateNotification();
                if (updateStepCallBack != null) {
                    int min= (int) ((System.currentTimeMillis()-startTime)/(1000 * 60));
                    updateStepCallBack.updateStep(GRAVITY_SENSOR_STEP_COUNT,min);
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
     * 保存记步数据
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            // 如果计时器正常结束，则开始计步
            time.cancel();
            save();
            startTimeCount();
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

    }

    /**
     * 保存记步数据
     */
    private void save() {
        int tempStep = CURRENT_STEP;

//        List<StepData> list = DbUtils.getQueryByWhere(StepData.class, "today", new String[]{CURRENT_DATE});
//        if (list.size() == 0 || list.isEmpty()) {
//            StepData data = new StepData();
//            data.setToday(CURRENT_DATE);
//            data.setStep(tempStep + "");
//            DbUtils.insert(data);
//        } else if (list.size() == 1) {
//            StepData data = list.get(0);
//            data.setStep(tempStep + "");
//            DbUtils.update(data);
//        } else {
//        }
    }


    //计步器服务销毁的时候调用
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

    //绑定服务的时候调用
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
