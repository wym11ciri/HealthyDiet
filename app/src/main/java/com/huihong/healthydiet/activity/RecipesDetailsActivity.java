package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eicky.ViewPagerGallery;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RvMaterialAdapter;
import com.huihong.healthydiet.adapter.RvTagAdapter;
import com.huihong.healthydiet.adapter.RvTypeAdapter3;
import com.huihong.healthydiet.bean.MaterialInfo;
import com.huihong.healthydiet.bean.RecipeItemInfo;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.huihong.healthydiet.widget.PageIndicator;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/13
 * 食谱详情界面
 */

public class RecipesDetailsActivity extends BaseTitleActivity {

    //画廊展示
    private ViewPagerGallery gallery;
    private PageIndicator mPageIndicator;


    private RecyclerView rvMaterial;
    private RvMaterialAdapter mRvMaterialAdapter;
    private List<String> materialList;

    private String RecipeId = "";


    private TextView tvConstitutionPercentage;

    @Override
    public int setLayoutId() {
        return R.layout.activity_recipes_details;
    }

    @Override
    public void initUI() {


        setTitle("鸡蛋蔬菜沙拉");
        tvConstitutionPercentage = (TextView) findViewById(R.id.tvConstitutionPercentage);
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
                mIn.putExtra("RecipeId", RecipeId);
                startActivity(mIn);
            }
        });


    }

    private void getInfo() {

        final Map<String, String> map = new HashMap<>();
        map.put("RecipeId", RecipeId);
        map.put("UserId", SPUtils.get(RecipesDetailsActivity.this, "UserId", 0) + "");
        HttpUtils.sendHttpAddToken(RecipesDetailsActivity.this
                , AppUrl.RECIPE_ITEM_INFO
                , map
                , new HttpUtilsListener() {
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
                                List<String> mImages = mListDataBean.getImages();
                                if (mImages.size() > 0) {
                                    List<View> viewList = new ArrayList<>();

                                    for (int i = 0; i < mImages.size(); i++) {
                                        SelectableRoundedImageView mSelectableRoundedImageView = new SelectableRoundedImageView(RecipesDetailsActivity.this);
                                        mSelectableRoundedImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                        mSelectableRoundedImageView.setBorderWidthDP(2);
                                        mSelectableRoundedImageView.setBorderColor(getResources().getColor(R.color.photo_ground));
                                        mSelectableRoundedImageView.setCornerRadiiDP(4, 4, 4, 4);
                                        Glide
                                                .with(RecipesDetailsActivity.this)
                                                .load(mListDataBean.getImages().get(i))
                                                .asBitmap()
                                                .into(mSelectableRoundedImageView);
                                        viewList.add(mSelectableRoundedImageView);
                                    }
                                    gallery.setImgResources(viewList);
                                }


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
                                        materialInfo.setId(getListFood.get(j).getFoodId());
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

                                //获类型列表
                                RecyclerView rvType = (RecyclerView) findViewById(R.id.rvType);
                                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(RecipesDetailsActivity.this);
                                linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                                rvType.setLayoutManager(linearLayoutManager2);
                                rvType.setAdapter(new RvTypeAdapter3(RecipesDetailsActivity.this, mListDataBean.getConstitution()));
                                //设置其他

                                TextView tvNAme= (TextView) findViewById(R.id.tvName);
                                tvNAme.setText(mListDataBean.getName());
//                                tvConstitutionPercentage.setText(mListDataBean.getConstitutionPercentage()+"%");
                                int percentage = mListDataBean.getConstitutionPercentage();

                                if (percentage > 90) {
                                    tvConstitutionPercentage.setTextColor(getResources().getColor(R.color.percentage_color_9));
                                } else if (percentage > 80 & percentage <= 90) {
                                    tvConstitutionPercentage.setTextColor(getResources().getColor(R.color.percentage_color_8));
                                } else if (percentage > 70 & percentage <= 80) {
                                    tvConstitutionPercentage.setTextColor(getResources().getColor(R.color.percentage_color_7));
                                } else if (percentage > 60 & percentage <= 70) {
                                    tvConstitutionPercentage.setTextColor(getResources().getColor(R.color.percentage_color_6));
                                } else {
                                    tvConstitutionPercentage.setTextColor(getResources().getColor(R.color.percentage_color_5));
                                }
                                tvConstitutionPercentage.setText(percentage + "%");

                            }
                        }
                    }
                });


