package com.huihong.healthydiet.bean;

/**
 * Created by zangyi_shuai_ge on 2017/7/31
 */

public class CustomerLikeOrNot {

    /**
     * HttpCode : 200
     * Message : 删除成功
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
