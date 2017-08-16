package com.huihong.healthydiet.model.mybean;

import com.huihong.healthydiet.utils.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zangyi_shuai_ge on 2017/7/27
 * 积分日期
 */

public class IntegralDate {

    //这里key 如果1-12
    //显示年份
    private int key;
    //星期
    private String week;
    //日期
    private String mdate;
    //积分图标地址
    private String iconAdd;
    //积分数量
    private double num;
    //获得积分事件
    private String  event;
    //从接口传递过来的完整事件
    private String date;



    //获得key用来显示header
    public int getKey() {
        try {
            String[] c=this.date.split("T");
            String mDate=c[0];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(mDate);
            Calendar mCalendar = Calendar.getInstance();
            int nowYear=mCalendar.get(Calendar.YEAR);
            mCalendar.setTime(date);
            int year = mCalendar.get(Calendar.YEAR);
            int month = mCalendar.get(Calendar.MONTH) + 1;
            int day = mCalendar.get(Calendar.DAY_OF_MONTH);

            if(nowYear!=year){
                return  year;
            }else {
                return month;
            }
//            return  month+"-"+day;

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    //通过接口传递过来的日期 截取自己想要的日期
    public String getMdate() {

        try {
            String[] c=this.date.split("T");
            String mDate=c[0];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(mDate);
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTime(date);
            int year = mCalendar.get(Calendar.YEAR);
            int month = mCalendar.get(Calendar.MONTH) + 1;
            int day = mCalendar.get(Calendar.DAY_OF_MONTH);
            return  month+"-"+day;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }



    public String getWeek() {
        String mTime="";

        try {
            String[] c=this.date.split("T");
            String mDate=c[0];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(mDate);
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTime(date);
            int year = mCalendar.get(Calendar.YEAR);
            int month = mCalendar.get(Calendar.MONTH) + 1;
            int day = mCalendar.get(Calendar.DAY_OF_MONTH);
            mTime=year+"-"+month+"-"+day;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "周"+DateUtil.getWeek(mTime);
    }

    public void setWeek(String week) {
        this.week = week;
    }



    public String getIconAdd() {
        return iconAdd;
    }

    public void setIconAdd(String iconAdd) {
        this.iconAdd = iconAdd;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
