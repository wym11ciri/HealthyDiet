package com.huihong.healthydiet.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity2;
import com.huihong.healthydiet.adapter.StickyTestAdapter;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.model.gsonbean.GetScoreList;
import com.huihong.healthydiet.model.mybean.IntegralDate;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.huihong.healthydiet.widget.StickyHeaderDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;




/**
 * Created by zangyi_shuai_ge on 2017/7/27
 * 积分记录界面
 */

public class IntegralRecordActivity extends BaseTitleActivity2 {
    @BindView(R.id.rvIntegralRecord)
    LRecyclerView rvIntegralRecord;
    @BindView(R.id.layoutNoMoreData)
    LinearLayout layoutNoMoreData;

    private List<IntegralDate> mIntegralDateList;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("积分记录");
        mIntegralDateList = new ArrayList<>();
        DividerDecoration divider = new DividerDecoration.Builder(IntegralRecordActivity.this)
                .setHeight(R.dimen.default_divider_padding)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.black)
                .build();
        rvIntegralRecord.setHasFixedSize(true);
        rvIntegralRecord.setLayoutManager(new LinearLayoutManager(IntegralRecordActivity.this));
        rvIntegralRecord.addItemDecoration(divider);

        StickyTestAdapter adapter = new StickyTestAdapter(IntegralRecordActivity.this, mIntegralDateList);
        StickyHeaderDecoration decor = new StickyHeaderDecoration(adapter);//这个类是用来设置头布局的
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        rvIntegralRecord.setAdapter(mLRecyclerViewAdapter);
        rvIntegralRecord.addItemDecoration(decor, 1);//相当于是把头部局作为分割线放入


        rvIntegralRecord.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getIntegralRecord();
            }
        });

        rvIntegralRecord.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                num = 1;
                mIntegralDateList.clear();
                mLRecyclerViewAdapter.notifyDataSetChanged();
                getIntegralRecord();
            }
        });
        rvIntegralRecord.refresh();

//        getIntegralRecord(num);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_integral_record;
    }


    //获取积分列表
    private void getIntegralRecord() {

        Map<String, String> map = new HashMap<>();
        map.put("PageNo", num + "");
        map.put("UserId", SPUtils.get(IntegralRecordActivity.this, "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(IntegralRecordActivity.this, AppUrl.GET_SCORE_LIST
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        rvIntegralRecord.refreshComplete(1);
                        LogUtil.i("积分列表", response);
                        Gson gson = new Gson();
                        GetScoreList mGetScoreList = gson.fromJson(response, GetScoreList.class);

                        if (mGetScoreList.getHttpCode() == 200) {
                            num++;
                            for (int i = 0; i < mGetScoreList.getListData().size(); i++) {
                                IntegralDate mIntegralDate = new IntegralDate();
                                mIntegralDate.setDate(mGetScoreList.getListData().get(i).getTime());
                                mIntegralDate.setEvent(mGetScoreList.getListData().get(i).getContent());
                                mIntegralDate.setIconAdd(mGetScoreList.getListData().get(i).getScoreType());
                                mIntegralDate.setNum(mGetScoreList.getListData().get(i).getScoreNum());
                                mIntegralDateList.add(mIntegralDate);
                            }
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        }
                        if (mIntegralDateList.size() == 0) {
                            layoutNoMoreData.setVisibility(View.VISIBLE);
                        } else {
                            layoutNoMoreData.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("积分列表", e.toString());
                    }
                });


    }

//    @Override
//    public void initUI() {

//        setTitle("积分记录");
//
//
//        rvIntegralRecord = (LRecyclerView) findViewById(R.id.rvIntegralRecord);
//        DividerDecoration divider = new DividerDecoration.Builder(IntegralRecordActivity.this)
//                .setHeight(R.dimen.default_divider_padding)
//                .setPadding(R.dimen.default_divider_padding)
//                .setColorResource(R.color.black)
//                .build();
//
//        rvIntegralRecord.setHasFixedSize(true);
//        rvIntegralRecord.setLayoutManager(new LinearLayoutManager(IntegralRecordActivity.this));
//        rvIntegralRecord.addItemDecoration(divider);
//
//

//

//
//


//    }

//    @Override
//    public void initOnClickListener() {
//
//    }
}
