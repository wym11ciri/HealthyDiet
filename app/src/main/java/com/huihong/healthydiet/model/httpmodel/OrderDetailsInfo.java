package com.huihong.healthydiet.model.httpmodel;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/21
 * 订单详情
 */

public class OrderDetailsInfo {

    /**
     * RecipeId : 4
     * RecipeName : 食谱3
     * RecipeImage : http://106.14.218.31:8081/img//recipe//recipe-201708181704510.jpg
     * Constitution : ["特禀质","气虚质","阴虚质"]
     * FoodRecipe : [{"RecipeItemName":"配菜","ListFood":"青菜 1g, 萝卜 2g"},{"RecipeItemName":"主食","ListFood":"鸡肉 3g, 牛肉 4g"}]
     * OrderPrice : 140
     * OrderTime : 2017-08-21T15:23:00
     * RestaurantId : 101
     * RestaurantName : 江浙1
     */

    private String RecipeId;
    private String RecipeName;
    private String RecipeImage;
    private String OrderPrice;
    private String OrderTime;
    private int RestaurantId;
    private String RestaurantName;
    private List<String> Constitution;
    private List<FoodRecipeBean> FoodRecipe;
    private String OrderId;
    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }
    public String getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(String RecipeId) {
        this.RecipeId = RecipeId;
    }

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String RecipeName) {
        this.RecipeName = RecipeName;
    }

    public String getRecipeImage() {
        return RecipeImage;
    }

    public void setRecipeImage(String RecipeImage) {
        this.RecipeImage = RecipeImage;
    }

    public String getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(String OrderPrice) {
        this.OrderPrice = OrderPrice;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String OrderTime) {
        this.OrderTime = OrderTime;
    }

    public int getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(int RestaurantId) {
        this.RestaurantId = RestaurantId;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String RestaurantName) {
        this.RestaurantName = RestaurantName;
    }

    public List<String> getConstitution() {
        return Constitution;
    }

    public void setConstitution(List<String> Constitution) {
        this.Constitution = Constitution;
    }

    public List<FoodRecipeBean> getFoodRecipe() {
        return FoodRecipe;
    }

    public void setFoodRecipe(List<FoodRecipeBean> FoodRecipe) {
        this.FoodRecipe = FoodRecipe;
    }

    public static class FoodRecipeBean {
        /**
         * RecipeItemName : 配菜
         * ListFood : 青菜 1g, 萝卜 2g
         */

        private String RecipeItemName;
        private String ListFood;

        public String getRecipeItemName() {
            return RecipeItemName;
        }

        public void setRecipeItemName(String RecipeItemName) {
            this.RecipeItemName = RecipeItemName;
        }

        public String getListFood() {
            return ListFood;
        }

        public void setListFood(String ListFood) {
            this.ListFood = ListFood;
        }
    }
}

