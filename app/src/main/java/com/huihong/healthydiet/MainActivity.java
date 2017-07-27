package com.huihong.healthydiet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.huihong.healthydiet.activity.base.BaseActivity;
import com.huihong.healthydiet.adapter.FragmentPagerAdapter;
import com.huihong.healthydiet.fragment.main.ArticleFragment;
import com.huihong.healthydiet.fragment.main.HomeFragment;
import com.huihong.healthydiet.fragment.main.MotionFragment;
import com.huihong.healthydiet.fragment.main.MyFragment;
import com.huihong.healthydiet.fragment.main.SleepFragment;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.mInterface.LocationListener;
import com.huihong.healthydiet.service.AlarmClockService;
import com.huihong.healthydiet.utils.common.StatusBarUtil;
import com.huihong.healthydiet.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    private RelativeLayout layoutTab01, layoutTab02, layoutTab03, layoutTab04, layoutTab05;
    private TextView tvTab01, tvTab02, tvTab03, tvTab04, tvTab05;
    private ImageView ivTab01, ivTab02, ivTab03, ivTab04, ivTab05;
    private List<Fragment> mList;
    private FragmentPagerAdapter mPagerAdapter;
    private MyViewPager mViewPager;
    private LocationService locationService;


    private LocationListener locationListener;

    public void setLocationListener(LocationListener locationListener) {
        this.locationListener = locationListener;
    }

    public static MainActivity mainActivity;

    //5个底部导航栏的监听回调
    private ItemOnClickListener itemOnClickListener01;
    private ItemOnClickListener itemOnClickListener02;
    private ItemOnClickListener itemOnClickListener03;
    private ItemOnClickListener itemOnClickListener04;
    private ItemOnClickListener itemOnClickListener05;

    //设置监听
    public void setItemOnClickListener01(ItemOnClickListener itemOnClickListener01) {
        this.itemOnClickListener01 = itemOnClickListener01;
    }

    public void setItemOnClickListener02(ItemOnClickListener itemOnClickListener02) {
        this.itemOnClickListener02 = itemOnClickListener02;
    }

    public void setItemOnClickListener03(ItemOnClickListener itemOnClickListener03) {
        this.itemOnClickListener03 = itemOnClickListener03;
    }

    public void setItemOnClickListener04(ItemOnClickListener itemOnClickListener04) {
        this.itemOnClickListener04 = itemOnClickListener04;
    }

    public void setItemOnClickListener05(ItemOnClickListener itemOnClickListener05) {
        this.itemOnClickListener05 = itemOnClickListener05;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
        setContentView(R.layout.activity_main);
//        StatusBarUtil.setTransparent(this);//设置状态栏沉浸
        mainActivity = this;
        initUI();
        setupService();
    }

    /**
     * 开启计步服务
     */
    private void setupService() {
        Intent intent = new Intent(this, AlarmClockService.class);
//        isBind = bindService(intent, conn, Context.BIND_AUTO_CREATE);
        startService(intent);


//        Intent intent2 = new Intent(this, StepService.class);
//        isBind = bindService(intent, conn, Context.BIND_AUTO_CREATE);
//        startService(intent2);

    }

    private void initUI() {
        layoutTab01 = (RelativeLayout) findViewById(R.id.layoutTab01);
        layoutTab02 = (RelativeLayout) findViewById(R.id.layoutTab02);
        layoutTab03 = (RelativeLayout) findViewById(R.id.layoutTab03);
        layoutTab04 = (RelativeLayout) findViewById(R.id.layoutTab04);
        layoutTab05 = (RelativeLayout) findViewById(R.id.layoutTab05);

        layoutTab01.setOnTouchListener(this);
        layoutTab02.setOnTouchListener(this);
        layoutTab03.setOnTouchListener(this);
        layoutTab04.setOnTouchListener(this);
        layoutTab05.setOnTouchListener(this);

        layoutTab01.setOnClickListener(this);
        layoutTab02.setOnClickListener(this);
        layoutTab03.setOnClickListener(this);
        layoutTab04.setOnClickListener(this);
        layoutTab05.setOnClickListener(this);

        tvTab01 = (TextView) findViewById(R.id.tvTab01);
        tvTab02 = (TextView) findViewById(R.id.tvTab02);
        tvTab03 = (TextView) findViewById(R.id.tvTab03);
        tvTab04 = (TextView) findViewById(R.id.tvTab04);
        tvTab05 = (TextView) findViewById(R.id.tvTab05);

        ivTab01 = (ImageView) findViewById(R.id.ivTab01);
        ivTab02 = (ImageView) findViewById(R.id.ivTab02);
        ivTab03 = (ImageView) findViewById(R.id.ivTab03);
        ivTab04 = (ImageView) findViewById(R.id.ivTab04);
        ivTab05 = (ImageView) findViewById(R.id.ivTab05);


        mViewPager = (MyViewPager) findViewById(R.id.mViewPager);
        mList = new ArrayList<>();

        mList.add(new MyFragment());
        mList.add(new ArticleFragment());
        mList.add(new HomeFragment());
        mList.add(new MotionFragment());
        mList.add(new SleepFragment());

        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(2);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.btn_alpha_scale_begin));
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.btn_alpha_scale_end));
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        restTab();
        switch (v.getId()) {

            case R.id.layoutTab01:
                mViewPager.setCurrentItem(0, false);
//                ivTab01.setImageResource(R.mipmap.);
                ivTab01.setImageResource(R.mipmap.logo_10);
                tvTab01.setTextColor(getResources().getColor(R.color.tab_bottom_text_select));

                if (itemOnClickListener01 != null) {
                    itemOnClickListener01.onClick();
                }


                break;
            case R.id.layoutTab02:
                ivTab02.setImageResource(R.mipmap.logo_6);
                mViewPager.setCurrentItem(1, false);
                tvTab02.setTextColor(getResources().getColor(R.color.tab_bottom_text_select));
                if (itemOnClickListener02 != null) {
                    itemOnClickListener02.onClick();
                }

                break;
            case R.id.layoutTab03:
                ivTab03.setImageResource(R.mipmap.logo_1);
                mViewPager.setCurrentItem(2, false);
                tvTab03.setTextColor(getResources().getColor(R.color.tab_bottom_text_select));
                if (itemOnClickListener03 != null) {
                    itemOnClickListener03.onClick();
                }

                break;
            case R.id.layoutTab04:
                ivTab04.setImageResource(R.mipmap.logo_8);
                mViewPager.setCurrentItem(3, false);
                tvTab04.setTextColor(getResources().getColor(R.color.tab_bottom_text_select));
                if (itemOnClickListener04 != null) {
                    itemOnClickListener04.onClick();
                }

                break;
            case R.id.layoutTab05:
                ivTab05.setImageResource(R.mipmap.logo_4);
                mViewPager.setCurrentItem(4, false);
                tvTab05.setTextColor(getResources().getColor(R.color.tab_bottom_text_select));

                if (itemOnClickListener05 != null) {
                    itemOnClickListener05.onClick();
                }
                break;
        }


    }

    private void restTab() {

        tvTab01.setTextColor(getResources().getColor(R.color.tab_bottom_text_normal));
        tvTab02.setTextColor(getResources().getColor(R.color.tab_bottom_text_normal));
        tvTab03.setTextColor(getResources().getColor(R.color.tab_bottom_text_normal));
        tvTab04.setTextColor(getResources().getColor(R.color.tab_bottom_text_normal));
        tvTab05.setTextColor(getResources().getColor(R.color.tab_bottom_text_normal));


        ivTab01.setImageResource(R.mipmap.logo_9);
        ivTab02.setImageResource(R.mipmap.logo_5);
        ivTab03.setImageResource(R.mipmap.logo_2);
        ivTab04.setImageResource(R.mipmap.logo_7);
        ivTab05.setImageResource(R.mipmap.logo_3);

    }


    @Override
    protected void onStart() {
        super.onStart();
        locationService = ((MyApplication) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();// 定位SDK
    }

    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {


            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {

                MyApplication.Latitude = location.getLatitude();
                MyApplication.Longitude = location.getLongitude();
                MyApplication.address = location.getLocationDescribe();

                if (locationListener != null) {
                    locationListener.isReLocation(true, location.getLocationDescribe());
                }

//                StringBuffer sb = new StringBuffer(256);
//                sb.append("time : ");
//                /**
//                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
//                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
//                 */
//                sb.append(location.getTime());
//                sb.append("\nlocType : ");// 定位类型
//                sb.append(location.getLocType());
//                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
//                sb.append(location.getLocTypeDescription());
//                sb.append("\nlatitude : ");// 纬度
//                sb.append(location.getLatitude());
//                sb.append("\nlontitude : ");// 经度
//                sb.append(location.getLongitude());
//                sb.append("\nradius : ");// 半径
//                sb.append(location.getRadius());
//                sb.append("\nCountryCode : ");// 国家码
//                sb.append(location.getCountryCode());
//                sb.append("\nCountry : ");// 国家名称
//                sb.append(location.getCountry());
//                sb.append("\ncitycode : ");// 城市编码
//                sb.append(location.getCityCode());
//                sb.append("\ncity : ");// 城市
//                sb.append(location.getCity());
//                sb.append("\nDistrict : ");// 区
//                sb.append(location.getDistrict());
//                sb.append("\nStreet : ");// 街道
//                sb.append(location.getStreet());
//                sb.append("\naddr : ");// 地址信息
//                sb.append(location.getAddrStr());
//                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
//                sb.append(location.getUserIndoorState());
//                sb.append("\nDirection(not all devices have value): ");
//                sb.append(location.getDirection());// 方向
//                sb.append("\nlocationdescribe: ");
//                sb.append(location.getLocationDescribe());// 位置语义化信息
//                sb.append("\nPoi: ");// POI信息
//                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
//                    for (int i = 0; i < location.getPoiList().size(); i++) {
//                        Poi poi = (Poi) location.getPoiList().get(i);
//                        sb.append(poi.getName() + ";");
//                    }
//                }
//                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
//                    sb.append("\nspeed : ");
//                    sb.append(location.getSpeed());// 速度 单位：km/h
//                    sb.append("\nsatellite : ");
//                    sb.append(location.getSatelliteNumber());// 卫星数目
//                    sb.append("\nheight : ");
//                    sb.append(location.getAltitude());// 海拔高度 单位：米
//                    sb.append("\ngps status : ");
//                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
//                    sb.append("\ndescribe : ");
//                    sb.append("gps定位成功");
//                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//                    // 运营商信息
//                    if (location.hasAltitude()) {// *****如果有海拔高度*****
//                        sb.append("\nheight : ");
//                        sb.append(location.getAltitude());// 单位：米
//                    }
//                    sb.append("\noperationers : ");// 运营商信息
//                    sb.append(location.getOperators());
//                    sb.append("\ndescribe : ");
//                    sb.append("网络定位成功");
//                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                    sb.append("\ndescribe : ");
//                    sb.append("离线定位成功，离线定位结果也是有效的");
//                } else if (location.getLocType() == BDLocation.TypeServerError) {
//                    sb.append("\ndescribe : ");
//                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                    sb.append("\ndescribe : ");
//                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
//                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                    sb.append("\ndescribe : ");
//                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//                }
////                logMsg(sb.toString());
            }
        }

        public void onConnectHotSpotMessage(String s, int i) {
        }
    };

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }


}
