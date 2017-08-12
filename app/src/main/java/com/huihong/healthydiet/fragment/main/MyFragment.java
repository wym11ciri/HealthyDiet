package com.huihong.healthydiet.fragment.main;

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
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.model.gsonbean.GetUserBodyInfo;
import com.huihong.healthydiet.model.httpmodel.PersonalAllInfo;
import com.huihong.healthydiet.model.mybean.PersonalInfo;
import com.huihong.healthydiet.utils.MyUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
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
    Unbinder unbinder;
    @BindView(R.id.mTreeView)
    TreeView mTreeView;

    private View mView;


    private LinearLayout layoutSettings;
    private LinearLayout layoutBodyData, layoutMajorTest;
    private LinearLayout layoutLike;
    private SelectableRoundedImageView ivHead;
    private RecyclerView rvIntegral;//获取积分的列表
    private RvIntegralAdapter rvIntegralAdapter;
    private ImageView ivSetting;
    private LinearLayout layoutIntegralRecord;


    //个人信息
    private TextView tvAge, tvName, tvSex, tvHeight, tvWeight;
    private ImageView ivConstitution;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Fragment的时候获取最新的用户信息
        getPersonalInfo();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my, null);
        unbinder = ButterKnife.bind(this, mView);
        initUI();
        initPersonalUI();

        return mView;
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


        rvIntegral = (RecyclerView) mView.findViewById(R.id.rvIntegral);
        List<String> zzz = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            zzz.add("");
        }

        rvIntegralAdapter = new RvIntegralAdapter(getActivity(), zzz);
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


    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i("嘻嘻");
        //由于个人信息会随时改变这里会从缓存中重新读取个人信息
        PersonalInfo personalInfo = CacheUtils.getPersonalInfo(getActivity());

        tvName.setText(personalInfo.getName());
        LogUtil.i("zangyi222",personalInfo.getName());
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
