package com.huihong.healthydiet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity2;
import com.huihong.healthydiet.adapter.RvRecordListAdapter;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.model.gsonbean.OrderList;
import com.huihong.healthydiet.model.httpmodel.OrderDetailsInfo;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.huihong.healthydiet.widget.expand.LGlideRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/8/11
 * 饮食记录
 */

public class DietRecordActivity extends BaseTitleActivity2 {
    @BindView(R.id.rvDietRecord)
    LGlideRecyclerView rvDietRecord;
    @BindView(R.id.layoutNoMoreData)
    LinearLayout layoutNoMoreData;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private List<OrderDetailsInfo> mList;

    private int  num=1;

    @Override
    public int setLayoutId() {
        return R.layout.activity_diet_record;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("饮食记录");
        mList=new ArrayList<>();


        RvRecordListAdapter mRvRecordAdapter=new RvRecordListAdapter(getContext(),mList);
        mLRecyclerViewAdapter=new LRecyclerViewAdapter(mRvRecordAdapter);
        rvDietRecord.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvDietRecord.setAdapter(mLRecyclerViewAdapter);

        rvDietRecord.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getInfo(num,false);
            }
        });

        rvDietRecord.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                num=1;
                getInfo(1,true);
            }
        });

        rvDietRecord.refresh();
    }

    private void getInfo(int i, final boolean needClear) {
        Map<String, String> map = new HashMap<>();
        map.put("PageNo",i+"");
        map.put("UserId",  SPUtils.get(getContext(),"UserId",0)+"");

        HttpUtils.sendHttpAddToken(getContext(), AppUrl.ORDER_LIST
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        rvDietRecord.refreshComplete(1);
                        LogUtil.i("饮食记录列表",response);
                        Gson gson = new Gson();
                        OrderList mOrderList = gson.fromJson(response, OrderList.class);
                        if(mOrderList.getHttpCode()==200){
                            num++;
                            if(needClear){
                                mList.clear();
                            }
                            mList.addAll(mOrderList.getListData());
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        }

                        if(mList.size()==0){
                            layoutNoMoreData.setVisibility(View.VISIBLE);
                        }else {
                            layoutNoMoreData.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("饮食记录列表",e.toString());
                    }
                });



    }


//    @Override
//    public void finishActivity() {
////        super.finishActivity();
//        ActivityCompat.finishAfterTransition(this);
//    }
}
