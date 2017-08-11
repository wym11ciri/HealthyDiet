package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/17
 */

public class RecipeListInfoByDRId extends HttpBaseInfo{


    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"id":4,"name":"食谱5","foodRecipe":[{"RecipeItemName":"配菜","ListFood":"青菜 1g, 萝卜 2g"},{"RecipeItemName":"主食","ListFood":"鸡肉 3g, 牛肉 4g"}],"Constitution":["平和质","气郁质","阴虚质"],"titleImage":"http://106.14.218.31:8081/img//recipe//recipe4-19823844.jpg","sales":11,"price":"11.00"}]
     */


    private List<ListDataBean> ListData;



    public List<ListDataBean> getListData() {
        return ListData;
    }

    public void setListData(List<ListDataBean> ListData) {
        this.ListData = ListData;
    }

    public static class ListDataBean {
        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        /**
         * id : 4
         * name : 食谱5
         * foodRecipe : [{"RecipeItemName":"配菜","ListFood":"青菜 1g, 萝卜 2g"},{"RecipeItemName":"主食","ListFood":"鸡肉 3g, 牛肉 4g"}]
         * Constitution : ["平和质","气郁质","阴虚质"]
         * titleImage : http://106.14.218.31:8081/img//recipe//recipe4-19823844.jpg
         * sales : 11
         * price : 11.00
         */

        private boolean isShow=false;
        private int id;
        private String name;
        private String titleImage;
        private int sales;
        private String price;
        private List<FoodRecipeBean> foodRecipe;
        private List<String> Constitution;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitleImage() {
            return titleImage;
        }

        public void setTitleImage(String titleImage) {
            this.titleImage = titleImage;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<FoodRecipeBean> getFoodRecipe() {
            return foodRecipe;
        }

        public void setFoodRecipe(List<FoodRecipeBean> foodRecipe) {
            this.foodRecipe = foodRecipe;
        }

        public List<String> getConstitution() {
            return Constitution;
        }

        public void setConstitution(List<String> Constitution) {
            this.Constitution = Constitution;
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
}
