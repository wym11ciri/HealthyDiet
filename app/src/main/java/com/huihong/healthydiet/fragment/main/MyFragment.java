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

import com.bumptech.glide.Glide;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.BodyDataActivity;
import com.huihong.healthydiet.activity.IntegralRecordActivity;
import com.huihong.healthydiet.activity.TestMajorActivity;
import com.huihong.healthydiet.activity.PersonalPreferenceActivity;
import com.huihong.healthydiet.activity.SettingsActivity;
import com.huihong.healthydiet.adapter.RvIntegralAdapter;
import com.huihong.healthydiet.view.TreeView;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/10
 * 我的界面
 */

public class MyFragment extends Fragment {
    private View mView;
    private LinearLayout layoutSettings;
    private  LinearLayout layoutBodyData,layoutMajorTest;

    private  LinearLayout   layoutLike;

    private SelectableRoundedImageView ivHead;



    private RecyclerView rvIntegral;//获取积分的列表
    private RvIntegralAdapter rvIntegralAdapter;


    private ImageView ivSetting;
    private TreeView mTreeView;

    private LinearLayout layoutIntegralRecord;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my, null);
        initUI();
        return mView;
    }

    private void initUI() {
        ivSetting= (ImageView) mView.findViewById(R.id.ivSetting);
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(getActivity(), SettingsActivity.class);
                startActivity(mIntent);
            }
        });
        layoutLike= (LinearLayout) mView.findViewById(R.id.layoutLike);
        layoutLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(getActivity(), PersonalPreferenceActivity.class);
                startActivity(mIntent);
            }
        });

        layoutBodyData= (LinearLayout) mView.findViewById(R.id.layoutBodyData);
        layoutBodyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(getActivity(), BodyDataActivity.class);
                startActivity(mIntent);
            }
        });

        layoutMajorTest= (LinearLayout) mView.findViewById(R.id.layoutMajorTest);
        layoutMajorTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(getActivity(), TestMajorActivity.class);
//                Intent mIntent=new Intent(getActivity(), SimpleTestActivity.class);
                startActivity(mIntent);
            }
        });

        ivHead= (SelectableRoundedImageView) mView.findViewById(R.id.ivHead);
        Glide.with(getActivity())
                .load("http://p.qq181.com/cms/1210/2012100413195471481.jpg")
                .asBitmap()
                .into(ivHead);

        rvIntegral= (RecyclerView) mView.findViewById(R.id.rvIntegral);
        List<String> zzz=new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            zzz.add("");
        }

        rvIntegralAdapter=new RvIntegralAdapter(getActivity(),zzz);
        rvIntegral.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rvIntegral.setAdapter(rvIntegralAdapter);

        mTreeView= (TreeView) mView.findViewById(R.id.mTreeView);
//        mTreeView.startThread();

        layoutIntegralRecord= (LinearLayout) mView.findViewById(R.id.layoutIntegralRecord);
        layoutIntegralRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(getActivity(), IntegralRecordActivity.class);
                startActivity(mIntent);
            }
        });

    }



}
