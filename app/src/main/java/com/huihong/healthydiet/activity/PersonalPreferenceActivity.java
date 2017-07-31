package com.huihong.healthydiet.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.FragmentPagerAdapter;
import com.huihong.healthydiet.fragment.LikeFragment;
import com.huihong.healthydiet.fragment.UnLikeFragment;
import com.huihong.healthydiet.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/20
 * 喜不喜欢
 */

public class PersonalPreferenceActivity extends BaseTitleActivity {

    private LinearLayout layoutLike, layoutDislike;
    private ImageView ivLike, ivDislike;
    private TextView tvLike, tvDislike;
    private MyViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mList;


    private View.OnClickListener onClickListener;


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

        mList = new ArrayList<>();
        mList.add(new LikeFragment());
        mList.add(new UnLikeFragment());
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mAdapter);


        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重置
                ivLike.setImageResource(R.mipmap.recipes_1);
                ivDislike.setImageResource(R.mipmap.recipes_6);
                tvLike.setTextColor(getResources().getColor(R.color.like_color_normal));
                tvDislike.setTextColor(getResources().getColor(R.color.like_color_normal));
                switch (v.getId()) {
                    case R.id.layoutLike:
                        mViewPager.setCurrentItem(0, true);
                        ivLike.setImageResource(R.mipmap.recipes_5);
                        tvLike.setTextColor(getResources().getColor(R.color.like_color_select));

                        break;
                    case R.id.layoutDislike:
                        mViewPager.setCurrentItem(1, true);
                        ivDislike.setImageResource(R.mipmap.recipes_2);
                        tvDislike.setTextColor(getResources().getColor(R.color.like_color_select));
                        break;
                }


            }
        };


        layoutLike.setOnClickListener(onClickListener);
        layoutDislike.setOnClickListener(onClickListener);


    }


    @Override
    public void initOnClickListener() {

    }
}