//        OkHttpUtils
//                .post()
//                .url(AppUrl.RECIPE_ITEM_INFO)
//                .addParams("RecipeId", RecipeId)
//                .addParams("UserId", "2")
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        LogUtil.i("接口，菜谱详情", e + "");
//                        Toast.makeText(RecipesDetailsActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        LogUtil.i("接口，菜谱详情", response + "");
//                        Gson gson = new Gson();
//                        RecipeItemInfo mRecipeItemInfo = gson.fromJson(response, RecipeItemInfo.class);
//                        int code = mRecipeItemInfo.getHttpCode();
//                        if (code == 200) {
//                            List<RecipeItemInfo.ListDataBean> mListData = mRecipeItemInfo.getListData();
//                            if (mListData.size() > 0) {
//                                RecipeItemInfo.ListDataBean mListDataBean = mListData.get(0);
//
//                                //设置画廊
//                                gallery = (ViewPagerGallery) findViewById(R.id.gallery);
//                                List<String> mImages = mListDataBean.getImages();
//                                if (mImages.size() > 0) {
//                                    List<View> viewList = new ArrayList<>();
//
//                                    for (int i = 0; i < mImages.size(); i++) {
//                                        SelectableRoundedImageView mSelectableRoundedImageView = new SelectableRoundedImageView(RecipesDetailsActivity.this);
//                                        mSelectableRoundedImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                                        mSelectableRoundedImageView.setBorderWidthDP(2);
//                                        mSelectableRoundedImageView.setBorderColor(getResources().getColor(R.color.photo_ground));
//                                        mSelectableRoundedImageView.setCornerRadiiDP(4, 4, 4, 4);
//                                        Glide
//                                                .with(RecipesDetailsActivity.this)
//                                                .load(mListDataBean.getImages().get(i))
//                                                .asBitmap()
//                                                .into(mSelectableRoundedImageView);
//                                        viewList.add(mSelectableRoundedImageView);
//                                    }
//                                    gallery.setImgResources(viewList);
//                                }
//
//
//                                //画廊下面的指示器
//                                mPageIndicator = (com.huihong.healthydiet.widget.PageIndicator) findViewById(R.id.PageIndicator);
//                                mPageIndicator.setViewPager(gallery);//给ViewPager设置指示器
//                                mPageIndicator.setIndicatorType(PageIndicator.IndicatorType.CIRCLE);
//                                //设置标题
//                                setTitle(mListDataBean.getName() + "");
//                                //获取材料列表
//                                List<MaterialInfo> MaterialInfoList = new ArrayList<>();
//                                List<RecipeItemInfo.ListDataBean.FoodRecipeBean> mFoodRecipe = mListDataBean.getFoodRecipe();
//                                for (int i = 0; i < mFoodRecipe.size(); i++) {
//                                    String RecipeItemName = mFoodRecipe.get(i).getRecipeItemName();//获取名称
//                                    List<RecipeItemInfo.ListDataBean.FoodRecipeBean.ListFoodBean> getListFood = mFoodRecipe.get(i).getListFood();
//                                    for (int j = 0; j < getListFood.size(); j++) {
//                                        MaterialInfo materialInfo = new MaterialInfo();
//                                        if (j == 0) {
//                                            materialInfo.setRecipeItemName(RecipeItemName);
//                                        } else {
//                                            materialInfo.setRecipeItemName("");
//                                        }
//                                        materialInfo.setFoodInfo(getListFood.get(j).getFoodName() + getListFood.get(j).getFoodWeight());
//                                        materialInfo.setWhetherLike(getListFood.get(j).getWhetherLike());
//                                        MaterialInfoList.add(materialInfo);
//                                    }
//                                }
//
//                                rvMaterial = (RecyclerView) findViewById(R.id.rvMaterial);
//                                mRvMaterialAdapter = new RvMaterialAdapter(RecipesDetailsActivity.this, MaterialInfoList);
//                                rvMaterial.setLayoutManager(new LinearLayoutManager(RecipesDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
//                                rvMaterial.setAdapter(mRvMaterialAdapter);
//
//                                //设置Tag列表
//                                List<String> mTags = mListDataBean.getTags();
//                                RecyclerView rvArticleTag = (RecyclerView) findViewById(R.id.rvArticleTag);
//                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecipesDetailsActivity.this);
//                                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//                                rvArticleTag.setLayoutManager(linearLayoutManager);
//                                rvArticleTag.setAdapter(new RvTagAdapter(RecipesDetailsActivity.this, mTags));
//
//                                //获类型列表
//                                RecyclerView rvType = (RecyclerView) findViewById(R.id.rvType);
//                                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(RecipesDetailsActivity.this);
//                                linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
//                                rvType.setLayoutManager(linearLayoutManager2);
//                                rvType.setAdapter(new RvTypeAdapter3(RecipesDetailsActivity.this, mListDataBean.getConstitution()));
//
//                            }
//                        }
//                    }
//                });

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
