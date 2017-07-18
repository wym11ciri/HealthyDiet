package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.eicky.ViewPagerGallery;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RvMaterialAdapter;
import com.huihong.healthydiet.adapter.RvTagAdapter;
import com.huihong.healthydiet.bean.MaterialInfo;
import com.huihong.healthydiet.bean.RecipeItemInfo;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.widget.PageIndicator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/13
 */

public class RecipesDetailsActivity extends BaseTitleActivity {

    //画廊展示
    private ViewPagerGallery gallery;
    private PageIndicator mPageIndicator;


    private RecyclerView rvMaterial;
    private RvMaterialAdapter mRvMaterialAdapter;
    private List<String> materialList;

    private String RecipeId = "";

    @Override
    public int setLayoutId() {
        return R.layout.activity_recipes_details;
    }

    @Override
    public void initUI() {


        setTitle("鸡蛋蔬菜沙拉");
        RecipeId = getIntent().getStringExtra("RecipeId");
        if (!RecipeId.equals("")) {
            //从接口读取信息
            getInfo();

        }


        initMaterial();





        ImageView ivBuy = (ImageView) findViewById(R.id.ivBuy);
        ivBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIn = new Intent(RecipesDetailsActivity.this, PayActivity.class);
                startActivity(mIn);
            }
        });
//        RecyclerView rvTag = (RecyclerView) findViewById(rvArticleTag);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecipesDetailsActivity.this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rvTag.setLayoutManager(linearLayoutManager);
//        rvTag.setAdapter(new RvTypeAdapter(RecipesDetailsActivity.this, zz));


    }

    private void getInfo() {

        OkHttpUtils
                .post()
                .url(AppUrl.RECIPE_ITEM_INFO)
                .addParams("RecipeId", RecipeId)//用户坐标
                .addParams("UserId", "2")//用户坐标
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("接口，菜谱详情", e + "");
                        Toast.makeText(RecipesDetailsActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，菜谱详情", response + "");
                        Gson gson = new Gson();
                        RecipeItemInfo mRecipeItemInfo = gson.fromJson(response, RecipeItemInfo.class);
                        int code = mRecipeItemInfo.getHttpCode();
                        if (code == 200) {
                            List<RecipeItemInfo.ListDataBean> mListData = mRecipeItemInfo.getListData();
                            if (mListData.size() > 0) {
                                RecipeItemInfo.ListDataBean mListDataBean = mListData.get(0);

                                //设置画廊
                                gallery = (ViewPagerGallery) findViewById(R.id.gallery);
                                gallery.setImgResources(mListDataBean.getImages());
                                //画廊下面的指示器
                                mPageIndicator = (com.huihong.healthydiet.widget.PageIndicator) findViewById(R.id.PageIndicator);
                                mPageIndicator.setViewPager(gallery);//给ViewPager设置指示器
                                mPageIndicator.setIndicatorType(PageIndicator.IndicatorType.CIRCLE);
                                //设置标题
                                setTitle(mListDataBean.getName() + "");
                                //获取材料列表
                                List<MaterialInfo> MaterialInfoList = new ArrayList<>();
                                List<RecipeItemInfo.ListDataBean.FoodRecipeBean> mFoodRecipe = mListDataBean.getFoodRecipe();
                                for (int i = 0; i < mFoodRecipe.size(); i++) {
                                    String RecipeItemName = mFoodRecipe.get(i).getRecipeItemName();//获取名称
                                    List<RecipeItemInfo.ListDataBean.FoodRecipeBean.ListFoodBean> getListFood = mFoodRecipe.get(i).getListFood();
                                    for (int j = 0; j < getListFood.size(); j++) {
                                        MaterialInfo materialInfo = new MaterialInfo();
                                        if (j == 0) {
                                            materialInfo.setRecipeItemName(RecipeItemName);
                                        } else {
                                            materialInfo.setRecipeItemName("");
                                        }
                                        materialInfo.setFoodInfo(getListFood.get(j).getFoodName() + getListFood.get(j).getFoodWeight());
                                        materialInfo.setWhetherLike(getListFood.get(j).getWhetherLike());
                                        MaterialInfoList.add(materialInfo);
                                    }
                                }

                                rvMaterial = (RecyclerView) findViewById(R.id.rvMaterial);
                                mRvMaterialAdapter = new RvMaterialAdapter(RecipesDetailsActivity.this, MaterialInfoList);
                                rvMaterial.setLayoutManager(new LinearLayoutManager(RecipesDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
                                rvMaterial.setAdapter(mRvMaterialAdapter);

                                //设置Tag列表
                                List<String> mTags = mListDataBean.getTags();
                                RecyclerView rvArticleTag = (RecyclerView) findViewById(R.id.rvArticleTag);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecipesDetailsActivity.this);
                                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                rvArticleTag.setLayoutManager(linearLayoutManager);
                                rvArticleTag.setAdapter(new RvTagAdapter(RecipesDetailsActivity.this, mTags));

                            }
                        }
                    }
                });

    }

    private void initMaterial() {

//        mLvMaterialAdapter=new LvMaterialAdapter(RecipesDetailsActivity.this,materialList);
//        rvMaterial.setAdapter(mLvMaterialAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void initOnClickListener() {

    }


}
