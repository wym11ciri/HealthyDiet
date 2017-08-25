package com.huihong.healthydiet.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.adapter.RvNearbyAdapter;
import com.huihong.healthydiet.model.httpmodel.RestaurantInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/10
 * 附近餐厅
 */

public class NearbyFragment extends Fragment {
    //创造它时候需要传递一个连接进来
    private View mView;
    private RecyclerView rvNearby;
    private RvNearbyAdapter mAdapter;
    private List<RestaurantInfo> mList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();
        mAdapter = new RvNearbyAdapter(getActivity(), mList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_nearby, null);
            initUI();
        }
        return mView;
    }

    private void initUI() {
        rvNearby = (RecyclerView) mView.findViewById(R.id.rvNearby);
        GridLayoutManager mGridLayoutManager=new GridLayoutManager(getActivity(), 3){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvNearby.setLayoutManager(mGridLayoutManager);
        rvNearby.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    public void refreshData(List<RestaurantInfo> pListData) {
        if (mList != null && pListData != null) {
            mList.clear();
            mList.addAll(pListData);
            mAdapter.notifyDataSetChanged();
        }
    }
}
