package com.huihong.healthydiet.utils.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huihong.healthydiet.R;

/**
 * 保留几个常用的Glide工具类
 */
public class GlideUtils {

    /**
     * 默认加载
     */
    public static void loadImageView(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .crossFade()
                .placeholder(R.mipmap.error_photo)
                .error(R.mipmap.error_photo)
                .into(mImageView);
    }

    /**
     * 由于一些自定义ImageView 需要转化为Bitmap才能显示
     */
    public static void loadImageViewAsBitmap(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .asBitmap()
                .placeholder(R.mipmap.error_photo)
                .error(R.mipmap.error_photo)
                .into(mImageView);
    }


    //设置跳过内存缓存
    public static void loadImageViewCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .skipMemoryCache(true)
                .into(mImageView);
    }


    /**
     * 清理磁盘缓存
     * 理磁盘缓存 需要在子线程中执行
     */
    public static void GuideClearDiskCache(Context mContext) {
        Glide
                .get(mContext)
                .clearDiskCache();
    }

    /**
     * 清理内存缓存
     * 清理内存缓存  可以在UI主线程中进行
     */
    public static void GuideClearMemory(Context mContext) {
        Glide
                .get(mContext)
                .clearMemory();
    }
}