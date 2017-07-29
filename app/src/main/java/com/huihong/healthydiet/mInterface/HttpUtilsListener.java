package com.huihong.healthydiet.mInterface;

/**
 * Created by zangyi_shuai_ge on 2017/4/21
 * 网络访问接口回调
 */

public interface HttpUtilsListener {
    void onResponse(String response, int id);
    void onError(okhttp3.Call call, Exception e, int id);
}
