package com.huihong.healthydiet.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihong.healthydiet.R;

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

}
