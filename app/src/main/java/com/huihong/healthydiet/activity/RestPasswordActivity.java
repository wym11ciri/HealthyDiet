package com.huihong.healthydiet.activity;

import android.os.CountDownTimer;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.model.gsonbean.MailRegister;
import com.huihong.healthydiet.model.gsonbean.SendMail;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/19
 * 忘记密码
 */

public class RestPasswordActivity extends BaseTitleActivity {

    private TextView tvRegister;

    private EditText etPhone;
    private EditText etPassword, etPassword2;
    private EditText etCode;


    private TextView tvGetCode;//获取验证码
    private CountDownTimer codeTimer;//验证码倒计时


    private ImageView ivEye;//查看密码

    @Override
    public int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initUI() {
        setTitle("忘记密码");

        initGetCodeButton();//设置获取验证码按钮
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPassword.setHint("新密码");
        etPassword2 = (EditText) findViewById(R.id.etPassword2);
        etPassword2.setHint("确认密码");
        etPhone = (EditText) findViewById(R.id.etPhone);
        etCode = (EditText) findViewById(R.id.etCode);


        ivEye = (ImageView) findViewById(R.id.ivEye);
        ivEye.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ivEye.setImageResource(R.mipmap.password_5);
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//按下可见
                    etPassword2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//按下可见
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ivEye.setImageResource(R.mipmap.password_6);
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//松开不可见
                    etPassword2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//松开不可见
                }
                return true;
            }
        });


        //注册按钮
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvRegister.setText("确认");
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhone.getText().toString().trim();
                String code = etCode.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String password2 = etPassword2.getText().toString().trim();
                if (phone.equals("")) {
                    Toast.makeText(RestPasswordActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else {
                    if (code.equals("")) {
                        Toast.makeText(RestPasswordActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password.equals("")) {
                            Toast.makeText(RestPasswordActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        } else {
                            if (password2.equals("")) {
                                Toast.makeText(RestPasswordActivity.this, "请输入确认密码", Toast.LENGTH_SHORT).show();
                            } else {
                                if (password.equals(password2)) {
                                    restPassword(phone, code, password);
                                } else {
                                    Toast.makeText(RestPasswordActivity.this, "密码与确认密码不一致", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }


                    }
                }

            }
        });

    }

    private void restPassword(final String phone, String code, String password) {

        OkHttpUtils
                .post()
                .url(AppUrl.RESET_USER_PASSWORD)
                .addParams("Phone", phone)//用户坐标
                .addParams("Code", code)//用户坐标
                .addParams("PassWord", password)//用户坐标
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(RestPasswordActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，重置密码" + response);
                        Gson gson = new Gson();
                        MailRegister mMailRegister = gson.fromJson(response, MailRegister.class);
                        int code = mMailRegister.getHttpCode();
                        if (code == 200) {
                            String Message = mMailRegister.getMessage();
                            Toast.makeText(RestPasswordActivity.this, Message, Toast.LENGTH_SHORT).show();

                            SPUtils.put(RestPasswordActivity.this,"phone",phone);
                            setResult(200);
                            finish();

                        } else {
                            String Message = mMailRegister.getMessage();
                            Toast.makeText(RestPasswordActivity.this, Message, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void initGetCodeButton() {


        tvGetCode = (TextView) findViewById(R.id.tvGetCode);
        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sPhone = etPhone.getText().toString().trim();
                if (isMobileNO(sPhone)) {
                    getCode(sPhone);//获取验证码
                } else {
                    Toast.makeText(RestPasswordActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                }
            }
        });


        codeTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //动态更新textView里面的内容
                tvGetCode.setText("重新获取(" + (millisUntilFinished / 1000) + ")");
            }

            @Override
            public void onFinish() {
                //倒计时结束时操作
                tvGetCode.setEnabled(true);
                tvGetCode.setText("获取验证码");

            }
        };
    }

    @Override
    public void initOnClickListener() {

    }

    public void getCode(String phone) {
        tvGetCode.setEnabled(false);
        OkHttpUtils
                .post()
                .url(AppUrl.SEND_MAIL)
                .addParams("Phone", phone)//用户坐标
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tvGetCode.setEnabled(true);
                        Toast.makeText(RestPasswordActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，获取验证码" + response);
                        Gson gson = new Gson();
                        SendMail mSendMail = gson.fromJson(response, SendMail.class);
                        int code = mSendMail.getHttpCode();
                        if (code == 200) {
                            codeTimer.start();
                            String Message = mSendMail.getMessage();
                            Toast.makeText(RestPasswordActivity.this, Message, Toast.LENGTH_SHORT).show();
                        } else {
                            tvGetCode.setEnabled(true);
                            String Message = mSendMail.getMessage();
                            Toast.makeText(RestPasswordActivity.this, Message, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
