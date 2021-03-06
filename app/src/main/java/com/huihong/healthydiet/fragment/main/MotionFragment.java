package com.huihong.healthydiet.fragment.main;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.mInterface.UpdateStepCallBack;
import com.huihong.healthydiet.mInterface.UpdateTimeCallBack;
import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.model.mybean.GetSportList;
import com.huihong.healthydiet.model.mybean.StepCount;
import com.huihong.healthydiet.service.StepService;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.common.ValueUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.huihong.healthydiet.widget.animation.MyYAnimation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/14
 * ????????????
 */

public class MotionFragment extends Fragment {

    private View mView;
    private LineChart mChart;

    private TextView tvSelectRight, tvSelectLeft, tvSelectMiddle;
    private View.OnClickListener onClickListener;


    private LinearLayout layoutCircle01, layoutCircle02, layoutCircle03;

    //??????1???????????????
    private TextView tvStepCount;//????????????
    private TextView tvCircle01Title;//?????????????????????
    private TextView tvStartStep;//??????????????????

    //??????3???????????????
    private TextView tvDistance, tvTime;//???????????????


    private StepCount mStepCount;


    //??????
    private Animation layoutAnimation01;
    private Animation layoutAnimation02;
    private Animation layoutAnimation03;
    private MyYAnimation myYAnimation;

    private int nowMultiple = 1;


    //????????????????????????
    private int stepCount = 0;
    private AlertDialog runningDialog;

