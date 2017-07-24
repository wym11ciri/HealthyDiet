package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;

/**
 * Created by zangyi_shuai_ge on 2017/7/19
 */

public class RegisterActivity extends BaseTitleActivity {

    private TextView tvRegister;
    @Override
    public int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initUI() {
        setTitle("注册");

        tvRegister= (TextView) findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(RegisterActivity.this, RegisterSuccessfulActivity.class);
                startActivity(mIntent);
            }
        });

    }

    @Override
    public void initOnClickListener() {

    }
}
