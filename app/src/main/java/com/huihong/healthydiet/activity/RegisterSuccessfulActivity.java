package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;

/**
 * Created by zangyi_shuai_ge on 2017/7/19
 */

public class RegisterSuccessfulActivity extends BaseTitleActivity {

    TextView tvGoSimpleTest;
    @Override
    public int setLayoutId() {
        return R.layout.activity_register_successful;
    }

    @Override
    public void initUI() {
        setTitle("注册");
        tvGoSimpleTest= (TextView) findViewById(R.id.tvGoSimpleTest);
        //点击 跳转到简易版测试界面
        //销毁当前界面
        tvGoSimpleTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(RegisterSuccessfulActivity.this, TestSimpleActivity.class);
                startActivity(mIntent);
                finish();
            }
        });

    }

    @Override
    public void initOnClickListener() {

    }
}
