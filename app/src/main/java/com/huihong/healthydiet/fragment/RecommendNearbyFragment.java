package com.huihong.healthydiet.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.RecommendActivity;
import com.huihong.healthydiet.adapter.RvRecommendNearbyAdapter;
import com.huihong.healthydiet.mInterface.ScreenTypeListener;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/12
 * 附近餐厅界面
 */

public class RecommendNearbyFragment extends Fragment {
    private View mView;

    //列表加载页数
    private int unm = 0;
    //列表
    private LRecyclerView recyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RvRecommendNearbyAdapter mRvRecommendAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_recommend2, null);
            initUI();
        }

        return mView;
    }

    private void initUI() {
        initRecyclerView();
        RecommendActivity.mRecommendActivity.setLeftScreenTypeListener(new ScreenTypeListener() {
            @Override
            public void screenType(boolean isRight, String type) {
                LogUtil.i("zzzz", isRight + type);
                if (!isRight) {
                    Toast.makeText(getActivity(), "附近餐厅收到" + type + "请求", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void initRecyclerView() {





        List<String> recommendList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            recommendList.add("KFC" + i);
        }
        recyclerView = (LRecyclerView) mView.findViewById(R.id.recyclerView);

        mRvRecommendAdapter = new RvRecommendNearbyAdapter(getActivity(), recommendList);

        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mRvRecommendAdapter);
        recyclerView.setAdapter(mLRecyclerViewAdapter);

        //setLayoutManager
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        //防止item位置互换
//        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

//        mLRecyclerViewAdapter.addHeaderView(new SampleHeader(this));

        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
//                mCurrentCounter = 0;
//                mDataAdapter.clear();
//                requestData();
            }
        });

        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
//                if (mCurrentCounter < TOTAL_COUNTER) {
//                    // loading more
//                    requestData();
//                } else {
//                    //the end
//                    mRecyclerView.setNoMore(true);
//                }
            }
        });


//        recyclerView.refresh();




        OkHttpUtils
                .post()
                .url(AppUrl.GET_RESTAURANT_LIST_INFO)
                .addParams("CoordX", "2")
                .addParams("CoordY", "2")
                .addParams("GroupBy", "Distance")
                .addParams("PageNo", "1")
                .addParams("TypeValue", "")
//                .addParams("ispregnant", pregnant)
//                .addParams("address", address)
//                .addParams("telphone", phoneNum)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("jiekou2"+e);
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        LogUtil.i("jiekou",response);

                    }
                });

    }
}
