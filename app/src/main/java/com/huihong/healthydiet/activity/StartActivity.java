package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.huihong.healthydiet.MainActivity;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseActivity;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.common.StatusBarUtil;

/**
 * Created by zangyi_shuai_ge on 2017/7/19
 * 启动界面
 */
public class StartActivity extends BaseActivity {


    private final int GO_LOGIN_TAG=100;
    private final int GO_MAIN_TAG=101;
    private final int GO_TEST_TAG=102;


    private ImageView mImageView;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case GO_LOGIN_TAG:
                    go2Activity(LoginActivity.class);
                    break;
                case GO_MAIN_TAG:
                    go2Activity(MainActivity.class);
                    break;
                case GO_TEST_TAG:
                    go2Activity(TestSimpleActivity.class);
                    break;
            }
        }
    };

    private void go2Activity(Class<?> cls) {
        Intent mIntent = new Intent(StartActivity.this, cls);
        startActivity(mIntent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_start);
        StatusBarUtil.setTransparent(this);//设置状态栏沉浸
        initUI();
    }

    private void initUI() {
        mImageView= (ImageView) findViewById(R.id.mImageView);
//        Glide
//                .with(StartActivity.this)
//                .load("http://qq.yh31.com/tp/zr/zr184.gif")
//               .asGif()
//                .into(mImageView);

//        Glide.with(StartActivity.this)
//                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501746830558&di=e5c3c8f88b9f5bad1a5f9bc4efaaaed4&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01bffc58b6cf28a801219c77ff60f9.gif")
//                .asGif()
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(mImageView);

//        SPUtils.put(StartActivity.this,"isDoSimpleTest",false);
//        SPUtils.put(StartActivity.this,"isLogin",false);

        //是否完成简易版测试
        boolean isDoSimpleTest= (boolean) SPUtils.get(StartActivity.this,"isDoSimpleTest",false);
        //是否已经登录过
        boolean isLogin=(boolean) SPUtils.get(StartActivity.this,"isLogin",false);
        if(isLogin){
            if(isDoSimpleTest){
                mHandler.sendEmptyMessageDelayed(GO_MAIN_TAG, 1000);
            }else {
                mHandler.sendEmptyMessageDelayed(GO_TEST_TAG, 1000);
            }
        }else {
            mHandler.sendEmptyMessageDelayed(GO_LOGIN_TAG, 1000);
        }
    }
}
