package com.huihong.healthydiet.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.mybean.Sleep;
import com.huihong.healthydiet.utils.common.DensityUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/1
 */

public class SleepChartView extends View {

    private Paint paintLimit;//限制线画笔
    private Paint paintNormal;//普通线画笔刻度线
    private Paint paintSleep;//睡眠记录画笔
    private Paint paintText;//绘制字的画笔

    private float screenW, screenH;//画布大小
    private float unitHeight;
    private float screenW_14;//把宽度14等分

    private float sleepLimitH;//睡眠线高度
    private float getUpLimitH;//起床线高度

    private List<Sleep> mSleepList;

    private int textWidth;
    private int textSize;


    //设置限制线的时间
    private int SleepLimitHour;
    private int SleepLimitMin;

    private int getUpLimitHour;
    private int getUpLimitMin;

    private Context mContext;

    //上下距离
    private int paddingTop;
    private int paddingBottom;


    public SleepChartView(Context context) {
        this(context, null);
    }

    public SleepChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SleepChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        paintLimit = new Paint();
        paintLimit.setColor(getResources().getColor(R.color.color_black));
        paintLimit.setStrokeWidth(3);
        paintLimit.setAntiAlias(true);//设置抗锯齿

        paintNormal = new Paint();
        paintNormal.setColor(getResources().getColor(R.color.sleep_chart_normal_line));
        paintNormal.setAntiAlias(true);//设置抗锯齿

        paintSleep = new Paint();
        paintSleep.setColor(getResources().getColor(R.color.sleep_chart_sleep_line));
        paintSleep.setAntiAlias(true);//设置抗锯齿
        paintSleep.setStrokeWidth(8);
        paintSleep.setStrokeCap(Paint.Cap.ROUND);

        paintText = new Paint();
        paintText.setColor(getResources().getColor(R.color.sleep_chart_normal_line));
        paintText.setAntiAlias(true);//设置抗锯齿
        paintText.setTextSize(DensityUtils.sp2px(mContext, 12));
        textWidth = DensityUtils.dp2px(mContext, 40);

        mSleepList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
           Sleep sleep=new Sleep();
            sleep.setGetUpHour(i+1);
            sleep.setGetUpMin(i+1);

