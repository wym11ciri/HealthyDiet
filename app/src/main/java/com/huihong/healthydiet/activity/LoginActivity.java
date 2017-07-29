package com.huihong.healthydiet.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseActivity;
import com.huihong.healthydiet.bean.Login;
import com.huihong.healthydiet.mybean.PersonalInfo;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.common.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/19
 * 登录界面
 */
public class LoginActivity extends BaseActivity {

    private TextView tvLogin, tvRegister, tvRestPassword;
    private final int REQUEST_REGISTER_CODE = 100;
    private final int REQUEST_RESET_CODE = 101;


    private EditText etPhone, etPassword;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTransparent(this);//设置状态栏沉浸
        initUI();
    }

    private void initUI() {
        mProgressDialog = new ProgressDialog(LoginActivity.this);
        mProgressDialog.setMessage("正在登录...");
        etPhone = (EditText) findViewById(R.id.etPhone);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = etPhone.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (phone.equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals("")) {
                        Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    } else {
                        tvLogin.setClickable(false);
                        passwordLogin(phone, password);
                    }
                }

//                UMShareAPI.get(LoginActivity.this).deleteOauth(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
//                UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
//                UMShareAPI.get(LoginActivity.this).deleteOauth(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
//                UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);

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


    /**
     * 登录模式
     */
    private void passwordLogin(String phone, String password) {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            mProgressDialog.show();
        }


        OkHttpUtils
                .post()
                .url(AppUrl.LOGIN)
                .addParams("TransMode", "PassWord")//登录模式
                .addParams("UserPhone", phone)
                .addParams("UserPassword", password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tvLogin.setClickable(true);
                        mProgressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        tvLogin.setClickable(true);
                        LogUtil.i("接口，普通登录" + response);
                        Gson gson = new Gson();
                        Login mLogin = gson.fromJson(response, Login.class);
                        int code = mLogin.getHttpCode();
                        if (code == 200) {
                            if (mLogin.getListData().size() > 0) {
                                Login.ListDataBean mInfo = mLogin.getListData().get(0);
                                SPUtils.put(LoginActivity.this, "UserId", mInfo.getUserId());
                                SPUtils.put(LoginActivity.this, "phone", mInfo.getPhone());
                                SPUtils.put(LoginActivity.this, "Token", mInfo.getToken());

                                PersonalInfo personalInfo = new PersonalInfo();

                                personalInfo.setName(mInfo.getName());
                                personalInfo.setHeight(mInfo.getHeight());
                                personalInfo.setWeight(mInfo.getWeight());
                                personalInfo.setMan(mInfo.isSex());
                                personalInfo.setHeadImageUrl(mInfo.getHeadImage());
                                personalInfo.setConstitution(mInfo.getConstitution());
                                personalInfo.setConstitution(mInfo.getAge());

                                CacheUtils.putPersonalInfo(LoginActivity.this, personalInfo);
                                //用户体质是根据简易测试来得到的
                                String constitution = mInfo.getConstitution();
                                if (constitution == null) {
                                    //没有填写基础版本测试
                                    SPUtils.put(LoginActivity.this, "isDoSimpleTest", false);
                                    Intent mIntent = new Intent(LoginActivity.this, TestSimpleActivity.class);
                                    startActivity(mIntent);
                                    finish();
                                } else {
                                    //已经填写了基础版本测试
                                    SPUtils.put(LoginActivity.this, "isDoSimpleTest", true);
                                    Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(mIntent);
                                    finish();
                                }
                                //表示已经成功登录过了
                                SPUtils.put(LoginActivity.this, "isLogin", true);
                            }
                        }
                        String Message = mLogin.getMessage();
                        Toast.makeText(LoginActivity.this, Message, Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();

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
