package com.huihong.healthydiet.model.httpmodel;

/**
 * Created by zangyi_shuai_ge on 2017/8/10
 * 个人全部信息
 */

public class PersonalAllInfo {
    /**
     * UserId : 16
     * name : 臧艺
     * phone : 15168212330
     * height : 173
     * weight : 22
     * sex : true
     * BirthDay : 2017-07-16T00:00:00
     * labourIntensity : 优秀
     * HeadImage : null
     * constitution : 气虚质
     * score : 0
     * age : 0
     * Token : null
     */

    private int UserId;
    private String name;
    private String phone;
    private String height;
    private String weight;
    private boolean sex;
    private String BirthDay;
    private String labourIntensity;
    private String HeadImage;
    private String constitution;
    private int score;
    private String age;
    private Object Token;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String BirthDay) {
        this.BirthDay = BirthDay;
    }

    public String getLabourIntensity() {
        return labourIntensity;
    }

    public void setLabourIntensity(String labourIntensity) {
        this.labourIntensity = labourIntensity;
    }

    public String getHeadImage() {
        return HeadImage;
    }

    public void setHeadImage(String HeadImage) {
        this.HeadImage = HeadImage;
    }

    public String getConstitution() {
        return constitution;
    }

    public void setConstitution(String constitution) {
        this.constitution = constitution;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Object getToken() {
        return Token;
    }

    public void setToken(Object Token) {
        this.Token = Token;
    }
}
