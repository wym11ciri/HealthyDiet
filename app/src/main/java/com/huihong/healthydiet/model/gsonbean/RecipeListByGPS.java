package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.model.httpmodel.RecipeInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/24
 */

public class RecipeListByGPS extends HttpBaseInfo{


    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"id":1,"name":"食谱1","titleImage":"http://106.14.218.31:8081/img//recipe//recipe1-1986270.jpg","ConstitutionPercentage":0,"sales":12,"price":"66.00","Tags":["清蒸","爆炒"],"Restaurant_Name":"餐厅2","Restaurant_Address":"杭州市拱墅区拱墅区","distance":null,"category":"3"},{"id":2,"name":"食谱21","titleImage":"http://106.14.218.31:8081/img//recipe//recipe2-19829097.jpg","ConstitutionPercentage":0,"sales":2,"price":"99.00","Tags":["红烧"],"Restaurant_Name":"咖啡1","Restaurant_Address":"北京市朝阳区k酷时尚广场","distance":null,"category":"3"},{"id":4,"name":"食谱5","titleImage":"http://106.14.218.31:8081/img//recipe//recipe4-19823844.jpg","ConstitutionPercentage":0,"sales":11,"price":"11.00","Tags":["清蒸","红烧"],"Restaurant_Name":"餐厅4","Restaurant_Address":"杭州市拱墅区北部软件园","distance":null,"category":"5"},{"id":5,"name":"食谱2","titleImage":"http://106.14.218.31:8081/img//recipe//recipe5-19812692.jpg","ConstitutionPercentage":0,"sales":99,"price":"99.00","Tags":["爆炒"],"Restaurant_Name":"餐厅5","Restaurant_Address":"杭州市拱墅区中国智慧信息产业园","distance":null,"category":"5"},{"id":6,"name":"食谱3","titleImage":"http://106.14.218.31:8081/img//recipe//recipe6-19863208.jpg","ConstitutionPercentage":0,"sales":11,"price":"11.00","Tags":["红烧"],"Restaurant_Name":"餐厅6","Restaurant_Address":"杭州市拱墅区方正荷塘月色","distance":null,"category":"5"},{"id":8,"name":"食谱6","titleImage":"http://106.14.218.31:8081/img//recipe//recipe8-198144654.jpg","ConstitutionPercentage":0,"sales":22,"price":"22.00","Tags":["清蒸"],"Restaurant_Name":"餐厅8","Restaurant_Address":"杭州市拱墅区杭州微盘信息技术有限公司","distance":null,"category":"5"},{"id":9,"name":"食谱7","titleImage":"http://106.14.218.31:8081/img//recipe//recipe9-19813850.jpg","ConstitutionPercentage":0,"sales":22,"price":"11.00","Tags":["爆炒"],"Restaurant_Name":"餐厅9","Restaurant_Address":"杭州市拱墅区杭州运河广告产业大厦","distance":null,"category":"5"},{"id":10,"name":"食谱8","titleImage":"http://106.14.218.31:8081/img//recipe//recipe10-19823844.jpg","ConstitutionPercentage":0,"sales":22,"price":"11.00","Tags":["清蒸"],"Restaurant_Name":"江浙1","Restaurant_Address":"","distance":null,"category":"2"},{"id":11,"name":"食谱9","titleImage":"http://106.14.218.31:8081/img//recipe//recipe11-19823844.jpg","ConstitutionPercentage":0,"sales":33,"price":"22.00","Tags":["红烧"],"Restaurant_Name":"江浙1","Restaurant_Address":"","distance":null,"category":"2"},{"id":12,"name":"食谱10","titleImage":"http://106.14.218.31:8081/img//recipe//recipe12-19863208.jpg","ConstitutionPercentage":0,"sales":33,"price":"22.00","Tags":["红烧"],"Restaurant_Name":"江浙1","Restaurant_Address":"","distance":null,"category":"2"}]
     */


    private List<RecipeInfo> ListData;



    public List<RecipeInfo> getListData() {
        return ListData;
    }

    public void setListData(List<RecipeInfo> ListData) {
        this.ListData = ListData;
    }


}
