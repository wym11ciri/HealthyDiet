package com.huihong.healthydiet.activity.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.huihong.healthydiet.R;

/**
 * Created by zangyi_shuai_ge on 2017/4/21
 * Activity 基类
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /**
     * 普通的跳转界面
     */
    public void go2Activity(Context packageContext, Class<?> cls) {
        Intent mIntent = new Intent(packageContext, cls);
        startActivity(mIntent);
    }

    /**
     * 跳转界面
     * 向右滑动动画
     */
    public void go2Activity2(Context packageContext, Class<?> cls) {
        Intent mIntent = new Intent(packageContext, cls);
        this.startActivity(mIntent);
        this.overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    /**
     * 跳转界面
     * 使用 ActivityOptionsCompat 进行跳转界面
     */
    private void go2ActivityWithActivityOptionsCompat(
            Class<?> cls, Activity activity, View sharedElement, String sharedElementName) {

        if (Build.VERSION.SDK_INT < 21) {
            //SDK小于21不支持ActivityOptionsCompat
            go2Activity(activity, cls);
        } else {
            Intent intent = new Intent(activity, cls);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, sharedElement, sharedElementName);
            startActivity(intent, options.toBundle());
        }
    }

    private void go2ActivityWithActivityOptionsCompat(
            Class<?> cls, Activity activity,
            Pair<View, String>... sharedElements) {

        if (Build.VERSION.SDK_INT < 21) {
            //SDK小于21不支持ActivityOptionsCompat
            go2Activity(activity, cls);
        } else {
            Intent intent = new Intent(activity, cls);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, sharedElements);
            startActivity(intent, options.toBundle());
        }
    }
}
