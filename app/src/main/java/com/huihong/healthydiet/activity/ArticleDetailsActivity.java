package com.huihong.healthydiet.activity;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.LvTagAdapterForArticleList;
import com.huihong.healthydiet.bean.GetArticleListInfo;
import com.huihong.healthydiet.widget.HorizontalListView;

/**
 * Created by zangyi_shuai_ge on 2017/7/27
 * 文章详情展示界面
 */

public class ArticleDetailsActivity extends BaseTitleActivity {

    private WebView mWebView;


    @Override
    public int setLayoutId() {
        return R.layout.activity_article_details;
    }

    @Override
    public void initUI() {
        setTitle("推荐饮食");

        GetArticleListInfo.ListDataBean mInfo = (GetArticleListInfo.ListDataBean) getIntent().getSerializableExtra("info");


        if (mInfo != null) {
            mWebView = (WebView) findViewById(R.id.mWebView);
            WebSettings webSettings = mWebView.getSettings();
            //设置WebView属性，能够执行Javascript脚本
//            webSettings.setJavaScriptEnabled(true);
            //设置可以访问文件
//            webSettings.setAllowFileAccess(true);
            //设置支持缩放
            webSettings.setBuiltInZoomControls(false);
            //加载需要显示的网页
            mWebView.loadUrl(mInfo.getUrl());
            //设置Web视图
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String request) {
                    view.loadUrl(request);
                    return true;
                }
            });

            TextView tvArticleTitle = (TextView) findViewById(R.id.tvArticleTitle);
            tvArticleTitle.setText(mInfo.getTitle());


            TextView tvClickCount = (TextView) findViewById(R.id.tvClickCount);
            tvClickCount.setText(mInfo.getCilckCount() + "");

            TextView tvLoveCount = (TextView) findViewById(R.id.tvLoveCount);
            tvLoveCount.setText(mInfo.getLoveCount() + "");

            TextView tvTime = (TextView) findViewById(R.id.tvTime);
            tvTime.setText(mInfo.getATime() + "");

            HorizontalListView lvTag = (HorizontalListView) findViewById(R.id.lvTag);
            lvTag.setAdapter(new LvTagAdapterForArticleList(ArticleDetailsActivity.this, mInfo.getTags()));


        } else {
            Toast.makeText(this, "获取文章详情失败", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    @Override
    public void initOnClickListener() {

    }
}
