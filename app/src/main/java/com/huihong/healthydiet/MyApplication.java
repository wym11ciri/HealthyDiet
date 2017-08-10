package com.huihong.healthydiet;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/11
 */

public class MyApplication extends Application {

    public static List<String> mList;
    public LocationService locationService;
    public Vibrator mVibrator;


    public static String address;

    public static double Latitude;//经度
    public static double Longitude;//纬度


    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        Connector.getDatabase();//创建数据
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
//        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
////        cookieJar
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .cookieJar(cookieJar)
//                //其他配置
//                .build();
//        OkHttpUtils.initClient(okHttpClient);


        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());


        mList = new ArrayList<>();
        mList.add("http://t1.niutuku.com/960/10/10-221576.jpg");
        mList.add("http://pica.nipic.com/2007-12-23/20071223014647_2.jpg");
        mList.add("http://i1.sinaimg.cn/travel/2014/0806/U7398P704DT20140806142406.jpg");
        mList.add("http://img.taopic.com/uploads/allimg/130828/240425-130RPRT944.jpg");
        mList.add("http://qingdao.sinaimg.cn/2014/0619/U11067P1534DT20140619115547.jpg");

        mList.add("http://img0.imgtn.bdimg.com/it/u=200987110,409871336&fm=26&gp=0.jpg");
        mList.add("http://img0.imgtn.bdimg.com/it/u=1834721349,965661657&fm=26&gp=0.jpg");
        mList.add("http://img4.imgtn.bdimg.com/it/u=3703792495,3164631985&fm=26&gp=0.jpg");
        mList.add("http://img0.imgtn.bdimg.com/it/u=5051244,3664524996&fm=26&gp=0.jpg");

//        Config.DEBUG = true;
//        QueuedWork.isUseThreadPool = false;
//        UMShareAPI.get(this);
//        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");

    }
}
