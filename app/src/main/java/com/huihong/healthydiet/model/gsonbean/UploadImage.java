package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

/**
 * Created by zangyi_shuai_ge on 2017/8/2
 */

public class UploadImage extends HttpBaseInfo{

    /**
     * HttpCode : 200
     * Message : 数据成功返回
     * Model1 : Content/UserImage/LST3JC636372841792850023.jpg
     */


    private String Model1;



    public String getModel1() {
        return Model1;
    }

    public void setModel1(String Model1) {
        this.Model1 = Model1;
    }
}
