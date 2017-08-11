package com.huihong.healthydiet.fragment.like;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.adapter.RvLikeAdapter;
import com.huihong.healthydiet.model.gsonbean.SelectUserPreference;
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
 * Created by zangyi_shuai_ge on 2017/7/20
 * 个人偏好不喜欢
 */

public class UnLikeFragment extends Fragment {

    //    private SwipeMenuAdapter mDataAdapter = null;
    private View mView;
    private LRecyclerView mLRecyclerView;
    private RvLikeAdapter mRvLikeAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private List<SelectUserPreference.ListDataBean> recommendList;

    private LinearLayout layoutNoData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {


            mView = inflater.inflate(R.layout.fragment_islike, null);
            layoutNoData = (LinearLayout) mView.findViewById(R.id.layoutNoData);

            mLRecyclerView = (LRecyclerView) mView.findViewById(R.id.mLRecyclerView);

            mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recommendList = new ArrayList<>();
            mRvLikeAdapter = new RvLikeAdapter("foodunlike", getActivity(), recommendList);
            mLRecyclerViewAdapter = new LRecyclerViewAdapter(mRvLikeAdapter);
            mLRecyclerView.setAdapter(mLRecyclerViewAdapter);


            mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    getInfo("foodunlike");
                }
            });
            mLRecyclerView.refresh();
        }


        return mView;
    }

    private void getInfo(String type) {
        Map<String, String> map = new HashMap<>();
        map.put("Type_Like", type);
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(getActivity()
                , AppUrl.SELECT_USER_PREFERENCE
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        mLRecyclerView.refreshComplete(1);
                        LogUtil.i("喜不喜欢", response);
                        Gson gson = new Gson();
                        SelectUserPreference mSelectUserPreference = gson.fromJson(response, SelectUserPreference.class);
                        int code = mSelectUserPreference.getHttpCode();
                        if (code == 200) {
                            layoutNoData.setVisibility(View.GONE);
                            recommendList.clear();
                            List<SelectUserPreference.ListDataBean> mListData = mSelectUserPreference.getListData();
                            recommendList.addAll(mListData);
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            String message = mSelectUserPreference.getMessage();
                            layoutNoData.setVisibility(View.VISIBLE);
//                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mLRecyclerView.refreshComplete(1);
                        layoutNoData.setVisibility(View.VISIBLE);
                        LogUtil.i("喜不喜欢", e.toString());
                    }
                });

    }
}
