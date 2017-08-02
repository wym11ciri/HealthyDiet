package com.huihong.healthydiet.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zuoni.dialog.picker.dialog.BottomGetPhotoDialog;

import java.io.File;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/8/1
 */

public class TestActivity extends TakePhotoActivity {
    ImageView ivTest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ivTest = (ImageView) findViewById(R.id.ivTest);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
//        TakePhoto takePhoto=getTakePhoto()
        result.getImage();

        this.getResources().getDrawable(R.drawable.ic_arrow_back);

        File file = new File(result.getImages().get(0).getOriginalPath());
        Glide
                .with(this)
                .load(file)
                .into(ivTest);

//
//
        OkHttpUtils
                .postFile()
                .url(AppUrl.UPLOAD_IMAGE)
                .file(file)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("上传" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("上传" + response);

                    }
                });
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    BottomGetPhotoDialog bottomGetPhotoDialog;
    TakePhoto takePhoto;
    public void Ceshi(View v) {
         takePhoto = getTakePhoto();


        BottomGetPhotoDialog.Builder builder = new BottomGetPhotoDialog.Builder(TestActivity.this);
        builder.setGetPhotoOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);
                takePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create(), false);//压缩

                takePhoto.onPickFromGalleryWithCrop(imageUri, new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create());
                Toast.makeText(TestActivity.this, "去相册", Toast.LENGTH_SHORT).show();
                bottomGetPhotoDialog.dismiss();
            }
        });

        builder.setTakePhotoOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this, "拍照", Toast.LENGTH_SHORT).show();

                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);
                takePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create(), false);//压缩

//                takePhoto.onPickFromGalleryWithCrop(imageUri, new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create());

                takePhoto.onPickFromCaptureWithCrop(imageUri, new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create());


                bottomGetPhotoDialog.dismiss();
            }
        });

        bottomGetPhotoDialog = builder.create();
        bottomGetPhotoDialog.show();


//        Uri imageUri = Uri.fromFile(file);
//        takePhoto.onPickMultiple(2);

    }
}
