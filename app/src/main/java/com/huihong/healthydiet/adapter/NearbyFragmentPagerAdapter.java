package com.huihong.healthydiet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/4/21
 */

public class NearbyFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private List<Fragment> mList;

    public NearbyFragmentPagerAdapter(FragmentManager fm, List<Fragment> pList) {
        super(fm);
        mList = pList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }
}
