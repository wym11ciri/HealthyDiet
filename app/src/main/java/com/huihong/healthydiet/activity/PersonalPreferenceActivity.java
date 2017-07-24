package com.huihong.healthydiet.activity;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.FragmentPagerAdapter;
import com.huihong.healthydiet.fragment.LikeFragment;
import com.huihong.healthydiet.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/20
 */

public class PersonalPreferenceActivity extends BaseTitleActivity {

    private LinearLayout layoutLike, layoutDislike;
    private ImageView ivLike, ivDislike;
    private TextView tvLike, tvDislike;
    private MyViewPager mViewPager;
    private FragmentPagerAdapter  mAdapter;
    private List<Fragment> mList;


    @Override
    public int setLayoutId() {
        return R.layout.activity_personal_preference;
    }

    @Override
    public void initUI() {
        setTitle("个人偏好");
        layoutLike = (LinearLayout) findViewById(R.id.layoutLike);
        layoutDislike = (LinearLayout) findViewById(R.id.layoutDislike);
        ivLike = (ImageView) findViewById(R.id.ivLike);
        ivDislike = (ImageView) findViewById(R.id.ivDislike);
        tvLike = (TextView) findViewById(R.id.tvLike);
        tvDislike = (TextView) findViewById(R.id.tvDislike);
        mViewPager = (MyViewPager) findViewById(R.id.mViewPager);

        mList=new ArrayList<>();
        mList.add(new LikeFragment());
        mList.add(new LikeFragment());

        mAdapter=new FragmentPagerAdapter(getSupportFragmentManager(),mList);

        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void initOnClickListener() {

    }
}
