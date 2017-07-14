package com.huihong.healthydiet.utils.current;

/**
 * Created by zangyi_shuai_ge on 2017/6/20
 * Dialog 工具类
 */

public class DialogUtil {


//    /**
//     * 创建一个AlertDialog
//     * 参数最多的
//     * 设置title message 左右按钮的点击事件
//     * 是否点击屏幕外可以取消
//     * 返回一个 AlertDialog
//     */
//
//    public static AlertDialog createAlertDialog(Context mContext, String title, String message
//            , String rightText, DialogInterface.OnClickListener rightOnClickListener
//            , String leftText, DialogInterface.OnClickListener leftOnClickListener
//            , boolean isCancelable) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.setPositiveButton(rightText, rightOnClickListener);
//        builder.setNegativeButton(leftText, leftOnClickListener);
//        builder.setCancelable(isCancelable);
//        return builder.create();
//    }
//
//    public AlertDialog createAlertDialog(Context mContext, String title, String message
//            , String rightText, DialogInterface.OnClickListener rightOnClickListener
//            , boolean isCancelable) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.setPositiveButton(rightText, rightOnClickListener);
////        builder.setNegativeButton(leftText, leftOnClickListener);
//        builder.setCancelable(isCancelable);
//        return builder.create();
//    }
//
//    //自定义一个AlertDialog
//
//    public AlertDialog createAlertDialog(Context mContext) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//        AlertDialog dialog;
//        //传入自定义布局
//        View v = LayoutInflater.from(mContext).inflate(R.layout.dialog_user_agreement, null);
//        //设置View里面的事件
//        Button btAccept = (Button) v.findViewById(R.id.btAccept);
//        Button btCancel = (Button) v.findViewById(R.id.btCancel);
//        btAccept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        builder.setCancelable(false);
//        builder.setView(v);
//
////        dialog=builder.create();
//        //设置宽度
////        WindowManager wm = dialog.getWma
////        int width = wm.getDefaultDisplay().getWidth();
////        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
////        params.width = width * 3 / 4;
////        dialog.getWindow().setAttributes(params);
////        dialog.getWindow().setWindowAnimations(R.style.mypopwindow_anim_style);
////        dialog.getWindow().seg
//        return builder.create();
//    }
//
//
//    //自定义一个AlertDialog
//
//    public Dialog createBottomDialog(Context mContext) {
//        //定义一个Dialog
//        Dialog dialog = new Dialog(mContext);
//        //自定义dialog界面
//        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_picker_data, null);
//        //这里拿到View后
//
//        //设置Dialog的位置
//        Window win = dialog.getWindow();
//        win.getDecorView().setPadding(0, 0, 0, 0);
//        //设置dialog的高宽
//        WindowManager.LayoutParams lp = win.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        win.setAttributes(lp);
//        win.setGravity(Gravity.BOTTOM); //设置Dialog的位置
//        win.setWindowAnimations(R.style.Animation_Bottom_Rising);//给Dialog设置动画
//        dialog.setContentView(view);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.setCancelable(true);
//
////            params.loopData = loopData;
////            dialog.setParams(params);
//
//        return dialog;
//
//
//    }


}
