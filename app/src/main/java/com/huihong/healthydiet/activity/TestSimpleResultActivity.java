package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.ActivityCollector;
import com.huihong.healthydiet.activity.base.BaseTitleActivity2;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.model.gsonbean.Login;
import com.huihong.healthydiet.model.mybean.AccountPassword;
import com.huihong.healthydiet.model.mybean.PersonalInfo;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/8/15
 */

public class TestSimpleResultActivity extends BaseTitleActivity2 {
    @BindView(R.id.tvConstitution)
    TextView tvConstitution;
    @BindView(R.id.tvDescribe)
    TextView tvDescribe;
    @BindView(R.id.tvGoHome)
    TextView tvGoHome;

    @Override
    public int setLayoutId() {
        return R.layout.activity_test_simple_result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        tvConstitution.setText(getIntent().getStringExtra("info01"));
        tvDescribe.setText("      "+getIntent().getStringExtra("info02"));
        setTitle("体质测试");
    }

    @OnClick(R.id.tvGoHome)
    public void onViewClicked() {
        //这里需要再调一次登录按钮

//        SPUtils.put(RegisterActivity.this, "phone", phone);
//        SPUtils.put(getContext(),"password",etPassword.getText().toString().trim());
//
//        //保存UserID
//        CacheUtils.setUserId(getContext(),mMailRegister.getListData().get(0).getUserId());
//        tvGoHome.setClickable(false);
//        ActivityCollector.finishAll();
//        Intent mIntent=new Intent(TestSimpleResultActivity.this, MainActivity.class);
//        startActivity(mIntent);
        AccountPassword accountPassword=CacheUtils.getAccountPassword(getContext());

        String phone= accountPassword.getAccount();
        String pass= accountPassword.getPassword();
        passwordLogin(phone,pass);
    }

    private void passwordLogin(String phone, String password) {



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
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        LogUtil.i("接口，普通登录" + response);
                        Gson gson = new Gson();
                        Login mLogin = gson.fromJson(response, Login.class);
                        int code = mLogin.getHttpCode();
                        if (code == 200) {
                            if (mLogin.getListData().size() > 0) {
                                Login.ListDataBean mInfo = mLogin.getListData().get(0);
                                //访问成功保存个人信息
                                SPUtils.put(getContext(), "UserId", mInfo.getUserId());
                                SPUtils.put(getContext(), "Token", mInfo.getToken());

                                PersonalInfo personalInfo = new PersonalInfo();
                                personalInfo.setName(mInfo.getName());
                                personalInfo.setHeight(mInfo.getHeight());
                                personalInfo.setWeight(mInfo.getWeight());
                                personalInfo.setMan(mInfo.isSex());
                                personalInfo.setHeadImageUrl(mInfo.getHeadImage());
                                personalInfo.setConstitution(mInfo.getConstitution());
                                personalInfo.setConstitution(mInfo.getAge());
                                personalInfo.setPhone(mInfo.getPhone());
                                CacheUtils.putPersonalInfo(getContext(), personalInfo);

                                //用户体质是根据简易测试来得到的
                                String constitution = mInfo.getConstitution();
                                if (constitution == null) {
                                    //没有填写基础版本测试
                                    SPUtils.put(getContext(), "isDoSimpleTest", false);
                                    Intent mIntent = new Intent(getContext(), TestSimpleActivity.class);
                                    startActivity(mIntent);
                                    finish();
                                } else {
                                    //已经填写了基础版本测试
                                    ActivityCollector.finishAll();
                                    SPUtils.put(getContext(), "isDoSimpleTest", true);
                                    Intent mIntent = new Intent(getContext(), MainActivity.class);
                                    startActivity(mIntent);
//                                    finish();
                                }
                                //表示已经成功登录过了
                                SPUtils.put(getContext(), "isLogin", true);
                            }
                        } else {
                            String Message = mLogin.getMessage();
                            Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
