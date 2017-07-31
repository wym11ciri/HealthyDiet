package com.huihong.healthydiet;

/**
 * Created by zangyi_shuai_ge on 2017/7/13
 */

public class AppUrl {
    private static final String TEST_URL = "http://192.168.10.102:8051/API/Restaurant/GetRestaurantInfoById";
    private static final String BASE_URL = "http://192.168.10.100:8051/";
    //    private static final String BASE_URL = "http://106.14.218.31:8020/";
    public static final String GET_RESTAURANT_LIST_INFO = BASE_URL + "API/Restaurant/GetRestaurantListInfo";//获取餐厅列表
    public static final String DATA_DICTIONARY = BASE_URL + "API/DataDictionary/Cate";//获取餐厅类型
    public static final String GET_RESTAURANT_INFO_BY_ID = BASE_URL + "API/Restaurant/GetRestaurantInfoById";//获取餐厅详情 上面那部分
    public static final String RECIPE_LIST_INFO_BY_DR_ID = BASE_URL + "API/Recipe/RecipeListInfoByDRId";//获取餐厅详情 下面列表部分
    public static final String RECIPE_ITEM_INFO = BASE_URL + "API/Recipe/RecipeItemInfo";//菜谱详情页面
    public static final String RECIPE_ITEM_INFO_FOR_PAY = BASE_URL + "API/Recipe/RecipeItemInfoForPay";
    public static final String RECIPE_LIST_BY_GPS = BASE_URL + "API/Recipe/RecipeListByGPS";
    //获取文章列表
    public static final String GET_ARTICLE_LIST_INFO = BASE_URL + "API/Article/GetArticleListInfo";
    //用户注册模块
    public static final String SEND_MAIL = BASE_URL + "API/User/SendMail";//发送验证码短信
    public static final String MAIL_REGISTER = BASE_URL + "API/User/MailRegister";//手机验证码注册
    public static final String RESET_USER_PASSWORD = BASE_URL + "API/User/ResetUserPassword";//手机验证码注册
    //个人中心
    public static final String SET_USER_BODY_INFO = BASE_URL + "API/User/SetUserBodyInfo";//设置用户身体信息
    public static final String GET_USER_BODY_INFO = BASE_URL + "API/User/GetUserBodyInfo";//获取用户身体信息
    public static final String SELECT_USER_PREFERENCE = BASE_URL + "API/User/SelectUserPreference";//获取用户喜欢喜欢界面

    //问卷
    public static final String GET_QUESTION_EXPRESS_LIST = BASE_URL + "API/Question/GetQuestionExpressList";//简易版问卷获取
    public static final String GET_SUBMIT_EXPRESS_QUESTION = BASE_URL + "API/Question/GetSubmitExpressQuestion";//简易版问卷提交
    public static final String GET_QUESTION_PROFESSION_LIST = BASE_URL + "Api/Question/GetQuestionProfessionList";//专业版问卷获取
    public static final String GET_SUBMIT_QUESTION = BASE_URL + "Api/Question/GetSubmitQuestion";//专业版问卷提交
    //登录
    public static final String LOGIN = BASE_URL + "API/User/Login";//登录

    //食谱详情

    public static final String CUSTOMER_LIKE_OR_NOT = BASE_URL + "API/Restaurant/CustomerLikeOrNot";//用户喜不喜欢

}
