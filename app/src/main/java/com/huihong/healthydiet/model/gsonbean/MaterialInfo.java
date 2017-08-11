package com.huihong.healthydiet.model.gsonbean;

/**
 * Created by zangyi_shuai_ge on 2017/7/18
 */

public class MaterialInfo {


    private String RecipeItemName;
    private String FoodInfo;
    private int WhetherLike;
    private int id;

    public String getRecipeItemName() {
        return RecipeItemName;
    }

    public void setRecipeItemName(String recipeItemName) {
        RecipeItemName = recipeItemName;
    }

    public String getFoodInfo() {
        return FoodInfo;
    }

    public void setFoodInfo(String foodInfo) {
        FoodInfo = foodInfo;
    }

    public int getWhetherLike() {
        return WhetherLike;
    }

    public void setWhetherLike(int whetherLike) {
        WhetherLike = whetherLike;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
