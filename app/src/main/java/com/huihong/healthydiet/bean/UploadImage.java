package com.huihong.healthydiet.bean;

/**
 * Created by zangyi_shuai_ge on 2017/8/2
 */

public class UploadImage {

    /**
     * HttpCode : 200
     * Message : 数据成功返回
     * Model1 : Content/UserImage/LST3JC636372841792850023.jpg
     */

    private int HttpCode;
    private String Message;
    private String Model1;

    public int getHttpCode() {
        return HttpCode;
    }

    public void setHttpCode(int HttpCode) {
        this.HttpCode = HttpCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getModel1() {
        return Model1;
    }

    public void setModel1(String Model1) {
        this.Model1 = Model1;
    }
}
