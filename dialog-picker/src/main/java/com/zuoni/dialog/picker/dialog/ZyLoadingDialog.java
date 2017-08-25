package com.zuoni.dialog.picker.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zuoni.android.dialog.R;

/**
 * 原来代码都是使用Builder方法来构造dialog的
 * 这里不使用Builder直接构造
 */
public class ZyLoadingDialog extends Dialog {

    private TextView tvMessage;
    private boolean cancelable=false;
    private boolean outsideCancelable=false;

    public ZyLoadingDialog(Context context) {
        //设置主题
        super(context,R.style.Theme_Light_NoTitle_NoShadow_Dialog);
        //设置UI
        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null);
        tvMessage = (TextView) view.findViewById(R.id.tvMessage);

        Window win = this.getWindow();
        assert win != null;
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        win.setGravity(Gravity.CENTER);
        this.setContentView(view);
        this.setCanceledOnTouchOutside(outsideCancelable);//点击外部取消
        this.setCancelable(cancelable);
    }

    public void setOutsideCancelable(boolean outsideCancelable){
        this.outsideCancelable=outsideCancelable;
        this.setCanceledOnTouchOutside(outsideCancelable);
    }
    public  void  setCancelablel(boolean cancelable){
        this.cancelable=cancelable;
        this.setCancelable(cancelable);
    }
    public void setMessage(String message){
        tvMessage.setText(message);
    }




}
