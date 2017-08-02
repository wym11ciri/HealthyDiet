package com.huihong.healthydiet.mybean;

/**
 * Created by zangyi_shuai_ge on 2017/8/1
 */

public class Sleep {

    private int sleepHour;
    private int sleepMin;

    private int getUpHour;
    private int getUpMin;



    private boolean isGetUp = false;

    public int getSleepHour() {
        return sleepHour;
    }

    public void setSleepHour(int sleepHour) {
        this.sleepHour = sleepHour;
    }

    public int getSleepMin() {
        return sleepMin;
    }

    public void setSleepMin(int sleepMin) {
        this.sleepMin = sleepMin;
    }

    public int getGetUpHour() {
        return getUpHour;
    }

    public void setGetUpHour(int getUpHour) {
        this.getUpHour = getUpHour;
    }

    public int getGetUpMin() {
        return getUpMin;
    }

    public void setGetUpMin(int getUpMin) {
        this.getUpMin = getUpMin;
    }

    public boolean isGetUp() {
        return isGetUp;
    }

    public void setGetUp(boolean getUp) {
        isGetUp = getUp;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    private int year;
    private int month;
    private int day;

}
