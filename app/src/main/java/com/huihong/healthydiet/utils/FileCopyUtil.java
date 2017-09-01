package com.huihong.healthydiet.utils;

import android.content.Context;
import android.os.Environment;

import com.huihong.healthydiet.utils.common.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import static com.joooonho.SelectableRoundedImageView.TAG;

/**
 * Created by zangyi_shuai_ge on 2017/8/28
 */

public class FileCopyUtil {




    /**
     * 拷贝数据库到sd卡
     *
     * @deprecated <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     */
    public static void copyDataBaseToSD(Context mContext){
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return ;
        }
        File dbFile = new File(mContext.getApplicationContext().getDatabasePath("Cache")+".db");
        File file = new File(Environment.getExternalStorageDirectory(), "健康饮食数据库.db");

        FileChannel inChannel = null,outChannel = null;

        try {
            file.createNewFile();
            inChannel = new FileInputStream(dbFile).getChannel();
            outChannel = new FileOutputStream(file).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (Exception e) {
            LogUtil.i(TAG, "copy dataBase to SD error.");
            e.printStackTrace();
        }finally{
            try {
                if (inChannel != null) {
                    inChannel.close();
                    inChannel = null;
                }
                if(outChannel != null){
                    outChannel.close();
                    outChannel = null;
                }
            } catch (IOException e) {
                LogUtil.i(TAG, "file close error.");
                e.printStackTrace();
            }
        }
    }


}
