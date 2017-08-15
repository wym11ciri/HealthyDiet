package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.model.httpmodel.RestaurantInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/15
 */

public class UserPreferenceRest extends HttpBaseInfo {


    /**
     * Message : null
     * ListData : [{"id":100,"name":"餐厅9","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant100-19812692.jpg","address":"杭州市拱墅区杭州运河广告产业大厦","phone":"18857120152","category":"5","sales":2,"consumption":2,"discount":"满100减200","distance":536},{"id":91,"name":"餐厅4","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant91-19863208.jpg","address":"杭州市拱墅区北部软件园","phone":"18857120152","category":"5","sales":654,"consumption":321,"discount":"满100减200","distance":176},{"id":113,"name":"西餐3","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant113-19863208.jpg","address":"杭州市拱墅区祥园路-道路","phone":"18857120152","category":"5","sales":111,"consumption":22,"discount":"满100减200","distance":914},{"id":108,"name":"餐厅17","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant108-198550116.jpg","address":"杭州市拱墅区杭州拱墅万达广场","phone":"18857120152","category":"5","sales":1111,"consumption":1,"discount":"满100减200","distance":959}]
     */

    private List<RestaurantInfo> ListData;

    public List<RestaurantInfo> getListData() {
        return ListData;
    }

    public void setListData(List<RestaurantInfo> ListData) {
        this.ListData = ListData;
    }

}
