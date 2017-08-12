package com.huihong.healthydiet.model.mybean;

/**
 * Created by zangyi_shuai_ge on 2017/8/12
 * 步数保存
 */

public class StepCount {

    private int stepCount;//运动步数
    private int time;//运动时长
    private float distance;//运动距离

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
