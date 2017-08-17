package com.huihong.healthydiet.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.cache.litepal.SleepCache;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.model.mybean.Time;
import com.huihong.healthydiet.utils.DateUtil;
import com.huihong.healthydiet.utils.MyUtils;
import com.huihong.healthydiet.utils.common.DateFormattedUtils;
import com.huihong.healthydiet.utils.common.DensityUtils;
import com.huihong.healthydiet.utils.common.LogUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/1
 */

public class SleepChartView extends View {

    private Paint paintLimit;//限制线画笔
    private Paint paintNormal;//普通线画笔刻度线
    private Paint paintSleep;//睡眠记录画笔
    private Paint paintText;//绘制字的画笔

    private float screenH;

    private float screenW;//绘制图表区域的宽度

    private float screenH_2;

    private float unitHeight;
    private float screenW_14;//把宽度14等分

    private float sleepLimitH;//睡眠线高度
    private float getUpLimitH;//起床线高度

    private List<SleepCache> mSleepList;


    private int textSize;


    //设置限制线的时间
    private int SleepLimitHour;
    private int SleepLimitMin;

    private int getUpLimitHour;
    private int getUpLimitMin;

    private Context mContext;

    //上下距离
    private int paddingTop;
    private int paddingBottom;


    private Time nowGetUpTime;
    private Time nowSleepTime;


    private int textWidth;//字的宽度

    public SleepChartView(Context context) {
        this(context, null);
    }

    public SleepChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SleepChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        paintLimit = new Paint();
        paintLimit.setColor(getResources().getColor(R.color.sleep_chart_limit_line));
        paintLimit.setStrokeWidth(DensityUtils.dp2px(mContext, 1));
        paintLimit.setAntiAlias(true);//设置抗锯齿

        paintNormal = new Paint();
        paintNormal.setColor(getResources().getColor(R.color.sleep_chart_normal_line));
        paintNormal.setAntiAlias(true);//设置抗锯齿

        paintSleep = new Paint();
        paintSleep.setColor(getResources().getColor(R.color.sleep_chart_sleep_line));
        paintSleep.setAntiAlias(true);//设置抗锯齿
        paintSleep.setStrokeWidth(DensityUtils.dp2px(mContext, 3));
        paintSleep.setStrokeCap(Paint.Cap.ROUND);

        paintText = new Paint();
        paintText.setColor(getResources().getColor(R.color.sleep_chart_normal_line));
        paintText.setAntiAlias(true);//设置抗锯齿
        paintText.setTextSize(DensityUtils.sp2px(mContext, 12));
        textWidth = DensityUtils.dp2px(mContext, 40);
        textSize = DensityUtils.sp2px(mContext, 12);
        paddingTop = DensityUtils.sp2px(mContext, 12) / 2;
        paddingBottom = DensityUtils.sp2px(mContext, 12) / 2;
        mSleepList = new ArrayList<>();

        nowGetUpTime = new Time();
        nowSleepTime = new Time();

