package com.huihong.healthydiet.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.huihong.healthydiet.R;


/**
 * Created by zangyi_shuai_ge on 2017/5/19
 * 仪表盘
 */

public class Dashboard extends View {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            nowValue = (int) msg.obj;
            Dashboard.this.nowValue = nowValue;
            invalidate();
        }
    };

    private Paint paintDefault;//绘制仪表盘刻度的画笔
    private Paint paintNow;//绘制当前值的画笔

    private int screenW, screenH;//手机大小

    private final double PI = 3.1415926;//π值
    private double GRADUATION_LINE_LENGTH = 56;//刻度线的长度
    private int GRADUATION_LINE_WIDTH = 10;//刻度线的宽度
    private final int GRADUATION_LINE_NUM = 45; //将刻度分成几份
    private final double GRADUATION_LINE_MAX_NUM = 45.001;//仪表盘最大刻度
    private final int ANIMATION_TIME = 1000;//刻度动画时间
    //最新的温度值
    private int nowValue;

    private double radiusOfExcircle;//外圆半径


    public Dashboard(Context context) {
        this(context, null);
    }

    public Dashboard(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Dashboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        mContext = context;
        paintDefault = new Paint();
        paintDefault.setColor(getResources().getColor(R.color.motion_normal));
        paintDefault.setAntiAlias(true);//设置抗锯齿
        paintNow = new Paint();
//        if (MyApplication.isNormal) {
//            paintNow.setColor(getResources().getColor(R.color.dashBoard_blue));

//        } else {
            paintNow.setColor(getResources().getColor(R.color.motion_normal));
//        }
        paintNow.setAntiAlias(true);//设置抗锯齿
    }

    //大小改变的时候
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenH = h;
        screenW = w;
        radiusOfExcircle = 0.8 * w;
        GRADUATION_LINE_LENGTH = 0.17 * radiusOfExcircle / 2;
        GRADUATION_LINE_WIDTH = (int) (radiusOfExcircle / 80);
        paintDefault.setStrokeWidth(GRADUATION_LINE_WIDTH);
        paintNow.setStrokeWidth(GRADUATION_LINE_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制仪表盘刻度
        canvas.translate(screenW / 2, screenW / 2);//将canvas 原点平移到画布中心
        canvas.rotate(150);//将坐标系旋转150°
        //绘制仪表盘
        for (double i = 0; i < 2 * PI * 0.66667; i = i + 2 * PI * 0.66667 / GRADUATION_LINE_NUM) {
            //计算终点
            int x1 = (int) ((radiusOfExcircle / 2) * Math.cos(i));
            int y1 = (int) ((radiusOfExcircle / 2) * Math.sin(i));
            //计算起点
            int x2 = (int) ((radiusOfExcircle / 2 - GRADUATION_LINE_LENGTH) * Math.cos(i));
            int y2 = (int) ((radiusOfExcircle / 2 - GRADUATION_LINE_LENGTH) * Math.sin(i));
            //绘制刻度
            canvas.drawLine(x2, y2, x1, y1, paintDefault);// 画线  起点，终点
        }
        //绘制当前刻度
        for (double i = 0; i < (PI * 2) * (nowValue / GRADUATION_LINE_MAX_NUM) * 0.66667; i = i + 2 * PI * 0.66667 / GRADUATION_LINE_NUM) {
            //计算终点
            int x1 = (int) ((radiusOfExcircle / 2) * Math.cos(i));
            int y1 = (int) ((radiusOfExcircle / 2) * Math.sin(i));
            //计算起点
            int x2 = (int) ((radiusOfExcircle / 2 - GRADUATION_LINE_LENGTH) * Math.cos(i));
            int y2 = (int) ((radiusOfExcircle / 2 - GRADUATION_LINE_LENGTH) * Math.sin(i));
            //绘制刻度
            canvas.drawLine(x2, y2, x1, y1, paintNow);// 画线  起点，终点
        }
    }


    public void upDate(final double temperature) {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < temperature; i++) {
                    Message message = Message.obtain();
                    message.obj = i;
                    mHandler.sendMessage(message);
                    //线程休眠
                    try {
                        Thread.sleep((long) (ANIMATION_TIME / temperature));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    public void upDateNormal(int temperature) {
        Dashboard.this.nowValue = temperature;
        invalidate();
    }


}
