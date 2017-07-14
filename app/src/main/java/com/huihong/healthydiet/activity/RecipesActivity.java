package com.huihong.healthydiet.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RvDetailRecipesAdapter;
import com.huihong.healthydiet.adapter.RvRecipesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/12
 */

public class RecipesActivity extends BaseTitleActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_recipes;
    }

    @Override
    public void initUI() {


        setTitle("餐厅详情");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        List<String> recommendList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            recommendList.add("KFC" + i);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(RecipesActivity.this, LinearLayoutManager.VERTICAL, false));
        RvRecipesAdapter mRvRecommendAdapter = new RvRecipesAdapter(RecipesActivity.this, recommendList);
        recyclerView.setAdapter(mRvRecommendAdapter);




        List<String> zz=new ArrayList<>();
        for (int i = 0; i <6 ; i++) {
            zz.add("红烧啊");
        }


        RecyclerView rvDetailPhoto= (RecyclerView) findViewById(R.id.rvDetailPhoto);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecipesActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDetailPhoto. setLayoutManager(linearLayoutManager);
        rvDetailPhoto.setAdapter(new RvDetailRecipesAdapter(RecipesActivity.this,zz));

    }

    @Override
    public void initOnClickListener() {

    }
}
