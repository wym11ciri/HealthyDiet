package com.huihong.healthydiet.mybean;

/**
 * Created by zangyi_shuai_ge on 2017/7/29
 * 个人信息
 */

public class PersonalInfo {

    private String headImageUrl;//头像链接地址
    private boolean isMan;//是否为男
    private String  age;//年龄
    private String height;//身高
    private String weight;//体重
    private String name;//名称
    private String constitution;//体质


    public String getHeadImageUrl() {
        if (headImageUrl == null) {
            return "";
        } else {
            return headImageUrl;
        }

    }

    public String getAge() {
        if (age == null) {
            return "";
        } else {
            return age;
        }

    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        if (height == null) {
            return "";
        } else {
            return height;
        }

    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        if (weight == null) {
            return "";
        } else {
            return weight;
        }

    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }


    public String getName() {
        if (name == null) {
            return "";
        } else {
            return name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConstitution() {
        if (constitution == null) {
            return "";
        } else {
            return constitution;
        }

    }

    public void setConstitution(String constitution) {
        this.constitution = constitution;
    }
}