        initData();
    }

    public void  invalidateData(){
        if(mSleepList!=null){
            mSleepList.clear();
            initData();
            invalidate();
        }
    }

    //初始化数据
    private void initData() {
        //查询当天是周几
        int nowWeek = DateUtil.getNowWeek();
//        nowWeek = 7;
        LogUtil.i("当前星期" + nowWeek);
        //去数据库查询前几天的数据
        //nowWeek 1时候为周天 需要去查询前6天的
        LogUtil.i("前1天" + DateUtil.getPastDate(1));
        LogUtil.i("前2天" + DateUtil.getPastDate(2));
        LogUtil.i("前3天" + DateUtil.getPastDate(3));
        LogUtil.i("前4天" + DateUtil.getPastDate(4));
        if (nowWeek == 1) {
            //当前为周天 获取前6天的数据 i=1 2 3 4 5 6
            for (int i = 1; i < 7; i++) {
                List<Integer> mDateList = DateUtil.getPastDate(i);
                //去数据库查询
                List<SleepCache> mSleepCacheList = DataSupport
                        .where("year = ? and month = ? and day = ?", mDateList.get(0) + "", mDateList.get(1) + "", mDateList.get(2) + "")
                        .find(SleepCache.class);

                if (mSleepCacheList.size() > 0) {
                    //当前有记录
                    //去绘制
                    SleepCache mSleepCache = mSleepCacheList.get(0);
                    mSleepCache.setDraw(true);
                    mSleepList.add(mSleepCache);
                } else {
                    //当前没有记录
                    //不去绘制
                    SleepCache mSleepCache = new SleepCache();
                    mSleepCache.setDraw(false);
                    mSleepList.add(mSleepCache);
                }
            }
        } else {
            //例如 nowWeek=2 是周一 需要去查询前0天的数据
            for (int i = 1; i < nowWeek - 1; i++) {
                List<Integer> mDateList = DateUtil.getPastDate(i);
                //去数据库查询
                List<SleepCache> mSleepCacheList = DataSupport
                        .where("year = ? and month = ? and day = ?", mDateList.get(0) + "", mDateList.get(1) + "", mDateList.get(2) + "")
                        .find(SleepCache.class);

                if (mSleepCacheList.size() > 0) {
                    LogUtil.i("嘻嘻", "有数据" + mSleepCacheList.size());
                    //当前有记录
                    //去绘制
                    SleepCache mSleepCache = mSleepCacheList.get(0);
                    mSleepCache.setDraw(true);
                    mSleepList.add(mSleepCache);
                    LogUtil.i("睡眠记录 起床 周"+i+"---" + mSleepCache.getGetUpHour() + ":" + mSleepCache.getGetUpMin());
                    LogUtil.i("睡眠记录 睡觉" + mSleepCache.getSleepHour() + ":" + mSleepCache.getSleepHour());
                } else {
                    LogUtil.i("嘻嘻", "没有数据");
                    //当前没有记录
                    //不去绘制
                    SleepCache mSleepCache = new SleepCache();
                    mSleepCache.setDraw(false);
                    mSleepList.add(mSleepCache);
                }
            }

        }
        //nowWeek 2时候为周1 去查询前1-1天的
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        screenW = w - (textWidth);

        screenH = h - (paddingTop + paddingBottom);
        screenH_2 = screenH / 2;
        screenW_14 = screenW / 14;
        unitHeight = screenH / 6;//高度分6份每个刻度当4小时有7根线


//        SleepLimitHour = (int) SPUtils.get(mContext, "startHour", 23);
//        SleepLimitMin = (int) SPUtils.get(mContext, "startMin", 0);
//        getUpLimitHour = (int) SPUtils.get(mContext, "endHour", 8);
//        getUpLimitMin = (int) SPUtils.get(mContext, "endMin", 0);
//
//        setLimitLineH(SleepLimitHour, SleepLimitMin, true);
//        setLimitLineH(getUpLimitHour, getUpLimitMin, false);



        setLimitLine(CacheUtils.getSleepTime(mContext),CacheUtils.getGetUpTime(mContext));

        LogUtil.i("调试", "SleepChartView onSizeChanged");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //绘制限制线
        //最下面那根限制线不动
        canvas.drawLine(textWidth, screenH+paddingBottom, screenW + textWidth, screenH+paddingBottom, paintLimit);
        canvas.drawText(DateFormattedUtils.formattedDate(nowSleepTime.getHour()) + ":" + DateFormattedUtils.formattedDate(nowSleepTime.getMin()),
                0, (float) (screenH+paddingTop+textSize / 2.3000), paintText);

        //上面那个限制线
        canvas.drawLine(textWidth, screenH - lineH+paddingBottom, screenW + textWidth, screenH - lineH+paddingBottom, paintLimit);
        canvas.drawText(DateFormattedUtils.formattedDate(nowGetUpTime.getHour()) + ":" + DateFormattedUtils.formattedDate(nowGetUpTime.getMin()),
                0, (float) (screenH - lineH + textSize / 2.3000+paddingTop), paintText);

        //时间
        //睡眠线固定

//        canvas.drawText(DateFormattedUtils.formattedDate(nowGetUpTime.getHour()) + ":" + DateFormattedUtils.formattedDate(nowGetUpTime.getMin()), 0, (float) (screenH - lineH + textSize / 2.3000), paintText);


        //绘制刻度线
        for (int i = 0; i < 7; i++) {
            //给刻度线加上限制
            float height = unitHeight * i + paddingTop;
            if (height <=  screenH+paddingBottom && height >= screenH - lineH+paddingBottom) {
                //刻度线
                canvas.drawLine(textWidth, unitHeight * i + paddingTop, screenW + textWidth, unitHeight * i + paddingTop, paintNormal);
            }
        }
        //绘制历史睡眠线
        LogUtil.i("睡眠记录大小"+mSleepList.size());
        int j=0;
        for ( int i = mSleepList.size()-1; i >=0 ; i--) {

            LogUtil.i("睡眠记录详细");
            if (mSleepList.get(i).isDraw()) {
                Time mSleepTime01 = new Time();
                LogUtil.i("睡眠记录详细"+i+"==="+mSleepList.get(i).getSleepHour());
                mSleepTime01.setHour(mSleepList.get(i).getSleepHour());
                mSleepTime01.setMin(mSleepList.get(i).getSleepMin());

                Time mGetUpTime01 = new Time();
                mGetUpTime01.setHour(mSleepList.get(i).getGetUpHour());
                mGetUpTime01.setMin(mSleepList.get(i).getGetUpMin());

                canvas.drawLine(textWidth + screenW_14 * (2 * j + 1), screenH +paddingTop- getLineHeight(mSleepTime01, mGetUpTime01),
                        textWidth + screenW_14 * (2 * j + 1), screenH +paddingTop , paintSleep);
//                canvas.drawLine(textWidth + screenW_14 * (2 * i + 1), getSleepLineY(mSleepTime01) - getLineHeight(mSleepTime01, mGetUpTime01), textWidth + screenW_14 * (2 * i + 1), getSleepLineY(mSleepTime01) , paintSleep);
            }
            j++;
        }



//        //画2根限制线
//        canvas.drawLine(textWidth, sleepLimitH + paddingTop, screenW + textWidth, sleepLimitH + paddingTop, paintLimit);
//        canvas.drawLine(textWidth, getUpLimitH + paddingTop, screenW + textWidth, getUpLimitH + paddingTop, paintLimit);
//

//
//

//
//        //画当前睡眠线
//        canvas.drawLine(textWidth + screenW_14 * (2 * (mSleepList.size()) + 1), getUpLimitH + paddingTop, textWidth + screenW_14 * (2 * (mSleepList.size()) + 1), sleepLimitH + paddingTop, paintSleep);

    }

    public void setLimitLineH(int hour, int min, boolean isSetSleep) {


        if (isSetSleep) {
            //设置睡眠高度
//            sleepLimitH = screenH * (mAngle / 360);
            int allMin = (hour - 13) * 60 + min;
            double percentage = 1 - (allMin / (12.001 * 60.0001));
            sleepLimitH = screenH / 2 + (float) (percentage * (screenH / 2));
            SleepLimitHour = hour;
            SleepLimitMin = min;


        } else {
            //起床时间为早上1点到12.59
            //计算获取时间
            int allMin = (hour - 1) * 60 + min;
            double percentage = 1 - (allMin / (12.001 * 60.0001));
            getUpLimitH = (float) (percentage * (screenH / 2));
            getUpLimitHour = hour;
            getUpLimitMin = min;
//            getUpLimitH = screenH * (mAngle / 360);
        }
        invalidate();
    }


    float lineH = 0;

    //设置限制线
    public void setLimitLine(Time sleepTime, Time getUpTime) {

        //通过时间差来计算睡眠时长
        int dAllMin = MyUtils.getTimeDifferenceMin(sleepTime, getUpTime);
        nowGetUpTime = getUpTime;
        nowSleepTime = sleepTime;

        lineH = (float) ((dAllMin / (24 * 60.00000)) * screenH);

        invalidate();
    }

    //获得限制线的Y坐标
    public float getSleepLineY(Time mSleepTime) {

        int allMin = mSleepTime.getHour() * 60 + mSleepTime.getMin();
        float a = (float) (screenH_2 + screenH_2 * (allMin / (60 * 24.00001))) + paddingTop;
        LogUtil.i("限制线高度" + a + "==" + mSleepTime.getHour() + "==" + mSleepTime.getMin());
        return a;
    }


    //获得限制线的Y坐标
    public float getLineHeight(Time sleepTime, Time getUpTime) {
        int dAllMin = MyUtils.getTimeDifferenceMin(sleepTime, getUpTime);
        return (float) ((dAllMin / (24 * 60.000)) * screenH);
    }

    public float getSleepH(SleepCache mSleep, boolean isGetSleep) {


        if (isGetSleep) {
            int hour = mSleep.getSleepHour();
            int min = mSleep.getSleepMin();
            //设置睡眠高度
//            sleepLimitH = screenH * (mAngle / 360);
            int allMin = (hour - 13) * 60 + min;
            double percentage = 1 - (allMin / (12.001 * 60.0001));
            return screenH / 2 + (float) (percentage * (screenH / 2));

        } else {

            int hour = mSleep.getGetUpHour();
            int min = mSleep.getGetUpMin();

            //起床时间为早上1点到12.59
            //计算获取时间
            int allMin = (hour - 1) * 60 + min;
            double percentage = 1 - (allMin / (12.001 * 60.0001));
            return (float) (percentage * (screenH / 2));

        }
    }
}
