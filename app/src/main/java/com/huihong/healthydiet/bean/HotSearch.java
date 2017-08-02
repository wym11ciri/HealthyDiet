package com.huihong.healthydiet.bean;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/2
 */

public class HotSearch {


    /**
     * HttpCode : 200
     * Message : 返回成功
     * ListData : ["餐厅","东北","咖啡","鸡","西餐","鸭","中餐"]
     */

    private int HttpCode;
    private String Message;
    private List<String> ListData;

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

    public List<String> getListData() {
        return ListData;
    }

    public void setListData(List<String> ListData) {
        this.ListData = ListData;
    }
}
