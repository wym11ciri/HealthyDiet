package com.huihong.healthydiet;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/11
 */

public class MyApplication extends Application {

  public  static List<String > mList;

    @Override
    public void onCreate() {
        super.onCreate();
        mList=new ArrayList<>();
        mList.add("http://f1.94uv.com/yuedu/2015-11/20151103154323996.jpg");
        mList.add("http://qingdao.sinaimg.cn/2014/0619/U11067P1534DT20140619115547.jpg");
        mList.add("http://qingdao.sinaimg.cn/2014/0619/U11067P1534DT20140619115547.jpg");

        mList.add("http://img.sc115.com/uploads/sc/jpgs/05/xpic6826_sc115.com.jpg");
        mList.add("http://image.tianjimedia.com/uploadImages/2014/351/53/0Y94V9AVTL1Y.jpg");
        mList.add("http://cimage1.tianjimedia.com/uploadImages/thirdImages/2017/191/5Z7KR1I33U77.jpg");
        mList.add("http://t1.niutuku.com/960/10/10-221576.jpg");
        mList.add("http://pica.nipic.com/2007-12-23/20071223014647_2.jpg");
        mList.add("http://i1.sinaimg.cn/travel/2014/0806/U7398P704DT20140806142406.jpg");
        mList.add("http://cimage1.tianjimedia.com/uploadImages/thirdImages/2017/191/FP5G9V960QWM.jpg");
        mList.add("http://img.taopic.com/uploads/allimg/130828/240425-130RPRT944.jpg");
        mList.add("http://qingdao.sinaimg.cn/2014/0619/U11067P1534DT20140619115547.jpg");
        mList.add("http://img0.imgtn.bdimg.com/it/u=1456097824,157924418&fm=26&gp=0.jpg");

    }
}
