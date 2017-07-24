package com.huihong.healthydiet.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.adapter.RvTestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/20
 */

public class LikeFragment extends Fragment {

    //    private SwipeMenuAdapter mDataAdapter = null;
    private View mView;
    private LRecyclerView mLRecyclerView;
    private RvTestAdapter mRvTestAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_islike, null);
        mLRecyclerView = (LRecyclerView) mView.findViewById(R.id.mLRecyclerView);

        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        List<String> recommendList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            recommendList.add("我喜爱的食物"+i);
        }
        mRvTestAdapter = new RvTestAdapter(getActivity(), recommendList);
        LRecyclerViewAdapter mLRecyclerViewAdapter = new LRecyclerViewAdapter(mRvTestAdapter);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        return mView;
    }
}
