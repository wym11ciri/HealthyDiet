package com.huihong.healthydiet.fragment.main;

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
import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.mInterface.UpdateStepCallBack;
import com.huihong.healthydiet.mybean.ChartDay;
import com.huihong.healthydiet.mybean.ChartMonth;
import com.huihong.healthydiet.mybean.ChartYear;
import com.huihong.healthydiet.service.StepService;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.widget.MyYAnimation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/14
 * 运动界面
 */

public class MotionFragment extends Fragment {

    private View mView;
    private LineChart mChart;

    private TextView tvSelectRight, tvSelectLeft, tvSelectMiddle;
    private View.OnClickListener onClickListener;


    private LinearLayout layoutCircle01, layoutCircle02, layoutCircle03;

    //圆形1里面的内容
    private TextView tvStepCount;//当前步数
    private TextView tvCircle01Title;//当前是否在运动
    private TextView tvStartStep;//点击开始运动

    //圆形3里面的内容
    private TextView tvDistance,tvTime;//行走的距离


    //当前是否在运动
    private boolean isRun = false;


    //动画
    private Animation layoutAnimation01;
    private Animation layoutAnimation02;
    private Animation layoutAnimation03;
    private MyYAnimation myYAnimation;

    private int nowMultiple = 1;


    //计步器当前的步数
    private int stepCount = 0;


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
                    layoutCircle02.startAnimation(layoutAnimation01);
                }
            });
        }

        return mView;
    }

    private void setLayoutCircle() {

        isRun = (boolean) SPUtils.get(getActivity(), "isRunning", false);

        tvStepCount = (TextView) mView.findViewById(R.id.tvStepCount);
        tvCircle01Title = (TextView) mView.findViewById(R.id.tvCircle01Title);
        tvStartStep = (TextView) mView.findViewById(R.id.tvStartStep);
        tvTime = (TextView) mView.findViewById(R.id.tvTime);
        if (!isRun) {
            tvStepCount.setVisibility(View.GONE);
            tvStartStep.setVisibility(View.VISIBLE);
            tvCircle01Title.setText("尚未开始");
        } else {
            tvStepCount.setVisibility(View.VISIBLE);
            tvStartStep.setVisibility(View.GONE);
            tvCircle01Title.setText("正在运动");
        }


        tvDistance = (TextView) mView.findViewById(R.id.tvDistance);

        layoutCircle01 = (LinearLayout) mView.findViewById(R.id.layoutCircle01);
        layoutCircle02 = (LinearLayout) mView.findViewById(R.id.layoutCircle02);
        layoutCircle03 = (LinearLayout) mView.findViewById(R.id.layoutCircle03);

        layoutAnimation01 = AnimationUtils.loadAnimation(getActivity(), R.anim.sacle);
        layoutAnimation02 = AnimationUtils.loadAnimation(getActivity(), R.anim.sacle);
        layoutAnimation03 = AnimationUtils.loadAnimation(getActivity(), R.anim.sacle);
        myYAnimation = new MyYAnimation();
        myYAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isRun) {
                    tvStepCount.setVisibility(View.VISIBLE);
                    tvStartStep.setVisibility(View.GONE);
                    tvCircle01Title.setText("正在运动");
                } else {
                    tvStepCount.setVisibility(View.GONE);
                    tvStartStep.setVisibility(View.VISIBLE);
                    tvCircle01Title.setText("尚未开始");
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        layoutAnimation01.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layoutCircle03.startAnimation(layoutAnimation02);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        layoutAnimation02.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layoutCircle01.startAnimation(layoutAnimation03);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        layoutCircle01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isRun) {
                    SPUtils.put(getActivity(), "isRunning", true);
                    isRun = true;
                    bindStepService();
                    //切换到运动状态
                    layoutCircle01.startAnimation(myYAnimation);
                } else {
                    isRun = false;
                    SPUtils.put(getActivity(), "isRunning", false);
                    stopStepService();
                    layoutCircle01.startAnimation(myYAnimation);
                    //提交成功了做上面的事情

                    //关闭计步器功能并提交数据
                    submitStepCount();

                }
            }
        });
    }


    //向服务器提交数据
    private void submitStepCount() {
        Calendar c = Calendar.getInstance();//
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
        MotionFragment.this.stepCount=0;

    }


    /**
     * 用于查询应用服务（application Service）的状态的一种interface，
     * 更详细的信息可以参考Service 和 context.bindService()中的描述，
     * 和许多来自系统的回调方式一样，ServiceConnection的方法都是进程的主线程中调用的。
     */
    private ServiceConnection conn = new ServiceConnection() {
        /**
         * 在建立起于Service的连接时会调用该方法，目前Android是通过IBind机制实现与服务的连接。
         * @param name 实际所连接到的Service组件名称
         * @param service 服务的通信信道的IBind，可以通过Service访问对应服务
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService stepService = ((StepService.StepBinder) service).getService();
            stepService.setUpdateStepCallBack(new UpdateStepCallBack() {
                @Override
                public void updateStep(int stepCount,int min) {
                    MotionFragment.this.stepCount = (int) (stepCount*0.8);
                    tvStepCount.setText(stepCount + "");
                    DecimalFormat df = new DecimalFormat("######0.0");
                    double a = MotionFragment.this.stepCount * 0.4;
                    tvDistance.setText(df.format(a) + "");
                    tvTime.setText(min+"");
                }
            });
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         * @param name 丢失连接的组件名称
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * 绑定计步器服务
     */
    private void bindStepService() {
        if (isRun) {
            Intent intent = new Intent(getActivity(), StepService.class);
            getActivity().bindService(intent, conn, Context.BIND_AUTO_CREATE);
//            getActivity().startService(intent);
        }
    }

    private void stopStepService() {
        getActivity().unbindService(conn);
    }

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
                        //设置折线图横坐标为年
                        setChartByYear();
                        break;
                    case R.id.tvSelectMiddle:
                        //设置折线图横坐标为月
                        tvSelectMiddle.setTextColor(getResources().getColor(R.color.motion_select));
                        tvSelectMiddle.setBackgroundResource(R.drawable.motion_bg_middle_select);

                        setChartByMonth();
                        break;
                    case R.id.tvSelectLeft:
                        //设置折线图横坐标为日
                        tvSelectLeft.setTextColor(getResources().getColor(R.color.motion_select));
                        tvSelectLeft.setBackgroundResource(R.drawable.motion_bg_left_select);
                        //
                        setChartByDay();

                        break;
                }

            }
        };
        tvSelectLeft.setOnClickListener(onClickListener);
        tvSelectMiddle.setOnClickListener(onClickListener);
        tvSelectRight.setOnClickListener(onClickListener);
    }

    private void setChartByDay() {
        final List<ChartDay> mChartDayList = new ArrayList<>();
        //生成一个假数据集合
        for (int i = 1; i < 30; i++) {
            ChartDay chartDay = new ChartDay();
            chartDay.setDay(i);
            chartDay.setMonth(7);
            chartDay.setCount((int) (Math.random() * 500));
            mChartDayList.add(chartDay);
        }

        //设置折线图的横坐标
        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int a = (int) value;
                if (a < mChartDayList.size()) {
                    return mChartDayList.get(a).getMonth() + "." + mChartDayList.get(a).getDay();
                } else {
                    return "";
                }
            }
        });
        xAxis.setLabelCount(15);//横轴个数
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // 移除所有限制线
        leftAxis.setAxisMaximum(500);//设置最大值


        //重新绘制折线图数据
        mChart.clear();//清空原来的数据
        //绘制主线
        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();
        for (int i = 0; i < mChartDayList.size(); i++) {
            values.add(new Entry(i, mChartDayList.get(i).getCount()));
            values2.add(new Entry(i, 0));
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
                return a + "步";
            }
        });

        //在坐标轴上画一条辅助的线
        LineDataSet auxiliaryLineDataSet = new LineDataSet(values2, "DataSet 2");
        auxiliaryLineDataSet.setColor(getResources().getColor(R.color.circle_zzzz));
        auxiliaryLineDataSet.setCircleColor(getResources().getColor(R.color.circle_zzzz));
        auxiliaryLineDataSet.setLineWidth(0.2f);
        auxiliaryLineDataSet.setCircleRadius(3f);
        auxiliaryLineDataSet.setDrawFilled(true);
        auxiliaryLineDataSet.setDrawValues(false);
        auxiliaryLineDataSet.setDrawCircleHole(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(mainLineDataSet); // add the datasets
        dataSets.add(auxiliaryLineDataSet); // add the datasets
        LineData data = new LineData(dataSets);

        // set data
        mChart.setData(data);
        setChartSize(2);


    }

    private void setChartByYear() {
        final List<ChartYear> mChartDayList = new ArrayList<>();
        //生成一个假数据集合
        for (int i = 1; i < 8; i++) {
            ChartYear chartDay = new ChartYear();
            chartDay.setYear(2010 + i);
            chartDay.setCount((int) (Math.random() * 500));
            mChartDayList.add(chartDay);
        }

        //设置折线图的横坐标
        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int a = (int) value;
                if (a < mChartDayList.size()) {
                    return mChartDayList.get(a).getYear() + "年";
                } else {
                    return "";
                }
            }
        });
        xAxis.setLabelCount(15);//横轴个数
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // 移除所有限制线
        leftAxis.setAxisMaximum(500);//设置最大值


        //重新绘制折线图数据
        mChart.clear();//清空原来的数据
        //绘制主线
        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();
        for (int i = 0; i < mChartDayList.size(); i++) {
            values.add(new Entry(i, mChartDayList.get(i).getCount()));
            values2.add(new Entry(i, 0));
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
                return a + "步";
            }
        });

        //在坐标轴上画一条辅助的线
        LineDataSet auxiliaryLineDataSet = new LineDataSet(values2, "DataSet 2");
        auxiliaryLineDataSet.setColor(getResources().getColor(R.color.circle_zzzz));
        auxiliaryLineDataSet.setCircleColor(getResources().getColor(R.color.circle_zzzz));
        auxiliaryLineDataSet.setLineWidth(0.2f);
        auxiliaryLineDataSet.setCircleRadius(3f);
        auxiliaryLineDataSet.setDrawFilled(true);
        auxiliaryLineDataSet.setDrawValues(false);
        auxiliaryLineDataSet.setDrawCircleHole(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(mainLineDataSet); // add the datasets
        dataSets.add(auxiliaryLineDataSet); // add the datasets
        LineData data = new LineData(dataSets);

        // set data
        mChart.setData(data);
        setChartSize(1);

    }

    //设置图表的放大倍数
    public void setChartSize(int multiple) {
        //放大倍数只有放大2倍数和普通两种状态

        if (multiple == 2) {
            //如果需要放大2被
            if (nowMultiple != 2) {
                mChart.zoom(2, 1, 0, 0);
                nowMultiple = 2;
            } else {
                mChart.zoom(1, 1, 0, 0);
                nowMultiple = 2;
            }


        } else {
            //需要变成原来的倍数
            if (nowMultiple == 2) {
                mChart.zoom(0.5f, 1, 0, 0);
                nowMultiple = 1;
            } else {
                mChart.zoom(1, 1, 0, 0);
                nowMultiple = 1;
            }

        }


    }

    private void setChartByMonth() {
        final List<ChartMonth> mChartDayList = new ArrayList<>();
        //生成一个假数据集合
        for (int i = 1; i < 13; i++) {
            ChartMonth chartMonth = new ChartMonth();
            chartMonth.setMonth(i);
            chartMonth.setCount((int) (Math.random() * 500));
            mChartDayList.add(chartMonth);
        }

        //设置折线图的横坐标
        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int a = (int) value;
                if (a < mChartDayList.size()) {
                    return mChartDayList.get(a).getMonth() + "月";
                } else {
                    return "";
                }
            }
        });
        xAxis.setLabelCount(15);//横轴个数
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // 移除所有限制线
        leftAxis.setAxisMaximum(500);//设置最大值


        //重新绘制折线图数据
        mChart.clear();//清空原来的数据
        //绘制主线
        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();
        for (int i = 0; i < mChartDayList.size(); i++) {
            values.add(new Entry(i, mChartDayList.get(i).getCount()));
            values2.add(new Entry(i, 0));
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
                return a + "步";
            }
        });

        //在坐标轴上画一条辅助的线
        LineDataSet auxiliaryLineDataSet = new LineDataSet(values2, "DataSet 2");
        auxiliaryLineDataSet.setColor(getResources().getColor(R.color.circle_zzzz));
        auxiliaryLineDataSet.setCircleColor(getResources().getColor(R.color.circle_zzzz));
        auxiliaryLineDataSet.setLineWidth(0.2f);
        auxiliaryLineDataSet.setCircleRadius(3f);
        auxiliaryLineDataSet.setDrawFilled(true);
        auxiliaryLineDataSet.setDrawValues(false);
        auxiliaryLineDataSet.setDrawCircleHole(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(mainLineDataSet); // add the datasets
        dataSets.add(auxiliaryLineDataSet); // add the datasets
        LineData data = new LineData(dataSets);
        // set data
        mChart.setData(data);
        setChartSize(1);
        mChart.fitScreen();

    }

    //设置图表控件
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
        xAxis.setGranularity(1f); //以1为单位跳跃
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(getResources().getColor(R.color.line_chart_normal_color));//设置字体颜色
        xAxis.setDrawGridLines(false);
        //设置Y轴属性
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // 移除所有限制线
        leftAxis.setAxisMaximum(200f);//设置最大值
        leftAxis.setAxisMinimum(0f);//设置最小值
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.setEnabled(false);

        mChart.getAxisRight().setEnabled(false);

        mChart.animateX(2500);
        Legend legend = mChart.getLegend();
        legend.setEnabled(false);
        setChartByDay();
    }
}
