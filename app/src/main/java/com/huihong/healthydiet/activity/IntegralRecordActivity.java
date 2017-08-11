package com.huihong.healthydiet.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.StickyTestAdapter;
import com.huihong.healthydiet.model.mybean.IntegralDate;
import com.huihong.healthydiet.widget.StickyHeaderDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/27
 * 积分记录界面
 */

public class IntegralRecordActivity extends BaseTitleActivity {


    private LRecyclerView rvIntegralRecord;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_integral_record;
    }

    @Override
    public void initUI() {

        setTitle("积分记录");


        rvIntegralRecord = (LRecyclerView) findViewById(R.id.rvIntegralRecord);
        DividerDecoration divider = new DividerDecoration.Builder(IntegralRecordActivity.this)
                .setHeight(R.dimen.default_divider_padding)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.black)
                .build();

        rvIntegralRecord.setHasFixedSize(true);
        rvIntegralRecord.setLayoutManager(new LinearLayoutManager(IntegralRecordActivity.this));
        rvIntegralRecord.addItemDecoration(divider);


        List<IntegralDate> mIntegralDateList = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            IntegralDate da1 = new IntegralDate();
            da1.setDate("2017-12-0" + i + "T00:00:00");//12月份+6天
            mIntegralDateList.add(da1);
        }

        for (int i = 1; i < 6; i++) {
            IntegralDate da1 = new IntegralDate();
            da1.setDate("2017-11-0" + i + "T00:00:00");//12月份+6天
            mIntegralDateList.add(da1);
        }
        for (int i = 1; i < 6; i++) {
            IntegralDate da1 = new IntegralDate();
            da1.setDate("2017-10-0" + i + "T00:00:00");//12月份+6天
            mIntegralDateList.add(da1);
        }
        for (int i = 1; i < 15; i++) {
            IntegralDate da1 = new IntegralDate();
            da1.setDate("201" + "6" + "-12-0" + i + "T00:00:00");//12月份+6天
            mIntegralDateList.add(da1);
        }


        StickyTestAdapter adapter = new StickyTestAdapter(IntegralRecordActivity.this, mIntegralDateList);
        StickyHeaderDecoration decor = new StickyHeaderDecoration(adapter);//这个类是用来设置头布局的
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        rvIntegralRecord.setAdapter(mLRecyclerViewAdapter);
        rvIntegralRecord.addItemDecoration(decor, 1);//相当于是把头部局作为分割线放入

    }

    @Override
    public void initOnClickListener() {

    }
}
