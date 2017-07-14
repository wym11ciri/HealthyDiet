package com.huihong.healthydiet.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.RecommendActivity;
import com.huihong.healthydiet.adapter.RvRecommendRecommendAdapter;
import com.huihong.healthydiet.mInterface.ScreenTypeListener;
import com.huihong.healthydiet.utils.common.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/12
 */

public class RecommendRecommendFragment extends Fragment {

    private  View mView;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(mView==null){
            mView=inflater.inflate(R.layout.fragment_recommend,null);
            initUI();
        }

        return mView;
    }

    private void initUI() {
        recyclerView= (RecyclerView) mView.findViewById(R.id.recyclerView);

        List<String> recommendList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            recommendList.add("KFC" + i);
        }

        recyclerView  .setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        RvRecommendRecommendAdapter mRvRecommendAdapter = new RvRecommendRecommendAdapter(getActivity(), recommendList);
        recyclerView.setAdapter(mRvRecommendAdapter);

        RecommendActivity.mRecommendActivity.setRightScreenTypeListener(new ScreenTypeListener() {
            @Override
            public void screenType(boolean isRight, String type) {
                LogUtil.i("zzzz",isRight+type);
                if(isRight){
                    Toast.makeText(getActivity(), "推荐饮食收到"+type+"请求", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
