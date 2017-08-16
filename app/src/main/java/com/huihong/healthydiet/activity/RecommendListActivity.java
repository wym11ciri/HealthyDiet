package com.huihong.healthydiet.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RecommendFragmentPagerAdapter;
import com.huihong.healthydiet.fragment.RecommendNearbyListFragment;
import com.huihong.healthydiet.fragment.RecommendRecipeListFragment;
import com.huihong.healthydiet.utils.common.DensityUtils;
import com.huihong.healthydiet.utils.common.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/12
 * 推荐列表界面
 */

public class RecommendListActivity extends BaseTitleActivity {

    private ViewPager vpRecommend;
    private List<Fragment> mList;
    private RecommendFragmentPagerAdapter mPagerAdapter;
    //顶部导航栏
    private LinearLayout layoutLeft2, layoutRight;
    private ImageView ivLeft, ivRight;
    private View viewLeft, viewRight;
    private TextView tvRight, tvLeft2;
    //设置4个筛选按钮的监听回调
    public static RecommendListActivity mRecommendListActivity;
    //底部弹出菜单
    private ImageView ivTest;
    private LinearLayout layoutFloatButton;
    private boolean nowIsOpen = false;


    @Override
    public int setLayoutId() {
        return R.layout.activity_recommend;
    }


    @Override
    public void initUI() {
        mRecommendListActivity = this;
        initTitleBar();//初始化标题栏

        initViewPager();//ViewPager

        initFloatButton();//悬浮菜单

        initTopBar();//初始化头部导航栏（附近餐厅 推荐饮食）

        setShowFragment();//设置要显示的界面
    }


    //设置界面进入时候显示的Fragment
    private void setShowFragment() {

        restTab();//重置选项卡
        String tag = getIntent().getStringExtra("tag");
        if (tag.equals("1")) {

            setTitle("附近餐厅");
            tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_select));
            viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
            ivLeft.setImageResource(R.mipmap.restaurant_1);
            vpRecommend.setCurrentItem(0);
        } else {
            setTitle("推荐饮食");

            vpRecommend.setCurrentItem(1);
            tvRight.setTextColor(getResources().getColor(R.color.recommend_text_select));
            viewRight.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
            ivRight.setImageResource(R.mipmap.restaurant_3);
        }
    }

    //初始化标题栏
    private void initTitleBar() {
        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(RecommendListActivity.this, SearchActivity.class);
                startActivity(mIntent);
            }
        });
    }

    //初始化ViewPager
    private void initViewPager() {

        vpRecommend = (ViewPager) findViewById(R.id.vpRecommend);

        mList = new ArrayList<>();

        RecommendNearbyListFragment mRecommendNearbyListFragment = new RecommendNearbyListFragment();
        mRecommendNearbyListFragment.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {

            }

            @Override
            public void onScrollStateChanged(int state) {
                if (state == 0) {
                    openButton(true);
                } else {
                    openButton(false);
                }

            }
        });

        mList.add(mRecommendNearbyListFragment);
        mList.add(new RecommendRecipeListFragment());

        mPagerAdapter = new RecommendFragmentPagerAdapter(getSupportFragmentManager(), mList);

        vpRecommend.setAdapter(mPagerAdapter);

        vpRecommend.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                restTab();

                if (position == 0) {
                    layoutFloatButton.setVisibility(View.VISIBLE);
                    setTitle("附近餐厅");
                    tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_select));
                    viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                    ivLeft.setImageResource(R.mipmap.restaurant_1);
                } else if (position == 1) {
                    layoutFloatButton.setVisibility(View.INVISIBLE);
                    setTitle("推荐饮食");
                    tvRight.setTextColor(getResources().getColor(R.color.recommend_text_select));
                    viewRight.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                    ivRight.setImageResource(R.mipmap.restaurant_3);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
    }


    //初始化头部选项卡
    private void initTopBar() {

        //最外层布局
        layoutLeft2 = (LinearLayout) findViewById(R.id.layoutLeft2);
        layoutRight = (LinearLayout) findViewById(R.id.layoutRight);
        //里面的图片
        ivLeft = (ImageView) findViewById(R.id.ivLeft);
        ivRight = (ImageView) findViewById(R.id.ivRight);
        //左右两根提示线
        viewLeft = findViewById(R.id.viewLeft);
        viewRight = findViewById(R.id.viewRight);
        //里面的文字
        tvLeft2 = (TextView) findViewById(R.id.tvLeft2);
        tvRight = (TextView) findViewById(R.id.tvRight);


        View.OnClickListener topBarOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restTab();
                switch (v.getId()) {
                    case R.id.layoutLeft2:
                        setTitle("附近餐厅");
                        vpRecommend.setCurrentItem(0);
                        tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_select));
                        viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                        ivLeft.setImageResource(R.mipmap.restaurant_1);
                        break;
                    case R.id.layoutRight:
                        setTitle("推荐饮食");
                        vpRecommend.setCurrentItem(1);
                        tvRight.setTextColor(getResources().getColor(R.color.recommend_text_select));
                        viewRight.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                        ivRight.setImageResource(R.mipmap.restaurant_3);
                        break;
                }
            }
        };

        //设置点击事件
        layoutLeft2.setOnClickListener(topBarOnClickListener);
        layoutRight.setOnClickListener(topBarOnClickListener);
    }


    @Override
    public void initOnClickListener() {

    }

    private void restTab() {
        tvRight.setTextColor(getResources().getColor(R.color.recommend_text_normal));
        tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_normal));

        viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_normal));
        viewRight.setBackgroundColor(getResources().getColor(R.color.recommend_line_normal));

        ivLeft.setImageResource(R.mipmap.restaurant_4);
        ivRight.setImageResource(R.mipmap.restaurant_2);
    }

    private void initFloatButton() {
        layoutFloatButton = (LinearLayout) findViewById(R.id.layoutFloatButton);
        View mButtonView = findViewById(R.id.mButtonView);
        int width = ScreenUtils.getScreenWidth(RecommendListActivity.this);

        ViewGroup.LayoutParams para1;
        para1 = mButtonView.getLayoutParams();
        para1.width = width - DensityUtils.dp2px(RecommendListActivity.this, 30);
        mButtonView.setLayoutParams(para1);

        ivTest = (ImageView) findViewById(R.id.ivTest);
        ivTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpRecommend.setCurrentItem(1);
            }
        });
        openButton(true);
    }

    //打开关闭菜单栏
    public void openButton(boolean needOpen) {

        if (needOpen) {
            if (!nowIsOpen) {
                nowIsOpen = true;
                ObjectAnimator
                        .ofFloat(ivTest, "translationX", 0, -DensityUtils.dp2px(RecommendListActivity.this, 60))
                        .setDuration(500)
                        .start();
            }
        } else {
            //关闭悬浮按钮
            //如果当前是打开则执行
            if (nowIsOpen) {
                nowIsOpen = false;
                ObjectAnimator
                        .ofFloat(ivTest, "translationX", -DensityUtils.dp2px(RecommendListActivity.this, 60), 0)
                        .setDuration(500)
                        .start();
            }
        }
    }
}