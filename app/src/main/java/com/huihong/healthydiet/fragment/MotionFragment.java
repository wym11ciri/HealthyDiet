package com.huihong.healthydiet.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.huihong.healthydiet.R;

import java.util.ArrayList;

/**
 * Created by zangyi_shuai_ge on 2017/7/14
 * 运动界面
 */

public class MotionFragment extends Fragment {

    private View mView;
    private LineChart mChart;

    private TextView tvSelectRight, tvSelectLeft, tvSelectMiddle;
    private View.OnClickListener onClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_motion, null);
            initChart();
            initTimeSelectBar();
        }

        return mView;
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
                        break;
                    case R.id.tvSelectMiddle:
                        tvSelectMiddle.setTextColor(getResources().getColor(R.color.motion_select));
                        tvSelectMiddle.setBackgroundResource(R.drawable.motion_bg_middle_select);
                        break;
                    case R.id.tvSelectLeft:
                        tvSelectLeft.setTextColor(getResources().getColor(R.color.motion_select));
                        tvSelectLeft.setBackgroundResource(R.drawable.motion_bg_left_select);
                        break;
                }

            }
        };
        tvSelectLeft.setOnClickListener(onClickListener);
        tvSelectMiddle.setOnClickListener(onClickListener);
        tvSelectRight.setOnClickListener(onClickListener);
    }


    //设置图表控件
    private void initChart() {
        mChart = (LineChart) mView.findViewById(R.id.chart1);

//        mChart.setDrawYValues(f) :


//        mChart.setOnChartGestureListener(this);
//        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
//        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setScaleXEnabled(true);
        mChart.setScaleYEnabled(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);


        XAxis xAxis = mChart.getXAxis();
//        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setGranularity(1f); //以1为单位跳跃
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(getResources().getColor(R.color.line_chart_normal_color));//设置字体颜色
        xAxis.setDrawGridLines(false);

        //设置Y轴属性
        YAxis leftAxis = mChart.getAxisLeft();
//        leftAxis.setDrawTopYLabelEntry(false);
        leftAxis.removeAllLimitLines(); // 移除所有限制线
        leftAxis.setAxisMaximum(200f);//设置最大值
        leftAxis.setAxisMinimum(0f);//设置最小值
        //leftAxis.setYOffset(20f);
        //设置表格框框
//        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        leftAxis.setEnabled(false);

        mChart.getAxisRight().setEnabled(false);
        setData(7, 100);

//        mChart.setVisibleXRange(20);
//        mChart.setVisibleYRange(20f, AxisDependency.LEFT);
//        mChart.centerViewTo(20, 50, AxisDependency.LEFT);

        mChart.animateX(2500);

        Legend legend = mChart.getLegend();
        legend.setEnabled(false);
        //mChart.invalidate();

        // get the legend (only possible after setting data)
//        Legend l = mChart.getLegend();

        // modify the legend ...
//        l.setForm(Legend.LegendForm.LINE);

    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 1; i < count; i++) {
            float val = (int) (Math.random() * range) + 3;
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

//            set1.setDrawIcons(false);

            // set the line to be drawn like this "- - - - - -"
//            set1.enableDashedLine(10f, 5f, 0f);
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(getResources().getColor(R.color.circle));
            set1.setCircleColor(getResources().getColor(R.color.circle));
            set1.setLineWidth(0.5f);
            set1.setCircleRadius(3f);
//            set1.setDrawCircleHole(false);
//            set1.setValueTextSize(9f);
//            set1.setDrawFilled(true);
//            set1.setFormLineWidth(1f);
//            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
//            set1.setFormSize(15.f);

//            if (Utils.getSDKInt() >= 18) {
//                // fill drawable only supported on api level 18 and above
//                Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.bg_03);
//                set1.setFillDrawable(drawable);
//            }
//            else {
//                set1.setFillColor(Color.BLACK);
//            }

            LineDataSet set2;

            ArrayList<Entry> values2 = new ArrayList<Entry>();
            for (int i = 1; i < count; i++) {
                values2.add(new Entry(i, 0));
            }

            set2 = new LineDataSet(values2, "DataSet 1");
            set2.setColor(getResources().getColor(R.color.circle_zzzz));
            set2.setCircleColor(getResources().getColor(R.color.circle_zzzz));
            set2.setLineWidth(0.2f);
            set2.setCircleRadius(3f);
            set2.setDrawFilled(true);
            set2.setDrawValues(false);
            set2.setDrawCircleHole(false);

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets
            dataSets.add(set2); // add the datasets
            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mChart.setData(data);
        }
    }
}
