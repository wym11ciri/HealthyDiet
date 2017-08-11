package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.model.httpmodel.RestaurantInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/15
 * 餐厅列表
 */

public class RestaurantList extends HttpBaseInfo {


    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"id":113,"name":"西餐3","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant113-19863208.jpg","address":"杭州市拱墅区祥园路-道路","phone":"18857120152","category":"5","sales":111,"consumption":22,"discount":"满100减200","distance":0},{"id":91,"name":"餐厅4","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant91-19863208.jpg","address":"杭州市拱墅区北部软件园","phone":"18857120152","category":"5","sales":654,"consumption":321,"discount":"满100减200","distance":1088},{"id":100,"name":"餐厅9","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant100-19812692.jpg","address":"杭州市拱墅区杭州运河广告产业大厦","phone":"18857120152","category":"5","sales":2,"consumption":2,"discount":"满100减200","distance":1436},{"id":108,"name":"餐厅17","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant108-198550116.jpg","address":"杭州市拱墅区杭州拱墅万达广场","phone":"18857120152","category":"5","sales":1111,"consumption":1,"discount":"满100减200","distance":1464},{"id":109,"name":"餐厅18","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant109-19812692.jpg","address":"杭州市拱墅区杭州拱墅万达广场","phone":"18857120152","category":"5","sales":12,"consumption":12,"discount":"满100减200","distance":1464},{"id":3,"name":"餐厅2","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant3-19834101.jpg","address":"杭州市拱墅区拱墅区","phone":"18857120152","category":"3","sales":88811,"consumption":251,"discount":"优惠2|优惠3","distance":4686},{"id":96,"name":"餐厅5","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant96-198197843.jpg","address":"杭州市拱墅区中国智慧信息产业园","phone":"18857120152","category":"5","sales":1,"consumption":2,"discount":"满100减200","distance":4686},{"id":97,"name":"餐厅6","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant97-198144654.jpg","address":"杭州市拱墅区方正荷塘月色","phone":"18857120152","category":"5","sales":111,"consumption":222,"discount":"满100减200","distance":4686},{"id":99,"name":"餐厅8","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant99-19823844.jpg","address":"杭州市拱墅区杭州微盘信息技术有限公司","phone":"18857120152","category":"5","sales":111,"consumption":2,"discount":"满100减200","distance":4686},{"id":105,"name":"餐厅14","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant105-198197843.jpg","address":"杭州市拱墅区拱墅皮肤病医院","phone":"18857120152","category":"5","sales":14,"consumption":14,"discount":"满100减200","distance":4686}]
     */


    private List<RestaurantInfo> ListData;

    public List<RestaurantInfo> getListData() {
        return ListData;
    }

    public void setListData(List<RestaurantInfo> ListData) {
        this.ListData = ListData;
    }

}
