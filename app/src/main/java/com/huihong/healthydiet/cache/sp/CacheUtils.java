package com.huihong.healthydiet.cache.sp;

import android.content.Context;

import com.huihong.healthydiet.mybean.PersonalInfo;
import com.huihong.healthydiet.utils.common.SPUtils;

import static com.huihong.healthydiet.utils.common.SPUtils.get;

/**
 * Created by zangyi_shuai_ge on 2017/7/29
 * 从SpUtil中取缓存数据
 */

public class CacheUtils {


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
        String phone= personalInfo.getPhone();
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

    //设置UserId
    public static void  setUserId(Context mContext,int newUserId) {
        SPUtils.put(mContext,"UserId",newUserId);
    }

    //设置token
    public static void  setToken(Context mContext,String  newToken) {
        SPUtils.put(mContext,"Token",newToken);
    }

    //获取UserId
    public static String getUserId(Context mContext) {
        return (int) SPUtils.get(mContext, "UserId", 0) + "";
    }

    //获取token
    public static String getToken(Context mContext) {
        return (String) SPUtils.get(mContext, "Token", "");
    }


}
