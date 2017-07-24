package com.huihong.healthydiet.bean;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/17
 */

public class RecipeListInfoByDRId {


    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"id":7,"name":"食谱4","foodRecipe":[{"RecipeItemName":"配菜","ListFood":"萝卜 11g, 海参 22g"},{"RecipeItemName":"主食","ListFood":"鸡肉 33g, 牛肉 44g"}],"Constitution":["平和质","气郁质","阴虚质"],"images":"http://106.14.218.31:8081/img//recipe//recipe7-19818599.jpg","sales":11,"price":"11.00"}]
     */

    private int HttpCode;
    private String Message;
    private List<ListDataBean> ListData;

    public int getHttpCode() {
        return HttpCode;
    }

    public void setHttpCode(int HttpCode) {
        this.HttpCode = HttpCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public List<ListDataBean> getListData() {
        return ListData;
    }

    public void setListData(List<ListDataBean> ListData) {
        this.ListData = ListData;
    }

    public static class ListDataBean {
        /**
         * id : 7
         * name : 食谱4
         * foodRecipe : [{"RecipeItemName":"配菜","ListFood":"萝卜 11g, 海参 22g"},{"RecipeItemName":"主食","ListFood":"鸡肉 33g, 牛肉 44g"}]
         * Constitution : ["平和质","气郁质","阴虚质"]
         * images : http://106.14.218.31:8081/img//recipe//recipe7-19818599.jpg
         * sales : 11
         * price : 11.00
         */

        private int id;
        private String name;
        private String images;
        private int sales;
        private String price;
        private List<FoodRecipeBean> foodRecipe;
        private List<String> Constitution;
        private  boolean isShow=false;

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

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

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
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
             * ListFood : 萝卜 11g, 海参 22g
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
