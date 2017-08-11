package com.huihong.healthydiet.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.LvPopTypeAdapter;
import com.huihong.healthydiet.adapter.RecommendFragmentPagerAdapter;
import com.huihong.healthydiet.model.gsonbean.DataDictionary;
import com.huihong.healthydiet.fragment.RecommendNearbyListFragment;
import com.huihong.healthydiet.fragment.RecommendRecipeListFragment;
import com.huihong.healthydiet.mInterface.ScreenTypeListener;
import com.huihong.healthydiet.model.mybean.Type;
import com.huihong.healthydiet.utils.common.DensityUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.ScreenUtils;
import com.huihong.healthydiet.utils.current.ListPopupUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static com.huihong.healthydiet.R.id.layoutType04;

/**
 * Created by zangyi_shuai_ge on 2017/7/12
 * 推荐饮食界面
 */

public class RecommendActivity extends BaseTitleActivity {

    private List<Type> mTypeList;


    private ViewPager vpRecommend;
    private List<Fragment> mList;
    private RecommendFragmentPagerAdapter mPagerAdapter;

    //顶部导航栏
    private LinearLayout layoutLeft2, layoutRight;
    private ImageView ivLeft, ivRight;
    private View viewLeft, viewRight;
    private TextView tvRight, tvLeft2;



    private boolean isRight = true;



    private String TypeValue;
    private int TypeId;


    //设置4个筛选按钮的监听回调
    public static RecommendActivity mRecommendActivity;
    private ScreenTypeListener mLeftScreenTypeListener;
    private ScreenTypeListener mRightScreenTypeListener;
    private TextView tvType01, tvType02, tvType03, tvType04;
    private LinearLayout layoutType4;


    //筛选
    private ListPopupWindow mListPopupWindow;//类型列表
    private  ImageView ivType;//第四个筛选里面那个箭头
    private String GroupBy;




    //底部弹出菜单
    private ImageView ivTest;
    private LinearLayout layoutFloatButton;
    private boolean nowIsOpen = false;



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
        initTitleBar();//初始化标题栏

        initViewPager();//ViewPager

        initFloatButton();//悬浮菜单

        initTopBar();//初始化头部导航栏（附近餐厅 推荐饮食）

        initScreenBar();//初始化筛选栏

        getDataDictionary();//获取数据



