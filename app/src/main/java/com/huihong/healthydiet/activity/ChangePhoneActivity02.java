package com.huihong.healthydiet.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.huihong.healthydiet.activity.base.ActivityCollector;
import com.huihong.healthydiet.activity.base.BaseTitleActivity2;
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

public class ChangePhoneActivity02 extends BaseTitleActivity2 {
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
        etPhone.setHint("请输入新手机号");
        tvButton.setText("完成");
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

    private AlertDialog alertDialog;

    @Override
    public void finishActivity() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChangePhoneActivity02.this);
        builder.setTitle("提示");
        builder.setMessage("确认放弃取消本次操作吗？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ChangePhoneActivity02.this.finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
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
                String mCode = etCode.getText().toString().trim();

                if (!isMobileNO(mPhone)) {
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                } else {
                    if (mCode.length() < 5) {
                        Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                    } else {

                        modifyUserPhone(mPhone, mCode);

                    }
                }
                break;
        }
    }

    private void modifyUserPhone(String mPhone, String mCode) {

        Map<String, String> map = new HashMap<>();
        map.put("VerificaCode", mCode);
        map.put("Phone", mPhone);
        map.put("UserId", SPUtils.get(ChangePhoneActivity02.this, "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(ChangePhoneActivity02.this, AppUrl.MODIFY_USER_PHONE
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("修改密码第二部", response);
                        Gson gson = new Gson();
                        HttpBaseInfo mHttpBaseInfo = gson.fromJson(response, HttpBaseInfo.class);
                        if (mHttpBaseInfo.getHttpCode() == 200) {
                            ActivityCollector.finishAll();
                            Intent mIntent = new Intent(ChangePhoneActivity02.this, LoginActivity.class);
                            startActivity(mIntent);
                        } else {
                            Toast.makeText(ChangePhoneActivity02.this, mHttpBaseInfo.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("修改密码第二部", e.toString());
                    }
                });

    }

    private void getCode(String phone) {
        tvGetCode.setClickable(false);
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("UserId", SPUtils.get(ChangePhoneActivity02.this, "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(ChangePhoneActivity02.this, AppUrl.SEND_MAIL
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
