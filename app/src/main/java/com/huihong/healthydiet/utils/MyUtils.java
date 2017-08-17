package com.huihong.healthydiet.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.model.mybean.Time;
import com.huihong.healthydiet.utils.common.LogUtil;

/**
 * Created by zangyi_shuai_ge on 2017/8/10
 * 我的工具类
 */

public class MyUtils {

    //百分比字体颜色
    public static void setTextViewColor(TextView tvConstitutionPercentage, int percentage, Context mContext) {
        if (percentage > 90) {
            tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_9));
        } else if (percentage > 80 & percentage <= 90) {
            tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_8));
        } else if (percentage > 70 & percentage <= 80) {
            tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_7));
        } else if (percentage > 60 & percentage <= 70) {
            tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_6));
        } else {
            tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_5));
        }
    }

    public static void setImageViewType(ImageView ivConstitution, String type) {

        if (type.equals("平和质")) {
            ivConstitution.setImageResource(R.mipmap.temperament_1);
        } else if (type.equals("气郁质")) {
            ivConstitution.setImageResource(R.mipmap.temperament_2);
        } else if (type.equals("阴虚质")) {
            ivConstitution.setImageResource(R.mipmap.temperament_8);
        } else if (type.equals("痰湿质")) {
            ivConstitution.setImageResource(R.mipmap.temperament_5);
        } else if (type.equals("阳虚质")) {
            ivConstitution.setImageResource(R.mipmap.temperament_9);
        } else if (type.equals("特禀质")) {
            ivConstitution.setImageResource(R.mipmap.temperament_6);
        } else if (type.equals("湿热质")) {
            ivConstitution.setImageResource(R.mipmap.temperament_4);
        } else if (type.equals("气虚质")) {
            ivConstitution.setImageResource(R.mipmap.temperament_2);
        } else if (type.equals("血瘀质")) {
            ivConstitution.setImageResource(R.mipmap.temperament_7);
        } else {
            ivConstitution.setVisibility(View.GONE);
        }
    }

    /**
     * 获得week
     */
    public static String getWeek(int i) {

        switch (i) {
            case 0:
                return "周一";
            case 1:
                return "周二";
            case 2:
                return "周三";
            case 3:
                return "周四";
            case 4:
                return "周五";
            case 5:
                return "周六";
            case 6:
                return "周日";
        }
        return " ";
    }
    //计算时间差
    public static Time getTimeDifference(Time sleepTime, Time getUpTime) {
        //计算时间差
        //总时长
        int allMin=24*60;//分钟

        //计算睡眠
        int sleepAllMin=sleepTime.getMin()+sleepTime.getHour()*60;
        int getUpAllMin=getUpTime.getMin()+getUpTime.getHour()*60;
        //睡眠分钟数目
        int mAllMin=allMin-sleepAllMin+getUpAllMin;
        //转换成小时
        int mHour=mAllMin/60;
        int mMin=mAllMin%60;

        if(mHour>=24){
            mHour=mHour-24;
        }

        Time dTime=new Time();
        dTime.setHour(mHour);
        dTime.setMin(mMin);

        return dTime;
    }

    //计算时间差
    public static int getTimeDifferenceMin(Time sleepTime, Time getUpTime) {
        //计算时间差
        //总时长
        int allMin=24*60;//分钟

        //计算睡眠
        int sleepAllMin=sleepTime.getMin()+sleepTime.getHour()*60;
        int getUpAllMin=getUpTime.getMin()+getUpTime.getHour()*60;
        //睡眠分钟数目
        int mAllMin=allMin-sleepAllMin+getUpAllMin;
        //转换成小时
        int mHour=mAllMin/60;
        int mMin=mAllMin%60;

        if(mHour>=24){
            mHour=mHour-24;
        }
        LogUtil.i("相差几个小时"+mHour);
        return mHour*60+mMin;
    }
}
