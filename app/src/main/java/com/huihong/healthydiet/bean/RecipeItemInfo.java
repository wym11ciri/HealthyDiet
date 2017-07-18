package com.huihong.healthydiet.bean;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/18
 * 食谱详情页面
 */

public class RecipeItemInfo {


    /**
     * HttpCode : 200
     * Message : null
     * ListData : [{"id":7,"name":"食谱4","foodRecipe":[{"RecipeId":3,"RecipeItemName":"配菜","ListFood":[{"FoodName":"萝卜","FoodWeight":"11g","WhetherLike":1},{"FoodName":"白菜","FoodWeight":"22g","WhetherLike":0}]},{"RecipeId":1,"RecipeItemName":"主食","ListFood":[{"FoodName":"鸡肉","FoodWeight":"33g","WhetherLike":2},{"FoodName":"牛肉","FoodWeight":"44g","WhetherLike":0}]}],"ConstitutionPercentage":-2147483648,"Constitution":["平和质","气郁质","阴虚质"],"Tags":["清蒸"],"images":["http://106.14.218.31:8081/img//recipe//recipe7-19818599.jpg","http://106.14.218.31:8081/img//recipe//recipe7-19829097.jpg"],"price":"11.00"}]
     */

    private int HttpCode;
    private Object Message;
    private List<ListDataBean> ListData;

    public int getHttpCode() {
        return HttpCode;
    }

    public void setHttpCode(int HttpCode) {
        this.HttpCode = HttpCode;
    }

    public Object getMessage() {
        return Message;
    }

    public void setMessage(Object Message) {
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
         * foodRecipe : [{"RecipeId":3,"RecipeItemName":"配菜","ListFood":[{"FoodName":"萝卜","FoodWeight":"11g","WhetherLike":1},{"FoodName":"白菜","FoodWeight":"22g","WhetherLike":0}]},{"RecipeId":1,"RecipeItemName":"主食","ListFood":[{"FoodName":"鸡肉","FoodWeight":"33g","WhetherLike":2},{"FoodName":"牛肉","FoodWeight":"44g","WhetherLike":0}]}]
         * ConstitutionPercentage : -2147483648
         * Constitution : ["平和质","气郁质","阴虚质"]
         * Tags : ["清蒸"]
         * images : ["http://106.14.218.31:8081/img//recipe//recipe7-19818599.jpg","http://106.14.218.31:8081/img//recipe//recipe7-19829097.jpg"]
         * price : 11.00
         */

        private int id;
        private String name;
        private int ConstitutionPercentage;
        private String price;
        private List<FoodRecipeBean> foodRecipe;
        private List<String> Constitution;
        private List<String> Tags;
        private List<String> images;

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

        public int getConstitutionPercentage() {
            return ConstitutionPercentage;
        }

        public void setConstitutionPercentage(int ConstitutionPercentage) {
            this.ConstitutionPercentage = ConstitutionPercentage;
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

        public List<String> getTags() {
            return Tags;
        }

        public void setTags(List<String> Tags) {
            this.Tags = Tags;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public static class FoodRecipeBean {
            /**
             * RecipeId : 3
             * RecipeItemName : 配菜
             * ListFood : [{"FoodName":"萝卜","FoodWeight":"11g","WhetherLike":1},{"FoodName":"白菜","FoodWeight":"22g","WhetherLike":0}]
             */

            private int RecipeId;
            private String RecipeItemName;
            private List<ListFoodBean> ListFood;

            public int getRecipeId() {
                return RecipeId;
            }

            public void setRecipeId(int RecipeId) {
                this.RecipeId = RecipeId;
            }

            public String getRecipeItemName() {
                return RecipeItemName;
            }

            public void setRecipeItemName(String RecipeItemName) {
                this.RecipeItemName = RecipeItemName;
            }

            public List<ListFoodBean> getListFood() {
                return ListFood;
            }

            public void setListFood(List<ListFoodBean> ListFood) {
                this.ListFood = ListFood;
            }

            public static class ListFoodBean {
                /**
                 * FoodName : 萝卜
                 * FoodWeight : 11g
                 * WhetherLike : 1
                 */

                private String FoodName;
                private String FoodWeight;
                private int WhetherLike;

                public String getFoodName() {
                    return FoodName;
                }

                public void setFoodName(String FoodName) {
                    this.FoodName = FoodName;
                }

                public String getFoodWeight() {
                    return FoodWeight;
                }

                public void setFoodWeight(String FoodWeight) {
                    this.FoodWeight = FoodWeight;
                }

                public int getWhetherLike() {
                    return WhetherLike;
                }

                public void setWhetherLike(int WhetherLike) {
                    this.WhetherLike = WhetherLike;
                }
            }
        }
    }
}
