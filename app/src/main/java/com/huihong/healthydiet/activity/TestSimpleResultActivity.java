package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        tvGoHome.setClickable(false);
        Intent mIntent=new Intent(TestSimpleResultActivity.this, MainActivity.class);
        startActivity(mIntent);
    }

}
