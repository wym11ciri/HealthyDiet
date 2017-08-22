package com.huihong.healthydiet.fragment.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.BodyDataActivity;
import com.huihong.healthydiet.activity.IntegralRecordActivity;
import com.huihong.healthydiet.activity.PersonalPreferenceActivity;
import com.huihong.healthydiet.activity.SettingsActivity;
import com.huihong.healthydiet.activity.TestMajorActivity;
import com.huihong.healthydiet.adapter.RvIntegralAdapter;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.mInterface.CustomViewOnSizeChangedListener;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.mInterface.OnLeafClickListener;
import com.huihong.healthydiet.model.gsonbean.GetClickScore;
import com.huihong.healthydiet.model.gsonbean.GetScoreList;
import com.huihong.healthydiet.model.gsonbean.GetUserBodyInfo;
import com.huihong.healthydiet.model.gsonbean.UserScoreInfo;
import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.model.httpmodel.LeafInfo;
import com.huihong.healthydiet.model.httpmodel.PersonalAllInfo;
import com.huihong.healthydiet.model.httpmodel.RankInfo;
import com.huihong.healthydiet.model.mybean.PersonalInfo;
import com.huihong.healthydiet.utils.MyUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.common.ValueUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.huihong.healthydiet.view.TreeView;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/10
 * 我的界面
 */

public class MyFragment extends Fragment {
    @BindView(R.id.iv01)
    ImageView iv01;
    @BindView(R.id.iv02)
    ImageView iv02;
    @BindView(R.id.iv03)
    ImageView iv03;
    @BindView(R.id.iv04)
    ImageView iv04;
    @BindView(R.id.mTreeView)
    TreeView mTreeView;
    @BindView(R.id.tvCurrentName)
    TextView tvCurrentName;
    @BindView(R.id.tvCurrentScore)
    TextView tvCurrentScore;
    @BindView(R.id.tvNextScore)
    TextView tvNextScore;
    @BindView(R.id.rvIntegral)
    RecyclerView rvIntegral;
    @BindView(R.id.ivTree)
    ImageView ivTree;
    Unbinder unbinder;
    private View mView;


