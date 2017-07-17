package com.huihong.healthydiet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huihong.healthydiet.activity.base.BaseActivity;
import com.huihong.healthydiet.adapter.FragmentPagerAdapter;
import com.huihong.healthydiet.fragment.ArticleFragment;
import com.huihong.healthydiet.fragment.Fragment02;
import com.huihong.healthydiet.fragment.HomeFragment;
import com.huihong.healthydiet.fragment.MotionFragment;
import com.huihong.healthydiet.fragment.SleepFragment;
import com.huihong.healthydiet.service.AlarmClockService;
import com.huihong.healthydiet.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    private RelativeLayout layoutTab01, layoutTab02, layoutTab03, layoutTab04, layoutTab05;
    private TextView tvTab01, tvTab02, tvTab03, tvTab04, tvTab05;
    private ImageView ivTab01, ivTab02, ivTab03, ivTab04, ivTab05;
    private List<Fragment> mList;
    private FragmentPagerAdapter mPagerAdapter;
    private MyViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        setupService();
    }
    /**
     * 开启计步服务
     */
    private void setupService() {
        Intent intent = new Intent(this, AlarmClockService.class);
//        isBind = bindService(intent, conn, Context.BIND_AUTO_CREATE);
        startService(intent);


//        Intent intent2 = new Intent(this, StepService.class);
//        isBind = bindService(intent, conn, Context.BIND_AUTO_CREATE);
//        startService(intent2);

    }
    private void initUI() {
        layoutTab01 = (RelativeLayout) findViewById(R.id.layoutTab01);
        layoutTab02 = (RelativeLayout) findViewById(R.id.layoutTab02);
        layoutTab03 = (RelativeLayout) findViewById(R.id.layoutTab03);
        layoutTab04 = (RelativeLayout) findViewById(R.id.layoutTab04);
        layoutTab05 = (RelativeLayout) findViewById(R.id.layoutTab05);

        layoutTab01.setOnTouchListener(this);
        layoutTab02.setOnTouchListener(this);
        layoutTab03.setOnTouchListener(this);
        layoutTab04.setOnTouchListener(this);
        layoutTab05.setOnTouchListener(this);

        layoutTab01.setOnClickListener(this);
        layoutTab02.setOnClickListener(this);
        layoutTab03.setOnClickListener(this);
        layoutTab04.setOnClickListener(this);
        layoutTab05.setOnClickListener(this);

        tvTab01 = (TextView) findViewById(R.id.tvTab01);
        tvTab02 = (TextView) findViewById(R.id.tvTab02);
        tvTab03 = (TextView) findViewById(R.id.tvTab03);
        tvTab04 = (TextView) findViewById(R.id.tvTab04);
        tvTab05 = (TextView) findViewById(R.id.tvTab05);

        ivTab01 = (ImageView) findViewById(R.id.ivTab01);
        ivTab02 = (ImageView) findViewById(R.id.ivTab02);
        ivTab03 = (ImageView) findViewById(R.id.ivTab03);
        ivTab04 = (ImageView) findViewById(R.id.ivTab04);
        ivTab05 = (ImageView) findViewById(R.id.ivTab05);


        mViewPager = (MyViewPager) findViewById(R.id.mViewPager);
        mList = new ArrayList<>();

        mList.add(new Fragment02());
        mList.add(new ArticleFragment());
        mList.add(new HomeFragment());
        mList.add(new MotionFragment());
        mList.add(new SleepFragment());

        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(2);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.btn_alpha_scale_begin));
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.btn_alpha_scale_end));
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        restTab();
        switch (v.getId()) {

            case R.id.layoutTab01:
                mViewPager.setCurrentItem(0, false);
//                ivTab01.setImageResource(R.mipmap.);
                ivTab01.setImageResource(R.mipmap.logo_10);
                tvTab01.setTextColor(getResources().getColor(R.color.tab_bottom_text_select));


                break;
            case R.id.layoutTab02:
                ivTab02.setImageResource(R.mipmap.logo_6);
                mViewPager.setCurrentItem(1, false);
                tvTab02.setTextColor(getResources().getColor(R.color.tab_bottom_text_select));
                break;
            case R.id.layoutTab03:
                ivTab03.setImageResource(R.mipmap.logo_1);
                mViewPager.setCurrentItem(2, false);
                tvTab03.setTextColor(getResources().getColor(R.color.tab_bottom_text_select));
                break;
            case R.id.layoutTab04:
                ivTab04.setImageResource(R.mipmap.logo_8);
                mViewPager.setCurrentItem(3, false);
                tvTab04.setTextColor(getResources().getColor(R.color.tab_bottom_text_select));
                break;
            case R.id.layoutTab05:
                ivTab05.setImageResource(R.mipmap.logo_4);
                mViewPager.setCurrentItem(4, false);
                tvTab05.setTextColor(getResources().getColor(R.color.tab_bottom_text_select));
                break;
        }


    }

    private void restTab() {

        tvTab01.setTextColor(getResources().getColor(R.color.tab_bottom_text_normal));
        tvTab02.setTextColor(getResources().getColor(R.color.tab_bottom_text_normal));
        tvTab03.setTextColor(getResources().getColor(R.color.tab_bottom_text_normal));
        tvTab04.setTextColor(getResources().getColor(R.color.tab_bottom_text_normal));
        tvTab05.setTextColor(getResources().getColor(R.color.tab_bottom_text_normal));


        ivTab01.setImageResource(R.mipmap.logo_9);
        ivTab02.setImageResource(R.mipmap.logo_5);
        ivTab03.setImageResource(R.mipmap.logo_2);
        ivTab04.setImageResource(R.mipmap.logo_7);
        ivTab05.setImageResource(R.mipmap.logo_3);

    }
}
