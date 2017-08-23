package com.huihong.healthydiet;

/**
 * Created by zangyi_shuai_ge on 2017/7/13
 */

public class AppUrl {
    private static final String TEST_URL = "http://192.168.10.102:8051/API/Restaurant/GetRestaurantInfoById";
//        private static final String BASE_URL = "http://192.168.10.100:8051/";
    private static final String BASE_URL = "http://106.14.218.31:8020/";
    public static final String GET_RESTAURANT_LIST_INFO = BASE_URL + "API/Restaurant/GetRestaurantListInfo";//获取餐厅列表
    public static final String DATA_DICTIONARY = BASE_URL + "API/DataDictionary/Cate";//获取餐厅类型
    public static final String GET_RESTAURANT_INFO_BY_ID = BASE_URL + "API/Restaurant/GetRestaurantInfoById";//获取餐厅详情 上面那部分
    public static final String RECIPE_LIST_INFO_BY_DR_ID = BASE_URL + "API/Recipe/RecipeListInfoByDRId";//获取餐厅详情 下面列表部分
    public static final String RECIPE_ITEM_INFO = BASE_URL + "API/Recipe/RecipeItemInfo";//菜谱详情页面
    public static final String RECIPE_ITEM_INFO_FOR_PAY = BASE_URL + "API/Recipe/RecipeItemInfoForPay";
    public static final String RECIPE_LIST_BY_GPS = BASE_URL + "API/Recipe/RecipeListByGPS";
    //问卷
    public static final String GET_QUESTION_EXPRESS_LIST = BASE_URL + "API/Question/GetQuestionExpressList";//简易版问卷获取
    public static final String GET_SUBMIT_EXPRESS_QUESTION = BASE_URL + "API/Question/GetSubmitExpressQuestion";//简易版问卷提交
    public static final String GET_QUESTION_PROFESSION_LIST = BASE_URL + "API/Question/GetQuestionProfessionList";//专业版问卷获取
    public static final String GET_SUBMIT_QUESTION = BASE_URL + "API/Question/GetSubmitQuestion";//专业版问卷提交
    //文章
    public static final String GET_ARTICLE_LIST_INFO = BASE_URL + "API/Article/GetArticleListInfo";//获取文章列表
    public static final String ARTICLE_POINT_PRAISE = BASE_URL + "API/Article/ArticlePointPraise";//文章点赞
    public static final String ARTICLE_CLICK = BASE_URL + "API/Article/ArticleClick";//文章查看
    public static final String GET_ARTICLE_ITEM_INFO = BASE_URL + "API/Article/GetArticleItemInfo";//根据id获取文章详情

    //运动
    public static final String GET_SPORT_LIST = BASE_URL + "API/Sport/GetSportList";//获得运动步数
    public static final String UPLOAD_SPORT_INFO = BASE_URL + "API/Sport/UpLoadSportInfo";//提交运动步数
    //餐厅
    public static final String USER_PREFERENCE_REST = BASE_URL + "API/Restaurant/UserPreferenceRest";//用户收藏餐厅
    public static final String SEARCH_VAGUE_RESTAURANT = BASE_URL + "API/Restaurant/SearchVagueRestaurant";//餐厅搜索
    public static final String HOT_SEARCH = BASE_URL + "API/Restaurant/HotSearch";//热门搜索
    public static final String TITLE_PAGE = BASE_URL + "API/Restaurant/TitlePage";//首页数据
    public static final String CUSTOMER_LIKE_OR_NOT = BASE_URL + "API/Restaurant/CustomerLikeOrNot";//材料喜欢

    //积分
    public static final String ADD_SCORE_RECORD = BASE_URL + "API/Score/AddScoreRecord"; //添加积分
    public static final String GET_CLICK_SCORE = BASE_URL + "API/Score/GetClickScore"; //叶子
    public static final String CLICK_SCORE = BASE_URL + "API/Score/ClickScore";//积分点击
    public static final String GET_SCORE_LIST = BASE_URL + "API/Score/GetScoreList";//积分列表
    //用户模块
    public static final String USER_SCORE_INFO = BASE_URL + "API/User/UserScoreInfo";//获取用户积分等级信息
    public static final String VERIFICATION_CODE = BASE_URL + "API/User/VerificationCode";//用户修改手机号码第一步
    public static final String MODIFY_USER_PHONE = BASE_URL + "API/User/ModifyUserPhone";//用户修改手机号码第二步
    public static final String MODIFY_USER_PASSWORD = BASE_URL + "API/User/ModifyUserPassword";//修改密码
    public static final String SEND_MAIL = BASE_URL + "API/User/SendMail";//发送验证码短信
    public static final String MAIL_REGISTER = BASE_URL + "API/User/MailRegister";//手机验证码注册
    public static final String RESET_USER_PASSWORD = BASE_URL + "API/User/ResetUserPassword";//手机验证码注册
    public static final String SET_USER_BODY_INFO = BASE_URL + "API/User/SetUserBodyInfo";//设置用户身体信息
    public static final String GET_USER_BODY_INFO = BASE_URL + "API/User/GetUserBodyInfo";//获取用户身体信息
    public static final String SELECT_USER_PREFERENCE = BASE_URL + "API/User/SelectUserPreference";//获取用户喜欢喜欢界面
    public static final String UPLOAD_IMAGE = BASE_URL + "API/User/UploadImage";//上传头像
    public static final String UploadUserHeadImage = BASE_URL + "API/User/UploadUserHeadImage";
    public static final String LOGIN = BASE_URL + "API/User/Login";//登录


    //订单
    public static final String ORDER_INFO = BASE_URL + "api/Orders/OrderInfo";
    public static final String ORDER_LIST = BASE_URL + "api/Orders/OrderList";
    public static final String PAY_AT_SHOP_ORDER = BASE_URL + "api/Orders/PayAtShopOrder";


}
