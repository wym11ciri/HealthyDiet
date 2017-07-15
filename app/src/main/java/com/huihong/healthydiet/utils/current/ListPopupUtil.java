package com.huihong.healthydiet.utils.current;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.BaseAdapter;


/**
 * Created by zangyi_shuai_ge on 2017/6/20
 * ListPopup工具类
 */

public class ListPopupUtil {


    /**
     * 展示一个ListPopup
     */
    public static ListPopupWindow showListPopup(Context mContext, View mAnchorView
            , BaseAdapter mAdapter, @DrawableRes int resId, int x, int y, int w, int h) {

        final ListPopupWindow listPopupWindow = new ListPopupWindow(mContext);
        if (resId != 0) {
            listPopupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(resId));
        }
//        PositionAdapter mAdapter = new PositionAdapter(mContext, mList);
        //设置ListView类型的适配器
        listPopupWindow.setAdapter(mAdapter);
        //给每个item设置监听事件
//        listPopupWindow.setOnItemClickListener(itemClickListener);
        //设置ListPopupWindow的锚点,也就是弹出框的位置是相对当前参数View的位置来显示，
        listPopupWindow.setAnchorView(mAnchorView);
        //ListPopupWindow 距锚点的距离，也就是相对锚点View的位置
        listPopupWindow.setHorizontalOffset(x);
        listPopupWindow.setVerticalOffset(y);
        //设置对话框的宽高
        listPopupWindow.setWidth(mAnchorView.getMeasuredWidth());
        listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setModal(false);
//        listPopupWindow.show();


        return listPopupWindow;

    }
    /**
     * 展示一个ListPopup
     */
    public static ListPopupWindow showListPopup2(Context mContext, View mAnchorView
            , BaseAdapter mAdapter, @DrawableRes int resId, int x, int y, int w, int h) {

        final ListPopupWindow listPopupWindow = new ListPopupWindow(mContext);
        if (resId != 0) {
            listPopupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(resId));
        }
//        PositionAdapter mAdapter = new PositionAdapter(mContext, mList);
        //设置ListView类型的适配器
        listPopupWindow.setAdapter(mAdapter);
        //给每个item设置监听事件
//        listPopupWindow.setOnItemClickListener(itemClickListener);
        //设置ListPopupWindow的锚点,也就是弹出框的位置是相对当前参数View的位置来显示，
        listPopupWindow.setAnchorView(mAnchorView);
        //ListPopupWindow 距锚点的距离，也就是相对锚点View的位置
        listPopupWindow.setHorizontalOffset(x);
        listPopupWindow.setVerticalOffset(y);
        //设置对话框的宽高
        listPopupWindow.setWidth(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setModal(false);
//        listPopupWindow.show();
        return listPopupWindow;

    }
}
