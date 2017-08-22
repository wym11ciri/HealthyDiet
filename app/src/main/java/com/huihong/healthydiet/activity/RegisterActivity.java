package com.huihong.healthydiet.activity;

import android.content.Intent;
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
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.model.gsonbean.MailRegister;
import com.huihong.healthydiet.model.gsonbean.SendMail;
import com.huihong.healthydiet.model.mybean.AccountPassword;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

import static com.zhy.http.okhttp.OkHttpUtils.post;

/**
 * Created by zangyi_shuai_ge on 2017/7/19
 * 注册界面
 */

public class RegisterActivity extends BaseTitleActivity {

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
        setTitle("注 册");
        initGetCodeButton();//设置获取验证码按钮
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPassword2 = (EditText) findViewById(R.id.etPassword2);
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
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhone.getText().toString().trim();
                String code = etCode.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String password2 = etPassword2.getText().toString().trim();
                if (phone.equals("")) {
                    Toast.makeText(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else {
                    if (code.equals("")) {
                        Toast.makeText(RegisterActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password.equals("")) {
                            Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        } else {
                            if (password2.equals("")) {
                                Toast.makeText(RegisterActivity.this, "请输入确认密码", Toast.LENGTH_SHORT).show();
                            } else {
                                if (password.equals(password2)) {
                                    register(phone, code, password);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "密码与确认密码不一致", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * 注册按钮
     * 注册成功了需要保存
     * UserId
     * 用户的 账号  密码
     * <p>
     * UserId用户下一步的测试使用
     */
    private void register(final String phone, String code, final  String password) {

        tvRegister.setClickable(false);
        post()
                .url(AppUrl.MAIL_REGISTER)
                .addParams("Phone", phone)
                .addParams("Code", code)
                .addParams("PassWord", password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tvRegister.setClickable(true);
                        Toast.makeText(RegisterActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        tvRegister.setClickable(true);
                        LogUtil.i("接口，注册" + response);
                        Gson gson = new Gson();
                        MailRegister mMailRegister = gson.fromJson(response, MailRegister.class);
                        int code = mMailRegister.getHttpCode();
                        if (code == 200) {
                            //注册成功

                            //保存手机号 密码
                            SPUtils.put(RegisterActivity.this, "phone", phone);
                            SPUtils.put(getContext(), "password", etPassword.getText().toString().trim());

                            //保存账号密码
                            AccountPassword accountPassword=new AccountPassword();
                            accountPassword.setPassword(password);
                            accountPassword.setAccount(phone);
                            CacheUtils.setAccountPassword(getContext(),accountPassword);
                            //保存UserId
                            CacheUtils.setUserId(getContext(), mMailRegister.getListData().get(0).getUserId());

                            //注册成功跳转到注册成功界面
                            Intent mIntent = new Intent(RegisterActivity.this, RegisterSuccessfulActivity.class);
                            startActivity(mIntent);
                            setResult(200);

                            //销毁当前注册界面
                            finish();
                            overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);

                        } else {
                            String Message = mMailRegister.getMessage();
                            Toast.makeText(RegisterActivity.this, Message, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
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
                tvGetCode.setClickable(true);
                tvGetCode.setText("获取验证码");

            }
        };
    }

    @Override
    public void initOnClickListener() {

    }

    public void getCode(String phone) {

        tvGetCode.setClickable(false);
        OkHttpUtils
                .post()
                .url(AppUrl.SEND_MAIL)
                .addParams("Phone", phone)//用户坐标
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tvGetCode.setClickable(true);
                        Toast.makeText(RegisterActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(RegisterActivity.this, Message, Toast.LENGTH_SHORT).show();
                        } else {
                            tvGetCode.setClickable(true);
                            String Message = mSendMail.getMessage();
                            Toast.makeText(RegisterActivity.this, Message, Toast.LENGTH_SHORT).show();
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
