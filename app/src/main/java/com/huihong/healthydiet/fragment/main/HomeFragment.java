package com.huihong.healthydiet.fragment.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.MyApplication;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.ArticleDetailsActivity;
import com.huihong.healthydiet.activity.DietRecordActivity;
import com.huihong.healthydiet.activity.RecipesDetailsActivity;
import com.huihong.healthydiet.activity.RecommendListActivity;
import com.huihong.healthydiet.activity.SearchResultActivity;
import com.huihong.healthydiet.adapter.NearbyFragmentPagerAdapter;
import com.huihong.healthydiet.adapter.RvRecommendAdapter;
import com.huihong.healthydiet.adapter.RvRecordHomePageAdapter;
import com.huihong.healthydiet.cache.litepal.SearchHistory;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.fragment.NearbyFragment;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.mInterface.LocationListener;
import com.huihong.healthydiet.model.gsonbean.TitlePage;
import com.huihong.healthydiet.model.gsonbean.UserScoreInfo;
import com.huihong.healthydiet.model.httpmodel.ArticleInfo;
import com.huihong.healthydiet.model.httpmodel.OrderDetailsInfo;
import com.huihong.healthydiet.model.httpmodel.RankInfo;
import com.huihong.healthydiet.model.httpmodel.RecipeInfo;
import com.huihong.healthydiet.model.httpmodel.RestaurantInfo;
import com.huihong.healthydiet.utils.MyUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.common.ValueUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.huihong.healthydiet.widget.banner.GlideImageLoader;
import com.joooonho.SelectableRoundedImageView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zuoni.dialog.picker.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/10
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.ivAlarm)
    ImageView ivAlarm;
    Unbinder unbinder;
    @BindView(R.id.tvCurrentName)
    TextView tvCurrentName;
    @BindView(R.id.tvNextScore)
    TextView tvNextScore;
    @BindView(R.id.tvRunState)
    TextView tvRunState;
    @BindView(R.id.layoutRecordMain)
    LinearLayout layoutRecordMain;


    private View mView;

    //????????????
    private ViewPager vpNearby;
    private NearbyFragmentPagerAdapter mNearbyFragmentPagerAdapter;
    private List<Fragment> nearbyList;
    //????????????
    private RecyclerView rvRecommend;
    private RvRecommendAdapter mRvRecommendAdapter;
    private List<RecipeInfo> recommendList;
    //????????????
    private RecyclerView rvRecord;
    private List<OrderDetailsInfo> recordList;
    private RvRecordHomePageAdapter mRvRecordHomePageAdapter;

    //3???????????????
    private LinearLayout layoutNearby, layoutRecommend, layoutRecord;
    private SwipeRefreshLayout layoutRefresh;
    private NearbyFragment nearbyFragmentRight;
    private NearbyFragment nearbyFragmentLeft;
    private NearbyFragment nearbyFragmentMiddle;
    private String mAddress;
    private boolean isFirst = true;
    //??????????????????
    private TextView tvRecommendName, tvConstitutionPercentage, tvRecommendPrice;
    private SelectableRoundedImageView ivRecommend;
    //??????
    private EditText etSearch;
    private TextView tvRecord;
    //    private ProgressDialog progressDialog;
    private LoadingDialog loadingDialog;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String address = (String) msg.obj;
            if (address == null) {
                tvAddress.setText("????????????");
            } else {
                tvAddress.setText(address);
            }

        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, null);
            unbinder = ButterKnife.bind(this, mView);
            LoadingDialog.Builder builder = new LoadingDialog.Builder(getActivity());
            builder.setMessage("?????????...");
            loadingDialog = builder.create();

            initUI();
//            getHomePageInfo();
            MainActivity.mainActivity.setLocationListener(new LocationListener() {
                @Override
                public void isReLocation(boolean isReLocation, String address) {
                    if (isReLocation) {
                        mAddress = address;
                        getHomePageInfo();
                    }
                }
            });

            getIntegral();
            String state = CacheUtils.getRunState(getContext());
            if (state.equals("OFF")) {
                tvRunState.setText("??????????????????");
            } else {
                tvRunState.setText("???????????????????????????");
            }
            MainActivity.mainActivity.setHomePageItemOnClickListener(new ItemOnClickListener() {
                @Override
                public void onClick() {
                    getIntegral();
                    String state = CacheUtils.getRunState(getContext());
                    if (state.equals("OFF")) {
                        tvRunState.setText("??????????????????");
                    } else {
                        tvRunState.setText("???????????????????????????");
                    }
                }
            });
        }

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();

    }

    @Override
    public void onResume() {
        super.onResume();
//        mBannerNet.startAutoPlay();
    }

    @Override
    public void onStop() {
        banner.stopAutoPlay();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        loadingDialog.dismiss();
        unbinder.unbind();
        super.onDestroyView();
    }


    TextView tvAddress;
