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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/10
 * 附近餐厅
 */

public class NearbyFragment extends Fragment {
    //创造它时候需要传递一个连接进来
//    public NearbyFragment() {
//    }


    private View  mView;
    private RecyclerView rvNearby;
    private RvNearbyAdapter mAdapter;
    private List<String > mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(mView==null){
            mView=  inflater.inflate(R.layout.fragment_nearby,null);
            initUI();
        }
//        if(mAdapter!=null){
//            mAdapter.notifyDataSetChanged();
//        }

        return mView;
    }

    private void initUI() {
        mList=new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            mList.add("肯德基"+i);
        }
        rvNearby= (RecyclerView) mView.findViewById(R.id.rvNearby);
        rvNearby.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mAdapter=new RvNearbyAdapter(getActivity(),mList);

        rvNearby.setAdapter(mAdapter);
    }
}
