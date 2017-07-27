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
import com.huihong.healthydiet.adapter.RvSimpleAnswerAdapter;
import com.huihong.healthydiet.bean.GetQuestionExpressList;
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

public class SimpleTestActivity extends BaseTitleActivity {

    private RecyclerView rvAnswer;
    private RvSimpleAnswerAdapter rvSimpleAnswerAdapter;
    private TextView tvAnswerTitle;
    private List<GetQuestionExpressList.ListDataBean> mListData;
    private List<MajorAnswer> MajorAnswerList;

    private List<GetQuestionExpressList.ListDataBean.OptionsBean> mAnswerList;




    private List<Integer> mAnswerOptionIdList;//所有答案的集合


//    private List<>

    private int num = 0;

    @Override
    public int setLayoutId() {
        return R.layout.activity_simple_test;
    }

    @Override
    public void initUI() {


        mAnswerOptionIdList=new ArrayList<>();
        mAnswerList = new ArrayList<>();
        //
        MajorAnswerList = new ArrayList<>();


        setTitle("体质测试");
        tvAnswerTitle = (TextView) findViewById(R.id.tvAnswerTitle);

        rvAnswer = (RecyclerView) findViewById(R.id.rvAnswer);
        rvAnswer.setLayoutManager(new LinearLayoutManager(SimpleTestActivity.this, LinearLayoutManager.VERTICAL, false));
        rvSimpleAnswerAdapter = new RvSimpleAnswerAdapter(SimpleTestActivity.this, mAnswerList);
        rvAnswer.setAdapter(rvSimpleAnswerAdapter);

        findViewById(R.id.tvNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击下一题的时候先获取上一题的答案
                List<Integer> thisAnswer=    rvSimpleAnswerAdapter.getAnswer();
                if(thisAnswer.size()<1){
                    Toast.makeText(SimpleTestActivity.this, "请选择本题答案", Toast.LENGTH_SHORT).show();
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

        String a = "";
        for (int i = 0; i < MajorAnswerList.size(); i++) {
            a = a + MajorAnswerList.get(i).getQuestionId() + "," + MajorAnswerList.get(i).getAnswer() + "|";
        }


        OkHttpUtils
                .post()
                .url(AppUrl.GET_SUBMIT_QUESTION)
//                .addParams("id", "2")
                .addParams("answer", a)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("error" + e);
                        Toast.makeText(SimpleTestActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，专业版测试:", response);


                    }
                });

    }


    private void getInfo() {
        OkHttpUtils
                .get()
                .url(AppUrl.GET_QUESTION_EXPRESS_LIST)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("error" + e);
                        Toast.makeText(SimpleTestActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，简易版测试:", response);
                        Gson gson = new Gson();
                        GetQuestionExpressList mGetQuestionExpressList = gson.fromJson(response, GetQuestionExpressList.class);
                        int code = mGetQuestionExpressList.getHttpCode();
                        if (code == 200) {
                            mListData = mGetQuestionExpressList.getListData();
//                            //设置第一题
                            tvAnswerTitle.setText("1、" + mListData.get(0).getQuestionContent());
                            TextView tvNum = (TextView) findViewById(R.id.tvNum);
                            tvNum.setText("*共" + mListData.size() + "道题，均为多选");

                            mAnswerList.clear();
                            mAnswerList.addAll(mListData.get(0).getOptions());
                            rvSimpleAnswerAdapter.notifyDataSetChanged();
//
                        } else {
                            String message = mGetQuestionExpressList.getMessage();
                            Toast.makeText(SimpleTestActivity.this, message, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
