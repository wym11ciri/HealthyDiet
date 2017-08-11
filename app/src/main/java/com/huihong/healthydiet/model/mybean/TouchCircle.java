package com.huihong.healthydiet.model.mybean;

/**
 * Created by zangyi_shuai_ge on 2017/7/14
 * 圆形时间选择需要触摸的2个圆形
 */

public class TouchCircle {


    public static final double PI = 3.14592654;

    //获得坐标
    public double getX() {
        double mAngle = 2 * PI * this.angle / 360.00001;
        return R * Math.cos(mAngle);
    }

    public double getY() {
        double mAngle = 2 * PI * this.angle / 360.00001;
        return R * Math.sin(mAngle);
    }

//    private double x;
//    private double y;


    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    private int r;//半径
    private int R;
    private float angle;//角度

    public TouchCircle(float angle, int r, int R) {
        this.angle = angle;
        this.r = r;
        this.R = R;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }


}
