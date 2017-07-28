package com.huihong.healthydiet.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.SearchActivity;
import com.huihong.healthydiet.adapter.RvArticleAdapter;
import com.huihong.healthydiet.bean.GetArticleListInfo;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by zangyi_shuai_ge on 2017/7/11
 */

public class ArticleFragment extends Fragment {

    private View mView;
    private ListView lvArticle;
    private List<String> articleList;
//    private LvArticleAdapter mLvArticleAdapter;

    private LRecyclerView recyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private List<GetArticleListInfo.ListDataBean> mList;


    private TextView tvTop01, tvTop02, tvTop03;


    private int mNum=1;



    private LinearLayout layoutTopRight;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_article, null);
            initUI();
            recyclerView = (LRecyclerView) mView.findViewById(R.id.recyclerView);
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


            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            RvArticleAdapter mRvRecommendAdapter = new RvArticleAdapter(getActivity(), mList);
            mLRecyclerViewAdapter = new LRecyclerViewAdapter(mRvRecommendAdapter);
            recyclerView.setAdapter(mLRecyclerViewAdapter);

            recyclerView.refresh();



            layoutTopRight= (LinearLayout) mView.findViewById(R.id.layoutTopRight);
            layoutTopRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent=new Intent(getActivity(), SearchActivity.class);
                    startActivity(mIntent);
                }
            });

        }


        return mView;
    }


    //获得信息
    private void getInfo(int pageNum) {


        OkHttpUtils
                .post()
                .url(AppUrl.GET_ARTICLE_LIST_INFO)
                .addParams("Id", "2")//用户坐标
                .addParams("PageNo", pageNum+"")//用户坐标
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        recyclerView.refreshComplete(1);
                        Toast.makeText(getActivity(), R.string.service_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，获取文章列表" + response);
                        Gson gson = new Gson();
                        GetArticleListInfo mGetArticleListInfo = gson.fromJson(response, GetArticleListInfo.class);
                        int code = mGetArticleListInfo.getHttpCode();
                        if (code == 200) {
                            mNum++;
                            //顶部3个TextView
                            GetArticleListInfo.ListData2Bean ListData2Bean = mGetArticleListInfo.getListData2().get(0);
                            tvTop01.setText("您的体质是" + ListData2Bean.getConstitution());
                            tvTop02.setText("适合吃" + ListData2Bean.getSuitEat());
                            tvTop03.setText("尽量少吃" + ListData2Bean.getNotSuitEat());

                            mList.addAll(mGetArticleListInfo.getListData());
                            mLRecyclerViewAdapter.notifyDataSetChanged();
//
                        } else {
                            String Message = mGetArticleListInfo.getMessage();
                            Toast.makeText(getActivity(), Message, Toast.LENGTH_SHORT).show();
                        }
                        recyclerView.refreshComplete(1);
                    }
                });


    }

    private void initUI() {
        tvTop01 = (TextView) mView.findViewById(R.id.tvTop01);
        tvTop02 = (TextView) mView.findViewById(R.id.tvTop02);
        tvTop03 = (TextView) mView.findViewById(R.id.tvTop03);

    }


}
