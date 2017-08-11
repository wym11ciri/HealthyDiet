package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.huihong.healthydiet.activity.base.ActivityCollector;
import com.huihong.healthydiet.activity.base.BaseTitleActivity2;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/19
 * 修改密码
 */

public class ChangePasswordActivity extends BaseTitleActivity2 {


    @BindView(R.id.etOldPassWord)
    EditText etOldPassWord;
    @BindView(R.id.ivEye)
    ImageView ivEye;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etPassword2)
    EditText etPassword2;
    @BindView(R.id.tvChangeWord)
    TextView tvChangeWord;
    @BindView(R.id.tvPhone)
    TextView tvPhone;

    @Override
    public int setLayoutId() {
        return R.layout.activity_change_password;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("修改密码");
        tvPhone.setText(CacheUtils.getPhone(ChangePasswordActivity.this));
        ivEye.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ivEye.setImageResource(R.mipmap.password_5);
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//按下可见
                    etPassword2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//按下可见
                    etOldPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//按下可见
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ivEye.setImageResource(R.mipmap.password_6);
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//松开不可见
                    etPassword2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//松开不可见
                    etOldPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//松开不可见
                }
                return true;
            }
        });


    }

    @OnClick({R.id.tvChangeWord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvChangeWord:
                String old = etOldPassWord.getText().toString().trim();
                String new1 = etPassword.getText().toString().trim();
                String new2 = etPassword2.getText().toString().trim();

                if (old.length() < 6) {
                    Toast.makeText(this, "旧密码过短或未输入，请重新输入", Toast.LENGTH_SHORT).show();
                } else {

                    if (new1.length() < 6) {
                        Toast.makeText(this, "新密码过短或未输入，请重新输入", Toast.LENGTH_SHORT).show();
                    } else {
                        if (new2.length() < 6) {
                            Toast.makeText(this, "确认新密码过短或未输入，请重新输入", Toast.LENGTH_SHORT).show();
                        } else {
                            if (new1.equals(new2)) {
                                changePassword(old, new1);
                            } else {
                                Toast.makeText(this, "确认密码与新密码不一致", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                break;
        }
    }

    private void changePassword(String OldPassword, String NewPassword) {

        tvChangeWord.setClickable(false);
        Map<String, String> map = new HashMap<>();
        map.put("OldPassword", OldPassword);
        map.put("NewPassword", NewPassword);
        map.put("UserId", SPUtils.get(ChangePasswordActivity.this, "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(ChangePasswordActivity.this, AppUrl.MODIFY_USER_PASSWORD
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        tvChangeWord.setClickable(true);
                        LogUtil.i("修改密码", response);
                        Gson gson = new Gson();
                        HttpBaseInfo mHttpBaseInfo = gson.fromJson(response, HttpBaseInfo.class);
                        if (mHttpBaseInfo.getHttpCode() == 200) {
                            SPUtils.put(ChangePasswordActivity.this, "isLogin", false);
                            ActivityCollector.finishAll();//销毁所有界面
                            Intent mIntent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                            startActivity(mIntent);

                        }
                        String message = mHttpBaseInfo.getMessage();
                        Toast.makeText(ChangePasswordActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tvChangeWord.setClickable(true);
                        LogUtil.i("修改密码", e.toString());
                    }
                });


    }
}
