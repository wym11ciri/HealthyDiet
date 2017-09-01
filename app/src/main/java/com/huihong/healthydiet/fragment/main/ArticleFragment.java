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
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.ArticleDetailsActivity;
import com.huihong.healthydiet.activity.SearchActivity;
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

import okhttp3.Call;


/**
 * Created by zangyi_shuai_ge on 2017/7/11
 * 文章列表界面
 */

public class ArticleFragment extends Fragment {

    private View mView;
    private LRecyclerView recyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private List<ArticleInfo> mList;
    private TextView tvTop01, tvTop02, tvTop03;
    private int mNum = 1;


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
//            recyclerView.setRefreshHeader(new ArrowRefreshHeader(getActivity()));
            recyclerView.setAdapter(mLRecyclerViewAdapter);
            recyclerView.refresh();

            mRvRecommendAdapter.setItemOnClickListener(new ArticleItemOnClickListener() {
                @Override
                public void onClick(int position) {
                    Intent mIntent = new Intent(getActivity(), ArticleDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info", mList.get(position));
                    bundle.putInt("pos", position);
                    mIntent.putExtras(bundle);
                    startActivityForResult(mIntent, 10086);
                }
            });


            LinearLayout layoutTopRight = (LinearLayout) mView.findViewById(R.id.layoutTopRight);
            layoutTopRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(getActivity(), SearchActivity.class);
                    startActivity(mIntent);
                }
            });


        }
        return mView;
    }

    //获取文章列表
    private void getInfo(int pageNum) {

        Map<String, String> map = new HashMap<>();
        map.put("PageNo", pageNum + "");
        map.put("Id", SPUtils.get(getActivity(), "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(getActivity(), AppUrl.GET_ARTICLE_LIST_INFO
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
                            //顶部3个TextView
                            GetArticleListInfo.ListData2Bean ListData2Bean = mGetArticleListInfo.getListData2().get(0);
                            tvTop01.setText("" + ListData2Bean.getConstitution());
                            tvTop02.setText("" + ListData2Bean.getSuitEat());
                            tvTop03.setText("" + ListData2Bean.getNotSuitEat());
                            //刷新列表
                            mList.addAll(mGetArticleListInfo.getListData());
                            mLRecyclerViewAdapter.notifyDataSetChanged();
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
