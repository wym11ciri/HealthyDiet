package com.huihong.healthydiet.bean;

/**
 * Created by zangyi_shuai_ge on 2017/7/27
 */

public class MailRegister {

    /**
     * HttpCode : 300
     * Message : 用户已注册
     */

    private int HttpCode;
    private String Message;

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
}
