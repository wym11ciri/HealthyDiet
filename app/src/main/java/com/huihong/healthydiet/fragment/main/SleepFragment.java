package com.huihong.healthydiet.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.SleepSettingsActivity;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.mInterface.CircleListener;
import com.huihong.healthydiet.mInterface.SwitchListener;
import com.huihong.healthydiet.utils.MyUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.view.SleepChartView;
import com.huihong.healthydiet.view.TimeSelectView;
import com.huihong.healthydiet.widget.SwitchImageView;

import java.util.List;

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
    private int _DH = 0;
    private int _DM = 0;
    private LinearLayout layoutSettings;
    //是否开启闹铃切换按钮
    private SwitchImageView mSwitchImageView;
    private TextView tvWeek;
    private NestedScrollView mNestedScrollView;
    private SleepChartView mSleepChartView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_sleep, null);
            initUI();
        }
        return mView;
    }

    private void initUI() {
        mNestedScrollView = (NestedScrollView) mView.findViewById(R.id.mNestedScrollView);
        mSleepChartView = (SleepChartView) mView.findViewById(R.id.mSleepChartView);
        initWeekText();
        initTimeSelectView();//初始化自定义时间选择器
        initSettingLayout();//初始化设置按钮布局
    }

    private void initWeekText() {
        tvWeek = (TextView) mView.findViewById(R.id.tvWeek);
        List<Boolean> cacheValueList = CacheUtils.getSleepWeek(getActivity());
        String text = "";
        for (int i = 0; i < cacheValueList.size(); i++) {
            if (cacheValueList.get(i)) {
                text = text + MyUtils.getWeek(i) + " ";
            }
        }
        tvWeek.setText(text);
    }

    //初始化设置按钮布局
    private void initSettingLayout() {
        mSwitchImageView = (SwitchImageView) mView.findViewById(R.id.mSwitchImageView);


        //从缓存中拿取
        boolean AlarmOpen = CacheUtils.isOpenAlarm(getActivity());
        mSwitchImageView.setChoose(AlarmOpen);

        mSwitchImageView.setmSwitchListener(new SwitchListener() {
            @Override
            public void mSwitch(boolean isChoose) {
                if (isChoose) {
                    CacheUtils.setOpenAlarm(getActivity(), true);
                } else {
                    CacheUtils.setOpenAlarm(getActivity(), false);
                }
            }
        });


        layoutSettings = (LinearLayout) mView.findViewById(R.id.layoutSettings);
        layoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIn = new Intent(getActivity(), SleepSettingsActivity.class);
                startActivityForResult(mIn, 10086);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086) {
            if (resultCode == 10086) {
                List<Boolean> cacheValueList = CacheUtils.getSleepWeek(getActivity());
                String text = "";
                for (int i = 0; i < cacheValueList.size(); i++) {
                    if (cacheValueList.get(i)) {
                        text = text +MyUtils.getWeek(i) + " ";
                    }
                }
                tvWeek.setText(text);
            }
        }
    }

    private boolean isStartAfter=false;
    private  float lastStartmAngle=250;
    //初始化时间选择器及其附属控件
    private void initTimeSelectView() {
        startHour = (int) SPUtils.get(getActivity(), "startHour", 23);
        startMin = (int) SPUtils.get(getActivity(), "startMin", 0);
        endHour = (int) SPUtils.get(getActivity(), "endHour", 8);
        endMin = (int) SPUtils.get(getActivity(), "endMin", 0);
        _DH = (int) SPUtils.get(getActivity(), "_DH", 9);
        _DM = (int) SPUtils.get(getActivity(), "_DM", 0);


        mTimeSelectView = (TimeSelectView) mView.findViewById(R.id.mTimeSelectView);//时间选择器
        tvTimeStart = (TextView) mView.findViewById(R.id.tvTimeStart);//开始时间
        tvTimeEnd = (TextView) mView.findViewById(R.id.tvTimeEnd);//结束时间
        //时间差
        tvDHour = (TextView) mView.findViewById(R.id.tvDHour);
        tvDMin = (TextView) mView.findViewById(R.id.tvDMin);

        if (startMin < 10) {
            tvTimeStart.setText(startHour + ":0" + startMin);
        } else {
            tvTimeStart.setText(startHour + ":" + startMin);
        }

        if (endHour < 10) {
            if (endMin < 10) {
                tvTimeEnd.setText("0" + endHour + ":0" + endMin);
            } else {
                tvTimeEnd.setText("0" + endHour + ":" + endMin);
            }
        } else {
            if (endMin < 10) {
                tvTimeEnd.setText(endHour + ":0" + endMin);
            } else {
                tvTimeEnd.setText(endHour + ":" + endMin);
            }
        }
        tvDHour.setText("" + _DH);
        tvDMin.setText("" + _DM);

        mTimeSelectView.setCircleListener(new CircleListener() {
            @Override
            public void move(boolean isSetStart, float mAngle, boolean isEnd) {







                //
                if (mAngle < 0) {
                    mAngle = mAngle + 360;
                }

                if (!isEnd) {
                    mNestedScrollView.requestDisallowInterceptTouchEvent(true);

                    if (isSetStart) {
                        //这里拿到了角度我们需要去判断当前是24小时中的 0-12 小时还是13-24
                        if(isStartAfter){
                            LogUtil.i("测试","下午");
                        }else {
                            LogUtil.i("测试","上午");
                        }

                        //需要去判断临界值
                        //上次角度上350度到360度
                        //这次角度是0度到10度的
                        //那么触发临界

                        if(lastStartmAngle>350&&lastStartmAngle<=360){
                            if(mAngle<10&&mAngle>=0){
                                //触发临界
                                isStartAfter=!isStartAfter;
                            }
                        }
                        lastStartmAngle=mAngle;
                        if(isStartAfter){
                            LogUtil.i("测试","下午");
                        }else {
                            LogUtil.i("测试","上午");
                        }



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
                        mSleepChartView.setLimitLineH(hour, min, true);

                        if (min < 10) {
                            tvTimeStart.setText(hour + ":0" + min);
                        } else {
                            tvTimeStart.setText(hour + ":" + min);
                        }
                    } else {
                        //起床时间需要加上12小时
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

                        mSleepChartView.setLimitLineH(hour, min, false);
                        if (min < 10) {
                            tvTimeEnd.setText(hour2 + ":0" + min);
                        } else {
                            tvTimeEnd.setText(hour2 + ":" + min);
                        }
                    }
                    //计算时间差

                    int startHour1 = 24 - startHour - 1;//剩余多少小时
                    int startMin1 = 60 - startMin;

                    _DH = startHour1 + endHour;
                    _DM = startMin1 + endMin;
                    if (_DM >= 60) {
                        _DM = _DM - 60;
                        _DH++;
                    }
                    tvDHour.setText("" + _DH);
                    tvDMin.setText("" + _DM);

                } else {
                    mNestedScrollView.requestDisallowInterceptTouchEvent(false);
                    //把时间保存起来
                    //就寝时间
                    SPUtils.put(getActivity(), "startHour", startHour);
                    SPUtils.put(getActivity(), "startMin", startMin);
                    //起床时间
                    SPUtils.put(getActivity(), "endHour", endHour);
                    SPUtils.put(getActivity(), "endMin", endMin);
                    //时间差
                    SPUtils.put(getActivity(), "_DH", _DH);
                    SPUtils.put(getActivity(), "_DM", _DM);

                    LogUtil.i("就寝时间：" + startHour + ":" + startMin + "睡眠时间" + endHour + ":" + endMin + "时间差：" + _DH + "小时" + _DM + "分钟");
                }
            }
        });
    }
}
