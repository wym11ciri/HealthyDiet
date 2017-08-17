package com.huihong.healthydiet.activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.widget.WeekSelectTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/15
 * 闹铃设置界面
 */

public class SleepSettingsActivity extends BaseTitleActivity {


    //星期选择的7个按钮
    private WeekSelectTextView ws01, ws02, ws03, ws04, ws05, ws06, ws07;
    private List<WeekSelectTextView> wsList;//把这7个按钮存到集合中去
    private String valueString = "";//把7个按钮的值拼接起来传递
    private AudioManager mAudioManager;
    private SeekBar mSeekBar;


    @Override
    public int setLayoutId() {
        return R.layout.activity_sleep_settings;
    }


    @Override
    public void initUI() {
        setTitle("睡眠设置");
        wsList = new ArrayList<>();
        ws01 = (WeekSelectTextView) findViewById(R.id.ws01);
        ws02 = (WeekSelectTextView) findViewById(R.id.ws02);
        ws03 = (WeekSelectTextView) findViewById(R.id.ws03);
        ws04 = (WeekSelectTextView) findViewById(R.id.ws04);
        ws05 = (WeekSelectTextView) findViewById(R.id.ws05);
        ws06 = (WeekSelectTextView) findViewById(R.id.ws06);
        ws07 = (WeekSelectTextView) findViewById(R.id.ws07);
        wsList.add(ws01);
        wsList.add(ws02);
        wsList.add(ws03);
        wsList.add(ws04);
        wsList.add(ws05);
        wsList.add(ws06);
        wsList.add(ws07);
        //从缓存中去拿
        List<Boolean> cacheValueList = CacheUtils.getSleepWeek(SleepSettingsActivity.this);
        for (int i = 0; i < wsList.size(); i++) {
            wsList.get(i).setChoose(cacheValueList.get(i));
        }
        setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存睡眠星期
                CacheUtils.setSleepWeek(SleepSettingsActivity.this, wsList);
                SPUtils.put(SleepSettingsActivity.this, "nowChooseTime", nowChooseTime);
                Intent mIntent = new Intent();
                mIntent.putExtra("valueString", valueString);
                setResult(10086, mIntent);
                finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
            }
        });


        initSleepTime();
        mSeekBar = (SeekBar) findViewById(R.id.mSeekBar);

        //音量控制,初始化定义
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        mSeekBar.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        mSeekBar.setProgress(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0); //tempVolume:音量绝对值
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    //就寝前多久提醒
    private LinearLayout layoutTime01, layoutTime03, layoutTime04, layoutTime02;
    private ImageView ivTime01, ivTime02, ivTime03, ivTime04;
    private View.OnClickListener timeOnClickListener;
    private String nowChooseTime = "1";//当前选择的提前提醒

    //初始化睡觉前多久闹铃
    private void initSleepTime() {

        nowChooseTime = CacheUtils.getLeadTimeType(SleepSettingsActivity.this);

        layoutTime01 = (LinearLayout) findViewById(R.id.layoutTime01);
        layoutTime02 = (LinearLayout) findViewById(R.id.layoutTime02);
        layoutTime03 = (LinearLayout) findViewById(R.id.layoutTime03);
        layoutTime04 = (LinearLayout) findViewById(R.id.layoutTime04);

        ivTime01 = (ImageView) findViewById(R.id.ivTime01);
        ivTime02 = (ImageView) findViewById(R.id.ivTime02);
        ivTime03 = (ImageView) findViewById(R.id.ivTime03);
        ivTime04 = (ImageView) findViewById(R.id.ivTime04);


        //从缓存中去取设置的提前提醒类型

        ivTime01.setImageResource(R.mipmap.circle_00009);
        ivTime02.setImageResource(R.mipmap.circle_00009);
        ivTime03.setImageResource(R.mipmap.circle_00009);
        ivTime04.setImageResource(R.mipmap.circle_00009);

        if (nowChooseTime.equals("1")) {
            ivTime01.setImageResource(R.mipmap.circle_00008);
        } else if (nowChooseTime.equals("2")) {
            ivTime02.setImageResource(R.mipmap.circle_00008);
        } else if (nowChooseTime.equals("3")) {
            ivTime03.setImageResource(R.mipmap.circle_00008);
        } else if (nowChooseTime.equals("4")) {
            ivTime04.setImageResource(R.mipmap.circle_00008);
        }


        timeOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivTime01.setImageResource(R.mipmap.circle_00009);
                ivTime02.setImageResource(R.mipmap.circle_00009);
                ivTime03.setImageResource(R.mipmap.circle_00009);
                ivTime04.setImageResource(R.mipmap.circle_00009);

                switch (v.getId()) {
                    case R.id.layoutTime01:
                        ivTime01.setImageResource(R.mipmap.circle_00008);
                        nowChooseTime = "1";

                        break;
                    case R.id.layoutTime02:
                        ivTime02.setImageResource(R.mipmap.circle_00008);
                        nowChooseTime = "2";
                        break;
                    case R.id.layoutTime03:
                        ivTime03.setImageResource(R.mipmap.circle_00008);
                        nowChooseTime = "3";
                        break;
                    case R.id.layoutTime04:
                        ivTime04.setImageResource(R.mipmap.circle_00008);
                        nowChooseTime = "4";
                        break;
                }
                CacheUtils.setLeadTimeType(SleepSettingsActivity.this, nowChooseTime);
            }
        };
        layoutTime01.setOnClickListener(timeOnClickListener);
        layoutTime02.setOnClickListener(timeOnClickListener);
        layoutTime03.setOnClickListener(timeOnClickListener);
        layoutTime04.setOnClickListener(timeOnClickListener);

    }

    @Override
    public void initOnClickListener() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {

            case KeyEvent.KEYCODE_VOLUME_DOWN:
                int a = mSeekBar.getProgress();
                a--;
                mSeekBar.setProgress(a);
                return false;

            case KeyEvent.KEYCODE_VOLUME_UP:
                int a2 = mSeekBar.getProgress();
                a2++;
                mSeekBar.setProgress(a2);
                return false;

        }
        return super.onKeyDown(keyCode, event);
    }
}
