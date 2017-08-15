package com.huihong.healthydiet.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.FragmentPagerAdapter;
import com.huihong.healthydiet.fragment.like.LikeFragment;
import com.huihong.healthydiet.fragment.like.RestaurantLikeFragment;
import com.huihong.healthydiet.fragment.like.UnLikeFragment;
import com.huihong.healthydiet.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/7/20
 * 喜不喜欢
 */

public class PersonalPreferenceActivity extends BaseTitleActivity {

    @BindView(R.id.ivRestaurantLike)
    ImageView ivRestaurantLike;
    @BindView(R.id.tvRestaurantLike)
    TextView tvRestaurantLike;
    @BindView(R.id.layoutRestaurantLike)
    LinearLayout layoutRestaurantLike;


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
        ButterKnife.bind(this);
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
        mList.add(new RestaurantLikeFragment());
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mAdapter);


        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重置
                ivLike.setImageResource(R.mipmap.recipes_1);
                ivDislike.setImageResource(R.mipmap.recipes_6);
                ivRestaurantLike.setImageResource(R.mipmap.collection_2);

                tvLike.setTextColor(getResources().getColor(R.color.like_color_normal));
                tvDislike.setTextColor(getResources().getColor(R.color.like_color_normal));
                tvRestaurantLike.setTextColor(getResources().getColor(R.color.like_color_normal));
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
                    case R.id.layoutRestaurantLike:
                        mViewPager.setCurrentItem(2, true);
                        ivRestaurantLike.setImageResource(R.mipmap.collection_1);
                        tvRestaurantLike.setTextColor(getResources().getColor(R.color.like_color_select));
                        break;
                }


            }
        };


        layoutLike.setOnClickListener(onClickListener);
        layoutDislike.setOnClickListener(onClickListener);
        layoutRestaurantLike.setOnClickListener(onClickListener);
    }


    @Override
    public void initOnClickListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}
