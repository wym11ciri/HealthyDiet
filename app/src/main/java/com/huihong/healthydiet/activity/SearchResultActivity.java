package com.huihong.healthydiet.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RecommendFragmentPagerAdapter;
import com.huihong.healthydiet.fragment.SearchResultLeftFragment;
import com.huihong.healthydiet.fragment.SearchResultRightFragment;
import com.huihong.healthydiet.mInterface.ScreenTypeListener;
import com.huihong.healthydiet.mybean.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/12
 * 推荐饮食界面
 */

public class SearchResultActivity extends BaseTitleActivity implements View.OnClickListener {

    private List<Type> mTypeList;
    private ViewPager vpRecommend;
    private List<Fragment> mList;
    private RecommendFragmentPagerAdapter mPagerAdapter;
    private LinearLayout layoutLeft2, layoutRight;
    private ImageView ivLeft, ivRight;
    private View viewLeft, viewRight;
    private TextView tvRight, tvLeft2;
    private boolean isRight = true;
    private String GroupBy;
    private String TypeValue;
    private int TypeId;


    //设置4个筛选按钮的监听回调
    public static SearchResultActivity mRecommendActivity;
    private ScreenTypeListener mLeftScreenTypeListener;
    private ScreenTypeListener mRightScreenTypeListener;

    public void setLeftScreenTypeListener(ScreenTypeListener pScreenTypeListener) {
        mLeftScreenTypeListener = pScreenTypeListener;
    }

    public void setRightScreenTypeListener(ScreenTypeListener pScreenTypeListener) {
        mRightScreenTypeListener = pScreenTypeListener;
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initUI() {

        setTitle("搜索结果");
        mRecommendActivity = this;
        vpRecommend = (ViewPager) findViewById(R.id.vpRecommend);
        mList = new ArrayList<>();
        mList.add(new SearchResultLeftFragment());
        mList.add(new SearchResultRightFragment());
        mPagerAdapter = new RecommendFragmentPagerAdapter(getSupportFragmentManager(), mList);
        vpRecommend.setAdapter(mPagerAdapter);
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


        //判断是从哪个界面进来的
        restTab();
        vpRecommend.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                restTab();

                if (position == 0) {
                    isRight = false;
                    tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_select));
                    viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                    ivLeft.setImageResource(R.mipmap.restaurant_1);
                } else if (position == 1) {
                    vpRecommend.setCurrentItem(1);
                    isRight = true;
                    tvRight.setTextColor(getResources().getColor(R.color.recommend_text_select));
                    viewRight.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                    ivRight.setImageResource(R.mipmap.restaurant_3);
                }
                if (mRightScreenTypeListener != null) {
                    //切换完成变成默认排序
                    mRightScreenTypeListener.screenType(isRight, GroupBy, 0, true);
                    mLeftScreenTypeListener.screenType(isRight, GroupBy, 0, true);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
        isRight = false;
        tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_select));
        viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
        ivLeft.setImageResource(R.mipmap.restaurant_1);
//        setTitle("附近餐厅");
        vpRecommend.setCurrentItem(0);

    }


    @Override
    public void initOnClickListener() {

    }

    @Override
    public void onClick(View v) {
        restTab();
        switch (v.getId()) {
            case R.id.layoutLeft2:
//                setTitle("附近餐厅");
                vpRecommend.setCurrentItem(0);
                isRight = false;
                tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_select));
                viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                ivLeft.setImageResource(R.mipmap.restaurant_1);
                break;
            case R.id.layoutRight:
//                setTitle("推荐饮食");
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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }


}
