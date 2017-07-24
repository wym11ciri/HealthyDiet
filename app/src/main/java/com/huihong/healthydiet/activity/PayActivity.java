package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RvPhotoAdapter;
import com.huihong.healthydiet.adapter.RvTagAdapter;
import com.huihong.healthydiet.adapter.RvTypeAdapter2;
import com.huihong.healthydiet.bean.RecipeItemInfoForPay;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by zangyi_shuai_ge on 2017/7/13
 */

public class PayActivity extends BaseTitleActivity {

    private String RecipeId;

    @Override
    public int setLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void initUI() {


        setTitle("支付");


        RecipeId = getIntent().getStringExtra("RecipeId");
        if (!RecipeId.equals("")) {
            //从接口读取信息
            getInfo();

        }


        List<String> zz = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            zz.add("红烧啊");
        }


        RecyclerView rvDetailPhoto = (RecyclerView) findViewById(R.id.rvDetailPhoto);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PayActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDetailPhoto.setLayoutManager(linearLayoutManager);
        rvDetailPhoto.setAdapter(new RvPhotoAdapter(PayActivity.this, zz));


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


        List<String> zz2 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            zz2.add("红烧啊");
        }
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(PayActivity.this);
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView rvArticleType = (RecyclerView) findViewById(R.id.rvType);
        rvArticleType.setLayoutManager(linearLayoutManager3);
        rvArticleType.setAdapter(new RvTypeAdapter2(PayActivity.this, zz2));


        LinearLayout layoutPayOnline = (LinearLayout) findViewById(R.id.layoutPayOnline);
        layoutPayOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(PayActivity.this, PayOnlineActivity.class);
                startActivity(m);
            }
        });
    }

    private void getInfo() {

        OkHttpUtils
                .post()
                .url(AppUrl.RECIPE_ITEM_INFO_FOR_PAY)
                .addParams("RecipeId", RecipeId)
                .addParams("UserId", "2")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("接口，支付详情", e + "");
                        Toast.makeText(PayActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，支付详情", response + "");
                        Gson gson = new Gson();
                        RecipeItemInfoForPay mRecipeItemInfoForPay = gson.fromJson(response, RecipeItemInfoForPay.class);
                        int code = mRecipeItemInfoForPay.getHttpCode();
                        if (code == 200) {
                            mRecipeItemInfoForPay.getListData();


                        }

                    }
                });

    }

    @Override
    public void initOnClickListener() {

    }


}
