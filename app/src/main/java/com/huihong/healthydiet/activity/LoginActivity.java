package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseActivity;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.common.StatusBarUtil;


/**
 * Created by zangyi_shuai_ge on 2017/7/19
 * 登录界面
 */

public class LoginActivity extends BaseActivity {

    private TextView tvLogin, tvRegister, tvRestPassword;
    private final int REQUEST_REGISTER_CODE = 100;
    private final int REQUEST_RESET_CODE = 101;


    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTransparent(this);//设置状态栏沉浸
        initUI();
    }

    private void initUI() {

        etPhone = (EditText) findViewById(R.id.etPhone);

        tvLogin = (TextView) findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        });

        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(mIntent, REQUEST_REGISTER_CODE);
            }
        });
        tvRestPassword = (TextView) findViewById(R.id.tvRestPassword);
        tvRestPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(LoginActivity.this, RestPasswordActivity.class);
                startActivityForResult(mIntent, REQUEST_RESET_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_REGISTER_CODE:

                if (resultCode == 200) {
                    String phone = (String) SPUtils.get(LoginActivity.this, "phone", "");

                    if (!phone.equals("")) {
                        etPhone.setText(phone);
                    }

                }

                break;
            case REQUEST_RESET_CODE:

                etPhone.setText("10086");

                if (resultCode == 200) {
                    String phone = (String) SPUtils.get(LoginActivity.this, "phone", "");
                    if (!phone.equals("")) {
                        etPhone.setText(phone);
                    }
                }
                break;
        }
    }
}
