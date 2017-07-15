package com.huihong.healthydiet;

/**
 * Created by zangyi_shuai_ge on 2017/7/13
 */

public class AppUrl {
    private static final String TEST_URL = "http://192.168.10.111:8051/API/Restaurant/GetRestaurantListInfo";
//    private static final String BASE_URL = "http://192.168.10.102:8051/";
    private static final String BASE_URL = "http://106.14.218.31:8020/";



    public static final String GET_RESTAURANT_LIST_INFO = BASE_URL + "API/Restaurant/GetRestaurantListInfo";//获取餐厅列表
    public static final String DATA_DICTIONARY = BASE_URL + "API/DataDictionary/Cate";//获取餐厅类型


}
