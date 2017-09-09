package com.huihong.healthydiet.activity.token;

import android.app.Activity;

import com.huihong.healthydiet.utils.common.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/4/21
 * 这个类专门用来收集Activity
 */

public class TokenErrorActivityCollector {


    private static List<Activity> mActivities = new ArrayList<>();

    //向集合里添加一个活动
    public static void addActivity(Activity pActivity) {
        mActivities.add(pActivity);
    }

    //从数组里移除一个活动
    public static void removeActivity(Activity pActivity) {
        mActivities.remove(pActivity);
    }

    //销毁所有活动
    public static void finishAll() {
        LogUtil.i("启动销毁个数"+mActivities.size());
        for (Activity activity : mActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
