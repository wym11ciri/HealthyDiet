package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RvPhotoAdapter;
import com.huihong.healthydiet.adapter.RvTagAdapter;
import com.huihong.healthydiet.adapter.RvTypeAdapter2;
import com.huihong.healthydiet.model.gsonbean.RecipeItemInfoForPay;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.utils.MyUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.zuoni.dialog.picker.pickerdialog.DataPickerDateDialog;
import com.zuoni.dialog.picker.pickerdialog.TimePickerDialog;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by zangyi_shuai_ge on 2017/7/13
 * 订单支付界面
 */

public class PayActivity extends BaseTitleActivity {
    private String RecipeId;
    private LinearLayout layoutTimeSelect;
    private TextView tvTimeSelect;
    private TextView tvName, tvPrice, tvSales, tvConstitutionPercentage;//食谱名称
    private TextView tvRestaurantName;
    private TextView tvAddress;
    private ImageView ivHead;
    private TextView tvDistance;
    private ImageView ivPhone;

    private String payMoney = "-1";
    private String payTime = "";
    private String paName = "";

    @Override
    public int setLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void initUI() {
        setTitle("支付");
        tvName = (TextView) findViewById(R.id.tvName);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvSales = (TextView) findViewById(R.id.tvSales);
        tvConstitutionPercentage = (TextView) findViewById(R.id.tvConstitutionPercentage);
        ivHead = (ImageView) findViewById(R.id.ivHead);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvRestaurantName = (TextView) findViewById(R.id.tvRestaurantName);
        tvDistance = (TextView) findViewById(R.id.tvDistance);

        ivPhone = (ImageView) findViewById(R.id.ivPhone);
        initTimeSelect();


        RecipeId = getIntent().getStringExtra("RecipeId");
        if (!RecipeId.equals("")) {
            //从接口读取信息
            getInfo();

        }

        LinearLayout layoutPayOnline = (LinearLayout) findViewById(R.id.layoutPayOnline);
        layoutPayOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(PayActivity.this, PayOnlineActivity.class);
                mIntent.putExtra("payMoney", payMoney);
                mIntent.putExtra("payName", paName);
                mIntent.putExtra("payTime", tvTimeSelect.getText().toString().trim());
                mIntent.putExtra("RecipeId", RecipeId);
                startActivity(mIntent);
            }
        });
    }


    private DataPickerDateDialog mDataPickerDateDialog;
    private TimePickerDialog mTimePickerDialog;
    private int chooseYear, chooseMonth, chooseDay, chooseHour, chooseMin;

    //初始化时间选择
    private void initTimeSelect() {
        layoutTimeSelect = (LinearLayout) findViewById(R.id.layoutTimeSelect);
        tvTimeSelect = (TextView) findViewById(R.id.tvTimeSelect);

        layoutTimeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先弹出日期选择框在弹出时间选择框
                DataPickerDateDialog.Builder builder = new DataPickerDateDialog.Builder(PayActivity.this);
                builder.canCancel(false);
                builder.setOnDateSelectedListener(new DataPickerDateDialog.OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(int[] dates) {
                        chooseYear = dates[0];
                        chooseMonth = dates[1];
                        chooseDay = dates[2];
                        showTimePickDialog();
                    }
                });
                mDataPickerDateDialog = builder.create();
                mDataPickerDateDialog.show();

            }
        });


    }

    private void showTimePickDialog() {
        TimePickerDialog.Builder builder = new TimePickerDialog.Builder(PayActivity.this);
        builder.setOnTimeSelectedListener(new TimePickerDialog.OnTimeSelectedListener() {
            @Override
            public void onTimeSelected(int[] times) {
                chooseHour = times[0];
                chooseMin = times[1];
                tvTimeSelect.setText(chooseYear + "年" + chooseMonth + "月" + chooseDay + "日" + chooseHour + ":" + chooseMin);
            }
        });
        builder.canCancel(false);
        mTimePickerDialog = builder.create();
        mTimePickerDialog.show();

    }

    private void getInfo() {


        Map<String, String> map = new HashMap<>();
        map.put("RecipeId", RecipeId);
        map.put("UserId", SPUtils.get(PayActivity.this, "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(PayActivity.this, AppUrl.RECIPE_ITEM_INFO_FOR_PAY
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("接口，支付详情", e + "");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，支付详情", response + "");
                        Gson gson = new Gson();
                        RecipeItemInfoForPay mRecipeItemInfoForPay = gson.fromJson(response, RecipeItemInfoForPay.class);
                        int code = mRecipeItemInfoForPay.getHttpCode();
                        if (code == 200) {
                            if (mRecipeItemInfoForPay.getListData().size() > 0) {
                                RecipeItemInfoForPay.ListDataBean mListDataBean = mRecipeItemInfoForPay.getListData().get(0);
                                //设置类型
                                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(PayActivity.this);
                                linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                                RecyclerView rvArticleType = (RecyclerView) findViewById(R.id.rvType);
                                rvArticleType.setLayoutManager(linearLayoutManager1);
                                rvArticleType.setAdapter(new RvTypeAdapter2(PayActivity.this, mListDataBean.getConstitution()));
                                //设置标签
                                RecyclerView rvArticleTag = (RecyclerView) findViewById(R.id.rvArticleTag);
                                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(PayActivity.this);
                                linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                                rvArticleTag.setLayoutManager(linearLayoutManager2);
                                rvArticleTag.setAdapter(new RvTagAdapter(PayActivity.this, mListDataBean.getTags()));


                                //设置图片列表
                                RecyclerView rvDetailPhoto = (RecyclerView) findViewById(R.id.rvDetailPhoto);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PayActivity.this);
                                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                rvDetailPhoto.setLayoutManager(linearLayoutManager);
                                rvDetailPhoto.setAdapter(new RvPhotoAdapter(PayActivity.this, mListDataBean.getImages()));
                                //设置食谱名称
                                tvName.setText(mListDataBean.getName());

                                tvPrice.setText("￥" + mListDataBean.getPrice());
                                payMoney = mListDataBean.getPrice();
                                tvSales.setText("本月销量" + mListDataBean.getSales() + "份");
                                //设置匹配度
                                int percentage = mListDataBean.getConstitutionPercentage();
                                MyUtils.setTextViewColor(tvConstitutionPercentage,percentage,PayActivity.this);
                                tvConstitutionPercentage.setText(percentage + "%");


                            }
                            //获得第二个列表
                            if (mRecipeItemInfoForPay.getListData2().size() > 0) {
                                final RecipeItemInfoForPay.ListData2Bean mListData2Bean = mRecipeItemInfoForPay.getListData2().get(0);
                                tvRestaurantName.setText(mListData2Bean.getName());
                                tvAddress.setText(mListData2Bean.getAddress());
                                Glide
                                        .with(PayActivity.this)
                                        .load(mListData2Bean.getTitleImage())
                                        .error(R.mipmap.error_photo)
                                        .into(ivHead);
                                int distance = mListData2Bean.getDistance();
                                if (distance > 1000) {
                                    distance = distance / 1000;
                                    tvDistance.setText(distance + "km");
                                } else {
                                    tvDistance.setText(distance + "m");
                                }

                                ivPhone.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mListData2Bean.getPhone()));
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                });
                                paName = mListData2Bean.getName();

                            }


                        }

                    }
                });


    }

    @Override
    public void initOnClickListener() {

    }


}
