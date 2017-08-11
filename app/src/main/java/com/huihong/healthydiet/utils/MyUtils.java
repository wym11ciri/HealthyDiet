package com.huihong.healthydiet.utils;

import android.content.Context;
import android.widget.TextView;

import com.huihong.healthydiet.R;

/**
 * Created by zangyi_shuai_ge on 2017/8/10
 * 我的工具类
 */

public class MyUtils {

    //百分比字体颜色
    public static void setTextViewColor(TextView tvConstitutionPercentage, int  percentage, Context mContext) {
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
}
