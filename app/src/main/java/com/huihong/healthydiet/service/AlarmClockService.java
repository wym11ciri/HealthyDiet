package com.huihong.healthydiet.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.view.WindowManager;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 这个服务用于监听闹铃
 */
public class AlarmClockService extends Service implements MediaPlayer.OnCompletionListener {

    private String TAG = "AlarmClockService";
    private MediaPlayer player;//音乐播放器

    private BroadcastReceiver mBatInfoReceiver;//广播接受者

    /**
     * IBinder对象，向Activity传递数据的桥梁
     */
    private StepBinder stepBinder = new StepBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i(TAG, "onCreate()");
        player = MediaPlayer.create(this, R.raw.tt);
        player.setOnCompletionListener(this);
        initBroadcastReceiver();
    }

    /**
     * 注册广播
     */
    private void initBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF); // 屏幕灭屏广播
        filter.addAction(Intent.ACTION_SHUTDOWN);//关机广播
        filter.addAction(Intent.ACTION_SCREEN_ON);// 屏幕亮屏广播
        filter.addAction(Intent.ACTION_USER_PRESENT); // 屏幕解锁广播
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);//Home键点击
        //监听日期变化
        filter.addAction(Intent.ACTION_DATE_CHANGED);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        filter.addAction(Intent.ACTION_TIME_TICK);

        //注册广播接收器
        mBatInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                String action = intent.getAction();
                if (Intent.ACTION_SCREEN_ON.equals(action)) {
                    LogUtil.i(TAG, "screen on");//屏幕亮
                } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                    LogUtil.i(TAG, "screen off");//屏幕灭调
                } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
                    LogUtil.i(TAG, "screen unlock");//屏幕解锁
                } else if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(intent.getAction())) {
                    LogUtil.i(TAG, " receive Intent.ACTION_CLOSE_SYSTEM_DIALOGS");//点击Home键按钮
                } else if (Intent.ACTION_SHUTDOWN.equals(intent.getAction())) {
                    LogUtil.i(TAG, " receive ACTION_SHUTDOWN");//关机了
                } else if (Intent.ACTION_DATE_CHANGED.equals(action)) {//日期变化
                    //需要重置步数
                    LogUtil.i(TAG, "日期变化了");
                } else if (Intent.ACTION_TIME_CHANGED.equals(action)) {//时间变化了
                    LogUtil.i(TAG, "日期时间了1" + new SimpleDateFormat("HH:mm").format(new Date()));
                } else if (Intent.ACTION_TIME_TICK.equals(action)) {//这个日期监听更准一点
                    isAlarmClock();
                    LogUtil.i(TAG, "日期时间了2" + new SimpleDateFormat("HH:mm").format(new Date()));
                }
            }
        };
        registerReceiver(mBatInfoReceiver, filter);
    }

    //判断当前时间是否为闹铃
    private void isAlarmClock() {
        String nowTime = new SimpleDateFormat("HH:mm").format(new Date()) + "";

        //去缓存中拿时间

        int startHour = (int) SPUtils.get(this, "startHour", 23);
        int startMin = (int) SPUtils.get(this, "startMin", 0);
        int endHour = (int) SPUtils.get(this, "endHour", 8);
        int endMin = (int) SPUtils.get(this, "endMin", 0);

        boolean sleepIsOpen = true;//闹铃是否开启

        String startTime;
        String endTime;

        if (startMin < 10) {
            startTime = startHour + ":0" + startMin;
        } else {
            startTime = startHour + ":" + startMin;
        }

        if (endHour < 10) {
            if (endMin < 10) {
                endTime = "0" + endHour + ":0" + endMin;
            } else {
                endTime = "0" + endHour + ":" + endMin;
            }
        } else {
            if (endMin < 10) {
                endTime = endHour + ":0" + endMin;
            } else {
                endTime = endHour + ":" + endMin;
            }
        }


        if (endTime.equals(nowTime)) {

//            if (!player.isPlaying()) {
            player.start();
//            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("闹钟提醒");
            builder.setMessage("您有新的闹钟啦");
            builder.setPositiveButton("知道啦", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (player.isPlaying()) {
                        player.stop();
                    }
                }
            });
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
            dialog.show();
        } else if (startTime.equals(nowTime)) {

//            if (!player.isPlaying()) {
            player.start();
//            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("闹钟提醒");
            builder.setMessage("您有新的闹钟啦");
            builder.setPositiveButton("知道啦", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (player.isPlaying()) {
                        player.stop();
                    }
                }
            });
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
            dialog.show();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stepBinder;
    }

    //音乐播放器完成
    @Override
    public void onCompletion(MediaPlayer mp) {
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
        public AlarmClockService getService() {
            return AlarmClockService.this;
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消前台进程
        stopForeground(true);
        unregisterReceiver(mBatInfoReceiver);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
