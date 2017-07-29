package com.huihong.healthydiet.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/18
 */

public class StringUtil {


    public static List<Boolean> getWeekList(String valueString) {
        List<Boolean> mList = new ArrayList<>();
        String[] sourceStrArray = valueString.split(",");
        for (int i = 0; i < sourceStrArray.length; i++) {
            if (sourceStrArray[i].equals("0")) {
                mList.add(false);
            } else {
                mList.add(true);
            }
        }
        return mList;
    }

    //传入日期 转换
    public static Calendar getDate(String dateString) {
        try {
            String[] c = dateString.split("T");
            String mDate = c[0];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(mDate);
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTime(date);
            return mCalendar;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
