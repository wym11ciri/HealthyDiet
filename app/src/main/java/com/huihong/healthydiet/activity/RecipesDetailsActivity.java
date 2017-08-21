package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eicky.ViewPagerGallery;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RvMaterialAdapter;
import com.huihong.healthydiet.adapter.RvTagAdapter;
import com.huihong.healthydiet.adapter.RvTypeAdapter3;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.model.gsonbean.MaterialInfo;
import com.huihong.healthydiet.model.gsonbean.RecipeItemInfo;
import com.huihong.healthydiet.utils.MyUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.huihong.healthydiet.widget.PageIndicator;
import com.joooonho.SelectableRoundedImageView;
import com.zuoni.dialog.picker.dialog.LoadingDialog;

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

    private boolean   isFromRest;


    private RecyclerView rvMaterial;
    private RvMaterialAdapter mRvMaterialAdapter;
    private List<String> materialList;

    private String RecipeId = "";


    private TextView tvConstitutionPercentage;

    private LoadingDialog loadingDialog;
    private LinearLayout layoutGoRest;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_recipes_details;
    }

    private  int mId;
    @Override
    public void initUI() {
        LoadingDialog.Builder builder =new LoadingDialog.Builder(getContext());
        builder.setMessage("载入中...");
        loadingDialog=builder.create();
        loadingDialog.show();
        layoutGoRest= (LinearLayout) findViewById(R.id.layoutGoRest);

        tvConstitutionPercentage = (TextView) findViewById(R.id.tvConstitutionPercentage);
        RecipeId = getIntent().getStringExtra("RecipeId");
        isFromRest=getIntent().getBooleanExtra("isFromRest",false);


        if (!RecipeId.equals("")) {
            //从接口读取信息
            getInfo();

        }

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
                        loadingDialog.dismiss();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loadingDialog.dismiss();
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
                                                .error(R.mipmap.error_photo)
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
                                MyUtils.setTextViewColor(tvConstitutionPercentage,percentage,getContext());
                                tvConstitutionPercentage.setText(percentage + "%");

                                TextView tvPrice= (TextView) findViewById(R.id.tvPrice);
                                tvPrice.setText("￥"+mListDataBean.getPrice());

                               mId=mListDataBean.getRestaurantId();

                                layoutGoRest.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(isFromRest){
                                            finish();
                                        }else {
                                            Intent mIntent=new Intent(getContext(),RestaurantDetailsActivity.class);
                                            mIntent.putExtra("id",mId);
                                            startActivity(mIntent);
                                        }

                                    }
                                });

                            }
                        }
                    }
                });
    }

    @Override
    public void initOnClickListener() {

    }


}
