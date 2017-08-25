package com.huihong.healthydiet.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RvPhotoAdapter;
import com.huihong.healthydiet.adapter.RvTagAdapter;
import com.huihong.healthydiet.adapter.RvTypeAdapter2;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.model.gsonbean.RecipeItemInfoForPay;
import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.utils.MyUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.zuoni.dialog.picker.dialog.LoadingDialog;
import com.zuoni.dialog.picker.picker.DataPickerDateDialog;
import com.zuoni.dialog.picker.picker.TimePickerDialog;

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
    private LoadingDialog loadingDialog;

    @Override
    public int setLayoutId() {
        return R.layout.activity_pay;
    }

//    RecyclerView rvArticleType;
//    private RvTypeAdapter2 mAdapter;
//    private List<String> mList;

    @Override
    public void initUI() {


        LoadingDialog.Builder builder = new LoadingDialog.Builder(getContext());
        builder.setMessage("载入中...");
        loadingDialog = builder.create();
        loadingDialog.show();
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
                String time = tvTimeSelect.getText().toString().trim();
                if (time.equals("")) {
                    Toast.makeText(PayActivity.this, "请选择到店时间", Toast.LENGTH_SHORT).show();
                } else {
                    Intent mIntent = new Intent(PayActivity.this, PayOnlineActivity.class);
                    mIntent.putExtra("payMoney", payMoney);
                    mIntent.putExtra("payName", paName);
                    mIntent.putExtra("payTime", tvTimeSelect.getText().toString().trim());
                    mIntent.putExtra("RecipeId", RecipeId);
                    startActivity(mIntent);
                }

            }
        });
        LinearLayout layoutPayOutline = (LinearLayout) findViewById(R.id.layoutPayOutline);
        layoutPayOutline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = tvTimeSelect.getText().toString().trim();
                if (time.equals("")) {
                    Toast.makeText(PayActivity.this, "请选择到店时间", Toast.LENGTH_SHORT).show();
                } else {
                    payOutLine();
                }
            }
        });
    }

    private void payOutLine() {

        Map<String, String> map = new HashMap<>();
        map.put("RecipeId", RecipeId);
        map.put("atshoptime", tvTimeSelect.getText().toString().trim());

        HttpUtils.sendHttpAddToken(getContext(), AppUrl.PAY_AT_SHOP_ORDER
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("到店支付", response);
                        Gson gson = new Gson();
                        HttpBaseInfo mHttpBaseInfo = gson.fromJson(response, HttpBaseInfo.class);
                        if (mHttpBaseInfo.getHttpCode() == 200) {
                            Toast.makeText(PayActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(PayActivity.this, mHttpBaseInfo.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("到店支付", e.toString());
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
                tvTimeSelect.setText(chooseYear + "年" + chooseMonth + "月" + chooseDay + "日 " + chooseHour + ":" + chooseMin);
            }
        });
        builder.canCancel(false);
        mTimePickerDialog = builder.create();
        mTimePickerDialog.show();

    }

    AlertDialog alertDialog;
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
                        loadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loadingDialog.dismiss();
                        LogUtil.i("接口，支付详情", response + "");
                        Gson gson = new Gson();
                        RecipeItemInfoForPay mRecipeItemInfoForPay = gson.fromJson(response, RecipeItemInfoForPay.class);
                        int code = mRecipeItemInfoForPay.getHttpCode();
                        if (code == 200) {
                            if (mRecipeItemInfoForPay.getListData().size() > 0) {
                                RecipeItemInfoForPay.ListDataBean mListDataBean = mRecipeItemInfoForPay.getListData().get(0);
                                //设置类型
                                RecyclerView    rvArticleType = (RecyclerView) findViewById(R.id.rvType);
                                rvArticleType.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                                rvArticleType.setAdapter(new RvTypeAdapter2(getContext(), mListDataBean.getConstitution()));

//                                LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(PayActivity.this);
//                                linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);


//                                rvArticleType.setAdapter(new RvTypeAdapter2(PayActivity.this, mListDataBean.getConstitution()));
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
                                MyUtils.setTextViewColor(tvConstitutionPercentage, percentage, PayActivity.this);
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


                        }else if (mRecipeItemInfoForPay.getHttpCode() == 302) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setMessage(mRecipeItemInfoForPay.getMessage());
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
                });


    }

    @Override
    public void initOnClickListener() {

    }


}
