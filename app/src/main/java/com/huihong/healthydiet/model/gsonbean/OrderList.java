package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.model.httpmodel.OrderDetailsInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/21
 */

public class OrderList extends HttpBaseInfo{

    /**
     * HttpCode : 200
     * Message : 数据成功返回
     * ListData : [{"OrderId":"9","RecipeId":"4","RecipeName":"食谱3","RecipeImage":"http://106.14.218.31:8081/img//recipe//recipe-201708181704510.jpg","Constitution":["特禀质","气虚质","阴虚质"],"FoodRecipe":[{"RecipeItemName":"配菜","ListFood":"青菜 1g, 萝卜 2g"},{"RecipeItemName":"主食","ListFood":"鸡肉 3g, 牛肉 4g"}],"OrderPrice":"140","OrderTime":"2017-08-21T15:23:00","RestaurantId":101,"RestaurantName":"江浙1"}]
     */

    private List<OrderDetailsInfo> ListData;


    public List<OrderDetailsInfo> getListData() {
        return ListData;
    }

    public void setListData(List<OrderDetailsInfo> ListData) {
        this.ListData = ListData;
    }


}
