package com.huihong.healthydiet.bean;

import com.huihong.healthydiet.model.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/2
 */

public class HotSearch extends HttpBaseInfo{


    /**
     * HttpCode : 200
     * Message : 返回成功
     * ListData : ["餐厅","东北","咖啡","鸡","西餐","鸭","中餐"]
     */

    private List<String> ListData;
    public List<String> getListData() {
        return ListData;
    }
    public void setListData(List<String> ListData) {
        this.ListData = ListData;
    }
}
