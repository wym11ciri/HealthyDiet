package com.huihong.healthydiet.activity.token;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.LoginActivity;
import com.huihong.healthydiet.activity.base.ActivityCollector;
import com.huihong.healthydiet.activity.base.BaseActivity;
import com.huihong.healthydiet.utils.common.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/9/9
 */

public class TokenErrorActivity extends BaseActivity {

    @BindView(R.id.btLogin)
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TokenErrorActivityCollector.addActivity(this);
        setContentView(R.layout.activity_token_error);
        ButterKnife.bind(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TokenErrorActivityCollector.removeActivity(this);
    }

    @OnClick(R.id.btLogin)
    public void onViewClicked() {
        SPUtils.put(TokenErrorActivity.this, "isDoSimpleTest", false);
        SPUtils.put(TokenErrorActivity.this, "isLogin", false);
        ActivityCollector.finishAll();//销毁所有界面
        Intent mIntent = new Intent(TokenErrorActivity.this, LoginActivity.class);
        startActivity(mIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
