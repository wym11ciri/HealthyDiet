package com.huihong.healthydiet.utils;

import java.util.ArrayList;
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
}
