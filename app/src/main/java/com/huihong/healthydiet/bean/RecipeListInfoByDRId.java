package com.huihong.healthydiet.bean;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/17
 */

public class RecipeListInfoByDRId {

    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"id":1,"name":"食谱1","foodRecipe":[{"RecipeItemName":"主食","ListFood":"萝卜 270g"},{"RecipeItemName":"配菜","ListFood":"青菜 100g, 鸡肉 80g"}],"Constitution":["气虚质","痰湿质","阴虚质"],"images":["http://106.14.218.31:8081/img//recipe//recipe1-1986270.jpg"],"sales":1,"price":"66"},{"id":2,"name":"食谱21","foodRecipe":[{"RecipeItemName":"配菜","ListFood":"牛肉 1001g, 鸡肉 300g"},{"RecipeItemName":"主食","ListFood":"萝卜 99g, 青菜 111g"}],"Constitution":["阳虚质","血瘀质","气虚质"],"images":null,"sales":2,"price":"99"}]
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
         * id : 1
         * name : 食谱1
         * foodRecipe : [{"RecipeItemName":"主食","ListFood":"萝卜 270g"},{"RecipeItemName":"配菜","ListFood":"青菜 100g, 鸡肉 80g"}]
         * Constitution : ["气虚质","痰湿质","阴虚质"]
         * images : ["http://106.14.218.31:8081/img//recipe//recipe1-1986270.jpg"]
         * sales : 1
         * price : 66
         */

        private int id;
        private String name;
        private int sales;
        private String price;
        private List<FoodRecipeBean> foodRecipe;
        private List<String> Constitution;
        private List<String> images;

        private boolean isShow=false;

        public void setShow(boolean isShow) {
            this.isShow=isShow;
        }

        public boolean isShow() {
            return  isShow;
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

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public static class FoodRecipeBean {
            /**
             * RecipeItemName : 主食
             * ListFood : 萝卜 270g
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
