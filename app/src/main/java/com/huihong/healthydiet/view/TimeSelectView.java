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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.mInterface.CircleListener;
import com.huihong.healthydiet.utils.common.DensityUtils;
import com.huihong.healthydiet.utils.common.LogUtil;

/**
 * Created by zangyi_shuai_ge on 2017/7/14
 */

public class TimeSelectView extends View {


    private Paint ringPaint;//外部圆环画笔
    private Paint circlePaint;//圆形画笔

    private Paint touchPaint;

    private int viewWidth;
    private int ringRadius;


    private int ringWidth = 60;

    private TouchCircle startCircle;//起点圆
    private TouchCircle endCircle;//终点圆

    private int[] circleColors ;

    private RectF rect;


    private Bitmap startBitmap, endBitmap;

    private int startHour = 23;
    private int endHour = 8;
    private int startMin = 0;
    private int endMin = 0;

    private CircleListener circleListener;
    public void  setCircleListener(CircleListener circleListener){
        this.circleListener=circleListener;
    }


    public TimeSelectView(Context context) {
        this(context, null);
    }

    public TimeSelectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }



    public TimeSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ringWidth= DensityUtils.dp2px(context,30);
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
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        //获得该View距离父布局的距离

        this.getTop();
        this.getLeft();
        LogUtil.i("获得该View距离父布局的距离" + this.getTop() + "===" + this.getLeft());

        viewWidth = w;
        ringRadius = w / 2 - ringWidth / 2;
        LogUtil.i("zangyi", ringRadius + "zzzz");
        rect = new RectF(-ringRadius, -ringRadius, ringRadius, ringRadius);

        if (circleColors.length > 1) {
            LinearGradient lg = new LinearGradient(0, 0, 100, 100, circleColors, null, Shader.TileMode.MIRROR);  //
//            circlePaint.setShader(new SweepGradient(0, 0, doughnutColors, null));
            circlePaint.setShader(lg);
        } else {
            circlePaint.setColor(getResources().getColor(R.color.recommend_text_select));
        }


        float startAngel = (float) ((startHour-12 )* 360.0000001 / 12.00001 + startMin * 15.0000001 / 60.00001)-90;
        float endAngel = (float) (endHour * 360.0000001 / 12.00001 + endMin * 15.000001 / 60.00001)-90;

        startCircle = new TouchCircle(startAngel, ringWidth / 2, ringRadius);
        endCircle = new TouchCircle(endAngel, ringWidth / 2, ringRadius);


        //加载2张图片

        Bitmap bt1 = BitmapFactory.decodeResource(getResources(), R.mipmap.sleep_3);
        startBitmap = Bitmap.createScaledBitmap(bt1, ringWidth, ringWidth, false);
        bt1.recycle();
        Bitmap bt2 = BitmapFactory.decodeResource(getResources(), R.mipmap.sleep_4);
        endBitmap = Bitmap.createScaledBitmap(bt2, ringWidth, ringWidth, false);
        bt2.recycle();

        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(viewWidth / 2, viewWidth / 2);//将canvas 原点平移到画布中心
        canvas.drawCircle(0, 0, ringRadius, ringPaint);//绘制圆形背景

        //绘制圆弧
//        canvas.drawArc(oval,0,140,false,backgroundPaint);
        //绘制圆弧
        //计算开始角度
        float startAngle = startCircle.getAngle();

        float endAngle = endCircle.getAngle() - startAngle;

        LinearGradient lg = new LinearGradient((float) startCircle.getX(), (float) startCircle.getY()
                , (float) endCircle.getX(), (float) endCircle.getY(), circleColors, null, Shader.TileMode.CLAMP );  //
//            circlePaint.setShader(new SweepGradient(0, 0, doughnutColors, null));
        circlePaint.setShader(lg);
        LogUtil.i("jiaodu","startAngle"+startAngle+"endAngle"+endAngle);

        if(endAngle<0){
            endAngle=endAngle+360;
        }

        canvas.drawArc(rect, startAngle, endAngle, false, circlePaint);
        //绘制2张图片

        canvas.drawBitmap(startBitmap,(float) startCircle.getX()-ringWidth/2,(float) startCircle.getY()-ringWidth/2,null);
        canvas.drawBitmap(endBitmap,(float) endCircle.getX()-ringWidth/2,(float) endCircle.getY()-ringWidth/2,null);
    }

    public  void init(float startAngle,float endAngle){
//        startCircle.setAngle(250);
//        endCircle.setAngle(0);
//        invalidate();
    }


    private boolean shouldReDraw = false;
    private boolean isStart = true;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //获取手指在屏幕上的坐标
        float x = event.getX();
        float y = event.getY();

        //获取手指的操作--》按下、移动、松开
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN://按下
                //先把坐标移动到圆心处获得新的坐标
                float getX = event.getX() - ringRadius;
                float getY = event.getY() - ringRadius;
                //计算点击的坐标是否在2个圆内

                if (getDistance(startCircle.getX(), startCircle.getY(), getX, getY) < startCircle.getR() + 30) {
                    isStart = true;
                    shouldReDraw = true;
                } else if (getDistance(endCircle.getX(), endCircle.getY(), getX, getY) < endCircle.getR() + 30) {
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
                    float mAngle = (float) (360 * a / (2 * TouchCircle.PI));
                    if (isStart) {
                        startCircle.setAngle(mAngle);
                    } else {
                        endCircle.setAngle(mAngle);
                    }
                    if(circleListener!=null){
                        circleListener.move(isStart,mAngle,false);
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP://松开
                //松开的时候把数据传递回来
                Log.i("zangyi", "ACTION_UP");
                if(shouldReDraw){
                    if(circleListener!=null){
                        circleListener.move(isStart,0,true);
                    }
                }

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

