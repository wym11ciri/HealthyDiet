package com.huihong.healthydiet.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.mInterface.CircleListener;
import com.huihong.healthydiet.mybean.TouchCircle;
import com.huihong.healthydiet.utils.common.DensityUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;

/**
 * Created by zangyi_shuai_ge on 2017/7/14
 * 自定义时间选择器View
 */

public class TimeSelectView extends View {


    private Paint ringPaint;//外部圆环画笔
    private Paint circlePaint;//圆形画笔


    private int viewWidth;//拿到View的宽度
    private int ringWidth;//圆环的宽度  即画笔的宽度  两个点击圆的直径
    private int addWidth;//圆外 冗余的宽度
    private int ringRadius;//圆环的半径
    private RectF rect;//画圆弧的区域
    //2个点击的圆
    private TouchCircle startCircle;//起点圆
    private TouchCircle endCircle;//终点圆
    private Bitmap startBitmap, endBitmap;//点击圆图片
    //画笔的渐变色
    private int[] circleColors;
    //设置启始 结束时间初始值
    private int startHour = 23;
    private int endHour = 8;
    private int startMin = 0;
    private int endMin = 0;
    //圆环的滑动监听
    private CircleListener circleListener;

    public void setCircleListener(CircleListener circleListener) {
        this.circleListener = circleListener;
    }

    //构造方法
    public TimeSelectView(Context context) {
        this(context, null);
    }

    public TimeSelectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public TimeSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ringWidth = DensityUtils.dp2px(context, 30);
        addWidth = DensityUtils.dp2px(context, 15);
        //外圈背景颜色
        ringPaint = new Paint();
        ringPaint.setStyle(Paint.Style.STROKE);//描边
        ringPaint.setStrokeWidth(ringWidth);
        ringPaint.setColor(getResources().getColor(R.color.circle_ring_bg));
        ringPaint.setAntiAlias(true);//设置抗锯齿

