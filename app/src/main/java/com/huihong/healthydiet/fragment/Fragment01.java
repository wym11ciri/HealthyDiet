package com.huihong.healthydiet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.PayActivity;
import com.huihong.healthydiet.activity.RecommendActivity;
import com.huihong.healthydiet.adapter.LvRecordAdapter;
import com.huihong.healthydiet.adapter.NearbyFragmentPagerAdapter;
import com.huihong.healthydiet.adapter.RvRecommendAdapter;
import com.joooonho.SelectableRoundedImageView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/10
 */

public class Fragment01 extends Fragment implements View.OnClickListener {


    private View mView;


    //附近餐厅
    private ViewPager vpNearby;
    private NearbyFragmentPagerAdapter mNearbyFragmentPagerAdapter;
    private List<Fragment> nearbyList;


    //推荐饮食
    private RecyclerView rvRecommend;
    private RvRecommendAdapter mRvRecommendAdapter;
    private List<String> recommendList;

    //饮食记录
    private ListView lvRecord;
    private List<String> recordList;
    private LvRecordAdapter mLvRecordAdapter;

    //3个跳转按钮
    private LinearLayout layoutNearby, layoutRecommend, layoutRecord;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, null);
            initUI();
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

    XBanner mBannerNet;

    private void initUI() {
        initBanner();
        initNearby();
        initRecommend();
        initRecord();
        initJump();


        SelectableRoundedImageView ivTest = (SelectableRoundedImageView) mView.findViewById(R.id.image0);

        Glide
                .with(getActivity())
                .load("http://image.tianjimedia.com/uploadImages/2015/131/41/5CI8TD94WZ10.jpg")
                .asBitmap()
//                .transform(new GlideRoundTransform(getActivity()))
                .into(ivTest);


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
        lvRecord = (ListView) mView.findViewById(R.id.lvRecord);
        recordList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            recordList.add("麦当劳" + i);
        }
        mLvRecordAdapter = new LvRecordAdapter(getActivity(), recordList);
        lvRecord.setAdapter(mLvRecordAdapter);


        lvRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jumpActivity(PayActivity.class);
            }
        });

    }

    //饮食推荐
    private void initRecommend() {
        rvRecommend = (RecyclerView) mView.findViewById(R.id.rvRecommend);
        recommendList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            recommendList.add("KFC" + i);
        }
        rvRecommend.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRvRecommendAdapter = new RvRecommendAdapter(getActivity(), recommendList);
        rvRecommend.setAdapter(mRvRecommendAdapter);


    }

    private ImageView ivCircle01, ivCircle02, ivCircle03;

    private void initNearby() {

        ivCircle01 = (ImageView) mView.findViewById(R.id.ivCircle01);
        ivCircle02 = (ImageView) mView.findViewById(R.id.ivCircle02);
        ivCircle03 = (ImageView) mView.findViewById(R.id.ivCircle03);
        //附近餐厅
        vpNearby = (ViewPager) mView.findViewById(R.id.vpNearby);
        nearbyList = new ArrayList<>();
        nearbyList.add(new NearbyFragment());
        nearbyList.add(new NearbyFragment());
        nearbyList.add(new NearbyFragment());
        mNearbyFragmentPagerAdapter = new NearbyFragmentPagerAdapter(getFragmentManager(), nearbyList);
        vpNearby.setAdapter(mNearbyFragmentPagerAdapter);


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


    }


    //初始化广告轮播
    private void initBanner() {
        mBannerNet = (XBanner) mView.findViewById(R.id.banner_1);
        final List<String> imgesUrl = new ArrayList<>();
        imgesUrl.add("http://image.tianjimedia.com/uploadImages/2015/131/41/5CI8TD94WZ10.jpg");
        imgesUrl.add("http://img.taopic.com/uploads/allimg/110604/2014-11060416241547.jpg");
        imgesUrl.add("http://www.qiwen007.com/images/image/2016/1212/6361714777668259239190221.jpg");
        imgesUrl.add("http://image.tianjimedia.com/uploadImages/2014/351/53/0Y94V9AVTL1Y.jpg");
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
                jumpActivity(RecommendActivity.class);
                break;

            case R.id.layoutRecommend:
                jumpActivity(RecommendActivity.class);
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
}
