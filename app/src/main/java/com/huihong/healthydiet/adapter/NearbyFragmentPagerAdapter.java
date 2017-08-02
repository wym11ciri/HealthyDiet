package com.huihong.healthydiet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/4/21
 */

public class NearbyFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private List<Fragment> mList;
    FragmentManager fm;
    public NearbyFragmentPagerAdapter(FragmentManager fm, List<Fragment> pList) {
        super(fm);
        this.fm=fm;
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

    public void setFragments(List<Fragment> mList) {
        if(this.mList != null){
            FragmentTransaction ft = fm.beginTransaction();
            for(Fragment f:this.mList){
                ft.remove(f);
            }
            ft.commit();
            ft=null;
            fm.executePendingTransactions();
        }
        this.mList = mList;
        notifyDataSetChanged();
    }
}
