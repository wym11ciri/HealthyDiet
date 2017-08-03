package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.ActivityCollector;
import com.huihong.healthydiet.bean.SetUserBodyInfo;
import com.huihong.healthydiet.bean.UploadImage;
import com.huihong.healthydiet.cache.sp.CacheUtils;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.mybean.PersonalInfo;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.joooonho.SelectableRoundedImageView;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zuoni.dialog.picker.dialog.BottomGetPhotoDialog;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by zangyi_shuai_ge on 2017/7/20
 * 设置界面
 */

public class SettingsActivity extends TakePhotoActivity {

    private BottomGetPhotoDialog bottomGetPhotoDialog;
    private TakePhoto takePhoto;//照片选择器
    private SelectableRoundedImageView ivHead;

    //个人信息
    private PersonalInfo personalInfo;


    //退出登录按钮
    private TextView tvLoginOut;

    private boolean isChangeHead = false;
    private String imageHead = "";


    private EditText etName;//名称
    private TextView tvPhone;//手机号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        personalInfo = CacheUtils.getPersonalInfo(SettingsActivity.this);
        takePhoto = getTakePhoto();
        initTopBar();
        initUI();
    }

    String name;

    //初始化头部
    private void initTopBar() {

        LinearLayout layoutRight = (LinearLayout) findViewById(R.id.layoutTopRight);
        LinearLayout layoutLeft = (LinearLayout) findViewById(R.id.layoutLeft);

        layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
            }
        });

        layoutRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString().trim();
                if (name.equals("")) {
                    Toast.makeText(SettingsActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                } else {
                    if(mFile!=null){
                        save();
                    }else {
                        saveData("", false);
//                        Toast.makeText(SettingsActivity.this, "请重新选择头像", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }

    private void save() {
        if (isChangeHead) {
            OkHttpUtils
                    .postFile()
                    .url(AppUrl.UPLOAD_IMAGE)
                    .file(mFile)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtil.i("上传" + e.toString());
                            Toast.makeText(SettingsActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtil.i("上传" + response);

                            Gson gson = new Gson();
                            UploadImage mUploadImage = gson.fromJson(response, UploadImage.class);
                            int code = mUploadImage.getHttpCode();
                            if (code == 200) {
                                imageHead = mUploadImage.getModel1();
                                saveData("", true);
                            } else {
                                String message = mUploadImage.getMessage();
                                Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            saveData("xixi", false);
        }


    }


    public void initUI() {
        tvPhone= (TextView) findViewById(R.id.tvPhone);
        String phone=personalInfo.getPhone();
        if(phone.equals("")){
            tvPhone.setText("尚未绑定");
        }else {
            tvPhone.setText(phone);
        }
        etName = (EditText) findViewById(R.id.etName);
        etName.setText(personalInfo.getName());
        tvLoginOut = (TextView) findViewById(R.id.tvLoginOut);
        tvLoginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.put(SettingsActivity.this, "isDoSimpleTest", false);
                SPUtils.put(SettingsActivity.this, "isLogin", false);
                ActivityCollector.finishAll();//销毁所有界面
                Intent mIntent = new Intent(SettingsActivity.this, LoginActivity.class);
                SettingsActivity.this.startActivity(mIntent);
            }
        });

        ivHead = (SelectableRoundedImageView) findViewById(R.id.ivHead);

        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BottomGetPhotoDialog.Builder builder = new BottomGetPhotoDialog.Builder(SettingsActivity.this);
                builder.setGetPhotoOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                        Uri imageUri = Uri.fromFile(file);
//                        takePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create(), false);//压缩
                        takePhoto.onPickFromGalleryWithCrop(imageUri, new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create());
                        bottomGetPhotoDialog.dismiss();
                    }
                });

                builder.setTakePhotoOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                        Uri imageUri = Uri.fromFile(file);
//                        takePhoto.onEnableCompress(new CompressConfig.Builder().create(), false);//压缩
                        takePhoto.onPickFromCaptureWithCrop(imageUri, new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create());
                        bottomGetPhotoDialog.dismiss();
                    }
                });
                bottomGetPhotoDialog = builder.create();
                bottomGetPhotoDialog.show();
            }
        });


        Glide.with(SettingsActivity.this)
                .load(personalInfo.getHeadImageUrl())
                .asBitmap()
                .error(R.mipmap.error_head)
                .into(ivHead);


    }

    File mFile;

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        isChangeHead = true;
        result.getImage();

//        this.getResources().getDrawable(R.drawable.ic_arrow_back);

//        file = ;

        //开启鲁班压缩
        Luban.with(this)
                .load(new File(result.getImages().get(0).getOriginalPath()))                     //传人要压缩的图片
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {

                    }
                    @Override
                    public void onSuccess(File file) {
                        Glide
                                .with(SettingsActivity.this)
                                .load(file)
                                .asBitmap()
                                .into(ivHead);
                        mFile=file;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();    //启动压缩


    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    //保存个人信息
    public void saveData(final String height, boolean isChangeHead) {
        Map<String, String> map = new HashMap<>();
        if (isChangeHead) {
            map.put("HeadImage", imageHead);
        }
        map.put("UserName",name);
        map.put("UserId", SPUtils.get(SettingsActivity.this, "UserId", 0) + "");

        HttpUtils.sendHttpAddToken(SettingsActivity.this
                , AppUrl.SET_USER_BODY_INFO
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("error" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("接口，个人信息保存:", response);
                        Gson gson = new Gson();
                        SetUserBodyInfo mSetUserBodyInfo = gson.fromJson(response, SetUserBodyInfo.class);
                        int code = mSetUserBodyInfo.getHttpCode();
                        String message = mSetUserBodyInfo.getMessage();
                        Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_SHORT).show();
                        if (code == 200) {
                            SetUserBodyInfo.ListDataBean mInfo = mSetUserBodyInfo.getListData().get(0);
                            personalInfo.setName(mInfo.getName());
                            personalInfo.setHeight(mInfo.getHeight());
                            personalInfo.setWeight(mInfo.getWeight());
                            personalInfo.setMan(mInfo.isSex());
                            personalInfo.setHeadImageUrl(mInfo.getHeadImage());
                            personalInfo.setConstitution(mInfo.getConstitution());
                            personalInfo.setAge(mInfo.getAge());
                            personalInfo.setPhone(mInfo.getPhone());
                            CacheUtils.putPersonalInfo(SettingsActivity.this, personalInfo);
                            finish();
                        }
                    }
                });

    }
}