    //???????????????  ????????????  ????????????
    private String runState = "OFF";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_motion, null);
            setLayoutCircle();
            bindStepService();
            initChart();
            initTimeSelectBar();
            MainActivity.mainActivity.setItemOnClickListener04(new ItemOnClickListener() {
                @Override
                public void onClick() {
                    layoutCircle01.startAnimation(layoutAnimation01);
                    layoutCircle02.startAnimation(layoutAnimation02);
                    layoutCircle03.startAnimation(layoutAnimation02);
                }
            });
            getSportList("day");
        }

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isFirstOpen = CacheUtils.isFirstOpen(getActivity());

        if (isFirstOpen) {
//            StepCount stepCount = new StepCount();//?????????0
//            stepCount.setStepCount(0);
//            stepCount.setTime(0);
//            CacheUtils.putStepCount(getActivity(), stepCount);
//            CacheUtils.setRunState(getActivity(), "OFF");
        }
    }

    private void setLayoutCircle() {
        tvDistance = (TextView) mView.findViewById(R.id.tvDistance);
        mStepCount = CacheUtils.getStepCount(getActivity());

        tvStepCount = (TextView) mView.findViewById(R.id.tvStepCount);
        tvCircle01Title = (TextView) mView.findViewById(R.id.tvCircle01Title);
        tvStartStep = (TextView) mView.findViewById(R.id.tvStartStep);
        tvTime = (TextView) mView.findViewById(R.id.tvTime);

        runState = CacheUtils.getRunState(getActivity());
        switch (runState) {
            case "OFF":
                tvStepCount.setVisibility(View.GONE);
                tvStartStep.setVisibility(View.VISIBLE);
                tvCircle01Title.setText("????????????");
                break;
            case "ON":
                tvStepCount.setVisibility(View.VISIBLE);
                tvStartStep.setVisibility(View.GONE);
                tvCircle01Title.setText("????????????");
                tvStepCount.setText(mStepCount.getStepCount() + "");
                tvTime.setText(mStepCount.getTime() + "");
                tvDistance.setText(ValueUtils.getDoubleValueString(mStepCount.getStepCount() * 0.4 ,1));
                break;
            default:
                //STOP
                tvStepCount.setVisibility(View.VISIBLE);
                tvStartStep.setVisibility(View.GONE);
                tvCircle01Title.setText("????????????");
                tvStepCount.setText(mStepCount.getStepCount() + "");
                tvTime.setText(mStepCount.getTime() + "");
                tvDistance.setText(ValueUtils.getDoubleValueString(mStepCount.getStepCount() * 0.4 ,1));
                break;
        }


        layoutCircle01 = (LinearLayout) mView.findViewById(R.id.layoutCircle01);
        layoutCircle02 = (LinearLayout) mView.findViewById(R.id.layoutCircle02);
        layoutCircle03 = (LinearLayout) mView.findViewById(R.id.layoutCircle03);

        layoutAnimation01 = AnimationUtils.loadAnimation(getActivity(), R.anim.sacle01);
        layoutAnimation02 = AnimationUtils.loadAnimation(getActivity(), R.anim.sacle02);
        layoutAnimation03 = AnimationUtils.loadAnimation(getActivity(), R.anim.sacle02);
        myYAnimation = new MyYAnimation();


        layoutCircle01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //????????????
                runState = CacheUtils.getRunState(getActivity());
                switch (runState) {
                    case "OFF":
                        //????????????
                        CacheUtils.setRunState(getActivity(), "ON");//????????????
                        tvStepCount.setVisibility(View.VISIBLE);
                        tvStartStep.setVisibility(View.GONE);
                        tvCircle01Title.setText("????????????");
                        bindStepService();
                        layoutCircle01.startAnimation(myYAnimation);
                        break;
                    case "ON":
                        CacheUtils.setRunState(getActivity(), "STOP");//????????????
                        tvStepCount.setVisibility(View.VISIBLE);
                        tvStartStep.setVisibility(View.GONE);
                        tvCircle01Title.setText("????????????");
                        stopStepService();
                        layoutCircle01.startAnimation(myYAnimation);
                        submitStepCount();
                        break;
                    default:
                        //STOP
                        CacheUtils.setRunState(getActivity(), "ON");//????????????
                        tvStepCount.setVisibility(View.VISIBLE);
                        tvStartStep.setVisibility(View.GONE);
                        tvCircle01Title.setText("????????????");
                        bindStepService();
                        layoutCircle01.startAnimation(myYAnimation);
                        break;
                }
            }
        });
    }


    //????????????????????????
    private void submitStepCount() {

//        mStepCount = CacheUtils.getStepCount(getActivity());
        CacheUtils.putStepCount(getActivity(),mStepCount);
        layoutCircle01.setClickable(false);
        Map<String, String> map = new HashMap<>();
        LogUtil.i("????????????" + mStepCount.getStepCount());
        map.put("Steps", mStepCount.getStepCount() + "");
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");
        HttpUtils.sendHttpAddToken(getActivity(), AppUrl.UPLOAD_SPORT_INFO
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        layoutCircle01.setClickable(true);
                        LogUtil.i("??????????????????", response);
                        Gson gson = new Gson();
                        HttpBaseInfo mHttpBaseInfo = gson.fromJson(response, HttpBaseInfo.class);
                        if (mHttpBaseInfo.getHttpCode() == 200) {
                            getSportList(nowChoose);
                        }


                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        layoutCircle01.setClickable(true);
                        LogUtil.i("??????????????????", e.toString());
                    }
                });


    }


    /**
     * ???????????????????????????application Service?????????????????????interface???
     * ??????????????????????????????Service ??? context.bindService()???????????????
     * ?????????????????????????????????????????????ServiceConnection????????????????????????????????????????????????
     */
    private ServiceConnection conn = new ServiceConnection() {
        /**
         * ???????????????Service???????????????????????????????????????Android?????????IBind?????????????????????????????????
         * @param name ?????????????????????Service????????????
         * @param service ????????????????????????IBind???????????????Service??????????????????
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService stepService = ((StepService.StepBinder) service).getService();
            stepService.setUpdateStepCallBack(new UpdateStepCallBack() {
                @Override
                public void updateStep(int stepCount, int min) {
                    mStepCount.setStepCount(stepCount);
                    mStepCount.setTime(min);
                    tvStepCount.setText(stepCount + "");
                    DecimalFormat df = new DecimalFormat("######0.0");
                    MotionFragment.this.stepCount=stepCount;
                    double a = MotionFragment.this.stepCount * 0.4000;
                    tvDistance.setText(df.format(a) + "");
                    tvTime.setText(min + "");
                }
            });

            stepService.setUpdateTimeCallBack(new UpdateTimeCallBack() {
                @Override
                public void updateTime(int min) {
                    tvTime.setText(min + "");
                }
            });
        }

        /**
         * ??????Service???????????????????????????????????????????????????
         * ???????????????????????????Service??????????????????????????????Kill??????????????????
         * ????????????????????????Service????????????????????????????????????????????????????????? onServiceConnected()???
         * @param name ???????????????????????????
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * ?????????????????????
     */
    private void bindStepService() {
        if (CacheUtils.getRunState(getActivity()).equals("ON")) {
            Intent intent = new Intent(getActivity(), StepService.class);
            getActivity().bindService(intent, conn, Context.BIND_AUTO_CREATE);
        }
    }

    private void stopStepService() {
        getActivity().unbindService(conn);
    }

    private String nowChoose = "day";

    private void initTimeSelectBar() {
        tvSelectLeft = (TextView) mView.findViewById(R.id.tvSelectLeft);
        tvSelectMiddle = (TextView) mView.findViewById(R.id.tvSelectMiddle);
        tvSelectRight = (TextView) mView.findViewById(R.id.tvSelectRight);

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSelectLeft.setTextColor(getResources().getColor(R.color.motion_normal));
                tvSelectMiddle.setTextColor(getResources().getColor(R.color.motion_normal));
                tvSelectRight.setTextColor(getResources().getColor(R.color.motion_normal));

                tvSelectLeft.setBackgroundResource(R.drawable.motion_bg_left_normal);
                tvSelectMiddle.setBackgroundResource(R.drawable.motion_bg_middle_normal);
                tvSelectRight.setBackgroundResource(R.drawable.motion_bg_right_normal);

                switch (v.getId()) {
                    case R.id.tvSelectRight:
                        tvSelectRight.setTextColor(getResources().getColor(R.color.motion_select));
                        tvSelectRight.setBackgroundResource(R.drawable.motion_bg_right_select);
                        //??????????????????????????????
                        getSportList("year");
                        nowChoose = "year";

                        break;
                    case R.id.tvSelectMiddle:
                        //??????????????????????????????
                        tvSelectMiddle.setTextColor(getResources().getColor(R.color.motion_select));
                        tvSelectMiddle.setBackgroundResource(R.drawable.motion_bg_middle_select);

                        getSportList("month");
                        nowChoose = "month";
                        break;
                    case R.id.tvSelectLeft:
                        //??????????????????????????????
                        tvSelectLeft.setTextColor(getResources().getColor(R.color.motion_select));
                        tvSelectLeft.setBackgroundResource(R.drawable.motion_bg_left_select);
                        //
                        getSportList("day");
                        nowChoose = "day";

                        break;
                }

            }
        };
        tvSelectLeft.setOnClickListener(onClickListener);
        tvSelectMiddle.setOnClickListener(onClickListener);
        tvSelectRight.setOnClickListener(onClickListener);
    }

    List<GetSportList.ListDataBean> mListData;

    private void getSportList(String dateType) {
        if (mListData != null) {
            mListData.clear();
        }

        //????????????
        Map<String, String> map = new HashMap<>();
        map.put("UserId", SPUtils.get(getActivity(), "UserId", 0) + "");
        map.put("dateType", dateType);
        HttpUtils.sendHttpAddToken(getActivity(), AppUrl.GET_SPORT_LIST
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("??????????????????", response);
                        Gson gson = new Gson();
                        GetSportList mGetSportList = gson.fromJson(response, GetSportList.class);
                        if (mGetSportList.getHttpCode() == 200) {
                            mListData = mGetSportList.getListData();
                            if (mListData != null && mListData.size() > 0) {

                                //???????????????????????????
                                XAxis xAxis = mChart.getXAxis();
                                xAxis.setValueFormatter(new IAxisValueFormatter() {
                                    @Override
                                    public String getFormattedValue(float value, AxisBase axis) {
                                        int a = (int) value;
                                        if (a < mListData.size()) {
                                            return mListData.get(a).getDate();
                                        } else {
                                            return "";
                                        }
                                    }
                                });
                                xAxis.setLabelCount(mListData.size());//????????????
                                YAxis leftAxis = mChart.getAxisLeft();
                                leftAxis.removeAllLimitLines(); // ?????????????????????

                                //???????????????????????????
                                mChart.clear();//?????????????????????
                                //????????????
                                ArrayList<Entry> values = new ArrayList<>();
                                ArrayList<Entry> values2 = new ArrayList<>();
                                for (int i = 0; i < mListData.size(); i++) {
                                    values.add(new Entry(i, mListData.get(i).getSteps()));
                                    values2.add(new Entry(i, 0));
                                }

                                if (mListData.size() == 1) {
                                    values2.add(new Entry(1, 0));
                                }

                                LineDataSet mainLineDataSet = new LineDataSet(values, "DataSet 1");
                                mainLineDataSet.setColor(getResources().getColor(R.color.circle));
                                mainLineDataSet.setCircleColor(getResources().getColor(R.color.circle));
                                mainLineDataSet.setLineWidth(0.5f);
                                mainLineDataSet.setCircleRadius(3f);

                                mainLineDataSet.setValueFormatter(new IValueFormatter() {
                                    @Override
                                    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                                        int a = (int) value;
                                        return a + "???";
                                    }
                                });

                                //????????????????????????????????????
                                LineDataSet auxiliaryLineDataSet = new LineDataSet(values2, "DataSet 2");
                                auxiliaryLineDataSet.setColor(getResources().getColor(R.color.circle_zzzz));
                                auxiliaryLineDataSet.setCircleColor(getResources().getColor(R.color.circle_zzzz));
                                auxiliaryLineDataSet.setLineWidth(0.2f);
                                auxiliaryLineDataSet.setCircleRadius(3f);
                                auxiliaryLineDataSet.setDrawFilled(false);
                                auxiliaryLineDataSet.setDrawValues(false);
                                auxiliaryLineDataSet.setDrawCircleHole(false);
                                auxiliaryLineDataSet.setHighlightEnabled(false);
                                ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                                dataSets.add(mainLineDataSet);
                                dataSets.add(auxiliaryLineDataSet);
                                LineData data = new LineData(dataSets);
                                // set data
                                mChart.setData(data);
//                                setChartSize(1);
                            }
                        } else {
                            String message = mGetSportList.getMessage();
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("??????????????????", e.toString());
                    }
                });
    }

    //???????????????????????????
    public void setChartSize(int multiple) {
        //????????????????????????2???????????????????????????

        if (multiple == 2) {
            //??????????????????2???
            if (nowMultiple != 2) {
                mChart.zoom(2, 1, 0, 0);
                nowMultiple = 2;
            } else {
                mChart.zoom(1, 1, 0, 0);
                nowMultiple = 2;
            }


        } else {
            //???????????????????????????
            if (nowMultiple == 2) {
                mChart.zoom(0.5f, 1, 0, 0);
                nowMultiple = 1;
            } else {
                mChart.zoom(1, 1, 0, 0);
                nowMultiple = 1;
            }

        }


    }


    //??????????????????
    private void initChart() {
        mChart = (LineChart) mView.findViewById(R.id.chart1);
        mChart.setDrawGridBackground(false);
        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setScaleXEnabled(false);
        mChart.setScaleYEnabled(false);
        mChart.setPinchZoom(true);
//        mChart.zoom(2, 1, 0, 0);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(1f); //???1???????????????
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(getResources().getColor(R.color.line_chart_normal_color));//??????????????????
        xAxis.setDrawGridLines(false);
        //??????Y?????????
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // ?????????????????????
//        leftAxis.setAxisMaximum(200f);//???????????????
        leftAxis.setAxisMinimum(0);//???????????????
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.setEnabled(false);

        mChart.getAxisRight().setEnabled(false);

        mChart.animateX(2500);
        Legend legend = mChart.getLegend();
        legend.setEnabled(false);
//        setChartByDay();
    }
}
