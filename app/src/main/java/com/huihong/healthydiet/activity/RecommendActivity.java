package com.huihong.healthydiet.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RecommendFragmentPagerAdapter;
import com.huihong.healthydiet.fragment.RecommendNearbyFragment;
import com.huihong.healthydiet.fragment.RecommendRecommendFragment;
import com.huihong.healthydiet.mInterface.ScreenTypeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/12
 * 推荐饮食界面
 */

public class RecommendActivity extends BaseTitleActivity implements View.OnClickListener {


    private ViewPager vpRecommend;
    private List<Fragment> mList;
    private RecommendFragmentPagerAdapter mPagerAdapter;


    private LinearLayout layoutLeft2, layoutRight;
    private ImageView ivLeft, ivRight;
    private View viewLeft, viewRight;
    private TextView tvRight, tvLeft2;

    private boolean isRight = true;

    //设置4个筛选按钮的监听回调
    public static RecommendActivity mRecommendActivity;
    private ScreenTypeListener mLeftScreenTypeListener;
    private ScreenTypeListener mRightScreenTypeListener;
    private TextView tvType01, tvType02, tvType03, tvType04;

    public void setLeftScreenTypeListener(ScreenTypeListener pScreenTypeListener) {
        mLeftScreenTypeListener = pScreenTypeListener;
    }

    public void setRightScreenTypeListener(ScreenTypeListener pScreenTypeListener) {
        mRightScreenTypeListener = pScreenTypeListener;
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_recommend;
    }

    @Override
    public void initUI() {
        mRecommendActivity = this;
        setTitle("推荐饮食");
        vpRecommend = (ViewPager) findViewById(R.id.vpRecommend);
        mList = new ArrayList<>();
        mList.add(new RecommendNearbyFragment());
        mList.add(new RecommendRecommendFragment());
        mPagerAdapter = new RecommendFragmentPagerAdapter(getSupportFragmentManager(), mList);
        vpRecommend.setAdapter(mPagerAdapter);
        vpRecommend.setCurrentItem(1);

        layoutLeft2 = (LinearLayout) findViewById(R.id.layoutLeft2);
        layoutRight = (LinearLayout) findViewById(R.id.layoutRight);
        ivLeft = (ImageView) findViewById(R.id.ivLeft);
        ivRight = (ImageView) findViewById(R.id.ivRight);
        viewLeft = findViewById(R.id.viewLeft);
        viewRight = findViewById(R.id.viewRight);
        tvLeft2 = (TextView) findViewById(R.id.tvLeft2);
        tvRight = (TextView) findViewById(R.id.tvRight);

        layoutLeft2.setOnClickListener(this);
        layoutRight.setOnClickListener(this);


        vpRecommend.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                restTab();
                restType();

                if (position == 0) {
                    setTitle("附近餐厅");
                    isRight = false;
                    tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_select));
                    viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                    ivLeft.setImageResource(R.mipmap.restaurant_1);
                } else if (position == 1) {
                    setTitle("推荐饮食");
                    vpRecommend.setCurrentItem(1);
                    isRight = true;
                    tvRight.setTextColor(getResources().getColor(R.color.recommend_text_select));
                    viewRight.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                    ivRight.setImageResource(R.mipmap.restaurant_3);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });


        tvType01 = (TextView) findViewById(R.id.tvType01);
        tvType02 = (TextView) findViewById(R.id.tvType02);
        tvType03 = (TextView) findViewById(R.id.tvType03);
        tvType04 = (TextView) findViewById(R.id.tvType04);


        View.OnClickListener typeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restType();

                if (mLeftScreenTypeListener != null && mRightScreenTypeListener != null) {
                    switch (v.getId()) {
                        case R.id.tvType01:
                            tvType01.setTextColor(getResources().getColor(R.color.recommend_type_text_select));

                            mLeftScreenTypeListener.screenType(isRight, "适合我的");
                            mRightScreenTypeListener.screenType(isRight, "适合我的");
                            break;
                        case R.id.tvType02:
                            tvType02.setTextColor(getResources().getColor(R.color.recommend_type_text_select));
                            mRightScreenTypeListener.screenType(isRight, "销量最高");
                            mLeftScreenTypeListener.screenType(isRight, "销量最高");
                            break;
                        case R.id.tvType03:
                            tvType03.setTextColor(getResources().getColor(R.color.recommend_type_text_select));
                            mRightScreenTypeListener.screenType(isRight, "距离最近");
                            mLeftScreenTypeListener.screenType(isRight, "距离最近");
                            break;
                        case R.id.tvType04:
                            tvType04.setTextColor(getResources().getColor(R.color.recommend_type_text_select));
                            mRightScreenTypeListener.screenType(isRight, "其他类型");
                            mLeftScreenTypeListener.screenType(isRight, "其他类型");
                            break;
                    }
                }

            }
        };
        tvType01.setOnClickListener(typeListener);
        tvType02.setOnClickListener(typeListener);
        tvType03.setOnClickListener(typeListener);
        tvType04.setOnClickListener(typeListener);
    }

    private void restType() {
        tvType01.setTextColor(getResources().getColor(R.color.recommend_type_text_normal));
        tvType02.setTextColor(getResources().getColor(R.color.recommend_type_text_normal));
        tvType03.setTextColor(getResources().getColor(R.color.recommend_type_text_normal));
        tvType04.setTextColor(getResources().getColor(R.color.recommend_type_text_normal));
    }

    @Override
    public void initOnClickListener() {

    }

    @Override
    public void onClick(View v) {
        restTab();
        switch (v.getId()) {
            case R.id.layoutLeft2:
                setTitle("附近餐厅");
                vpRecommend.setCurrentItem(0);
                isRight = false;
                tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_select));
                viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                ivLeft.setImageResource(R.mipmap.restaurant_1);
                break;
            case R.id.layoutRight:
                setTitle("推荐饮食");
                vpRecommend.setCurrentItem(1);
                isRight = true;
                tvRight.setTextColor(getResources().getColor(R.color.recommend_text_select));
                viewRight.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                ivRight.setImageResource(R.mipmap.restaurant_3);
                break;
        }

    }

    private void restTab() {
        tvRight.setTextColor(getResources().getColor(R.color.recommend_text_normal));
        tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_normal));

        viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_normal));
        viewRight.setBackgroundColor(getResources().getColor(R.color.recommend_line_normal));

        ivLeft.setImageResource(R.mipmap.restaurant_4);
        ivRight.setImageResource(R.mipmap.restaurant_2);
    }
}
