package com.huihong.healthydiet.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.mInterface.SwitchListener;

/**
 * Created by zangyi_shuai_ge on 2017/7/15
 * 切换按钮控件
 */

public class SwitchImageView extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener {

    private boolean isChoose;
    private SwitchListener mSwitchListener;

    public void setmSwitchListener(SwitchListener mSwitchListener) {
        this.mSwitchListener = mSwitchListener;
    }


    public SwitchImageView(Context context) {
        super(context);
    }

    public SwitchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        isChoose = false;
        this.setImageResource(R.mipmap.switch_2);
        this.setOnClickListener(this);
    }

    public SwitchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        if (isChoose) {
            isChoose = false;
            this.setImageResource(R.mipmap.switch_2);
        } else {
            isChoose = true;
            this.setImageResource(R.mipmap.switch_1);
        }
        if (mSwitchListener != null) {
            mSwitchListener.mSwitch(isChoose);
        }
    }
}
