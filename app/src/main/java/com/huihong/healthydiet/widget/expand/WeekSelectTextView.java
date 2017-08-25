package com.huihong.healthydiet.widget.expand;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.huihong.healthydiet.R;

/**
 * Created by zangyi_shuai_ge on 2017/7/15
 */

public class WeekSelectTextView extends android.support.v7.widget.AppCompatTextView implements View.OnClickListener {


    private boolean isChoose = false;

    public WeekSelectTextView(Context context) {
        super(context);
    }

    public WeekSelectTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        isChoose = false;
        this.setBackgroundResource(R.mipmap.circle_00009);
        this.setTextColor(getResources().getColor(R.color.black_2));
        this.setOnClickListener(this);
    }

    public WeekSelectTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        if (isChoose) {
            isChoose = false;
            this.setBackgroundResource(R.mipmap.circle_00009);
            this.setTextColor(getResources().getColor(R.color.black_2));
        } else {
            isChoose = true;
            this.setBackgroundResource(R.mipmap.circle_00008);
            this.setTextColor(getResources().getColor(R.color.color_white));
        }

    }

    public void setChoose(boolean isChoose) {

        this.isChoose = isChoose;
        if (isChoose) {
            this.setBackgroundResource(R.mipmap.circle_00008);
            this.setTextColor(getResources().getColor(R.color.color_white));
        } else {
            this.setBackgroundResource(R.mipmap.circle_00009);
            this.setTextColor(getResources().getColor(R.color.black_2));
        }


    }

    public boolean getChoose() {
        return isChoose;
    }
}
