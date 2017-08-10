package com.huihong.healthydiet.activity.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;

/**
 * Created by zangyi_shuai_ge on 2017/5/15
 * 带有标题的Activity
 */
public abstract class BaseTitleActivity2 extends BaseActivity {


    private ImageView ivTitleLeft;
    private LinearLayout layoutTitleLeft;
    private ImageView ivTitleRight;
    private LinearLayout layoutTitleRight;
    private TextView tvTitleText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        initTitleBarUI();
        layoutTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
    }

    private void initTitleBarUI() {
        layoutTitleLeft = (LinearLayout) findViewById(R.id.layoutTitleLeft);
        ivTitleLeft = (ImageView) findViewById(R.id.ivTitleLeft);
        ivTitleRight = (ImageView) findViewById(R.id.ivTitleRight);
        layoutTitleRight = (LinearLayout) findViewById(R.id.layoutTitleRight);
        tvTitleText = (TextView) findViewById(R.id.tvTitleText);
    }


    //设置标题
    public void setLeftIcon(int ImageRes) {
        ivTitleLeft.setImageResource(ImageRes);
    }

    public void setRightIcon(int ImageRes) {
        ivTitleRight.setImageResource(ImageRes);
    }

    //设置可见
    public void setRightVisibility(int Visibility) {
        layoutTitleRight.setVisibility(Visibility);
    }

    public void setLeftVisibility(int Visibility) {
        layoutTitleLeft.setVisibility(Visibility);
    }

    //设置标题
    public void setTitle(String title) {
        tvTitleText.setText(title);
    }


    //设置监听
    public void setLeftOnClickListener(View.OnClickListener onClickListener) {
        layoutTitleLeft.setOnClickListener(onClickListener);
    }

    public void setRightOnClickListener(View.OnClickListener onClickListener) {
        layoutTitleRight.setVisibility(View.VISIBLE);
        layoutTitleRight.setOnClickListener(onClickListener);
    }


    //设置标题字颜色
    public void setHeadTitleColor(int colorRes) {
        tvTitleText.setTextColor(getResources().getColor(colorRes));
    }

    public void finishActivity() {
        finish();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

    //设置绑定页面id
    public abstract int setLayoutId();


    @Override
    public void onBackPressed() {
        finishActivity();
    }


}
