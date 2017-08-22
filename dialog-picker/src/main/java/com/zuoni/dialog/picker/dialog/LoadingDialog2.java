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
 * 从底部弹出的Dialog
 */
public class LoadingDialog2 extends Dialog {

    private TextView tvMessage;
    private boolean cancelable=false;
    private boolean outsideCelable=false;

    public LoadingDialog2(Context context,int a) {
        super(context,a);
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
//            win.setWindowAnimations(R.style.Animation_Bottom_Rising);

        this.setContentView(view);
        this.setCanceledOnTouchOutside(outsideCelable);//点击外部取消
        this.setCancelable(cancelable);

    }


    public void setMessage(String message){
        tvMessage.setText(message);
    }




}