    private boolean treeView = false;
    int levels = 1;
    private LinearLayout layoutSettings;
    private LinearLayout layoutBodyData, layoutMajorTest;
    private LinearLayout layoutLike;
    private SelectableRoundedImageView ivHead;
    private RvIntegralAdapter rvIntegralAdapter;
    private ImageView ivSetting;
    private LinearLayout layoutIntegralRecord;
    private List<GetScoreList.ListDataBean> mListDataBean;
    //个人信息
    private TextView tvAge, tvName, tvSex, tvHeight, tvWeight;
    private ImageView ivConstitution;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Fragment的时候获取最新的用户信息
        levels = CacheUtils.getLevels(getActivity());
        getPersonalInfo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my, null);
        unbinder = ButterKnife.bind(this, mView);
        setTreeView(levels);
        mTreeView.setCustomViewOnSizeChangedListener(new CustomViewOnSizeChangedListener() {
            @Override
            public void OnSizeChanged() {
                treeView = true;
                mTreeView.setLevels(levels);
                getLeafInfo();
                getScoreList();
                getIntegral();
            }
        });


        //叶子的点击事件
        mTreeView.setOnLeafClickListener(new OnLeafClickListener() {
            @Override
            public void onClick(int pos) {
                switch (pos) {
                    case 0:
                        if (leafinfo01 != null) {
                            getScore(leafinfo01.getListId());
                        }
                        break;
                    case 1:
                        if (leafinfo02 != null) {
                            getScore(leafinfo02.getListId());
                        }
                        break;
                    case 2:
                        if (leafinfo03 != null) {
                            getScore(leafinfo03.getListId());
                        }
                        break;
                }
            }
        });

        initUI();
        initPersonalUI();


        return mView;
    }

    private void setTreeView(int i) {

        switch (i) {
            case 1:
                ivTree.setImageResource(R.mipmap.tree_1);
                break;
            case 2:
                ivTree.setImageResource(R.mipmap.tree_2);
                break;
            case 3:
                ivTree.setImageResource(R.mipmap.tree_3);
                break;
            case 4:
                ivTree.setImageResource(R.mipmap.tree_4);
                break;
            case 5:
                ivTree.setImageResource(R.mipmap.tree_5);
                break;
            case 6:
                ivTree.setImageResource(R.mipmap.tree_6);
                break;
            case 7:
                ivTree.setImageResource(R.mipmap.tree_7);
                break;
            case 8:
                ivTree.setImageResource(R.mipmap.tree_8);
                break;
        }


    }

    private AlertDialog dialog;

    private void getIntegral() {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");
        HttpUtils.sendHttpAddToken(getActivity(), AppUrl.USER_SCORE_INFO
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("获取积分", response);
                        Gson gson = new Gson();
                        UserScoreInfo mUserScoreInfo = gson.fromJson(response, UserScoreInfo.class);
                        if (mUserScoreInfo.getHttpCode() == 200) {
                            RankInfo mRankInfo = mUserScoreInfo.getModel1();
                            tvCurrentName.setText(mRankInfo.getCurrent_Name());
                            tvCurrentScore.setText(ValueUtils.getDoubleValueString(mRankInfo.getCurrent_Score(), 1));
                            tvNextScore.setText(ValueUtils.getDoubleValueString(mRankInfo.getNext_Score(), 1));
                            if (mRankInfo.getCurrent_Lv() > levels) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("恭喜你！升级啦");
                                builder.setPositiveButton("知道啦", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog = builder.create();
                                dialog.show();
                            }

                            if (levels != mRankInfo.getCurrent_Lv()) {
                                mTreeView.setLevels(mRankInfo.getCurrent_Lv());
                                levels = mRankInfo.getCurrent_Lv();
                                CacheUtils.setLevels(getActivity(), levels);
                                setTreeView(levels);
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("获取积分", e.toString());
                    }
                });


    }


    //点击叶子获取积分
    private void getScore(String listId) {
        Map<String, String> map = new HashMap<>();
        map.put("ScoreIds", listId);
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(getActivity(), AppUrl.CLICK_SCORE
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("点击获取积分", response);
                        Gson gson = new Gson();
                        HttpBaseInfo mHttpBaseInfo = gson.fromJson(response, HttpBaseInfo.class);
                        if (mHttpBaseInfo.getHttpCode() == 200) {
                            //刷一下积分获取列表
                            getScoreList();
                            //获取等级信息
                            getIntegral();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("点击获取积分", e.toString());
                    }
                });


    }

    private void initPersonalUI() {


        tvAge = (TextView) mView.findViewById(R.id.tvAge);
        tvName = (TextView) mView.findViewById(R.id.tvName);
        tvSex = (TextView) mView.findViewById(R.id.tvSex);
        tvHeight = (TextView) mView.findViewById(R.id.tvHeight);
        tvWeight = (TextView) mView.findViewById(R.id.tvWeight);
        ivConstitution = (ImageView) mView.findViewById(R.id.ivConstitution);
    }

    private void initUI() {
        LinearLayout layoutSetting = (LinearLayout) mView.findViewById(R.id.layoutSetting);
//        ivSetting = (ImageView) mView.findViewById(R.id.ivSetting);
        layoutSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(mIntent);
            }
        });
        layoutLike = (LinearLayout) mView.findViewById(R.id.layoutLike);
        layoutLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), PersonalPreferenceActivity.class);
                startActivity(mIntent);
            }
        });

        layoutBodyData = (LinearLayout) mView.findViewById(R.id.layoutBodyData);
        layoutBodyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), BodyDataActivity.class);
                startActivity(mIntent);
            }
        });

        layoutMajorTest = (LinearLayout) mView.findViewById(R.id.layoutMajorTest);
        layoutMajorTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), TestMajorActivity.class);
                startActivity(mIntent);
            }
        });

        ivHead = (SelectableRoundedImageView) mView.findViewById(R.id.ivHead);


        mListDataBean = new ArrayList<>();

        rvIntegralAdapter = new RvIntegralAdapter(getActivity(), mListDataBean);
        rvIntegral.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvIntegral.setAdapter(rvIntegralAdapter);


        layoutIntegralRecord = (LinearLayout) mView.findViewById(R.id.layoutIntegralRecord);
        layoutIntegralRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), IntegralRecordActivity.class);
                startActivity(mIntent);
            }
        });


    }

    private void getScoreList() {

        Map<String, String> map = new HashMap<>();
        map.put("PageNo", "1");
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(getActivity(), AppUrl.GET_SCORE_LIST
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("获取积分列表", response);
                        Gson gson = new Gson();
                        GetScoreList mGetScoreList = gson.fromJson(response, GetScoreList.class);
                        if (mGetScoreList.getHttpCode() == 200) {
                            if (mGetScoreList.getListData().size() >= 3) {
                                mListDataBean.clear();
                                mListDataBean.addAll(mGetScoreList.getListData().subList(0, 3));
                            } else {
                                mListDataBean.clear();
                                mListDataBean.addAll(mGetScoreList.getListData());
                            }
                            rvIntegralAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("获取积分列表", e.toString());
                    }
                });

    }


    @Override
    public void onResume() {
        super.onResume();
        //由于个人信息会随时改变这里会从缓存中重新读取个人信息
        PersonalInfo personalInfo = CacheUtils.getPersonalInfo(getActivity());

        tvName.setText(personalInfo.getName());
        tvHeight.setText(personalInfo.getHeight() + "cm");
        tvWeight.setText(personalInfo.getWeight() + "kg");
        if (personalInfo.isMan()) {
            tvSex.setText("男");
            iv01.setImageResource(R.mipmap.male);
            iv02.setImageResource(R.mipmap.age_male);
            iv03.setImageResource(R.mipmap.height_male);
            iv04.setImageResource(R.mipmap.weight_male);

        } else {
            iv01.setImageResource(R.mipmap.female);
            iv02.setImageResource(R.mipmap.age_female);
            iv03.setImageResource(R.mipmap.height_female);
            iv04.setImageResource(R.mipmap.weight_female);
            tvSex.setText("女");
        }
        tvAge.setText(personalInfo.getAge() + "岁");

        Glide.with(getActivity())
                .load(personalInfo.getHeadImageUrl())
                .asBitmap()
                .error(R.mipmap.error_head)
                .into(ivHead);

        String type = personalInfo.getConstitution();
        ivConstitution.setVisibility(View.VISIBLE);
        MyUtils.setImageViewType(ivConstitution, type);


        //重新获取叶子
        if (treeView) {
            getLeafInfo();
        }


    }

    private LeafInfo leafinfo01;
    private LeafInfo leafinfo02;
    private LeafInfo leafinfo03;

    private void getLeafInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");
        HttpUtils.sendHttpAddToken(getActivity(), AppUrl.GET_CLICK_SCORE
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，获取叶子" + response);
                        Gson gson = new Gson();
                        GetClickScore mGetClickScore = gson.fromJson(response, GetClickScore.class);
                        if (mGetClickScore.getHttpCode() == 200) {
                            leafinfo01 = mGetClickScore.getModel1();
                            leafinfo02 = mGetClickScore.getModel2();
                            leafinfo03 = mGetClickScore.getModel3();
                            mTreeView.invalidateView(mGetClickScore.getModel1(), mGetClickScore.getModel2(), mGetClickScore.getModel3());
                        }

                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("获取叶子", e.toString());
                    }
                });
    }


    /**
     * 获取用户个人信息
     */
    private void getPersonalInfo() {

        Map<String, String> map = new HashMap<>();
        map.put("Id", SPUtils.get(getActivity(), "UserId", 0) + "");
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");
        HttpUtils.sendHttpAddToken(getActivity(), AppUrl.GET_USER_BODY_INFO
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，获取用户信息" + response);
                        Gson gson = new Gson();
                        GetUserBodyInfo mGetUserBodyInfo = gson.fromJson(response, GetUserBodyInfo.class);
                        int code = mGetUserBodyInfo.getHttpCode();
                        if (code == 200) {
                            if (mGetUserBodyInfo.getListData().size() > 0) {
                                PersonalAllInfo mInfo = mGetUserBodyInfo.getListData().get(0);

                                PersonalInfo personalInfo = new PersonalInfo();
                                personalInfo.setName(mInfo.getName());
                                personalInfo.setHeight(mInfo.getHeight());
                                personalInfo.setWeight(mInfo.getWeight());
                                personalInfo.setMan(mInfo.isSex());
                                personalInfo.setHeadImageUrl(mInfo.getHeadImage());
                                personalInfo.setConstitution(mInfo.getConstitution());
                                personalInfo.setAge(mInfo.getAge());
                                personalInfo.setPhone(mInfo.getPhone());
                                CacheUtils.putPersonalInfo(getActivity(), personalInfo);
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("西部喜欢", e.toString());
                    }
                });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
