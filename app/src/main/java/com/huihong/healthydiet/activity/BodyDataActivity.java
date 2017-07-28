package com.huihong.healthydiet.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.bean.SetUserBodyInfo;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zuoni.dialog.picker.mInterface.OnSingleDataSelectedListener;
import com.zuoni.dialog.picker.pickerdialog.DataPickerDateDialog;
import com.zuoni.dialog.picker.pickerdialog.DataPickerSingleDialog;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/20
 * 设置个人身体数据
 */

public class BodyDataActivity extends BaseTitleActivity {

    private TextView tvBirthday, tvLabour;
    private DataPickerDateDialog mDataPickerDateDialog;

    private DataPickerSingleDialog mLabourDialog;


    private ImageView ivBoy, ivGirl;
    private View.OnClickListener onSexClickListener;


    private TextView tvSave;


    private EditText etWeight, etHeight;


    //用户生日
    private String userBirthTime = "2017-7-28";
    //劳动强度
    private String labInten = "一般";
    //性别
    private boolean isMan = false;

    @Override
    public int setLayoutId() {
        return R.layout.activity_body_data;
    }

    @Override
    public void initUI() {
        setTitle("身体数据");

        etWeight = (EditText) findViewById(R.id.etWeight);
        etHeight = (EditText) findViewById(R.id.etHeight);


        tvBirthday = (TextView) findViewById(R.id.tvBirthday);
        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataPickerDateDialog.Builder builder = new DataPickerDateDialog.Builder(BodyDataActivity.this);
                builder.setOnDateSelectedListener(new DataPickerDateDialog.OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(int[] dates) {
                        tvBirthday.setText(dates[0] + "年" + dates[1] + "月" + dates[2] + "日");
                        userBirthTime = dates[0] + "-" + dates[1] + "-" + dates[2];
                    }
                });
                mDataPickerDateDialog = builder.create();
                mDataPickerDateDialog.show();
            }
        });


        //劳动强度选择器
        tvLabour = (TextView) findViewById(R.id.tvLabour);
        tvLabour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> datas = new ArrayList<>();
                datas.add("较差");
                datas.add("一般");
                datas.add("良好");
                datas.add("优秀");
                DataPickerSingleDialog.Builder builder = new DataPickerSingleDialog.Builder(BodyDataActivity.this);
                builder.setData(datas);
                builder.setOnDataSelectedListener(new OnSingleDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue) {
                        tvLabour.setText(itemValue);
                        labInten = itemValue;
                    }
                });
                mLabourDialog = builder.create();
                mLabourDialog.show();
            }
        });


        ivBoy = (ImageView) findViewById(R.id.ivBoy);
        ivGirl = (ImageView) findViewById(R.id.ivGirl);
        onSexClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivGirl.setImageResource(R.mipmap.body_2);
                ivBoy.setImageResource(R.mipmap.body_3);

                switch (v.getId()) {
                    case R.id.ivBoy:
                        ivBoy.setImageResource(R.mipmap.body_4);
                        isMan = true;
                        break;
                    case R.id.ivGirl:
                        ivGirl.setImageResource(R.mipmap.body_5);
                        isMan = false;
                        break;
                }

            }
        };

        ivBoy.setOnClickListener(onSexClickListener);
        ivGirl.setOnClickListener(onSexClickListener);


        tvSave = (TextView) findViewById(R.id.tvSave);
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = etHeight.getText().toString().trim();
                String weight = etWeight.getText().toString().trim();

                if ("".equals(height)) {
                    Toast.makeText(BodyDataActivity.this, "请输入身高", Toast.LENGTH_SHORT).show();
                } else {
                    if ("".equals(weight)) {
                        Toast.makeText(BodyDataActivity.this, "请输入体重", Toast.LENGTH_SHORT).show();
                    } else {
                        saveData(height, weight);
                    }
                }


            }
        });
    }

    @Override
    public void initOnClickListener() {

    }

    public void saveData(String height, String weight) {


        OkHttpUtils
                .post()
                .url(AppUrl.SET_USER_BODY_INFO)
                .addParams("UserId", "2")
                .addParams("UserSex", isMan + "")
                .addParams("UserBirthTime", userBirthTime)
                .addParams("UserHeight", height)
                .addParams("UserWeight", weight)
                .addParams("labInten", labInten)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("error" + e);
                        Toast.makeText(BodyDataActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，个人信息保存:", response);
                        Gson gson = new Gson();
                        SetUserBodyInfo mSetUserBodyInfo = gson.fromJson(response, SetUserBodyInfo.class);
                        int code = mSetUserBodyInfo.getHttpCode();
                        String message = mSetUserBodyInfo.getMessage();
                        Toast.makeText(BodyDataActivity.this, message, Toast.LENGTH_SHORT).show();
                        if (code == 200) {
                            finish();
                        }
                    }
                });
    }
}
