package com.huihong.healthydiet.fragment.like;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.MyApplication;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.adapter.RvRestaurantLikeAdapter;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.model.gsonbean.UserPreferenceRest;
import com.huihong.healthydiet.model.httpmodel.RestaurantInfo;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/20
 * 个人偏好喜欢
 */

public class RestaurantLikeFragment extends Fragment {

    //    private SwipeMenuAdapter mDataAdapter = null;
    private View mView;
    private LRecyclerView mLRecyclerView;
    private RvRestaurantLikeAdapter mRvLikeAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private List<RestaurantInfo> recommendList;

    private LinearLayout layoutNoData;
    private int num=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_islike, null);
            mLRecyclerView = (LRecyclerView) mView.findViewById(R.id.mLRecyclerView);
            layoutNoData = (LinearLayout) mView.findViewById(R.id.layoutNoData);
            mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recommendList = new ArrayList<>();
            mRvLikeAdapter = new RvRestaurantLikeAdapter( getActivity(), recommendList);
            mLRecyclerViewAdapter = new LRecyclerViewAdapter(mRvLikeAdapter);
            mLRecyclerView.setAdapter(mLRecyclerViewAdapter);


            mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    num=1;
                    recommendList.clear();
                    mLRecyclerViewAdapter.notifyDataSetChanged();
                    getInfo(num);
                }
            });
            mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {

                }
            });

            mLRecyclerView.refresh();
//            getInfo("foodlike");
        }


        return mView;
    }

    private void getInfo(int pageNum) {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");
        map.put("CoordY", MyApplication.Latitude+"");
        map.put("CoordX", MyApplication.Longitude+"");
        map.put("pageNum", pageNum+"");
        HttpUtils.sendHttpAddToken(getActivity()
                , AppUrl.USER_PREFERENCE_REST
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        mLRecyclerView.refreshComplete(1);
                        LogUtil.i("餐厅喜不喜欢", response);
                        Gson gson = new Gson();
                        UserPreferenceRest mUserPreferenceRest = gson.fromJson(response, UserPreferenceRest.class);
                        int code = mUserPreferenceRest.getHttpCode();
                        if (code == 200) {
                            num++;
                            layoutNoData.setVisibility(View.GONE);
                            recommendList.clear();
                            List<RestaurantInfo> mListData = mUserPreferenceRest.getListData();
                            recommendList.addAll(mListData);
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            String message = mUserPreferenceRest.getMessage();
                            layoutNoData.setVisibility(View.VISIBLE);
//                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mLRecyclerView.refreshComplete(1);
                        LogUtil.i("喜不喜欢", e.toString());
                        layoutNoData.setVisibility(View.VISIBLE);
                    }
                });

    }
}
