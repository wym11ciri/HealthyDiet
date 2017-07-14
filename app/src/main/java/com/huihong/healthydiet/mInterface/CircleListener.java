package com.huihong.healthydiet.mInterface;

/**
 * Created by zangyi_shuai_ge on 2017/6/27
 * 筛选
 */

public interface CircleListener {
    //是否在设置睡眠时间  角度  是否结束
    void move(boolean isSetStart, float mAngle, boolean isEnd);
}
