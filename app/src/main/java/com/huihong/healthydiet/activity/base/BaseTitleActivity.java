package com.huihong.healthydiet.activity.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;

import butterknife.ButterKnife;


/**
 * Created by zangyi_shuai_ge on 2017/5/15
 * 带有标题的界面
 */

public abstract class BaseTitleActivity extends BaseActivity {


    private TextView tvTitle;
    private LinearLayout layoutRight;
    private LinearLayout layoutLeft;
    private TextView tvBack;

    private ImageView ivLeft;
    private ImageView ivRight;

    public RelativeLayout layoutHead;

    private TextView tvRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setLayoutId());
        ButterKnife.bind(this);


//        layoutHead = (RelativeLayout) findViewById(R.id.layoutHead);
        tvTitle = (TextView) findViewById(R.id.tvTitle);//标题
        layoutRight = (LinearLayout) findViewById(R.id.layoutTopRight);//右侧按钮
        layoutLeft = (LinearLayout) findViewById(R.id.layoutLeft);//左侧按钮
//        ivLeft = (ImageView) findViewById(R.id.ivLeft);//左侧图标
//        ivRight = (ImageView) findViewById(R.id.ivRight);//右侧图标
//        tvBack = (TextView) findViewById(R.id.tvBack);
        layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);

            }
        });
//        tvRight = (TextView) findViewById(R.id.tvRight);
//


        initUI();
        initOnClickListener();
    }


    //设置右边
    public void setRightText(String text, int color, View.OnClickListener onClickListener) {
        tvRight.setText(text);
        tvRight.setTextColor(color);
        tvRight.setOnClickListener(onClickListener);
        layoutRight.setVisibility(View.VISIBLE);
        ivRight.setVisibility(View.GONE);
    }

    //设置右边
    public void setRightText(String text, View.OnClickListener onClickListener) {
        tvRight.setText(text);
        tvRight.setOnClickListener(onClickListener);
        layoutRight.setVisibility(View.VISIBLE);
        ivRight.setVisibility(View.GONE);
    }


    //设置标题
    public void setLeftIcon(int ImageRes) {
        ivLeft.setImageResource(ImageRes);
    }

    public void setRightIcon(int ImageRes) {
        ivRight.setImageResource(ImageRes);
    }

    //设置可见
    public void setRightVisibility(int Visibility) {
        layoutRight.setVisibility(Visibility);
    }

    public void setLeftVisibility(int Visibility) {
        layoutLeft.setVisibility(Visibility);
    }

    public void setBackText(int Visibility) {
        tvBack.setVisibility(Visibility);
    }

    //设置标题
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setBackText(String text) {
        tvBack.setText(text);
        tvBack.setVisibility(View.VISIBLE);
    }

    //设置监听
    public void setLeftOnClickListener(View.OnClickListener onClickListener) {
        layoutLeft.setOnClickListener(onClickListener);
    }

    public void setRightOnClickListener(View.OnClickListener onClickListener) {
        layoutRight.setVisibility(View.VISIBLE);
        layoutRight.setOnClickListener(onClickListener);
    }

    //设置标题栏头部颜色
    public void setHeadColor(int colorRes) {
        layoutHead.setBackgroundColor(getResources().getColor(colorRes));
    }

    //设置标题字颜色
    public void setHeadTitleColor(int colorRes) {
        tvTitle.setTextColor(getResources().getColor(colorRes));
    }


    //设置绑定页面id
    public abstract int setLayoutId();

    //初始化界面
    public abstract void initUI();

    //初始化监听事件
    public abstract void initOnClickListener();

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
