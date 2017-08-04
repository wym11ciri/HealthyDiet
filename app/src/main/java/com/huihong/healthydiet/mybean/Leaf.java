package com.huihong.healthydiet.mybean;

/**
 * Created by zangyi_shuai_ge on 2017/7/26
 * 积分树
 */

public class Leaf {
    //绘制bitmap的时候坐标是从左上角开始的
    //x,y为左上角的坐标
    private int startX;
    private float startY;
    private int integralWidth=60;
    private int eventWidth=70;

    private int alpha;

    private  float move;

    public float getMove() {
        return move;
    }

    public void setMove(float move) {
        this.move = move;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY+move;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public int getLeafSize() {
        return leafSize;
    }

    public void setLeafSize(int leafSize) {
        this.leafSize = leafSize;
        this.integralWidth= leafSize+5;
        this.eventWidth= leafSize+5;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    //叶子的宽度和高度
    private int leafSize;
    //加的积分
    private String integral;
    //事件
    private String event;




    //获得绘制积分的开始坐标X
    public float getDrawIntegralX() {
        return startX+leafSize;
    }
    //获得绘制积分的开始坐标Y
    public int getDrawIntegralY() {
        return (int) (startY+leafSize*0.8+move);
    }

    //获得绘制事件的开始坐标X
    public int getDrawEventX() {
        return startX+leafSize+integralWidth;
    }
    //获得绘制事件的开始坐标Y
    public float getDrawEventY() {
        return (int) (move+startY+leafSize*0.8);
    }

    //获得叶子点击事件区域的X
    public int getLeafXEnd() {
        return startX+leafSize+integralWidth+eventWidth;
    }
    //获得叶子点击事件区域的X
    public float getLeafYEnd() {
        return startY+leafSize;
    }

}
