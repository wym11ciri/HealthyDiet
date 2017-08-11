package com.huihong.healthydiet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity2;

/**
 * Created by zangyi_shuai_ge on 2017/8/11
 * 饮食记录
 */

public class DietRecordActivity extends BaseTitleActivity2 {
    @Override
    public int setLayoutId() {
        return R.layout.activity_diet_record;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("饮食记录");
    }

    @Override
    public void finishActivity() {
//        super.finishActivity();
        ActivityCompat.finishAfterTransition(this);
    }
}
