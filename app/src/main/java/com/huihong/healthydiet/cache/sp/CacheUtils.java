package com.huihong.healthydiet.cache.sp;

import android.content.Context;

import com.huihong.healthydiet.mybean.PersonalInfo;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.widget.WeekSelectTextView;

import java.util.ArrayList;
import java.util.List;

import static com.huihong.healthydiet.utils.common.SPUtils.get;

/**
 * Created by zangyi_shuai_ge on 2017/7/29
 * 从SpUtil中取缓存数据
 */

public class CacheUtils {

    //个人信息
    //从缓存中读取个人信息
    public static PersonalInfo getPersonalInfo(Context mContext) {
        String headImageUrl = (String) get(mContext, "headImageUrl", "");//头像链接地址
        boolean isMan = (boolean) SPUtils.get(mContext, "isMan", true);//是否为男
        String age = (String) SPUtils.get(mContext, "age", "15");//年龄
        String height = (String) SPUtils.get(mContext, "height", "170");//身高
        String weight = (String) SPUtils.get(mContext, "weight", "50");//体重
        String name = (String) SPUtils.get(mContext, "name", "zangyi");//名称
        String constitution = (String) SPUtils.get(mContext, "constitution", "");//体质
        String phone = (String) SPUtils.get(mContext, "phone", "");//体质
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setAge(age);
        personalInfo.setHeight(height);
        personalInfo.setMan(isMan);
        personalInfo.setWeight(weight);
        personalInfo.setName(name);
        personalInfo.setHeadImageUrl(headImageUrl);
        personalInfo.setConstitution(constitution);
        personalInfo.setPhone(phone);
        return personalInfo;
    }

    //向缓存中存入个人信息
    public static void putPersonalInfo(Context mContext, PersonalInfo personalInfo) {

        String headImageUrl = personalInfo.getHeadImageUrl();//头像链接地址
        boolean isMan = personalInfo.isMan();//是否为男
        String age = personalInfo.getAge();//年龄
        String height = personalInfo.getHeight();//身高
        String weight = personalInfo.getWeight();//体重
        String name = personalInfo.getName();//名称
        String constitution = personalInfo.getConstitution();//体质
        String phone = personalInfo.getPhone();
        if (!name.equals("")) {
            SPUtils.put(mContext, "name", name);
        }
        if (!constitution.equals("")) {
            SPUtils.put(mContext, "constitution", constitution);
        }

        if (!headImageUrl.equals("")) {
            SPUtils.put(mContext, "headImageUrl", headImageUrl);
        }

        SPUtils.put(mContext, "isMan", isMan);
        SPUtils.put(mContext, "age", age);

        SPUtils.put(mContext, "height", height);
        SPUtils.put(mContext, "weight", weight);
        SPUtils.put(mContext, "phone", phone);
    }

    //UserId
    //设置UserId
    public static void setUserId(Context mContext, int newUserId) {
        SPUtils.put(mContext, "UserId", newUserId);
    }

    //获取UserId
    public static String getUserId(Context mContext) {
        return (int) SPUtils.get(mContext, "UserId", 0) + "";
    }

    //token
    //设置token
    public static void setToken(Context mContext, String newToken) {
        SPUtils.put(mContext, "Token", newToken);
    }

    //获取token
    public static String getToken(Context mContext) {
        return (String) SPUtils.get(mContext, "Token", "");
    }

    //睡眠星期
    //设置睡眠星期
    public static void setSleepWeek(Context mContext, List<WeekSelectTextView> mWeekList) {
        String weekString = "";
        if (mWeekList.size() == 7) {
            for (int i = 0; i < 7; i++) {
                if (mWeekList.get(i).getChoose()) {
                    weekString = weekString + "1" + ",";
                } else {
                    weekString = weekString + "0" + ",";
                }
            }
            SPUtils.put(mContext, "weekValueString", weekString);
        }
    }

    //返回睡眠星期
    public static List<Boolean> getSleepWeek(Context mContext) {

        String weekValueString = (String) SPUtils.get(mContext, "weekValueString", "0,0,0,0,0,0,0,");

        List<Boolean> mList = new ArrayList<>();
        assert weekValueString != null;
        String[] sourceStrArray = weekValueString.split(",");
        for (String aSourceStrArray : sourceStrArray) {
            if (aSourceStrArray.equals("0")) {
                mList.add(false);
            } else {
                mList.add(true);
            }
        }
        return mList;
    }

    //步数
    //获得当前行走的步数
    public static int getStepCount(Context mContext) {
        return (int) SPUtils.get(mContext, "stepCount", 0);
    }

    //保存步数
    public static void putStepCount(Context mContext, int nowCount) {
        SPUtils.put(mContext, "stepCount", nowCount);
    }

    //运动状态
    //设置运动状态
    public static void setRun(Context mContext, boolean isRunning) {
        SPUtils.put(mContext, "isRunning", isRunning);
    }

    //获得运动状态
    public static boolean getRun(Context mContext) {
        return (boolean) SPUtils.get(mContext, "isRunning", false);
    }

    //闹铃状态
    //设置闹铃状态
    public static void setOpenAlarm(Context mContext, boolean isOpen) {
        SPUtils.put(mContext, "isOpenAlarm", isOpen);
    }

    //获得闹铃状态
    public static boolean isOpenAlarm(Context mContext) {
        return (boolean) SPUtils.get(mContext, "isOpenAlarm", false);
    }
}
