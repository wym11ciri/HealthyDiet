package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity2;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/8/11
 * 修改手机号码
 */

public class ChangePhoneActivity01 extends BaseTitleActivity2 {
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.tvButton)
    TextView tvButton;
    private CountDownTimer codeTimer;

    @Override
    public int setLayoutId() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("修改手机号码");
//        etPhone.setHint("请输入已绑定手机号");
        etPhone.setText(CacheUtils.getPhone(ChangePhoneActivity01.this));
        etPhone.setEnabled(false);
        etPhone.setFocusable(false);

        tvButton.setText("下一步");
        codeTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //动态更新textView里面的内容
                tvGetCode.setText("重新获取(" + (millisUntilFinished / 1000) + ")");
            }

            @Override
            public void onFinish() {
                //倒计时结束时操作
                tvGetCode.setClickable(true);
                tvGetCode.setText("获取验证码");

            }
        };
    }

    @OnClick({R.id.tvGetCode, R.id.tvButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGetCode:
                String phone = etPhone.getText().toString().trim();
                if (!isMobileNO(phone)) {
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                } else {
                    getCode(phone);
                }

                break;
            case R.id.tvButton:
                String mPhone = etPhone.getText().toString().trim();
                String mCode = etPhone.getText().toString().trim();

                if (!isMobileNO(mPhone)) {
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                } else {
                    if (mCode.length() < 5) {
                        Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                    } else {

                        verificationCode(mPhone,mCode);
                        go2Activity2(ChangePhoneActivity01.this, ChangePhoneActivity02.class);
                        finishActivity();
                    }
                }
                break;
        }
    }

    private void verificationCode(String mPhone, String mCode) {

        Map<String, String> map = new HashMap<>();
        map.put("VerificaCode",mCode);
        map.put("Phone",mPhone);
        map.put("UserId",  SPUtils.get(ChangePhoneActivity01.this,"UserId",0)+"");

        HttpUtils.sendHttpAddToken(ChangePhoneActivity01.this, AppUrl.VERIFICATION_CODE
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("手机验证01",response);
                        Gson gson = new Gson();
                        HttpBaseInfo mHttpBaseInfo = gson.fromJson(response, HttpBaseInfo.class);
                        if(mHttpBaseInfo.getHttpCode()==200){
                            Intent mIntent=new Intent(ChangePhoneActivity01.this,ChangePhoneActivity02.class);
                            startActivity(mIntent);
                            finishActivity();
                        }else {
                            Toast.makeText(ChangePhoneActivity01.this, mHttpBaseInfo.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("手机验证01",e.toString());
                    }
                });




    }

    private void getCode(String phone) {
        tvGetCode.setClickable(false);
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("UserId", SPUtils.get(ChangePhoneActivity01.this, "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(ChangePhoneActivity01.this, AppUrl.SEND_MAIL
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        codeTimer.start();
                        LogUtil.i("获取验证码", response);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("获取验证码", e.toString());
                        tvGetCode.setClickable(true);
                    }
                });
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
