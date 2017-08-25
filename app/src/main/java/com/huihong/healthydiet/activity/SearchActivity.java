package com.huihong.healthydiet.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseActivity;
import com.huihong.healthydiet.adapter.RvHistorySearchAdapter;
import com.huihong.healthydiet.adapter.RvHotSearchAdapter;
import com.huihong.healthydiet.model.gsonbean.HotSearch;
import com.huihong.healthydiet.cache.litepal.SearchHistory;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.mInterface.ItemOnClickListener2;
import com.huihong.healthydiet.widget.FlowLayoutManager;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/20
 * 搜索时界面
 */

public class SearchActivity extends BaseActivity {
    private RecyclerView rvHotSearch;
    private RvHotSearchAdapter mRvHotSearchAdapter;
    private RecyclerView rvHistorySearch;
    private RvHistorySearchAdapter mRvHistorySearchAdapter;
    private ImageView ivDeleteHistory;
    private LinearLayout layoutBack;
    private EditText etSearch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initUI();
    }

    private AlertDialog mAlertDialog;

    private void initUI() {

        etSearch = (EditText) findViewById(R.id.etSearch);

        layoutBack = (LinearLayout) findViewById(R.id.layoutBack);
        layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initHistorySearch();

        ivDeleteHistory = (ImageView) findViewById(R.id.ivDeleteHistory);
        ivDeleteHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAlertDialog != null) {
                    mAlertDialog.dismiss();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                builder.setTitle("清除历史记录");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAlertDialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        historyList.clear();
                        mRvHistorySearchAdapter.notifyDataSetChanged();
                        DataSupport.deleteAll(SearchHistory.class);
                        mAlertDialog.dismiss();
                    }
                });
                builder.setCancelable(false);
                mAlertDialog = builder.create();
                mAlertDialog.show();

            }
        });


        TextView tvSearch = (TextView) findViewById(R.id.tvSearch);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        getHotSearchInfo();
    }

    private void search() {
        String searchText = etSearch.getText().toString().trim();
        if (searchText.equals("")) {
            Toast.makeText(SearchActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
        } else {
            if (searchText.length() < 2) {
                Toast.makeText(SearchActivity.this, "输入搜索内容过短", Toast.LENGTH_SHORT).show();
            } else {
                //先把原来的那条删除再添加
                DataSupport.deleteAll(SearchHistory.class,"searchHistory = ?",searchText);

                SearchHistory searchHistory = new SearchHistory();
                searchHistory.setSearchHistory(searchText);
                searchHistory.save();

                for (int i = 0; i <historyList.size() ; i++) {

                    if(historyList.get(i).getSearchHistory().equals(searchText)){
                        historyList.remove(i);
                    }
                }

                historyList.add(searchHistory);
                mRvHistorySearchAdapter.notifyDataSetChanged();

                Intent mIntent = new Intent(SearchActivity.this, SearchResultActivity.class);
                mIntent.putExtra("searchText", searchText);
                startActivity(mIntent);
            }


        }
    }


    private void getHotSearchInfo() {

        Map<String, String> map = new HashMap<>();
        map.put("UserId", SPUtils.get(SearchActivity.this, "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(SearchActivity.this, AppUrl.HOT_SEARCH
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("热门搜索", response);
                        Gson gson = new Gson();
                        HotSearch mHotSearch = gson.fromJson(response, HotSearch.class);
                        if (mHotSearch.getHttpCode() == 200) {
                            rvHotSearch = (RecyclerView) findViewById(R.id.rvHotSearch);
                            mRvHotSearchAdapter = new RvHotSearchAdapter(SearchActivity.this, mHotSearch.getListData());
                            FlowLayoutManager flowLayoutManager = new FlowLayoutManager(SearchActivity.this);
                            rvHotSearch.setLayoutManager(flowLayoutManager);
                            rvHotSearch.setAdapter(mRvHotSearchAdapter);
                            mRvHotSearchAdapter.setItemOnClickListener(new ItemOnClickListener2() {
                                @Override
                                public void onClick(String text) {
                                    etSearch.setText(text);
                                    search();
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                });
    }

    private List<SearchHistory> historyList;

    private void initHistorySearch() {
        rvHistorySearch = (RecyclerView) findViewById(R.id.rvHistorySearch);
        historyList = new ArrayList<>();
        historyList = DataSupport.findAll(SearchHistory.class);

        rvHistorySearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvHistorySearchAdapter = new RvHistorySearchAdapter(SearchActivity.this, historyList);
        rvHistorySearch.setAdapter(mRvHistorySearchAdapter);

        mRvHistorySearchAdapter.setItemOnClickListener(new ItemOnClickListener2() {
            @Override
            public void onClick(String text) {
                etSearch.setText(text);
                search();
            }
        });
    }


}
