package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity2;
import com.huihong.healthydiet.adapter.RvArticleAdapter;
import com.huihong.healthydiet.mInterface.ArticleItemOnClickListener;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.model.gsonbean.GetArticleItemInfo;
import com.huihong.healthydiet.model.gsonbean.GetArticleListInfo;
import com.huihong.healthydiet.model.httpmodel.ArticleInfo;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/8/12
 */

public class ArticleActivity extends BaseTitleActivity2 {
    @BindView(R.id.recyclerView)
    LRecyclerView recyclerView;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private int mNum = 1;
    private List<ArticleInfo> mList;

    @Override
    public int setLayoutId() {
        return R.layout.activity_article;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("推荐文章");

        mList = new ArrayList<>();
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mNum = 1;
                mList.clear();
                mLRecyclerViewAdapter.notifyDataSetChanged();
                getInfo(mNum);
            }
        });

        //加载更多
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getInfo(mNum);
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(ArticleActivity.this, LinearLayoutManager.VERTICAL, false));
        RvArticleAdapter mRvRecommendAdapter = new RvArticleAdapter(ArticleActivity.this, mList);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mRvRecommendAdapter);
        recyclerView.setAdapter(mLRecyclerViewAdapter);
        recyclerView.refresh();

        mRvRecommendAdapter.setItemOnClickListener(new ArticleItemOnClickListener() {
            @Override
            public void onClick(int position) {
                Intent mIntent = new Intent(ArticleActivity.this, ArticleDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", mList.get(position));
                bundle.putInt("pos", position);
                mIntent.putExtras(bundle);
                startActivityForResult(mIntent, 10086);
            }
        });


    }

    //获得信息
    private void getInfo(int pageNum) {

        Map<String, String> map = new HashMap<>();
        map.put("PageNo", pageNum + "");
        map.put("Id", SPUtils.get(ArticleActivity.this, "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(ArticleActivity.this, AppUrl.GET_ARTICLE_LIST_INFO
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        recyclerView.refreshComplete(1);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，获取文章列表" + response);
                        Gson gson = new Gson();
                        GetArticleListInfo mGetArticleListInfo = gson.fromJson(response, GetArticleListInfo.class);
                        int code = mGetArticleListInfo.getHttpCode();
                        if (code == 200) {
                            mNum++;
                            mList.addAll(mGetArticleListInfo.getListData());
                            mLRecyclerViewAdapter.notifyDataSetChanged();
//
                        } else {
                            String Message = mGetArticleListInfo.getMessage();
                            Toast.makeText(ArticleActivity.this, Message, Toast.LENGTH_SHORT).show();
                        }
                        recyclerView.refreshComplete(1);
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086) {
//            if(resultCode==2)
            int pos = data.getIntExtra("pos", -1);
            if (pos != -1) {
                GetArticleItemInfo.ListDataBean mListDataBean = (GetArticleItemInfo.ListDataBean) data.getSerializableExtra("info");
                if (mListDataBean != null) {
                    mList.get(pos).setLoveCount(mListDataBean.getLoveCount());
                    mList.get(pos).setPointPraise(mListDataBean.isPointPraise());
                    mList.get(pos).setCilckCount(mListDataBean.getCilckCount());
                    mLRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
