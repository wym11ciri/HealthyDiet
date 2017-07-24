package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseActivity;
import com.huihong.healthydiet.utils.common.StatusBarUtil;



/**
 * Created by zangyi_shuai_ge on 2017/7/19
 * 登录界面
 */

public class LoginActivity extends BaseActivity {

    private TextView tvLogin,tvRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTransparent(this);//设置状态栏沉浸
        initUI();
    }

    private void initUI() {
        tvLogin= (TextView) findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        });

        tvRegister= (TextView) findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(mIntent);
//                finish();
            }
        });
    }
}
