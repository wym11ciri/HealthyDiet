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
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.RecommendActivity;
import com.huihong.healthydiet.adapter.RvRecommendRecommendAdapter;
import com.huihong.healthydiet.bean.RecipeListByGPS;
import com.huihong.healthydiet.mInterface.ScreenTypeListener;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/12
 */

public class RecommendRecipeListFragment extends Fragment {

    private View mView;
    private int num = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_recommend_recipe_list, null);
            initUI();
        }

        return mView;
    }

    private void initUI() {
        initRecyclerView();


        RecommendActivity.mRecommendActivity.setRightScreenTypeListener(new ScreenTypeListener() {
            @Override
            public void screenType(boolean isRight, String type, int typeId, boolean isSwitch) {
                if (isRight) {
                    if (!isSwitch) {
                        num = 1;
                        recommendList.clear();
                        mLRecyclerViewAdapter.notifyDataSetChanged();
                    }

                    GroupBy = type;
                    TypeValue = typeId + "";

//                    getInfo(num);
                }
            }
        });

    }


    //列表参数
    private LRecyclerView recyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RvRecommendRecommendAdapter mRvRecommendAdapter;
    private List<RecipeListByGPS.ListDataBean> recommendList;

    private void initRecyclerView() {

        recommendList = new ArrayList<>();
        recyclerView = (LRecyclerView) mView.findViewById(R.id.recyclerView);
        mRvRecommendAdapter = new RvRecommendRecommendAdapter(getActivity(), recommendList);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mRvRecommendAdapter);
        recyclerView.setAdapter(mLRecyclerViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                num = 1;
                recommendList.clear();
                mLRecyclerViewAdapter.notifyDataSetChanged();
                getInfo(num);
            }
        });

        //加载更多
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getInfo(num);
            }
        });
        recyclerView.refresh();


    }

    private String GroupBy = "";
    private String TypeValue = "";

    //获取餐厅列表信息
    private void getInfo(int num) {

//
        OkHttpUtils
                .post()
                .url(AppUrl.RECIPE_LIST_BY_GPS)
                .addParams("CoordX", "120.132566")//用户坐标
                .addParams("CoordY", "30.267515")
                .addParams("GroupBy", GroupBy)//筛选方式
                .addParams("PageNo", num + "")//页数
                .addParams("TypeValue", TypeValue)
                .addParams("UserId", "2")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        recyclerView.refreshComplete(1);
                        LogUtil.i("error" + e);
                        Toast.makeText(getActivity(), R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，推荐饮食列表:", response);
                        recyclerView.refreshComplete(1);
                        Gson gson = new Gson();
                        RecipeListByGPS mRecipeListByGPS = gson.fromJson(response, RecipeListByGPS.class);
                        int code = mRecipeListByGPS.getHttpCode();
                        if (code == 200) {
                            RecommendRecipeListFragment.this.num++;
                            List<RecipeListByGPS.ListDataBean> mListData = mRecipeListByGPS.getListData();//拿到餐厅列表
//                            recommendList.clear();
                            recommendList.addAll(mListData);
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        } else if (code == 300) {
                            Toast.makeText(getActivity(), R.string.no_more_date, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
