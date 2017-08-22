package com.huihong.healthydiet.cache.sp;

import android.content.Context;

import com.huihong.healthydiet.model.mybean.AccountPassword;
import com.huihong.healthydiet.model.mybean.LevelInfo;
import com.huihong.healthydiet.model.mybean.MyDate;
import com.huihong.healthydiet.model.mybean.PersonalInfo;
import com.huihong.healthydiet.model.mybean.StepCount;
import com.huihong.healthydiet.model.mybean.Time;
import com.huihong.healthydiet.utils.CalendarUtils;
import com.huihong.healthydiet.utils.DateUtil;
import com.huihong.healthydiet.utils.common.LogUtil;
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
        String name = (String) SPUtils.get(mContext, "name", "");//名称
        String constitution = (String) SPUtils.get(mContext, "constitution", "");//体质
        String phone = (String) SPUtils.get(mContext, "phone", "");
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

        SPUtils.put(mContext, "name", name);
        SPUtils.put(mContext, "constitution", constitution);
        SPUtils.put(mContext, "headImageUrl", headImageUrl);
        SPUtils.put(mContext, "isMan", isMan);
        SPUtils.put(mContext, "age", age);
        SPUtils.put(mContext, "height", height);
        SPUtils.put(mContext, "weight", weight);
        SPUtils.put(mContext, "phone", phone);
    }

    //phone
    public static void setPhone(Context mContext, String Phone) {
        SPUtils.put(mContext, "phone", Phone);
    }

    //
    public static String getPhone(Context mContext) {
        return (String) SPUtils.get(mContext, "phone", "") + "";
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

        String weekValueString = (String) SPUtils.get(mContext, "weekValueString", "1,1,1,1,1,0,0,");

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
//    public static int getStepCount(Context mContext) {
//        return (int) SPUtils.get(mContext, "stepCount", 0);
//    }

    //保存步数
    public static void putStepCount(Context mContext, int nowCount) {
        SPUtils.put(mContext, "stepCount", nowCount);
    }

    //保存当前运动状态

    //保存步数
    public static void putStepCount(Context mContext, StepCount stepCount) {
        //存步数的时候去判断下 如果得到的时间不是今天
        String stepDate = (String) SPUtils.get(mContext, "stepDate", "");
        String nowDate = CalendarUtils.getYear() + "-" + CalendarUtils.getMonth() + "-" + CalendarUtils.getDay();

        assert stepDate != null;
        if (stepDate.equals(nowDate)) {
            //如果时间相等
            int mStepCount = stepCount.getStepCount();
            int mTime = stepCount.getTime();
            float mDistance = stepCount.getDistance();

            SPUtils.put(mContext, "stepCount", mStepCount);
            SPUtils.put(mContext, "stepTime", mTime);
            SPUtils.put(mContext, "stepDistance", mDistance);
        } else {
            //如果不相等 清0数据
            SPUtils.put(mContext, "stepCount", 0);
            SPUtils.put(mContext, "stepTime", 0);
            SPUtils.put(mContext, "stepDate", nowDate);
//            SPUtils.put(mContext, "stepDistance", mDistance);

        }

    }

    //获得当前行走的步数
    public static StepCount getStepCount(Context mContext) {

        String nowDate = CalendarUtils.getYear() + "-" + CalendarUtils.getMonth() + "-" + CalendarUtils.getDay();
        StepCount stepCount = new StepCount();
        int mStepCount = (int) SPUtils.get(mContext, "stepCount", 0);
        int stepTime = (int) SPUtils.get(mContext, "stepTime", 0);
        String stepDate = (String) SPUtils.get(mContext, "stepDate", "");
        //拿数据的时候也判断下
        assert stepDate != null;
        if (stepDate.equals(nowDate)) {
            //时间一样直接返回查询到的数据
            stepCount.setTime(stepTime);
            stepCount.setStepCount(mStepCount);

        } else {
            SPUtils.put(mContext, "stepCount", 0);
            SPUtils.put(mContext, "stepTime", 0);
            SPUtils.put(mContext, "stepDate", nowDate);
            stepCount.setTime(0);
//        stepCount.setDistance(stepDistance);
            stepCount.setStepCount(0);
        }
        return stepCount;
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


    public static void setRunState(Context mContext, String state) {
        SPUtils.put(mContext, "RunState", state);
    }

    public static String getRunState(Context mContext) {
        //先判断是不是当天第一次打开
        String lastDate = (String) SPUtils.get(mContext, "lastDate", "");
        String nowDate = CalendarUtils.getYear() + "-" + CalendarUtils.getMonth() + "-" + CalendarUtils.getDay();

        if (lastDate.equals(nowDate)) {
            return (String) SPUtils.get(mContext, "RunState", "OFF");
        } else {
            SPUtils.put(mContext, "RunState", "OFF");
            SPUtils.put(mContext, "lastDate", nowDate);
            return "OFF";
        }


    }

    //闹铃状态
    //设置闹铃状态
    public static void setOpenAlarm(Context mContext, boolean isOpen) {
        SPUtils.put(mContext, "isOpenAlarm", isOpen);
    }

    //获得闹铃状态
    public static boolean isOpenAlarm(Context mContext) {
        return (boolean) SPUtils.get(mContext, "isOpenAlarm", true);
    }

    //运动记录缓存
    public static void setDayRunCache(Context mContext, String dayRunCache) {
        SPUtils.put(mContext, "dayRunCache", dayRunCache);
    }


    //设置睡眠时间
    //获得睡眠时间
    public static void setSleepTime(Context mContext, int hour, int min) {
        SPUtils.put(mContext, "sleepHour", hour);
        SPUtils.put(mContext, "sleepMin", min);
    }

    public static void setGetUpTime(Context mContext, int hour, int min) {
        SPUtils.put(mContext, "getUpHour", hour);
        SPUtils.put(mContext, "getUpMin", min);
    }

    public static void setGetUpTime(Context mContext, Time time) {
        SPUtils.put(mContext, "getUpHour", time.getHour());
        SPUtils.put(mContext, "getUpMin", time.getMin());
        LogUtil.i("闹钟起床" + time.getHour() + ":" + time.getMin());
    }

    public static void setSleepTime(Context mContext, Time time) {
        SPUtils.put(mContext, "sleepHour", time.getHour());
        SPUtils.put(mContext, "sleepMin", time.getMin());
        LogUtil.i("闹钟睡眠" + time.getHour() + ":" + time.getMin());
    }

    public static Time getGetUpTime(Context mContext) {

        Time time = new Time();
        int hour = (int) SPUtils.get(mContext, "getUpHour", 0);
        int min = (int) SPUtils.get(mContext, "getUpMin", 0);

        time.setHour(hour);
        time.setMin(min);

        LogUtil.i("闹钟获得起床", hour + ":" + min);

        return time;
    }

    public static Time getSleepTime(Context mContext) {

        Time time = new Time();
        int hour = (int) SPUtils.get(mContext, "sleepHour", 0);
        int min = (int) SPUtils.get(mContext, "sleepMin", 0);

        time.setHour(hour);
        time.setMin(min);
        LogUtil.i("闹钟获得睡眠", hour + ":" + min);
        return time;
    }

    public static void setLevels(Context mContext, int levels) {
        SPUtils.put(mContext, "levels", levels);
    }

    public static int getLevels(Context mContext) {

        int a = (int) SPUtils.get(mContext, "levels", 1);
        return a;
    }

    //设置提前时长的类型
    public static void setLeadTimeType(Context mContext, String type) {
        SPUtils.put(mContext, "LeadTimeType", type);
    }

    public static String getLeadTimeType(Context mContext) {
        return (String) SPUtils.get(mContext, "LeadTimeType", "1");
    }

    //判断App是不是今天第一次打开
    public static boolean isFirstOpen(Context mContext) {
        //获得今天的时间
        String nowDay = String.valueOf(DateUtil.getYear()) + ":" + String.valueOf(DateUtil.getMonth()) + ":" + String.valueOf(DateUtil.getDay()) + "";


        String cacheDay = (String) SPUtils.get(mContext, "appFirstOpen", "");
        String zzz = nowDay + "YES";
        LogUtil.i("判断字符串是否一样", zzz + "====" + cacheDay + zzz.equals(cacheDay));
        if (zzz.equals(cacheDay)) {
            LogUtil.i("判断字符串是否一样", zzz + "====" + cacheDay + zzz.equals(cacheDay));
            return true;
        } else {
            LogUtil.i("判断字符串是否一样", zzz + "====" + cacheDay + zzz.equals(cacheDay));
            SPUtils.put(mContext, "appFirstOpen", zzz);
            return false;
        }
    }


    //设置闹铃的日期
    public static void setGetUpDate(Context mContext) {
        int year = DateUtil.getYear();
        int month = DateUtil.getMonth();
        int day = DateUtil.getDay();

        SPUtils.put(mContext, "GetUpDateYear", year);
        SPUtils.put(mContext, "GetUpDateMonth", month);
        SPUtils.put(mContext, "GetUpDateDay", day);
    }

    public static MyDate getGetUpDate(Context mContext) {
        MyDate myDate = new MyDate();
        myDate.setDay((int) SPUtils.get(mContext, "GetUpDateDay", 1));
        myDate.setMonth((int) SPUtils.get(mContext, "GetUpDateMonth", 1));
        myDate.setYear((int) SPUtils.get(mContext, "GetUpDateYear", 2016));
        return myDate;
    }

    //获取等级信息
    public static void putLevelInfo(Context mContext, LevelInfo mLevelInfo) {
        String currentScore = mLevelInfo.getCurrentScore();
        String nextScore = mLevelInfo.getNextScore();
        String currentName = mLevelInfo.getCurrentName();
        int currentLv = mLevelInfo.getCurrentLv();

        SPUtils.put(mContext, "currentScore", currentScore);
        SPUtils.put(mContext, "nextScore", nextScore);
        SPUtils.put(mContext, "currentName", currentName);
        SPUtils.put(mContext, "currentLv", currentLv);
    }

    public static LevelInfo getLevelInfo(Context mContext) {
        LevelInfo levelInfo = new LevelInfo();
        String currentScore = (String) SPUtils.get(mContext, "currentScore", "0");
        String nextScore = (String) SPUtils.get(mContext, "nextScore", "0");
        String currentName = (String) SPUtils.get(mContext, "currentName", "");
        int currentLv = (int) SPUtils.get(mContext, "currentLv", 1);
        return levelInfo;
    }


    //设置用户账号密码

    public static void setAccountPassword(Context mContext, AccountPassword accountPassword) {
        String account = accountPassword.getAccount();
        String password = accountPassword.getPassword();
        SPUtils.put(mContext,"UserAccount",account);
        SPUtils.put(mContext,"UserPassword",password);
        LogUtil.i("存取账号密码",account+"=="+password);
    }

    public static AccountPassword getAccountPassword(Context mContext) {
        AccountPassword accountPassword=new AccountPassword();
        String password= (String) SPUtils.get(mContext,"UserPassword","");
        String account= (String) SPUtils.get(mContext,"UserAccount","");
        LogUtil.i("获得账号密码",account+"=="+password);
        accountPassword.setAccount(account);
        accountPassword.setPassword(password);
        return  accountPassword;
    }

}
