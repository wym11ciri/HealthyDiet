package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.model.httpmodel.ScreenType;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/15
 */

public class DataDictionary extends HttpBaseInfo{

    /**
     * HttpCode : 200
     * Message : null
     * ListData : [{"id":2,"typeValue":"江浙菜"},{"id":3,"typeValue":"咖啡厅"},{"id":5,"typeValue":"西餐"}]
     */

    private List<ScreenType> ListData;

    public List<ScreenType> getListData() {
        return ListData;
    }

    public void setListData(List<ScreenType> ListData) {
        this.ListData = ListData;
    }

}
