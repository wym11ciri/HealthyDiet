package com.huihong.healthydiet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.SleepSettingsActivity;
import com.huihong.healthydiet.mInterface.CircleListener;
import com.huihong.healthydiet.view.TimeSelectView;

/**
 * Created by zangyi_shuai_ge on 2017/7/14
 */

public class SleepFragment extends Fragment {

    private View mView;
    private TimeSelectView mTimeSelectView;
    private TextView tvTimeStart, tvTimeEnd;
    private TextView tvDHour, tvDMin;

    private int startHour = 23;
    private int endHour = 8;
    private int startMin = 0;
    private int endMin = 0;

    private LinearLayout layoutSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_sleep, null);
            initUI();
        }
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

//        mTimeSelectView.init(startAngel, endAngel);
    }

    private void initUI() {
        mTimeSelectView = (TimeSelectView) mView.findViewById(R.id.mTimeSelectView);




        tvTimeStart = (TextView) mView.findViewById(R.id.tvTimeStart);
        tvTimeEnd = (TextView) mView.findViewById(R.id.tvTimeEnd);
        tvDHour = (TextView) mView.findViewById(R.id.tvDHour);
        tvDMin = (TextView) mView.findViewById(R.id.tvDMin);

        mTimeSelectView.setCircleListener(new CircleListener() {
            @Override
            public void move(boolean isSetStart, float mAngle, boolean isEnd) {


                if (mAngle < 0) {
                    mAngle = mAngle + 360;
                }

                if (!isEnd) {
                    if (isSetStart) {

                        //就寝时间需要加上12小时
                        int a = (int) mAngle / 30;//获得小时
                        int hour = a + 3;
                        if (hour > 12) {
                            hour = hour - 12;
                        }
                        hour = hour + 12;
                        int b = (int) mAngle % 30;//获得分
                        int min = (int) (60 * (b / 30.0001));

                        startHour = hour;
                        startMin = min;

                        if (min < 10) {
                            tvTimeStart.setText(hour + ":0" + min);
                        } else {
                            tvTimeStart.setText(hour + ":" + min);
                        }
                    } else {
                        //就寝时间需要加上12小时
                        int a = (int) mAngle / 30;//获得小时
                        int hour = a + 3;
                        if (hour > 12) {
                            hour = hour - 12;
                        }
                        String hour2 = hour + "";
                        if (hour < 10) {
                            hour2 = "0" + hour;
                        }
                        int b = (int) mAngle % 30;//获得分
                        int min = (int) (60 * (b / 30.0001));

                        endHour = hour;
                        endMin = min;
                        if (min < 10) {
                            tvTimeEnd.setText(hour2 + ":0" + min);
                        } else {
                            tvTimeEnd.setText(hour2 + ":" + min);
                        }
                    }
                    //计算时间差

                    int startHour1 = 24 - startHour - 1;//剩余多少小时
                    int startMin1 = 60 - startMin;


//                    int start=12*60-((60-startMin)+(12-(startHour-12))*60);
//                    int end=endHour*60+endMin;
//
//                    int _D=start+end;
//
//                    int _H=_D/60;
//                    int _M=_D%60;
                    int _DH = startHour1 + endHour;
                    int _DM = startMin1 + endMin;
                    if (_DM > 60) {
                        _DM = _DM - 60;
                        _DH++;
                    }

                    tvDHour.setText("" + _DH);
                    tvDMin.setText("" + _DM);

                }
            }
        });
        layoutSettings= (LinearLayout) mView.findViewById(R.id.layoutSettings);
        layoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIn=new Intent(getActivity(), SleepSettingsActivity.class);
                startActivity(mIn);
            }
        });

    }
}
