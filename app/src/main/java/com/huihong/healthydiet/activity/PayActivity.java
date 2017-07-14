package com.huihong.healthydiet.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RvPhotoAdapter;
import com.huihong.healthydiet.adapter.RvTagAdapter;
import com.huihong.healthydiet.adapter.RvTypeAdapter2;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by zangyi_shuai_ge on 2017/7/13
 */

public class PayActivity extends BaseTitleActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void initUI() {


        setTitle("支付");


        List<String> zz=new ArrayList<>();
        for (int i = 0; i <6 ; i++) {
            zz.add("红烧啊");
        }




        RecyclerView rvDetailPhoto= (RecyclerView) findViewById(R.id.rvDetailPhoto);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PayActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDetailPhoto. setLayoutManager(linearLayoutManager);
        rvDetailPhoto.setAdapter(new RvPhotoAdapter(PayActivity.this,zz));



//        RecyclerView rvArticleTag = (RecyclerView) findViewById(R.id.rvArticleTag);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecipesDetailsActivity.this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rvArticleTag.setLayoutManager(linearLayoutManager);
//        rvArticleTag.setAdapter(new RvTagAdapter(RecipesDetailsActivity.this, zz));

//        RecyclerView rvTag = (RecyclerView) findViewById(R.id.rvArticleTag);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecipesDetailsActivity.this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rvTag.setLayoutManager(linearLayoutManager);
//        rvTag.setAdapter(new RvTypeAdapter(PayActivity.this, zz));



        RecyclerView rvArticleTag = (RecyclerView) findViewById(R.id.rvArticleTag);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(PayActivity.this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvArticleTag.setLayoutManager(linearLayoutManager2);
        rvArticleTag.setAdapter(new RvTagAdapter(PayActivity.this, zz));


        List<String> zz2=new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            zz2.add("红烧啊");
        }
        LinearLayoutManager linearLayoutManager3= new LinearLayoutManager(PayActivity.this);
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView rvArticleType = (RecyclerView) findViewById(R.id.rvType);
        rvArticleType.setLayoutManager(linearLayoutManager3);
        rvArticleType.setAdapter(new RvTypeAdapter2(PayActivity.this, zz2));

    }

    @Override
    public void initOnClickListener() {

    }
}
