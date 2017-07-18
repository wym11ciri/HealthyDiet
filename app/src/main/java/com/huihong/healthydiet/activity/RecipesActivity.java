package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RvDetailRecipesAdapter;
import com.huihong.healthydiet.adapter.RvRecipesAdapter;
import com.huihong.healthydiet.bean.GetRestaurantInfoById;
import com.huihong.healthydiet.bean.RecipeListInfoByDRId;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/12
 */

public class RecipesActivity extends BaseTitleActivity {
    private int id;

    @Override
    public int setLayoutId() {
        return R.layout.activity_recipes;
    }

    RecyclerView recyclerView;
    RvRecipesAdapter mRvRecommendAdapter;
    List<RecipeListInfoByDRId.ListDataBean> recommendList;

    @Override
    public void initUI() {
        id = getIntent().getIntExtra("id", 0);


        setTitle("餐厅详情");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recommendList = new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(RecipesActivity.this, LinearLayoutManager.VERTICAL, false));
        mRvRecommendAdapter = new RvRecipesAdapter(RecipesActivity.this, recommendList);
        recyclerView.setAdapter(mRvRecommendAdapter);


        if (id != 0) {
            //获取餐厅上面那部分数据
            getInfoUp();
            getDownListInfo();
        }

    }


    @Override
    public void initOnClickListener() {

    }


    private void getDownListInfo() {
        OkHttpUtils
                .post()
                .url(AppUrl.RECIPE_LIST_INFO_BY_DRID)
                .addParams("id", id + "")//用户坐标
                .addParams("CoordX", "120.132566")//用户坐标
                .addParams("CoordY", "30.267515")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("接口，餐厅详情下面部分" + e);
                        Toast.makeText(RecipesActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，餐厅详情下面部分" + response);
                        Gson gson = new Gson();
                        RecipeListInfoByDRId mRecipeListInfoByDRId = gson.fromJson(response, RecipeListInfoByDRId.class);
                        int code = mRecipeListInfoByDRId.getHttpCode();
                        if (code == 200) {
                            List<RecipeListInfoByDRId.ListDataBean> mListData = mRecipeListInfoByDRId.getListData();
                            recommendList.addAll(mListData);
                            mRvRecommendAdapter.notifyDataSetChanged();

                        } else {
                            String Message = mRecipeListInfoByDRId.getMessage();
                            Toast.makeText(RecipesActivity.this, Message, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void getInfoUp() {

        OkHttpUtils
                .post()
                .url(AppUrl.GET_RESTAURANT_INFO_BY_ID)
                .addParams("id", id + "")//用户坐标
                .addParams("CoordX", "120.132566")//用户坐标
                .addParams("CoordY", "30.267515")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("接口，餐厅详情上面部分" + e);
                        Toast.makeText(RecipesActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，餐厅详情上面部分" + response);
                        Gson gson = new Gson();
                        GetRestaurantInfoById mGetRestaurantInfoById = gson.fromJson(response, GetRestaurantInfoById.class);
                        int code = mGetRestaurantInfoById.getHttpCode();
                        if (code == 200) {
                            List<GetRestaurantInfoById.ListDataBean> mListData = mGetRestaurantInfoById.getListData();
                            //获取餐厅信息
                            if (mListData.size() > 0) {
                                GetRestaurantInfoById.ListDataBean mListDataBean = mListData.get(0);


//                                Id	int	餐厅Id
//                                name	字符串	餐厅名称
//                                images	字符串数组	餐厅图片
//                                address	字符串	餐厅地址
//                                phone	字符串	餐厅电话
//                                sales	int	餐厅销售量
//                                consumption	Int	餐厅平均消费
//                                distance	double	距离
//                                category	字符串	餐厅类型

                                int Id = mListDataBean.getId();
                                String name = mListDataBean.getName();
                                String address = mListDataBean.getAddress();
                                final String phone = mListDataBean.getPhone();
                                int sales = mListDataBean.getSales();
                                int consumption = mListDataBean.getConsumption();
                                double distance = mListDataBean.getDistance();
                                String category = mListDataBean.getCategory();

                                List<String> images = mListDataBean.getImages();

                                TextView tvName = (TextView) findViewById(R.id.tvName);
                                tvName.setText(name);
                                TextView tvAddress = (TextView) findViewById(R.id.tvAddress);
                                tvAddress.setText(address);
                                TextView tvSales = (TextView) findViewById(R.id.tvSales);
                                tvSales.setText("本月销售" + sales + "份");

                                TextView tvConsumption = (TextView) findViewById(R.id.tvConsumption);
                                tvConsumption.setText("人均￥ " + consumption);

                                TextView tvCategory = (TextView) findViewById(R.id.tvCategory);
                                tvCategory.setText(category);

                                TextView tvDistance = (TextView) findViewById(R.id.tvDistance);
                                int mDistance;
                                if (distance > 1000) {
                                    mDistance = (int) (distance / 1000);
                                    tvDistance.setText(mDistance + "km");
                                } else {
                                    mDistance = (int) distance;
                                    tvDistance.setText(mDistance + "m");
                                }

                                ImageView ivPhone = (ImageView) findViewById(R.id.ivPhone);
                                ivPhone.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                });

                                RecyclerView rvDetailPhoto = (RecyclerView) findViewById(R.id.rvDetailPhoto);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecipesActivity.this);
                                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                rvDetailPhoto.setLayoutManager(linearLayoutManager);
                                rvDetailPhoto.setAdapter(new RvDetailRecipesAdapter(RecipesActivity.this, images));
                            }
                        }
                    }
                });
    }
}
