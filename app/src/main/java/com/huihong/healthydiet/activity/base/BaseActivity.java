package com.huihong.healthydiet.activity.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zangyi_shuai_ge on 2017/4/21
 * Activity 基类
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果为普通模式则设置蓝色的状态栏
//        if(MyApplication.isNormal){
//            StatusBarUtils.setWindowStatusBarColor(this, R.color.blue);
//        }
        ActivityCollector.addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
    }


    @Override
    protected void onDestroy() {
        ActivityCollector.removeActivity(this);
        super.onDestroy();

    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            v.startAnimation(AnimationUtils.loadAnimation(BaseActivity.this, R.anim.btn_alpha_scale_begin));
//        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//            v.startAnimation(AnimationUtils.loadAnimation(BaseActivity.this, R.anim.btn_alpha_scale_end));
//        }
//        return false;
//
//    }


}
