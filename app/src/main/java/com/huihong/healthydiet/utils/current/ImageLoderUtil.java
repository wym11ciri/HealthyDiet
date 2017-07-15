package com.huihong.healthydiet.utils.current;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huihong.healthydiet.MyApplication;
import com.huihong.healthydiet.R;

/**
 * Created by zangyi_shuai_ge on 2017/7/13
 */

public class ImageLoderUtil {

    //加载图片
    public  static  void showImage(Context mContext, String url, ImageView mImageView){

        int a= (int)(1+Math.random()*(MyApplication.mList.size()-1)); //从1到10的int型随数

        Glide
                .with(mContext)
                .load(MyApplication.mList.get(a))
                .asBitmap()
                .placeholder(R.mipmap.error_photo)
                .error(R.mipmap.error_photo)
                .into(mImageView);
    }

    //加载图片
    public  static  void showImage2(Context mContext, int i, ImageView mImageView){

        Glide
                .with(mContext)
                .load(MyApplication.mList.get(i%(MyApplication.mList.size()-1)))
                .asBitmap()
                .placeholder(R.mipmap.error_photo)
                .error(R.mipmap.error_photo)
                .into(mImageView);
    }

}
