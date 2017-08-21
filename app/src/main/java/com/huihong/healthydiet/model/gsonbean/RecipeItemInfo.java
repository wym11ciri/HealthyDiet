package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/18
 * 食谱详情页面
 */

public class RecipeItemInfo extends HttpBaseInfo{


    private List<ListDataBean> ListData;

    public List<ListDataBean> getListData() {
        return ListData;
    }

    public void setListData(List<ListDataBean> ListData) {
        this.ListData = ListData;
    }

    public static class ListDataBean {
        /**
         * id : 8
         * name : 食谱7
         * foodRecipe : [{"RecipeId":5,"RecipeItemName":"水果","ListFood":[{"FoodId":35,"FoodName":"青菜","FoodWeight":"555g","WhetherLike":2}]}]
         * ConstitutionPercentage : 30
         * Constitution : ["气虚质","阴虚质","痰湿质"]
         * Tags : ["清蒸"]
         * images : ["http://106.14.218.31:8081/img//recipe//recipe-201708181705420.jpg","http://106.14.218.31:8081/img//recipe//recipe-201708181705421.jpg","http://106.14.218.31:8081/img//recipe//recipe-201708181705422.jpg"]
         * price : 1.00
         * RestaurantId : 91
         */

        private int id;
        private String name;
        private int ConstitutionPercentage;
        private String price;
        private int RestaurantId;
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

        public int getRestaurantId() {
            return RestaurantId;
        }

        public void setRestaurantId(int RestaurantId) {
            this.RestaurantId = RestaurantId;
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
             * RecipeId : 5
             * RecipeItemName : 水果
             * ListFood : [{"FoodId":35,"FoodName":"青菜","FoodWeight":"555g","WhetherLike":2}]
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
                 * FoodId : 35
                 * FoodName : 青菜
                 * FoodWeight : 555g
                 * WhetherLike : 2
                 */

                private int FoodId;
                private String FoodName;
                private String FoodWeight;
                private int WhetherLike;

                public int getFoodId() {
                    return FoodId;
                }

                public void setFoodId(int FoodId) {
                    this.FoodId = FoodId;
                }

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
