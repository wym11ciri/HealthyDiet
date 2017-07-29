package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RvSimpleAnswerAdapter;
import com.huihong.healthydiet.bean.GetQuestionExpressList;
import com.huihong.healthydiet.bean.GetSubmitExpressQuestion;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/25
 * 基础版问卷调查
 */

public class TestSimpleActivity extends BaseTitleActivity {

    private RecyclerView rvAnswer;
    private RvSimpleAnswerAdapter rvSimpleAnswerAdapter;
    private TextView tvAnswerTitle;//问题题目

    private int questionNum = 0;//当前题目号码
    private List<GetQuestionExpressList.ListDataBean> mListData;//所有问题的几个
    private List<GetQuestionExpressList.ListDataBean.OptionsBean> mAnswerList;//某个问题答案的集合
    private List<Integer> mAnswerOptionIdList;//所有答案的集合


    @Override
    public int setLayoutId() {
        return R.layout.activity_test_simple;
    }

    @Override
    public void initUI() {
        setTitle("体质测试");
        mAnswerOptionIdList = new ArrayList<>();
        mAnswerList = new ArrayList<>();
        tvAnswerTitle = (TextView) findViewById(R.id.tvAnswerTitle);

        rvAnswer = (RecyclerView) findViewById(R.id.rvAnswer);
        rvAnswer.setLayoutManager(new LinearLayoutManager(TestSimpleActivity.this, LinearLayoutManager.VERTICAL, false));
        rvSimpleAnswerAdapter = new RvSimpleAnswerAdapter(TestSimpleActivity.this, mAnswerList);
        rvAnswer.setAdapter(rvSimpleAnswerAdapter);

        findViewById(R.id.tvNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击下一题的时候先获取上一题的答案
                List<Integer> thisAnswer = rvSimpleAnswerAdapter.getAnswer();
                if (thisAnswer.size() < 1) {
                    Toast.makeText(TestSimpleActivity.this, "请选择本题答案", Toast.LENGTH_SHORT).show();
                } else {
                    //把答案添加到我的集合中去
                    mAnswerOptionIdList.addAll(thisAnswer);
                    questionNum++;
                    if (questionNum >= mListData.size()) {
                        Toast.makeText(TestSimpleActivity.this, "答题完成", Toast.LENGTH_SHORT).show();
                        submitQuestion();//提交问卷
                    } else {
                        //切换下一题
                        mAnswerList.clear();
                        mAnswerList.addAll(mListData.get(questionNum).getOptions());
                        rvSimpleAnswerAdapter.notifyDataSetChanged();
                        //设置题目号码
                        tvAnswerTitle.setText((questionNum + 1) + "、" + mListData.get(questionNum).getQuestionContent());
                    }

                }


            }
        });


        getInfo();
    }

    @Override
    public void initOnClickListener() {

    }

    //基础版问卷提交
    private void submitQuestion() {

        String a = "";
        for (int i = 0; i < mAnswerOptionIdList.size(); i++) {
            a = a + mAnswerOptionIdList.get(i) + ",";
        }

        OkHttpUtils
                .post()
                .url(AppUrl.GET_SUBMIT_EXPRESS_QUESTION)
                .addParams("UserId",  SPUtils.get(TestSimpleActivity.this,"UserId",0)+"")
                .addParams("answer", a)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("error" + e);
                        Toast.makeText(TestSimpleActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，基础版测试提交:", response);
                        Gson gson = new Gson();
                        GetSubmitExpressQuestion mGetSubmitExpressQuestion = gson.fromJson(response, GetSubmitExpressQuestion.class);
                        int code = mGetSubmitExpressQuestion.getHttpCode();
                        if (code == 200) {
                            Intent mIntent = new Intent(TestSimpleActivity.this, MainActivity.class);
                            startActivity(mIntent);
                            SPUtils.put(TestSimpleActivity.this, "isDoSimpleTest",true);//完成简易版本测试
                            finish();
                        }
                        String message = mGetSubmitExpressQuestion.getMessage();
                        if (message != null) {
                            Toast.makeText(TestSimpleActivity.this, message, Toast.LENGTH_SHORT).show();
                        }

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
                        Toast.makeText(TestSimpleActivity.this, R.string.service_error, Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(TestSimpleActivity.this, message, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
