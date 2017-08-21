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

import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.SleepSettingsActivity;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.mInterface.CircleListener;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.mInterface.SwitchListener;
import com.huihong.healthydiet.model.mybean.Time;
import com.huihong.healthydiet.utils.MyUtils;
import com.huihong.healthydiet.utils.common.DateFormattedUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.view.SleepChartView;
import com.huihong.healthydiet.view.TimeSelectView;
import com.huihong.healthydiet.widget.SwitchImageView;

import java.util.List;

import static com.huihong.healthydiet.utils.MyUtils.getTimeDifference;

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
            MainActivity.mainActivity.setItemOnClickListenerSleep(new ItemOnClickListener() {
                @Override
                public void onClick() {
                    if(mSleepChartView!=null){
                        mSleepChartView.invalidateData();
                    }

                }
            });
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
                        text = text + MyUtils.getWeek(i) + " ";
                    }
                }
                tvWeek.setText(text);
            }
        }
    }


    //睡眠时间设置
    private boolean isSleepAfter = false;
    private float lastSleepAngle = 250;
    private Time sleepTime;
    //
    private boolean isGetUpAfter = false;
    private float lastGetUpAngle = 250;
    private Time getUpTime;


    //初始化时间选择器及其附属控件
    private void initTimeSelectView() {

        sleepTime = CacheUtils.getSleepTime(getActivity());
        getUpTime = CacheUtils.getGetUpTime(getActivity());

        if(sleepTime.getHour()>=12){
            isSleepAfter=true;
        }else {
            isSleepAfter=false;
        }

        if(getUpTime.getHour()>=12){
            isGetUpAfter=true;
        }else {
            isGetUpAfter=false;
        }


        mTimeSelectView = (TimeSelectView) mView.findViewById(R.id.mTimeSelectView);//时间选择器
        tvTimeStart = (TextView) mView.findViewById(R.id.tvTimeStart);//开始时间
        tvTimeEnd = (TextView) mView.findViewById(R.id.tvTimeEnd);//结束时间
        //时间差
        tvDHour = (TextView) mView.findViewById(R.id.tvDHour);
        tvDMin = (TextView) mView.findViewById(R.id.tvDMin);

        tvTimeStart.setText(DateFormattedUtils.formattedDate(sleepTime.getHour()) + ":" + DateFormattedUtils.formattedDate(sleepTime.getMin()));
        tvTimeEnd.setText(DateFormattedUtils.formattedDate(getUpTime.getHour()) + ":" + DateFormattedUtils.formattedDate(getUpTime.getMin()));

        //计算时间差
        Time dTime=getTimeDifference(sleepTime,getUpTime);
        tvDHour.setText("" + dTime.getHour());
        tvDMin.setText("" + dTime.getMin());

        mTimeSelectView.setCircleListener(new CircleListener() {
            @Override
            public void move(boolean isSetSleep, float mAngle, boolean isEnd) {

                LogUtil.i("角度" + mAngle);
                //不是停止
                if (!isEnd) {
                    //不允许滑动
                    mNestedScrollView.requestDisallowInterceptTouchEvent(true);
                    if (isSetSleep) {
                        //去判断角度触发临界值
                        //如果原来是359度 后来是1度 那么就要切换上午下午了
                        if (lastSleepAngle > 350 && lastSleepAngle <= 360) {
                            if (mAngle < 10 && mAngle >= 0) {
                                //触发临界
                                LogUtil.i("触发临界值1" + lastSleepAngle);
                                isSleepAfter = !isSleepAfter;
                            }
                        }
                        //如果原来是1度 现在是359度那么也要切换
                        if (lastSleepAngle >= 0 && lastSleepAngle <= 10) {
                            if (mAngle <= 360 && mAngle > 350) {
                                //触发临界
                                LogUtil.i("触发临界值2" + lastSleepAngle);
                                isSleepAfter = !isSleepAfter;
                            }
                        }
                        lastSleepAngle = mAngle;
                        //获取12小时制的 hour
                        int hour = (int) (mAngle / 30);
                        //获取分钟
                        double b =  mAngle % 30;//获得分
                        LogUtil.i("取余"+b);
                        int min = (int) (60 * (b / 30.0001));
                        if (isSleepAfter) {
                            hour = hour + 12;
                        }
                        sleepTime.setHour(hour);
                        sleepTime.setMin(min);
                        //设置UI
                        tvTimeStart.setText(DateFormattedUtils.formattedDate(hour) + ":" + DateFormattedUtils.formattedDate(min));
                    } else {
                        //去判断角度触发临界值
                        //如果原来是359度 后来是1度 那么就要切换上午下午了
                        if (lastGetUpAngle > 350 && lastGetUpAngle <= 360) {
                            if (mAngle < 10 && mAngle >= 0) {
                                //触发临界
                                LogUtil.i("触发临界值1" + lastGetUpAngle);
                                isGetUpAfter = !isGetUpAfter;
                            }
                        }
                        //如果原来是1度 现在是359度那么也要切换
                        if (lastGetUpAngle >= 0 && lastGetUpAngle <= 10) {
                            if (mAngle <= 360 && mAngle > 350) {
                                //触发临界
                                LogUtil.i("触发临界值2" + lastGetUpAngle);
                                isGetUpAfter = !isGetUpAfter;
                            }
                        }
                        lastGetUpAngle = mAngle;
                        //获取12小时制的 hour
                        int hour = (int) (mAngle / 30);
                        //获取分钟
                        double b = mAngle % 30;//获得分
                        int min = (int) (60 * (b / 30.0001));
                        if (isGetUpAfter) {
                            hour = hour + 12;
                        }
                        getUpTime.setHour(hour);
                        getUpTime.setMin(min);
                        //设置UI
                        tvTimeEnd.setText(DateFormattedUtils.formattedDate(hour) + ":" + DateFormattedUtils.formattedDate(min));
                    }
                    //设置时间差
                    Time dTime=getTimeDifference(sleepTime,getUpTime);
                    tvDHour.setText("" + dTime.getHour());
                    tvDMin.setText("" + dTime.getMin());

                    mSleepChartView.setLimitLine(sleepTime,getUpTime);


                } else {
                    mNestedScrollView.requestDisallowInterceptTouchEvent(false);
                    //保存时间
                    CacheUtils.setSleepTime(getActivity(), sleepTime);
                    CacheUtils.setGetUpTime(getActivity(), getUpTime);
                }
            }
        });
    }


}
