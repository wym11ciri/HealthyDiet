package com.huihong.healthydiet.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.MyApplication;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.RecipesDetailsActivity;
import com.huihong.healthydiet.activity.RecommendActivity;
import com.huihong.healthydiet.adapter.NearbyFragmentPagerAdapter;
import com.huihong.healthydiet.adapter.RvRecommendAdapter;
import com.huihong.healthydiet.adapter.RvRecordAdapter;
import com.huihong.healthydiet.bean.TitlePage;
import com.huihong.healthydiet.fragment.NearbyFragment;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.mInterface.LocationListener;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.joooonho.SelectableRoundedImageView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

import static com.huihong.healthydiet.MyApplication.mList;

/**
 * Created by zangyi_shuai_ge on 2017/7/10
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private View mView;

    //附近餐厅
    private ViewPager vpNearby;
    private NearbyFragmentPagerAdapter mNearbyFragmentPagerAdapter;
    private List<Fragment> nearbyList;


    //推荐饮食
    private RecyclerView rvRecommend;
    private RvRecommendAdapter mRvRecommendAdapter;
    private List<TitlePage.ListData2Bean> recommendList;

    //饮食记录
    private RecyclerView rvRecord;
    private List<String> recordList;
    private RvRecordAdapter mRvRecordAdapter;

    //3个跳转按钮
    private LinearLayout layoutNearby, layoutRecommend, layoutRecord;

    private SwipeRefreshLayout layoutRefresh;


    private NearbyFragment nearbyFragmentRight;
    private NearbyFragment nearbyFragmentLeft;
    private NearbyFragment nearbyFragmentMiddle;


    private String mAddress;
    private boolean isFirst = true;

    //大图推荐饮食
    private TextView tvRecommendName, tvConstitutionPercentage,tvRecommendPrice;
    private SelectableRoundedImageView ivRecommend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, null);
            initUI();
//            getHomePageInfo();
            MainActivity.mainActivity.setLocationListener(new LocationListener() {
                @Override
                public void isReLocation(boolean isReLocation, String address) {
                    if (isReLocation) {
//                        tvAddress = (TextView) mView.findViewById(R.id.tvAddress);
//                        tvAddress.setText(address);
                        mAddress = address;

                        if (isFirst) {
                            isFirst = false;
                            getHomePageInfo();
                        }
                    }
                }
            });

        }

        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mBannerNet.startAutoPlay();
    }

    @Override
    public void onStop() {
        mBannerNet.stopAutoPlay();
        super.onStop();
    }

    TextView tvAddress;
    XBanner mBannerNet;

    private void initUI() {
        tvAddress = (TextView) mView.findViewById(R.id.tvAddress);
        layoutRefresh = (SwipeRefreshLayout) mView.findViewById(R.id.layoutRefresh);
        layoutRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHomePageInfo();
            }
        });

        ivRecommend = (SelectableRoundedImageView) mView.findViewById(R.id.ivRecommend);
        tvRecommendName = (TextView) mView.findViewById(R.id.tvRecommendName);
        tvConstitutionPercentage = (TextView) mView.findViewById(R.id.tvConstitutionPercentage);
        tvRecommendPrice= (TextView) mView.findViewById(R.id.tvRecommendPrice);
        initBanner();
        initNearby();
        initRecommend();
        initRecord();
        initJump();
//


//        Glide
//                .with(getActivity())
//                .load(MyApplication.mList.get(2))
//                .asBitmap()
////                .transform(new GlideRoundTransform(getActivity()))
//                .into(ivTest);


    }

    @Override
    public void onStart() {
        super.onStart();


    }

    private void initJump() {
        layoutNearby = (LinearLayout) mView.findViewById(R.id.layoutNearby);
        layoutRecommend = (LinearLayout) mView.findViewById(R.id.layoutRecommend);
        layoutRecord = (LinearLayout) mView.findViewById(R.id.layoutRecord);

        layoutNearby.setOnClickListener(this);
        layoutRecommend.setOnClickListener(this);
        layoutRecord.setOnClickListener(this);
    }

    private void initRecord() {
        rvRecord = (RecyclerView) mView.findViewById(R.id.rvRecord);
        rvRecord.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recordList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            recordList.add("麦当劳" + i);
        }
        mRvRecordAdapter = new RvRecordAdapter(getActivity(), recordList);
        rvRecord.setAdapter(mRvRecordAdapter);
//
//
//        lvRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                jumpActivity(PayActivity.class);
//            }
//        });

    }

    //饮食推荐
    private void initRecommend() {
        rvRecommend = (RecyclerView) mView.findViewById(R.id.rvRecommend);
        recommendList = new ArrayList<>();
        rvRecommend.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRvRecommendAdapter = new RvRecommendAdapter(getActivity(), recommendList);
        rvRecommend.setAdapter(mRvRecommendAdapter);
    }

    private ImageView ivCircle01, ivCircle02, ivCircle03;


    //附近餐厅
    private void initNearby() {
        ivCircle01 = (ImageView) mView.findViewById(R.id.ivCircle01);
        ivCircle02 = (ImageView) mView.findViewById(R.id.ivCircle02);
        ivCircle03 = (ImageView) mView.findViewById(R.id.ivCircle03);
        //附近餐厅
        nearbyFragmentRight = new NearbyFragment();
        nearbyFragmentMiddle = new NearbyFragment();
        nearbyFragmentLeft = new NearbyFragment();

        vpNearby = (ViewPager) mView.findViewById(R.id.vpNearby);
        nearbyList = new ArrayList<>();
        nearbyList.add(nearbyFragmentLeft);
        nearbyList.add(nearbyFragmentMiddle);
        nearbyList.add(nearbyFragmentRight);

        mNearbyFragmentPagerAdapter = new NearbyFragmentPagerAdapter(getFragmentManager(), nearbyList);
        vpNearby.setAdapter(mNearbyFragmentPagerAdapter);
        vpNearby.setOffscreenPageLimit(1);
        vpNearby.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //重置小圆点
                ivCircle01.setImageResource(R.mipmap.circle3);
                ivCircle02.setImageResource(R.mipmap.circle3);
                ivCircle03.setImageResource(R.mipmap.circle3);

                Log.i("zzz", "pos" + position);
                if (position == 0) {
                    ivCircle01.setImageResource(R.mipmap.circle2);
                } else if (position == 1) {
                    ivCircle02.setImageResource(R.mipmap.circle2);
                } else if (position == 2) {
                    ivCircle03.setImageResource(R.mipmap.circle2);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        vpNearby.setOffscreenPageLimit(2);
    }


    //初始化广告轮播
    private void initBanner() {
        mBannerNet = (XBanner) mView.findViewById(R.id.banner_1);
        final List<String> imgesUrl = new ArrayList<>();
        imgesUrl.add(mList.get(0));
        imgesUrl.add(mList.get(1));
        imgesUrl.add(mList.get(2));
        imgesUrl.add(mList.get(3));

        //添加广告数据
        mBannerNet.setData(imgesUrl, null);//第二个参数为提示文字资源集合
        mBannerNet.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(imgesUrl.get(position)).into((ImageView) view);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutNearby:
//                jumpActivity(MainActivity.class);
                jumpActivity2("1");
                break;

            case R.id.layoutRecommend:
                jumpActivity2("2");
                break;
            case R.id.layoutRecord:
//                jumpActivity(MainActivity.class);
                break;
        }

    }

    private void jumpActivity(Class<?> cls) {
        Intent mIntent = new Intent(getActivity(), cls);
        startActivity(mIntent);
    }

    private void jumpActivity2(String tag) {
        Intent mIntent = new Intent(getActivity(), RecommendActivity.class);
        mIntent.putExtra("tag", tag);
        startActivity(mIntent);
    }


    /**
     * 获取首页信息
     */
    private void getHomePageInfo() {
        LogUtil.i("调用我");
        Map<String, String> map = new HashMap<>();
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");
        map.put("CoordY", MyApplication.Latitude + "");
        map.put("CoordX", MyApplication.Longitude + "");
        HttpUtils.sendHttpAddToken(getActivity(), AppUrl.TITLE_PAGE
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        layoutRefresh.setRefreshing(false);
                        LogUtil.i("首页数据", response);
                        Gson gson = new Gson();
                        TitlePage mTitlePage = gson.fromJson(response, TitlePage.class);
                        int code = mTitlePage.getHttpCode();
                        if (code == 200) {
                            //获取附近餐厅列表
                            List<TitlePage.ListDataBean> mListData = mTitlePage.getListData();
                            setNearbyInfo(mListData);//设置附近餐厅信息
                            //获取推荐饮食
                            final List<TitlePage.ListData2Bean> mListData2 = mTitlePage.getListData2();
                            //大于2条数据
                            if (mListData2.size() >= 2) {
                                recommendList.clear();
                                recommendList.addAll(mListData2.subList(1, mListData2.size()));
                                mRvRecommendAdapter.notifyDataSetChanged();
                                //大图推荐饮食
                                Glide
                                        .with(getActivity())
                                        .load(mListData2.get(0).getTitleImage())
                                        .asBitmap()
                                        .error(R.mipmap.error_photo)
                                        .into(ivRecommend);

                                tvRecommendName.setText(mListData2.get(0).getName());

                                ivRecommend.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent mIn = new Intent(getActivity(), RecipesDetailsActivity.class);
                                        mIn.putExtra("RecipeId", mListData2.get(0).getId() + "");
                                        startActivity(mIn);
                                    }
                                });

                                int percentage = mListData2.get(0).getConstitutionPercentage();
                                tvConstitutionPercentage.setText(percentage + "%");
                                if (percentage > 90) {
                                    tvConstitutionPercentage.setTextColor(getResources().getColor(R.color.percentage_color_9));
                                } else if (percentage > 80 & percentage <= 90) {
                                    tvConstitutionPercentage.setTextColor(getResources().getColor(R.color.percentage_color_8));
                                } else if (percentage > 70 & percentage <= 80) {
                                    tvConstitutionPercentage.setTextColor(getResources().getColor(R.color.percentage_color_7));
                                } else if (percentage > 60 & percentage <= 70) {
                                    tvConstitutionPercentage.setTextColor(getResources().getColor(R.color.percentage_color_6));
                                } else {
                                    tvConstitutionPercentage.setTextColor(getResources().getColor(R.color.percentage_color_5));
                                }

                                tvRecommendPrice.setText("￥"+mListData2.get(0).getPrice());
                            }


                        } else {
                            String message = mTitlePage.getMessage();
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("首页数据", e.toString());
                        if (layoutRefresh != null) {
                            layoutRefresh.setRefreshing(false);
                        }
                    }
                });
        tvAddress.setText(mAddress + "");
    }

    private void setNearbyInfo(List<TitlePage.ListDataBean> mListData) {


        if (mListData.size() <= 3) {
            //只显示一页
//            nearbyList.add(new NearbyFragment(mListData));
            nearbyFragmentLeft.refreshData(mListData);
//            mNearbyFragmentPagerAdapter.notifyDataSetChanged();
            //设置小圆点
            ivCircle01.setVisibility(View.GONE);
            ivCircle02.setVisibility(View.GONE);
            ivCircle03.setVisibility(View.GONE);
        } else if (mListData.size() > 3 & mListData.size() <= 6) {
            nearbyFragmentLeft.refreshData(mListData.subList(0, 3));
            nearbyFragmentMiddle.refreshData(mListData.subList(3, mListData.size()));
//            nearbyList.add(new NearbyFragment(mListData.subList(0, 3)));
//            nearbyList.add(new NearbyFragment());
//            mNearbyFragmentPagerAdapter.notifyDataSetChanged();
            //设置小圆点
            ivCircle01.setVisibility(View.VISIBLE);
            ivCircle02.setVisibility(View.VISIBLE);
            ivCircle03.setVisibility(View.GONE);
        } else {
            LogUtil.i("测试222", "重新拿到数据了");
            nearbyFragmentLeft.refreshData(mListData.subList(0, 3));
            nearbyFragmentMiddle.refreshData(mListData.subList(3, 6));
            nearbyFragmentRight.refreshData(mListData.subList(6, mListData.size()));
//            nearbyList.add(new NearbyFragment(mListData.subList(0, 3)));
//            nearbyList.add(new NearbyFragment(mListData.subList(3, 6)));
//            nearbyList.add(new NearbyFragment(mListData.subList(6, mListData.size())));
//            mNearbyFragmentPagerAdapter.notifyDataSetChanged();

//            mNearbyFragmentPagerAdapter.setFragments(nearbyList);
            //设置小圆点
            ivCircle01.setVisibility(View.VISIBLE);
            ivCircle02.setVisibility(View.VISIBLE);
            ivCircle03.setVisibility(View.VISIBLE);
        }
    }
}
