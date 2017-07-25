package com.huihong.healthydiet.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.zuoni.dialog.picker.mInterface.OnSingleDataSelectedListener;
import com.zuoni.dialog.picker.pickerdialog.DataPickerDateDialog;
import com.zuoni.dialog.picker.pickerdialog.DataPickerSingleDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/20
 */

public class BodyDataActivity extends BaseTitleActivity {

    private TextView tvBirthday,tvLabour;
    private DataPickerDateDialog mDataPickerDateDialog;

    private DataPickerSingleDialog mLabourDialog;



    private ImageView ivBoy,ivGirl;
    private View.OnClickListener onSexClickListener;


    private TextView tvSave;

    @Override
    public int setLayoutId() {
        return R.layout.activity_body_data;
    }

    @Override
    public void initUI() {
        setTitle("身体数据");

        tvBirthday = (TextView) findViewById(R.id.tvBirthday);
        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataPickerDateDialog.Builder builder = new DataPickerDateDialog.Builder(BodyDataActivity.this);
                builder.setOnDateSelectedListener(new DataPickerDateDialog.OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(int[] dates) {
                        tvBirthday.setText(dates[0]+"年"+dates[1]+"月"+dates[2]+"日");
                    }
                });
                mDataPickerDateDialog=builder.create();
                mDataPickerDateDialog.show();
            }
        });


        //劳动强度选择器
        tvLabour= (TextView) findViewById(R.id.tvLabour);
        tvLabour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> datas=new ArrayList<>();
                datas.add("较差");
                datas.add("一般");
                datas.add("良好");
                datas.add("优秀");
                DataPickerSingleDialog.Builder builder=new DataPickerSingleDialog.Builder(BodyDataActivity.this);
                builder.setData(datas);
                builder.setOnDataSelectedListener(new OnSingleDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue) {
                        tvLabour.setText(itemValue);
                    }
                });
                mLabourDialog=builder.create();
                mLabourDialog.show();
            }
        });


        ivBoy= (ImageView) findViewById(R.id.ivBoy);
        ivGirl= (ImageView) findViewById(R.id.ivGirl);
        onSexClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivGirl.setImageResource(R.mipmap.body_2);
                ivBoy.setImageResource(R.mipmap.body_3);

                switch (v.getId()){
                    case R.id.ivBoy:
                        ivBoy.setImageResource(R.mipmap.body_4);

                        break;
                    case R.id.ivGirl:
                        ivGirl.setImageResource(R.mipmap.body_5);
                        break;
                }

            }
        };

        ivBoy.setOnClickListener(onSexClickListener);
        ivGirl.setOnClickListener(onSexClickListener);


        tvSave= (TextView) findViewById(R.id.tvSave);
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initOnClickListener() {

    }
}
