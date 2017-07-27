package com.huihong.healthydiet.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RvMajorAnswerAdapter;
import com.huihong.healthydiet.bean.GetQuestionProfessionList;
import com.huihong.healthydiet.mybean.MajorAnswer;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/25
 */

public class MajorTestActivity extends BaseTitleActivity {

    private RecyclerView rvAnswer;
    private RvMajorAnswerAdapter rvMajorAnswerAdapter;
    private TextView tvAnswerTitle;
    private List<GetQuestionProfessionList.ListDataBean> getListData;
    private List<MajorAnswer> MajorAnswerList;

    private int num = 0;

    @Override
    public int setLayoutId() {
        return R.layout.activity_major_test;
    }

    @Override
    public void initUI() {
        MajorAnswerList = new ArrayList<>();
        setTitle("专业版测试");
        tvAnswerTitle = (TextView) findViewById(R.id.tvAnswerTitle);

        rvAnswer = (RecyclerView) findViewById(R.id.rvAnswer);
        rvAnswer.setLayoutManager(new LinearLayoutManager(MajorTestActivity.this, LinearLayoutManager.VERTICAL, false));
        rvMajorAnswerAdapter = new RvMajorAnswerAdapter(MajorTestActivity.this);
        rvAnswer.setAdapter(rvMajorAnswerAdapter);

        findViewById(R.id.tvNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getListData != null) {
                    int answer = rvMajorAnswerAdapter.getAnswer();
                    if (answer == -1) {
                        Toast.makeText(MajorTestActivity.this, "请先选择本题答案", Toast.LENGTH_SHORT).show();
                    } else {
                        rvMajorAnswerAdapter.resetChooseId();
                        //
                        MajorAnswer majorAnswer = new MajorAnswer();
                        majorAnswer.setAnswer(answer);
                        majorAnswer.setQuestionId(getListData.get(num).getQuestionId());
                        MajorAnswerList.add(majorAnswer);
                        //
                        num++;
                        if (num < getListData.size()) {
                            tvAnswerTitle.setText((num+1) + "、" + getListData.get(num).getQuestionContent());
                        } else {
                            getInfo2();
                            finish();
                            Toast.makeText(MajorTestActivity.this, "答题完成", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


        getInfo();
    }

    @Override
    public void initOnClickListener() {

    }
    //获取餐厅列表信息
    private void getInfo2() {

        String a="";
        for (int i = 0; i <MajorAnswerList.size() ; i++) {
            a=a+MajorAnswerList.get(i).getQuestionId()+","+MajorAnswerList.get(i).getAnswer()+"|";
        }


        OkHttpUtils
                .post()
                .url(AppUrl.GET_SUBMIT_QUESTION)
//                .addParams("id", "2")
                .addParams("answer",a)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("error" + e);
                        Toast.makeText(MajorTestActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，专业版测试:", response);


                    }
                });

    }

    //获取餐厅列表信息
    private void getInfo() {
        OkHttpUtils
                .post()
                .url(AppUrl.GET_QUESTION_PROFESSION_LIST)
                .addParams("id", "2")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("error" + e);
                        Toast.makeText(MajorTestActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，专业版测试:", response);
                        Gson gson = new Gson();
                        GetQuestionProfessionList mGetQuestionProfessionList = gson.fromJson(response, GetQuestionProfessionList.class);
                        int code = mGetQuestionProfessionList.getHttpCode();
                        if (code == 200) {
                            getListData = mGetQuestionProfessionList.getListData();
                            //设置第一题
                            tvAnswerTitle.setText("1、" + getListData.get(0).getQuestionContent());
                            TextView tvNum = (TextView) findViewById(R.id.tvNum);
                            tvNum.setText("*共" + getListData.size() + "道题，均为单选");

                        } else {
                            String message = mGetQuestionProfessionList.getMessage();
                            Toast.makeText(MajorTestActivity.this, message, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
