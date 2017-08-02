package com.zuoni.dialog.picker.mInterface;

/**
 * Created by zangyi_shuai_ge on 2017/7/1
 * 双列滚轮监听
 */

public interface OnDoubleDataSelectedListener {
    void onDataSelectedLeft(String itemValue);

    void onDataSelectedRight(String itemValue);
}