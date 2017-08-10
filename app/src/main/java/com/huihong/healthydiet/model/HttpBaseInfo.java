package com.huihong.healthydiet.model;

/**
 * Created by zangyi_shuai_ge on 2017/8/10
 * 网络访问基类
 */

public class HttpBaseInfo {

    private int HttpCode;
    private String Message;

    public int getHttpCode() {
        return HttpCode;
    }

    public void setHttpCode(int httpCode) {
        HttpCode = httpCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