        setShowFragment();//设置要显示的界面
    }


    //设置界面进入时候显示的Fragment
    private void setShowFragment() {

        restTab();//重置选项卡
        String tag = getIntent().getStringExtra("tag");
        if (tag.equals("1")) {
            isRight = false;
            setTitle("附近餐厅");
            tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_select));
            viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
            ivLeft.setImageResource(R.mipmap.restaurant_1);
            vpRecommend.setCurrentItem(0);
        } else {
            setTitle("推荐饮食");
            isRight = true;
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
                Intent mIntent = new Intent(RecommendActivity.this, SearchActivity.class);
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
                restType();

                if (mListPopupWindow != null) {
                    mListPopupWindow.dismiss();
                }
                if (position == 0) {
                    layoutFloatButton.setVisibility(View.VISIBLE);
                    setTitle("附近餐厅");
                    isRight = false;
                    tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_select));
                    viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                    ivLeft.setImageResource(R.mipmap.restaurant_1);
                } else if (position == 1) {
                    layoutFloatButton.setVisibility(View.INVISIBLE);
                    setTitle("推荐饮食");
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
    }

    //初始化筛选栏
    private void initScreenBar() {
        mTypeList = new ArrayList<>();
        //四个筛选
        tvType01 = (TextView) findViewById(R.id.tvType01);
        tvType02 = (TextView) findViewById(R.id.tvType02);
        tvType03 = (TextView) findViewById(R.id.tvType03);
        //最后一个筛选
        tvType04 = (TextView) findViewById(R.id.tvType04);
        layoutType4 = (LinearLayout) findViewById(layoutType04);
        ivType = (ImageView) findViewById(R.id.ivType);
        //筛选按钮的监听器
        View.OnClickListener typeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restType();

                if (mLeftScreenTypeListener != null && mRightScreenTypeListener != null) {
                    switch (v.getId()) {
                        case R.id.tvType01:
                            if (mListPopupWindow != null && mListPopupWindow.isShowing()) {
                                mListPopupWindow.dismiss();
                            }
                            GroupBy = "SuitMe";
                            tvType01.setTextColor(getResources().getColor(R.color.recommend_type_text_select));
                            mLeftScreenTypeListener.screenType(isRight, GroupBy, 0, false);
                            mRightScreenTypeListener.screenType(isRight, GroupBy, 0, false);
                            break;
                        case R.id.tvType02:
                            if (mListPopupWindow != null && mListPopupWindow.isShowing()) {
                                mListPopupWindow.dismiss();
                            }
                            GroupBy = "SalesVolume";
                            tvType02.setTextColor(getResources().getColor(R.color.recommend_type_text_select));
                            mRightScreenTypeListener.screenType(isRight, GroupBy, 0, false);
                            mLeftScreenTypeListener.screenType(isRight, GroupBy, 0, false);
                            break;
                        case R.id.tvType03:
                            if (mListPopupWindow != null && mListPopupWindow.isShowing()) {
                                mListPopupWindow.dismiss();
                            }
                            GroupBy = "Distance";
                            tvType03.setTextColor(getResources().getColor(R.color.recommend_type_text_select));
                            mRightScreenTypeListener.screenType(isRight, GroupBy, 0, false);
                            mLeftScreenTypeListener.screenType(isRight, GroupBy, 0, false);
                            break;
                        case layoutType04:

                            GroupBy = "Type";
                            tvType04.setTextColor(getResources().getColor(R.color.recommend_type_text_select));
                            if (mListPopupWindow != null) {
                                if (mListPopupWindow.isShowing()) {
                                    mListPopupWindow.dismiss();
                                } else {
                                    showListPopup(layoutType4);
                                }
                            } else {
                                showListPopup(layoutType4);
                            }
                            break;
                    }
                }

            }
        };
        tvType01.setOnClickListener(typeListener);
        tvType02.setOnClickListener(typeListener);
        tvType03.setOnClickListener(typeListener);
        layoutType4.setOnClickListener(typeListener);
        restType();
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
                        isRight = false;
                        vpRecommend.setCurrentItem(0);
                        tvLeft2.setTextColor(getResources().getColor(R.color.recommend_text_select));
                        viewLeft.setBackgroundColor(getResources().getColor(R.color.recommend_line_select));
                        ivLeft.setImageResource(R.mipmap.restaurant_1);
                        break;
                    case R.id.layoutRight:
                        setTitle("推荐饮食");
                        isRight = true;
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



        //下面


    }

    private void restType() {

        tvType01.setTextColor(getResources().getColor(R.color.recommend_type_text_normal));
        tvType02.setTextColor(getResources().getColor(R.color.recommend_type_text_normal));
        tvType03.setTextColor(getResources().getColor(R.color.recommend_type_text_normal));
        tvType04.setTextColor(getResources().getColor(R.color.recommend_type_text_normal));

        GroupBy = "";
        TypeValue = "类型";
        TypeId = 0;
        tvType04.setText("类型");
        ivType.setImageResource(R.mipmap.up);
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

    //获取餐厅类型
    private void getDataDictionary() {
        OkHttpUtils
                .post()
                .url(AppUrl.DATA_DICTIONARY)
                .addParams("TypeValue", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {


                        Toast.makeText(RecommendActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，获取餐厅类型:", response);

                        Gson gson = new Gson();
                        DataDictionary DataDictionary = gson.fromJson(response, DataDictionary.class);
//
                        int code = DataDictionary.getHttpCode();
                        if (code == 200) {
                            List<com.huihong.healthydiet.model.gsonbean.DataDictionary.ListDataBean> ListData = DataDictionary.getListData();
                            for (int i = 0; i < ListData.size(); i++) {
                                Type mType = new Type();
                                mType.setId(ListData.get(i).getId());
                                mType.setTypeValue(ListData.get(i).getTypeValue());
                                mTypeList.add(mType);
                            }
                        }
                    }
                });
    }

    private void showListPopup(LinearLayout mTextView) {

        if (mTypeList.size() > 0) {

            if (mListPopupWindow == null) {
                LvPopTypeAdapter mAdapter = new LvPopTypeAdapter(RecommendActivity.this, mTypeList);
                mListPopupWindow = ListPopupUtil.showListPopup(RecommendActivity.this, mTextView, mAdapter, R.drawable.bg_03, 0, 2, 100, 0);
                mListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mListPopupWindow.dismiss();
                        mRightScreenTypeListener.screenType(isRight, GroupBy, mTypeList.get(position).getId(), false);
                        mLeftScreenTypeListener.screenType(isRight, GroupBy, mTypeList.get(position).getId(), false);
                        tvType04.setText(mTypeList.get(position).getTypeValue());
                        TypeValue = mTypeList.get(position).getTypeValue();
                        TypeId = mTypeList.get(position).getId();
                    }
                });
                mListPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                        ivType.setImageResource(R.mipmap.up);
//                        Toast.makeText(RecommendActivity.this, "消失了", Toast.LENGTH_SHORT).show();
//                        layoutType4.setClickable(true);
                    }
                });
                mListPopupWindow.setForceIgnoreOutsideTouch(true);
            }
            ivType.setImageResource(R.mipmap.down);
            mListPopupWindow.show();

        }

    }

    private void initFloatButton() {
        layoutFloatButton = (LinearLayout) findViewById(R.id.layoutFloatButton);
        View mButtonView = findViewById(R.id.mButtonView);
        int width = ScreenUtils.getScreenWidth(RecommendActivity.this);

        ViewGroup.LayoutParams para1;
        para1 = mButtonView.getLayoutParams();
        para1.width = width - DensityUtils.dp2px(RecommendActivity.this, 30);
        mButtonView.setLayoutParams(para1);

        ivTest = (ImageView) findViewById(R.id.ivTest);
        ivTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RecommendActivity.this, "点我干嘛呀", Toast.LENGTH_SHORT).show();
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
                        .ofFloat(ivTest, "translationX", 0, -DensityUtils.dp2px(RecommendActivity.this, 60))
                        .setDuration(500)
                        .start();
            }
        } else {
            //关闭悬浮按钮
            //如果当前是打开则执行
            if (nowIsOpen) {
                nowIsOpen = false;
                ObjectAnimator
                        .ofFloat(ivTest, "translationX", -DensityUtils.dp2px(RecommendActivity.this, 60), 0)
                        .setDuration(500)
                        .start();
            }
        }
    }
}
