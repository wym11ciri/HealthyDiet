package com.huihong.healthydiet;

/**
 * Created by zangyi_shuai_ge on 2017/7/13
 */

public class AppUrl {
    private static final String TEST_URL = "http://192.168.10.102:8051/API/Restaurant/GetRestaurantInfoById";
    private static final String BASE_URL = "http://192.168.10.102:8051/";
//    private static final String BASE_URL = "http://106.14.218.31:8020/";



    public static final String GET_RESTAURANT_LIST_INFO = BASE_URL + "API/Restaurant/GetRestaurantListInfo";//获取餐厅列表
    public static final String DATA_DICTIONARY = BASE_URL + "API/DataDictionary/Cate";//获取餐厅类型
    public static final String GET_RESTAURANT_INFO_BY_ID = BASE_URL + "API/Restaurant/GetRestaurantInfoById";//获取餐厅详情 上面那部分
    public static final String RECIPE_LIST_INFO_BY_DRID = BASE_URL + "API/Recipe/RecipeListInfoByDRId";//获取餐厅详情 下面列表部分

    public static final String RECIPE_ITEM_INFO = BASE_URL + "API/Recipe/RecipeItemInfo";//菜谱详情页面

    public static final String RECIPE_ITEM_INFO_FOR_PAY = BASE_URL + "API/Recipe/RecipeItemInfoForPay";//菜谱详情页面



}
