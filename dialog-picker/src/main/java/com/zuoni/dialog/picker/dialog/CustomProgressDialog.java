package com.zuoni.dialog.picker.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.zuoni.android.dialog.R;

/**
 * 自定义ProgressDialog
 */
public class CustomProgressDialog extends Dialog {

    private Params params;

    public CustomProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private void setParams(CustomProgressDialog.Params params) {
        this.params = params;
    }


    public static class Builder {
        private final Context context;
        private final CustomProgressDialog.Params params;

        public Builder(Context context) {
            this.context = context;
            params = new CustomProgressDialog.Params();
        }


        public Builder setTakePhotoOnClickListener(View.OnClickListener takePhotoOnClickListener) {
            params.takePhotoOnClickListener = takePhotoOnClickListener;
            return this;
        }

        public Builder setGetPhotoOnClickListener(View.OnClickListener getPhotoOnClickListener) {
            params.getPhotoOnClickListener = getPhotoOnClickListener;
            return this;
        }

        public CustomProgressDialog create() {
            CustomProgressDialog dialog = new CustomProgressDialog(context, params.shadow ? R.style.Theme_Light_NoTitle_Dialog : R.style.Theme_Light_NoTitle_NoShadow_Dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.bottom_get_photo_dialog, null);

            Button btGetPhoto = (Button) view.findViewById(R.id.btGetPhoto);
            Button btTakePhoto = (Button) view.findViewById(R.id.btTakePhoto);

            if(params.takePhotoOnClickListener!=null){
                btTakePhoto.setOnClickListener(params.takePhotoOnClickListener);
            }
            if(params.getPhotoOnClickListener!=null){
                btGetPhoto.setOnClickListener(params.getPhotoOnClickListener);
            }

            Window win = dialog.getWindow();
            assert win != null;
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            win.setAttributes(lp);
            win.setGravity(Gravity.BOTTOM);
            win.setWindowAnimations(R.style.Animation_Bottom_Rising);

            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(params.canCancel);//点击外部取消
            dialog.setCancelable(params.canCancel);

            dialog.setParams(params);

            return dialog;
        }

    }


    private static final class Params {
        private boolean shadow = true;
        private boolean canCancel = true;

        private View.OnClickListener getPhotoOnClickListener;
        private View.OnClickListener takePhotoOnClickListener;
    }
}