            sleep.setSleepHour(i+13);
            sleep.setSleepMin(i+13);
            mSleepList.add(sleep);
        }
        LogUtil.i("调试", "SleepChartView 构造");

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        textSize = DensityUtils.sp2px(mContext, 12);
        paddingTop = DensityUtils.sp2px(mContext, 12);
        paddingBottom = DensityUtils.sp2px(mContext, 12) / 2;

        screenW = w - (textWidth);
        screenH = h - (paddingTop + paddingBottom);
        screenW_14 = screenW / 14;
        unitHeight = screenH / 6;//高度分6份每个刻度当4小时有7根线

        SleepLimitHour = (int) SPUtils.get(mContext, "startHour", 23);
        SleepLimitMin = (int) SPUtils.get(mContext, "startMin", 0);
        getUpLimitHour = (int) SPUtils.get(mContext, "endHour", 8);
        getUpLimitMin = (int) SPUtils.get(mContext, "endMin", 0);

        setLimitLineH(SleepLimitHour, SleepLimitMin, true);
        setLimitLineH(getUpLimitHour, getUpLimitMin, false);

        LogUtil.i("调试", "SleepChartView onSizeChanged");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制刻度线
        for (int i = 0; i < 7; i++) {
            //给刻度线加上限制
            float height = unitHeight * i;
            if (height >= getUpLimitH && height <= sleepLimitH) {
                canvas.drawLine(textWidth, unitHeight * i + paddingTop, screenW + textWidth, unitHeight * i + paddingTop, paintNormal);
            }
        }
        //画2根限制线
        canvas.drawLine(textWidth, sleepLimitH + paddingTop, screenW + textWidth, sleepLimitH + paddingTop, paintLimit);
        canvas.drawLine(textWidth, getUpLimitH + paddingTop, screenW + textWidth, getUpLimitH + paddingTop, paintLimit);
        //绘制右侧2个时间
        if (SleepLimitMin < 10) {
            canvas.drawText(SleepLimitHour + ":0" + SleepLimitMin, 0, sleepLimitH + paddingTop + paddingTop / 3, paintText);
        } else {
            canvas.drawText(SleepLimitHour + ":" + SleepLimitMin, 0, sleepLimitH + paddingTop + paddingTop / 3, paintText);
        }

        if (getUpLimitMin < 10) {
            if (getUpLimitHour < 10) {
                canvas.drawText( "0" +getUpLimitHour + ":0" + getUpLimitMin, 0, getUpLimitH + paddingTop + paddingTop / 3, paintText);
            } else {
                canvas.drawText(getUpLimitHour + ":0" + getUpLimitMin, 0, getUpLimitH + paddingTop + paddingTop / 3, paintText);
            }
        } else {
            if (getUpLimitHour < 10) {
                canvas.drawText("0" + getUpLimitHour + ":" + getUpLimitMin, 0, getUpLimitH + paddingTop + paddingTop / 3, paintText);
            } else {
                canvas.drawText(getUpLimitHour + ":" + getUpLimitMin, 0, getUpLimitH + paddingTop + paddingTop / 3, paintText);
            }
        }


        //绘制历史睡眠线
        for (int i = 0; i < mSleepList.size()-1; i++) {
            canvas.drawLine(textWidth + screenW_14 * (2 * i + 1),  getSleepH(mSleepList.get(i),false)+ paddingTop, textWidth + screenW_14 * (2 * i + 1),  getSleepH(mSleepList.get(i),true) + paddingTop, paintSleep);
//            canvas.drawLine(textWidth + screenW_14 * i, getUpLimitH + paddingTop, textWidth + screenW_14 * i, sleepLimitH + paddingTop, paintSleep);
        }

        //画当前睡眠线
        canvas.drawLine(textWidth + screenW_14 * (2 * 6 + 1),  getUpLimitH+ paddingTop, textWidth + screenW_14 * (2 * 6 + 1),  sleepLimitH + paddingTop, paintSleep);

    }

    public void setLimitLineH(int hour, int min, boolean isSetSleep) {


        if (isSetSleep) {
            //设置睡眠高度
//            sleepLimitH = screenH * (mAngle / 360);
            int allMin = (hour - 13) * 60 + min;
            double percentage = 1 - (allMin / (12.001 * 60.0001));
            sleepLimitH = screenH / 2 + (float) (percentage * (screenH / 2));
            SleepLimitHour = hour;
            SleepLimitMin = min;


        } else {
            //起床时间为早上1点到12.59
            //计算获取时间
            int allMin = (hour - 1) * 60 + min;
            double percentage = 1 - (allMin / (12.001 * 60.0001));
            getUpLimitH = (float) (percentage * (screenH / 2));
            getUpLimitHour = hour;
            getUpLimitMin = min;
//            getUpLimitH = screenH * (mAngle / 360);
        }
        invalidate();
    }

    public float getSleepH(Sleep mSleep, boolean isGetSleep) {


        if (isGetSleep) {
            int hour=mSleep.getSleepHour();
            int min=mSleep.getSleepMin();
            //设置睡眠高度
//            sleepLimitH = screenH * (mAngle / 360);
            int allMin = (hour - 13) * 60 + min;
            double percentage = 1 - (allMin / (12.001 * 60.0001));
            return screenH / 2 + (float) (percentage * (screenH / 2));

        } else {

            int hour=mSleep.getGetUpHour();
            int min=mSleep.getGetUpMin();

            //起床时间为早上1点到12.59
            //计算获取时间
            int allMin = (hour - 1) * 60 + min;
            double percentage = 1 - (allMin / (12.001 * 60.0001));
            return   (float) (percentage * (screenH / 2));

        }
    }
}
