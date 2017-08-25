package com.huihong.healthydiet.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.MyApplication;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity2;
import com.huihong.healthydiet.adapter.RvOrderDetailsMaterialAdapter;
import com.huihong.healthydiet.adapter.RvTagAdapter;
import com.huihong.healthydiet.adapter.RvTypeAdapter3;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.model.gsonbean.MaterialInfo;
import com.huihong.healthydiet.model.gsonbean.OrderInfo;
import com.huihong.healthydiet.utils.MyUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.joooonho.SelectableRoundedImageView;
import com.zuoni.dialog.picker.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/8/15
 * 订单详情
 */

public class OrderDetailsActivity extends BaseTitleActivity2 {
    @BindView(R.id.tvFoodName)
    TextView tvFoodName;
    @BindView(R.id.rvType)
    RecyclerView rvType;
    @BindView(R.id.rvTag)
    RecyclerView rvTag;
    @BindView(R.id.tvConstitutionPercentage)
    TextView tvConstitutionPercentage;
    @BindView(R.id.rvMaterial)
    RecyclerView rvMaterial;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvRestaurantName)
    TextView tvRestaurantName;
    @BindView(R.id.tvDistance)
    TextView tvDistance;
    @BindView(R.id.tvRestaurantAddress)
    TextView tvRestaurantAddress;
    @BindView(R.id.ivRestaurantPhone)
    ImageView ivRestaurantPhone;
    @BindView(R.id.tvGetAgain)
    TextView tvGetAgain;
    @BindView(R.id.ivRestaurantHead)
    SelectableRoundedImageView ivRestaurantHead;
    private RvOrderDetailsMaterialAdapter mRvMaterialAdapter;

    private List<String> tagList;
    private List<String> typeList;
    private List<MaterialInfo> MaterialInfoList;

    private LoadingDialog loadingDialog;


    private String OrderId = "";

    @Override
    public int setLayoutId() {
        return R.layout.activity_order_details;
    }

    private RvTypeAdapter3 mRvTypeAdapter3;
    private RvTagAdapter mRvTagAdapter;

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("订单详情");
        OrderId = getIntent().getStringExtra("OrderId");

        getInfo();

        LoadingDialog.Builder builder = new LoadingDialog.Builder(getContext());
        builder.setMessage("载入中...");
        loadingDialog = builder.create();
        loadingDialog.show();


        //类型列表
        typeList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvType.setLayoutManager(linearLayoutManager2);
        mRvTypeAdapter3 = new RvTypeAdapter3(getContext(), typeList);
        rvType.setAdapter(mRvTypeAdapter3);

        //标签列表
        tagList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTag.setLayoutManager(linearLayoutManager);
        mRvTagAdapter = new RvTagAdapter(getContext(), tagList);
        rvTag.setAdapter(mRvTagAdapter);

        //材料列表
        MaterialInfoList = new ArrayList<>();
        mRvMaterialAdapter = new RvOrderDetailsMaterialAdapter(getContext(), MaterialInfoList);
        rvMaterial.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvMaterial.setAdapter(mRvMaterialAdapter);


    }

    private void getInfo() {

        Map<String, String> map = new HashMap<>();
        map.put("CoordY", MyApplication.Latitude + "");
        map.put("CoordX", MyApplication.Longitude + "");
        map.put("OrderId", OrderId);
        map.put("UserId", SPUtils.get(getContext(), "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(getContext(), AppUrl.ORDER_INFO
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        loadingDialog.dismiss();
                        LogUtil.i("订单详情", response);
                        Gson gson = new Gson();
                        OrderInfo mOrderInfo = gson.fromJson(response, OrderInfo.class);
                        if (mOrderInfo.getHttpCode() == 200) {
                            OrderInfo.Model1Bean mModel1Bean = mOrderInfo.getModel1();
                            if (mModel1Bean != null) {
                                tvFoodName.setText(mModel1Bean.getRecipeName());
                                RecipeId = mModel1Bean.getRecipeId() + "";
                                //类型列表
                                typeList.clear();
                                if (mModel1Bean.getConstitution() != null) {
                                    typeList.addAll(mModel1Bean.getConstitution());
                                }
                                mRvTypeAdapter3.notifyDataSetChanged();
                                //标签列表
                                tagList.clear();
                                if (mModel1Bean.getTags() != null) {
                                    tagList.addAll(mModel1Bean.getTags());
                                }
                                mRvTagAdapter.notifyDataSetChanged();

                                int percentage = mModel1Bean.getConstitutionPercentage();
                                MyUtils.setTextViewColor(tvConstitutionPercentage, percentage, getContext());
                                tvConstitutionPercentage.setText(percentage + "%");

                                tvPrice.setText("￥" + mModel1Bean.getOrderPrice());
                                String time = mModel1Bean.getOrderTime();
                                time = time.replace("T", " ");
                                tvTime.setText(time);
                                tvRestaurantName.setText(mModel1Bean.getRestaurantName());

                                //餐厅头像
                                Glide
                                        .with(getContext())
                                        .load(mModel1Bean.getRestaurantImage())
                                        .asBitmap()
                                        .error(R.mipmap.error_photo)
                                        .into(ivRestaurantHead);

                                int distance = mModel1Bean.getRestaurantDistance();
                                if (distance >= 1000) {
                                    double mDistance = distance / 1000;
                                    tvDistance.setText(mModel1Bean.getRestaurantType() + "  " + mDistance + "km");
                                } else {
                                    tvDistance.setText(mModel1Bean.getRestaurantType() + "  " + distance + "m");
                                }

                                tvRestaurantAddress.setText(mModel1Bean.getRestaurantAddress());

                                //材料列表
                                MaterialInfoList.clear();
                                List<OrderInfo.Model1Bean.FoodRecipeBean> mFoodRecipe = mModel1Bean.getFoodRecipe();
                                if (mFoodRecipe != null) {
                                    for (int i = 0; i < mFoodRecipe.size(); i++) {
                                        String RecipeItemName = mFoodRecipe.get(i).getRecipeItemName();//获取名称
                                        List<OrderInfo.Model1Bean.FoodRecipeBean.ListFoodBean> getListFood = mFoodRecipe.get(i).getListFood();
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
                                }
                                mRvMaterialAdapter.notifyDataSetChanged();
                                final String mPhone = mModel1Bean.getRestaurantPhone();

                                //拨打电话
                                ivRestaurantPhone.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mPhone));
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                });

                            }
                        } else if (mOrderInfo.getHttpCode() == 302) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setMessage(mOrderInfo.getMessage());
                            builder.setTitle("提示");
                            builder.setPositiveButton("知道啦", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alertDialog.dismiss();
                                    finish();
                                }
                            });
                            builder.setCancelable(false);
                            alertDialog=builder.create();
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("订单详情", e.toString());
                        loadingDialog.dismiss();
                        finishActivity();
                    }
                });


    }

    private String RecipeId = "";

    @OnClick(R.id.tvGetAgain)
    public void onViewClicked() {
        Intent mIn = new Intent(getContext(), PayActivity.class);
        mIn.putExtra("RecipeId", RecipeId);
        startActivity(mIn);
    }
}
