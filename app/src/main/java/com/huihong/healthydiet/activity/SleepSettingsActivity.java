package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.utils.StringUtil;
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
        String value = (String) SPUtils.get(SleepSettingsActivity.this, "weekValueString", "0,0,0,0,0,0,0,");
        List<Boolean> cacheValueList = StringUtil.getWeekList(value);
        for (int i = 0; i < wsList.size(); i++) {
            wsList.get(i).setChoose(cacheValueList.get(i));
        }


        setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传入数据
                for (int i = 0; i < wsList.size(); i++) {
                    if (wsList.get(i).getChoose()) {
                        valueString = valueString + "1" + ",";
                    } else {
                        valueString = valueString + "0" + ",";
                    }
                }
                //退出的时候把变量保存起来
                SPUtils.put(SleepSettingsActivity.this, "weekValueString", valueString);
                SPUtils.put(SleepSettingsActivity.this, "nowChooseTime", nowChooseTime);

                Intent mIntent = new Intent();
                mIntent.putExtra("valueString", valueString);
                // 设置结果，并进行传送
                setResult(10086, mIntent);
                finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
            }
        });


        initSleepTime();

    }

    //就寝前多久提醒
    private LinearLayout layoutTime01, layoutTime03, layoutTime04, layoutTime02;
    private ImageView ivTime01, ivTime02, ivTime03, ivTime04;
    private View.OnClickListener timeOnClickListener;
    private String nowChooseTime = "1";//当前选择的提前提醒

    //初始化睡觉前多久闹铃
    private void initSleepTime() {
        layoutTime01 = (LinearLayout) findViewById(R.id.layoutTime01);
        layoutTime02 = (LinearLayout) findViewById(R.id.layoutTime02);
        layoutTime03 = (LinearLayout) findViewById(R.id.layoutTime03);
        layoutTime04 = (LinearLayout) findViewById(R.id.layoutTime04);

        ivTime01 = (ImageView) findViewById(R.id.ivTime01);
        ivTime02 = (ImageView) findViewById(R.id.ivTime02);
        ivTime03 = (ImageView) findViewById(R.id.ivTime03);
        ivTime04 = (ImageView) findViewById(R.id.ivTime04);



        //从缓存中去取设置的提前提醒类型
        nowChooseTime = (String) SPUtils.get(SleepSettingsActivity.this, "nowChooseTime", "1");
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
}