//    XBanner mBannerNet;

    private AlertDialog locationDialog;


    private void initUI() {
//        progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage("?????????...");
//        progressDialog.show();
//        progressDialog.setCancelable(true);
        loadingDialog.show();
        tvRecord = (TextView) mView.findViewById(R.id.tvRecord);
        tvAddress = (TextView) mView.findViewById(R.id.tvAddress);

        ivAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainActivity.mViewPager.setCurrentItem(3);
            }
        });


        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("??????????????????:");
                builder.setMessage(mAddress + "");
                builder.setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.mainActivity.locationService.start();
//                        progressDialog.setMessage("????????????...");
//                        progressDialog.show();
                        loadingDialog.show();
                    }
                });
                builder.setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        locationDialog.dismiss();
                    }
                });

                locationDialog = builder.create();
                locationDialog.show();


            }
        });

        layoutRefresh = (SwipeRefreshLayout) mView.findViewById(R.id.layoutRefresh);
        layoutRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHomePageInfo();
            }
        });

        ivRecommend = (SelectableRoundedImageView) mView.findViewById(R.id.ivRecommend);
        tvRecommendName = (TextView) mView.findViewById(R.id.tvRecommendName);
        tvConstitutionPercentage = (TextView) mView.findViewById(R.id.tvConstitutionPercentage);
        tvRecommendPrice = (TextView) mView.findViewById(R.id.tvRecommendPrice);
        initBanner();
        initNearby();
        initRecommend();
        initRecord();
        initJump();
        initSearch();


    }

    private void initSearch() {
        etSearch = (EditText) mView.findViewById(R.id.etSearch);
        //?????????????????????????????????????????????????????????????????????
        etSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {//?????????????????????
                    if (etSearch.getText().toString().trim().length() < 2) {
                        Toast.makeText(getActivity(), "?????????????????????", Toast.LENGTH_SHORT).show();
                    } else {
                        SearchHistory mSearchHistory = new SearchHistory();
                        mSearchHistory.setSearchHistory(etSearch.getText().toString().trim());
                        mSearchHistory.saveOrUpdate("searchHistory=?", mSearchHistory.getSearchHistory());
                        Intent mIntent = new Intent(getActivity(), SearchResultActivity.class);
                        mIntent.putExtra("searchText", mSearchHistory.getSearchHistory());
                        startActivity(mIntent);
                    }
                }

                return false;
            }
        });

    }

    private void initJump() {
        layoutNearby = (LinearLayout) mView.findViewById(R.id.layoutNearby);
        layoutRecommend = (LinearLayout) mView.findViewById(R.id.layoutRecommend);
        layoutRecord = (LinearLayout) mView.findViewById(R.id.layoutRecord);

        layoutNearby.setOnClickListener(this);
        layoutRecommend.setOnClickListener(this);
        layoutRecord.setOnClickListener(this);
    }

    private void initRecord() {
        rvRecord = (RecyclerView) mView.findViewById(R.id.rvRecord);
        //????????????
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvRecord.setLayoutManager(linearLayoutManager);
        recordList = new ArrayList<>();
        mRvRecordHomePageAdapter = new RvRecordHomePageAdapter(getActivity(), recordList);
        rvRecord.setAdapter(mRvRecordHomePageAdapter);
    }

    //????????????
    private void initRecommend() {
        rvRecommend = (RecyclerView) mView.findViewById(R.id.rvRecommend);
        recommendList = new ArrayList<>();
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvRecommend.setLayoutManager(mGridLayoutManager);
        mRvRecommendAdapter = new RvRecommendAdapter(getActivity(), recommendList);
        rvRecommend.setAdapter(mRvRecommendAdapter);
    }

    private ImageView ivCircle01, ivCircle02, ivCircle03;


    //????????????
    private void initNearby() {
        ivCircle01 = (ImageView) mView.findViewById(R.id.ivCircle01);
        ivCircle02 = (ImageView) mView.findViewById(R.id.ivCircle02);
        ivCircle03 = (ImageView) mView.findViewById(R.id.ivCircle03);
        //????????????
        nearbyFragmentRight = new NearbyFragment();
        nearbyFragmentMiddle = new NearbyFragment();
        nearbyFragmentLeft = new NearbyFragment();

        vpNearby = (ViewPager) mView.findViewById(R.id.vpNearby);
        nearbyList = new ArrayList<>();
        nearbyList.add(nearbyFragmentLeft);
        nearbyList.add(nearbyFragmentMiddle);
        nearbyList.add(nearbyFragmentRight);

        mNearbyFragmentPagerAdapter = new NearbyFragmentPagerAdapter(getFragmentManager(), nearbyList);
        vpNearby.setAdapter(mNearbyFragmentPagerAdapter);
        vpNearby.setOffscreenPageLimit(1);
        vpNearby.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //???????????????
                ivCircle01.setImageResource(R.mipmap.circle3);
                ivCircle02.setImageResource(R.mipmap.circle3);
                ivCircle03.setImageResource(R.mipmap.circle3);
                if (position == 0) {
                    ivCircle01.setImageResource(R.mipmap.circle2);
                } else if (position == 1) {
                    ivCircle02.setImageResource(R.mipmap.circle2);
                } else if (position == 2) {
                    ivCircle03.setImageResource(R.mipmap.circle2);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        vpNearby.setOffscreenPageLimit(2);
    }


    private List<ArticleInfo> mArticleInfoList;
    private List<String> mArticlePathList;

    Banner banner;

    //?????????????????????
    private void initBanner() {
        mArticleInfoList = new ArrayList<>();
        mArticlePathList = new ArrayList<>();
        for (int i = 0; i < mArticleInfoList.size(); i++) {
            mArticlePathList.add(mArticleInfoList.get(i).getTitleImage());
        }


        banner = (Banner) mView.findViewById(R.id.banner_1);
        //?????????????????????
        banner.setImageLoader(new GlideImageLoader());
        //??????????????????
//        banner.setImages(mArticlePathList);
        //banner?????????????????????????????????????????????
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent mIntent = new Intent(getActivity(), ArticleDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", mArticleInfoList.get(position));
                bundle.putInt("pos", position);
                mIntent.putExtras(bundle);
                startActivity(mIntent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutNearby:
//                jumpActivity(MainActivity.class);
                jumpActivity2("1");
                break;

            case R.id.layoutRecommend:
                jumpActivity2("2");
                break;
            case R.id.layoutRecord:
                Intent mIntent = new Intent(getActivity(), DietRecordActivity.class);
//                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), tvRecord, "tvTitle");
//                startActivity(mIntent, options.toBundle());
                startActivity(mIntent);
                break;
        }

    }

    private void jumpActivity(Class<?> cls) {
        Intent mIntent = new Intent(getActivity(), cls);
        startActivity(mIntent);
    }

    private void jumpActivity2(String tag) {
        Intent mIntent = new Intent(getActivity(), RecommendListActivity.class);
        mIntent.putExtra("tag", tag);
        startActivity(mIntent);
    }


    /**
     * ??????????????????
     */
    private void getHomePageInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");
        map.put("CoordY", MyApplication.Latitude + "");
        map.put("CoordX", MyApplication.Longitude + "");
        HttpUtils.sendHttpAddToken(getActivity(), AppUrl.TITLE_PAGE
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        if (loadingDialog.isShowing()) {
                            loadingDialog.dismiss();
                        }

                        layoutRefresh.setRefreshing(false);
                        LogUtil.i("????????????", response);
                        Gson gson = new Gson();
                        TitlePage mTitlePage = gson.fromJson(response, TitlePage.class);
                        int code = mTitlePage.getHttpCode();
                        if (code == 200) {
                            //????????????????????????
                            List<RestaurantInfo> mListData = mTitlePage.getListData();
                            setNearbyInfo(mListData);//????????????????????????
                            //??????????????????
                            final List<RecipeInfo> mListData2 = mTitlePage.getListData2();
                            //??????2?????????
                            if (mListData2.size() >= 2) {
                                recommendList.clear();
                                recommendList.addAll(mListData2.subList(1, mListData2.size()));
                                mRvRecommendAdapter.notifyDataSetChanged();
                                //??????????????????
                                Glide
                                        .with(getActivity())
                                        .load(mListData2.get(0).getTitleImage())
                                        .asBitmap()
                                        .error(R.mipmap.error_photo)
                                        .into(ivRecommend);

                                tvRecommendName.setText(mListData2.get(0).getName());

                                ivRecommend.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent mIn = new Intent(getActivity(), RecipesDetailsActivity.class);
                                        mIn.putExtra("RecipeId", mListData2.get(0).getId() + "");
                                        mIn.putExtra("isFromRest", false);
                                        startActivity(mIn);
                                    }
                                });

                                int percentage = mListData2.get(0).getConstitutionPercentage();
                                tvConstitutionPercentage.setText(percentage + "%");
                                MyUtils.setTextViewColor(tvConstitutionPercentage, percentage, getActivity());
                                tvRecommendPrice.setText("???" + mListData2.get(0).getPrice());
                            }

                            mArticleInfoList.clear();
                            mArticleInfoList.addAll(mTitlePage.getListData3());

                            mArticlePathList.clear();
                            List<String> mPathList = new ArrayList<>();
                            for (int i = 0; i < mArticleInfoList.size(); i++) {
                                mPathList.add(mArticleInfoList.get(i).getTitleImage());
                            }
                            banner.update(mPathList);

                            recordList.clear();
                            recordList.addAll(mTitlePage.getListData4());
                            mRvRecordHomePageAdapter.notifyDataSetChanged();
                            if(recordList.size()==0){
                                layoutRecordMain.setVisibility(View.GONE);
                            }else {
                                layoutRecordMain.setVisibility(View.VISIBLE);
                            }


                        } else {
                            String message = mTitlePage.getMessage();
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (loadingDialog.isShowing()) {
                            loadingDialog.dismiss();
                        }
                        LogUtil.i("????????????", e.toString());
                        if (layoutRefresh != null) {
                            layoutRefresh.setRefreshing(false);
                        }
                    }
                });
        Message message = Message.obtain();
        message.obj = mAddress;
        handler.sendMessage(message);

    }

    private void setNearbyInfo(List<RestaurantInfo> mListData) {


        if (mListData.size() <= 3) {
            //???????????????
//            nearbyList.add(new NearbyFragment(mListData));
            nearbyFragmentLeft.refreshData(mListData);
//            mNearbyFragmentPagerAdapter.notifyDataSetChanged();
            //???????????????
            ivCircle01.setVisibility(View.GONE);
            ivCircle02.setVisibility(View.GONE);
            ivCircle03.setVisibility(View.GONE);
        } else if (mListData.size() > 3 & mListData.size() <= 6) {
            nearbyFragmentLeft.refreshData(mListData.subList(0, 3));
            nearbyFragmentMiddle.refreshData(mListData.subList(3, mListData.size()));
//            nearbyList.add(new NearbyFragment(mListData.subList(0, 3)));
//            nearbyList.add(new NearbyFragment());
//            mNearbyFragmentPagerAdapter.notifyDataSetChanged();
            //???????????????
            ivCircle01.setVisibility(View.VISIBLE);
            ivCircle02.setVisibility(View.VISIBLE);
            ivCircle03.setVisibility(View.GONE);
        } else {
            nearbyFragmentLeft.refreshData(mListData.subList(0, 3));
            nearbyFragmentMiddle.refreshData(mListData.subList(3, 6));
            nearbyFragmentRight.refreshData(mListData.subList(6, mListData.size()));
//            nearbyList.add(new NearbyFragment(mListData.subList(0, 3)));
//            nearbyList.add(new NearbyFragment(mListData.subList(3, 6)));
//            nearbyList.add(new NearbyFragment(mListData.subList(6, mListData.size())));
//            mNearbyFragmentPagerAdapter.notifyDataSetChanged();

//            mNearbyFragmentPagerAdapter.setFragments(nearbyList);
            //???????????????
            ivCircle01.setVisibility(View.VISIBLE);
            ivCircle02.setVisibility(View.VISIBLE);
            ivCircle03.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.ivAlarm)
    public void onViewClicked() {
    }

    private void getIntegral() {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");
        HttpUtils.sendHttpAddToken(getActivity(), AppUrl.USER_SCORE_INFO
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("????????????", response);
                        Gson gson = new Gson();
                        UserScoreInfo mUserScoreInfo = gson.fromJson(response, UserScoreInfo.class);
                        if (mUserScoreInfo.getHttpCode() == 200) {
                            RankInfo mRankInfo = mUserScoreInfo.getModel1();
                            tvCurrentName.setText(mRankInfo.getCurrent_Name());
//                            tvCurrentScore.setText(ValueUtils.getDoubleValueString(mRankInfo.getCurrent_Score(), 1));
                            tvNextScore.setText(ValueUtils.getDoubleValueString(mRankInfo.getNext_Score(), 1));

                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("????????????", e.toString());
                    }
                });


    }
}
