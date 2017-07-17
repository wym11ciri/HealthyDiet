package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.view.View;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.widget.WeekSelectTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/15
 */

public class SleepSettingsActivity extends BaseTitleActivity {

    private WeekSelectTextView ws01, ws02, ws03, ws04, ws05, ws06, ws07;

    private List<WeekSelectTextView> wsList;
    private ArrayList<Integer> valueList;

    @Override
    public int setLayoutId() {
        return R.layout.activity_sleep_settings;
    }
  private   String valueString="";
    @Override
    public void initUI() {
        setTitle("睡眠设置");
        wsList=new ArrayList<>();
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

        valueList=new ArrayList<>();

        setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传入数据
                for (int i = 0; i <wsList.size() ; i++) {
                    if(wsList.get(i).getChoose()){
                        valueList.add(1);
                        valueString=valueString+"1"+",";
                    }else {
                        valueString=valueString+"0"+",";
                        valueList.add(0);
                    }
                }
                Intent mIntent = new Intent();
                mIntent.putIntegerArrayListExtra("valueList", valueList);
                mIntent.putExtra("valueString",valueString);
                // 设置结果，并进行传送
                setResult(10086, mIntent);

                finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
            }
        });
    }

    @Override
    public void initOnClickListener() {

    }
}
