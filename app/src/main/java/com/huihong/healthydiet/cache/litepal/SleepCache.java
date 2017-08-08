package com.huihong.healthydiet.cache.litepal;

import org.litepal.crud.DataSupport;

/**
 * Created by zangyi_shuai_ge on 2017/8/8
 */

public class SleepCache extends DataSupport {

    //时间
    private int year;
    private int month;
    private int day;
    //睡眠时间
    private int sleepHour;
    private int sleepMin;
    //起床时间
    private int getUpHour;
    private int getUpMin;

    private boolean isDraw=true;

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
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
}
