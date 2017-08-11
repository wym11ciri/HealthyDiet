package com.huihong.healthydiet.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.model.mybean.Coordinate;
import com.huihong.healthydiet.model.mybean.Leaf;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/26
 * 积分树
 */

public class TreeView extends View {

    private List<Leaf> mLeafList;//叶子集合


    private Bitmap LeafBitmap;//叶子图片
    private Paint textIntegralPaint;//绘制积分的画笔
    private Paint textEventPaint;//绘制事件的画笔

    //自定义View尺寸
    private int viewW;
    private int viewH;

    //叶子的长度
    private int leafSize;
    //字体1的长度
    private int textSize01;
    //字体2的长度
    private int textSize02;

    private Context context;

    private int clickNum = -1;//上次点击的叶子

    private boolean isAnimation = false;//是否在执行动画
    private boolean isRunAlphaAnimation = false;//是否需要执行透明度动画


    private ValueAnimator animatorFloat;//浮动动画
    private ValueAnimator animatorDisappear;//消失动画


    private List<Coordinate> mCoordinateList;

    public TreeView(Context context) {
        this(context, null);
    }

    public TreeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mCoordinateList = new ArrayList<>();


        mLeafList = new ArrayList<>();
        animatorFloat = ValueAnimator.ofInt(-5, 5, -5);
        animatorFloat.setDuration(1500);
        animatorFloat.setRepeatCount(-1);
        animatorFloat.start();
        animatorFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int f = (int) animation.getAnimatedValue();
                for (int i = 0; i < mLeafList.size(); i++) {
                    mLeafList.get(i).setMove(f);
                }
                invalidate();
            }
        });


    }

    private void setCoordinate(double x, double y) {
        Coordinate mCoordinate = new Coordinate();
        mCoordinate.setX(x);
        mCoordinate.setY(y);
        mCoordinateList.add(mCoordinate);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewW = w;
        viewH = h;
        leafSize = w / 16;
        textSize01 = (int) (leafSize * 0.8);
        textSize02 = (int) (leafSize * 0.6);
        Bitmap bt1 = BitmapFactory.decodeResource(getResources(), R.mipmap.leaf);
        LeafBitmap = Bitmap.createScaledBitmap(bt1, leafSize, leafSize, false);
        bt1.recycle();//释放旧的图片资源

        textIntegralPaint = new Paint();
        textIntegralPaint.setStrokeWidth(4);
        textIntegralPaint.setTextSize(textSize01);
        textIntegralPaint.setColor(getResources().getColor(R.color.leaf_integral));
//        textIntegralPaint.setStyle(Paint.Style.STROKE);//描边
        textIntegralPaint.setAntiAlias(true);//设置抗锯齿

//        textPaint.setTextAlign(Paint.Align.LEFT);
        textEventPaint = new Paint();
        textEventPaint.setStrokeWidth(3);
        textEventPaint.setTextSize(textSize02);
        textEventPaint.setColor(getResources().getColor(R.color.leaf_event));
//        textIntegralPaint.setStyle(Paint.Style.STROKE);//描边
        textEventPaint.setAntiAlias(true);//设置抗锯齿


        //设置树叶可能显示坐标位置的地方

        //文件tree1
//        setCoordinate(0.3444,0.11);
//        setCoordinate(0.571,0.274);
//        setCoordinate(0.3848,0.261);

//        //文件tree2
//        setCoordinate(0.3987,0.14388);
//        setCoordinate(0.14767,0.3597);
//        setCoordinate(0.6835,0.7458);
//
//        //文件tree3
//        setCoordinate(0.38526, 0.12857);
//        setCoordinate(0.11789, 0.29523);
//        setCoordinate(0.7052, 0.7);
//
//        //文件tree4
//        setCoordinate(0.195,0.933);
//        setCoordinate(0.922,0.679);
//        setCoordinate(0.71698,0.79425);
//
//        //文件tree5
        setCoordinate(0.077568, 0.6610);
        setCoordinate(0.18238, 0.85336);
        setCoordinate(0.8134, 0.786);

        for (int i = 0; i < 3; i++) {
            Leaf leaf = new Leaf();
            leaf.setStartX((int) (mCoordinateList.get(i).getX() * viewW));
            leaf.setStartY((int) (mCoordinateList.get(i).getY() * viewH));
            leaf.setEvent("事件" + i);
            leaf.setIntegral(" +" + i);
            leaf.setLeafSize(leafSize);
            leaf.setAlpha(255);
            mLeafList.add(leaf);
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //是否在执行动画

        for (int i = 0; i < mLeafList.size(); i++) {
            //绘制叶子
            textIntegralPaint.setAlpha(mLeafList.get(i).getAlpha());
            canvas.drawBitmap(LeafBitmap, mLeafList.get(i).getStartX(), mLeafList.get(i).getStartY(), textIntegralPaint);
            //绘制积分
            textIntegralPaint.setAlpha(mLeafList.get(i).getAlpha());
            canvas.drawText(mLeafList.get(i).getIntegral(), mLeafList.get(i).getDrawIntegralX(), mLeafList.get(i).getDrawIntegralY(), textIntegralPaint);
            //绘制事件
            textEventPaint.setAlpha(mLeafList.get(i).getAlpha());
            canvas.drawText(mLeafList.get(i).getEvent(), mLeafList.get(i).getDrawEventX(), mLeafList.get(i).getDrawEventY(), textEventPaint);
        }

        if (!animatorFloat.isRunning()) {
            animatorFloat.start();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            //如果正在执行动画就不允许点击
            if (!isAnimation) {
                for (int i = 0; i < mLeafList.size(); i++) {
                    if (x > mLeafList.get(i).getStartX() && x < mLeafList.get(i).getLeafXEnd() && y > mLeafList.get(i).getStartY() && y < mLeafList.get(i).getLeafYEnd()) {

                        //启动消失动画
                        clickNum = i;
                        isRunAlphaAnimation = false;
                        PositionAnimationThread positionAnimationThread = new PositionAnimationThread();
                        positionAnimationThread.start();
                        isAnimation = true;
                        break;
                    }
                }
            }
        }
        return false;
    }

    private class PositionAnimationThread extends Thread {
        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {

                try {
                    sleep(30);
                    if (mLeafList != null && clickNum != -1 && clickNum < mLeafList.size()) {
                        float y = mLeafList.get(clickNum).getStartY();
                        mLeafList.get(clickNum).setStartY(y - 20);
                        postInvalidate();//异步更新
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            mLeafList.remove(clickNum);
            clickNum = -1;
            isAnimation = false;
            postInvalidate();//异步更新
        }
    }

}
