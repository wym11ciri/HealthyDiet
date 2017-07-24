package com.huihong.healthydiet.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.BodyDataActivity;
import com.huihong.healthydiet.activity.PersonalPreferenceActivity;
import com.huihong.healthydiet.activity.SettingsActivity;

/**
 * Created by zangyi_shuai_ge on 2017/7/10
 */

public class MyFragment extends Fragment {
    private View mView;
    private LinearLayout layoutSettings;
    private  LinearLayout layoutBodyData;

    private  LinearLayout   layoutLike;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my, null);
        initUI();
        return mView;
    }

    private void initUI() {
        layoutSettings= (LinearLayout) mView.findViewById(R.id.layoutSettings);
        layoutSettings.setOnClickListener(new View.OnClickListener() {
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
    }
}
