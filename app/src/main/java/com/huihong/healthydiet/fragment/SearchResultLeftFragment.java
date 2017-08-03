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
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.MyApplication;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.SearchResultActivity;
import com.huihong.healthydiet.adapter.RvRecommendNearbyAdapter;
import com.huihong.healthydiet.bean.RestaurantList;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
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
 * 附近餐厅界面
 */

public class SearchResultLeftFragment extends Fragment {
    private View mView;

    //列表加载页数
    private int num = 1;

    private String searchText="";


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
    private RvRecommendNearbyAdapter mRvRecommendAdapter;
    private List<RestaurantList.ListDataBean> recommendList;

    private void initRecyclerView() {

        recommendList = new ArrayList<>();
        recyclerView = (LRecyclerView) mView.findViewById(R.id.recyclerView);
        mRvRecommendAdapter = new RvRecommendNearbyAdapter(getActivity(), recommendList);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mRvRecommendAdapter);
        recyclerView.setAdapter(mLRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                num = 1;
                recommendList.clear();
                mLRecyclerViewAdapter.notifyDataSetChanged();
                getInfo();
            }
        });

        //加载更多
//        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                getInfo();
//            }
//        });
        recyclerView.refresh();
    }


    //获取餐厅列表信息
    private void getInfo() {

        Map<String, String> map = new HashMap<>();
        map.put("CoordY", MyApplication.Latitude + "");
        map.put("CoordX", MyApplication.Longitude + "");
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");
        map.put("SearchType","Restaurant");
        map.put("PageNo", "1");
        map.put("Name",searchText);
        HttpUtils.sendHttpAddToken(getActivity()
                , AppUrl.SEARCH_VAGUE_RESTAURANT
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("搜索餐厅", response);

                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("搜索餐厅", e.toString());
                    }
                });

//
//        OkHttpUtils
//                .post()
//                .url(AppUrl.GET_RESTAURANT_LIST_INFO)
//                .addParams("CoordX", "120.132566")//用户坐标
//                .addParams("CoordY", "30.267515")
//                .addParams("PageNo", num + "")//页数
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        recyclerView.refreshComplete(1);
//                        LogUtil.i("error" + e);
//                        Toast.makeText(getActivity(), R.string.service_error, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        LogUtil.i("接口，餐厅列表:", response);
//                        recyclerView.refreshComplete(1);
//                        Gson gson = new Gson();
//                        RestaurantList RestaurantList = gson.fromJson(response, RestaurantList.class);
//                        int code = RestaurantList.getHttpCode();
//                        if (code == 200) {
//                            SearchResultLeftFragment.this.num++;
//                            List<com.huihong.healthydiet.bean.RestaurantList.ListDataBean> mListData = RestaurantList.getListData();//拿到餐厅列表
////                            recommendList.clear();
//                            recommendList.addAll(mListData);
//                            mLRecyclerViewAdapter.notifyDataSetChanged();
//                        } else if (code == 300) {
//                            Toast.makeText(getActivity(), R.string.no_more_date, Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });

    }


}
