package com.huihong.healthydiet.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.MyApplication;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.SearchResultActivity;
import com.huihong.healthydiet.adapter.RvSearchResultRightAdapter;
import com.huihong.healthydiet.bean.SearchVagueRestaurant;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.model.ArticleInfo;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/12
 */

public class SearchResultRightFragment extends Fragment {

    private View mView;

    //列表加载页数
    private int num = 1;

    private String searchText = "";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchText = SearchResultActivity.searchText;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_recommend_nearby_list, null);
            initUI();
        }

        return mView;
    }

    private void initUI() {
        initRecyclerView();
    }


    //列表
    private LRecyclerView recyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    private List<ArticleInfo> mListData2;
    private List<SearchVagueRestaurant.ListData3Bean> mListData3;
    private void initRecyclerView() {

        mListData2 = new ArrayList<>();
        mListData3 = new ArrayList<>();
        recyclerView = (LRecyclerView) mView.findViewById(R.id.recyclerView);
        RvSearchResultRightAdapter mRvRecommendAdapter = new RvSearchResultRightAdapter(getActivity(), mListData2,mListData3);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mRvRecommendAdapter);
        recyclerView.setAdapter(mLRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                num = 1;
                mLRecyclerViewAdapter.notifyDataSetChanged();
                getInfo();
            }
        });

        recyclerView.refresh();
    }


    //获取餐厅列表信息
    private void getInfo() {

        Map<String, String> map = new HashMap<>();
        map.put("CoordY", MyApplication.Latitude + "");
        map.put("CoordX", MyApplication.Longitude + "");
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");
        map.put("SearchType", "Recipe");
        map.put("PageNo", "1");
        map.put("Name", searchText);
        HttpUtils.sendHttpAddToken(getActivity()
                , AppUrl.SEARCH_VAGUE_RESTAURANT
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        recyclerView.refreshComplete(1);
                        LogUtil.i("搜索餐厅", response);
                        Gson gson = new Gson();
                        SearchVagueRestaurant mSearchVagueRestaurant = gson.fromJson(response, SearchVagueRestaurant.class);

                        if (mSearchVagueRestaurant.getHttpCode() == 200) {

                            mListData2.clear();
                            mListData2.addAll(mSearchVagueRestaurant.getListData2());//餐厅列表

                            mListData3.clear();
                            mListData3.addAll(mSearchVagueRestaurant.getListData3());//餐厅列表
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("搜索餐厅", e.toString());
                        recyclerView.refreshComplete(1);
                    }
                });

    }
}
