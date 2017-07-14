package com.huihong.healthydiet.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.adapter.NearbyFragmentPagerAdapter;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/10
 */

public class Fragment02 extends Fragment {


    private View mView;


    //附近餐厅
    private ViewPager vpNearby;
    private NearbyFragmentPagerAdapter mNearbyFragmentPagerAdapter;
    private List<Fragment> nearbyList;


    //推荐饮食
    private RecyclerView rvRecommend;
    private List<String> recommendList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_02, null);
//        initUI();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mBannerNet.startAutoPlay();
    }

    @Override
    public void onStop() {
//        mBannerNet.stopAutoPlay();
        super.onStop();
    }

    XBanner mBannerNet;

    private void initUI() {
        initBanner();
        initNearby();
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
}