        //轨迹圈的颜色
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);//描边
        circlePaint.setStrokeWidth(ringWidth);
        circlePaint.setAntiAlias(true);//设置抗锯齿

        circleColors = new int[]{
                getResources().getColor(R.color.circle_color_09),
                getResources().getColor(R.color.circle_color_08),
                getResources().getColor(R.color.circle_color_07),
                getResources().getColor(R.color.circle_color_06),
                getResources().getColor(R.color.circle_color_05),
                getResources().getColor(R.color.circle_color_04),
                getResources().getColor(R.color.circle_color_03),
                getResources().getColor(R.color.circle_color_02),
                getResources().getColor(R.color.circle_color_01),
        };

        startHour = (int) SPUtils.get(context, "startHour", 23);
        startMin = (int) SPUtils.get(context, "startMin", 0);
        endHour = (int) SPUtils.get(context, "endHour", 8);
        endMin = (int) SPUtils.get(context, "endMin", 0);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        viewWidth = w;
        ringRadius = w / 2 - ringWidth / 2;
        rect = new RectF(-ringRadius, -ringRadius, ringRadius, ringRadius);
        //给画笔设置渐变色
        LinearGradient lg = new LinearGradient(0, 0, 100, 100, circleColors, null, Shader.TileMode.MIRROR);
        circlePaint.setShader(lg);
        //计算初始角度
        float startAngel = (float) ((startHour - 12) * 360.0000001 / 12.00001 + startMin * 15.0000001 / 60.00001) - 90;
        float endAngel = (float) (endHour * 360.0000001 / 12.00001 + endMin * 15.000001 / 60.00001) - 90;
        //构造2个点击事件
        startCircle = new TouchCircle(startAngel, ringWidth / 2, ringRadius);
        endCircle = new TouchCircle(endAngel, ringWidth / 2, ringRadius);
        //加载2张图片
        Bitmap bt1 = BitmapFactory.decodeResource(getResources(), R.mipmap.sleep_3);
        startBitmap = Bitmap.createScaledBitmap(bt1, ringWidth, ringWidth, false);
        bt1.recycle();//释放旧的图片资源
        Bitmap bt2 = BitmapFactory.decodeResource(getResources(), R.mipmap.sleep_4);
        endBitmap = Bitmap.createScaledBitmap(bt2, ringWidth, ringWidth, false);
        bt2.recycle();

        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(viewWidth / 2, viewWidth / 2);//将canvas 原点平移到画布中心
        canvas.drawCircle(0, 0, ringRadius, ringPaint);//绘制外部灰色圆环背景

        //绘制圆弧

        float startAngle = startCircle.getAngle();//开始角度
        float sweepAngle = endCircle.getAngle() - startAngle;//扫描的角度

        if (sweepAngle < 0) {
            sweepAngle = sweepAngle + 360;
        }

        //重新设置渐变色
        LinearGradient lg = new LinearGradient(
                (float) startCircle.getX(), (float) startCircle.getY(),
                (float) endCircle.getX(), (float) endCircle.getY(),
                circleColors, null, Shader.TileMode.CLAMP);  //

        circlePaint.setShader(lg);


        canvas.drawArc(rect, startAngle, sweepAngle, false, circlePaint);
        //绘制2张可以点击的图片

        canvas.drawBitmap(startBitmap, (float) startCircle.getX() - ringWidth / 2, (float) startCircle.getY() - ringWidth / 2, null);
        canvas.drawBitmap(endBitmap, (float) endCircle.getX() - ringWidth / 2, (float) endCircle.getY() - ringWidth / 2, null);
    }


    private boolean shouldReDraw = false;//是否需要重新绘制  即按下的时候处于2个圆环里面
    private boolean isStart = true;//是否在设置开始时间

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN://按下
                //先把坐标移动到圆心处获得新的坐标
                float getX = event.getX() - ringRadius;
                float getY = event.getY() - ringRadius;
                //计算点击的坐标是否在2个圆内

                if (getDistance(startCircle.getX(), startCircle.getY(), getX, getY) < startCircle.getR() + addWidth) {
                    isStart = true;//在设置开始时间
                    shouldReDraw = true;//需要重新绘制
                } else if (getDistance(endCircle.getX(), endCircle.getY(), getX, getY) < endCircle.getR() + addWidth) {
                    shouldReDraw = true;
                    isStart = false;
                } else {
                    shouldReDraw = false;
                }
                break;
            case MotionEvent.ACTION_MOVE://移动
                if (shouldReDraw) {
                    //转换坐标
                    float getX2 = event.getX() - ringRadius;
                    float getY2 = event.getY() - ringRadius;
                    //反三角函数求角度
                    double a = Math.atan2(getY2, getX2);//给的是弧度
                    float mAngle = (float) (360 * a / (2 * TouchCircle.PI));//弧度转角度
                    if (isStart) {
                        startCircle.setAngle(mAngle);
                    } else {
                        endCircle.setAngle(mAngle);
                    }
                    //通知更新时间
                    if (circleListener != null) {
                        circleListener.move(isStart, mAngle, false);
                    }
                    invalidate();
//                    return true;
                }
                break;
            case MotionEvent.ACTION_UP://松开

                LogUtil.i("zzzzzzzzzzzzzzzzzzzzzzz");

                if (shouldReDraw) {
                    //松开的时候把数据传递回来
                    if (circleListener != null) {
                        //通知已经松开手指 结束本次操作了
                        circleListener.move(isStart, 0, true);
                    }
                }
                //重置状态
                shouldReDraw = false;
                isStart = true;
                break;
        }
        return true;
    }

    public double getDistance(double x1, double y1, double x2, double y2) {
        double _x = Math.abs(x1 - x2);
        double _y = Math.abs(y1 - y2);
        return Math.sqrt(_x * _x + _y * _y);
    }
}

