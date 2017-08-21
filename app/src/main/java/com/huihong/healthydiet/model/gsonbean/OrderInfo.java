package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/21
 */

public class OrderInfo extends HttpBaseInfo {


    /**
     * Message : null
     * Model1 : {"RecipeId":1,"RecipeName":"食谱1","Constitution":["气虚质","痰湿质","阴虚质"],"ConstitutionPercentage":45,"Tags":["清蒸","爆炒"],"FoodRecipe":[{"RecipeId":1,"RecipeItemName":"主食","ListFood":[{"FoodId":54,"FoodName":"萝卜","FoodWeight":"270g","WhetherLike":0}]},{"RecipeId":3,"RecipeItemName":"配菜","ListFood":[{"FoodId":35,"FoodName":"青菜","FoodWeight":"100g","WhetherLike":0},{"FoodId":4,"FoodName":"鸡肉","FoodWeight":"80g","WhetherLike":0}]}],"OrderPrice":"99.00","OrderTime":"2017-07-03T15:31:00","RestaurantId":101,"RestaurantName":"江浙1","RestaurantImage":"http://106.14.218.31:8081/img//restaurant//restaurant101-19812692.jpg","RestaurantType":"江浙菜","RestaurantDistance":12859720,"RestaurantAddress":"杭州市拱墅区杭州运河广告产业大厦","RestaurantPhone":"18857120152"}
     */


    private Model1Bean Model1;



    public Model1Bean getModel1() {
        return Model1;
    }

    public void setModel1(Model1Bean Model1) {
        this.Model1 = Model1;
    }

    public static class Model1Bean {
        /**
         * RecipeId : 1
         * RecipeName : 食谱1
         * Constitution : ["气虚质","痰湿质","阴虚质"]
         * ConstitutionPercentage : 45
         * Tags : ["清蒸","爆炒"]
         * FoodRecipe : [{"RecipeId":1,"RecipeItemName":"主食","ListFood":[{"FoodId":54,"FoodName":"萝卜","FoodWeight":"270g","WhetherLike":0}]},{"RecipeId":3,"RecipeItemName":"配菜","ListFood":[{"FoodId":35,"FoodName":"青菜","FoodWeight":"100g","WhetherLike":0},{"FoodId":4,"FoodName":"鸡肉","FoodWeight":"80g","WhetherLike":0}]}]
         * OrderPrice : 99.00
         * OrderTime : 2017-07-03T15:31:00
         * RestaurantId : 101
         * RestaurantName : 江浙1
         * RestaurantImage : http://106.14.218.31:8081/img//restaurant//restaurant101-19812692.jpg
         * RestaurantType : 江浙菜
         * RestaurantDistance : 12859720
         * RestaurantAddress : 杭州市拱墅区杭州运河广告产业大厦
         * RestaurantPhone : 18857120152
         */

        private int RecipeId;
        private String RecipeName;
        private int ConstitutionPercentage;
        private String OrderPrice;
        private String OrderTime;
        private int RestaurantId;
        private String RestaurantName;
        private String RestaurantImage;
        private String RestaurantType;
        private int RestaurantDistance;
        private String RestaurantAddress;
        private String RestaurantPhone;
        private List<String> Constitution;
        private List<String> Tags;
        private List<FoodRecipeBean> FoodRecipe;

        public int getRecipeId() {
            return RecipeId;
        }

        public void setRecipeId(int RecipeId) {
            this.RecipeId = RecipeId;
        }

        public String getRecipeName() {
            return RecipeName;
        }

        public void setRecipeName(String RecipeName) {
            this.RecipeName = RecipeName;
        }

        public int getConstitutionPercentage() {
            return ConstitutionPercentage;
        }

        public void setConstitutionPercentage(int ConstitutionPercentage) {
            this.ConstitutionPercentage = ConstitutionPercentage;
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

        public String getRestaurantImage() {
            return RestaurantImage;
        }

        public void setRestaurantImage(String RestaurantImage) {
            this.RestaurantImage = RestaurantImage;
        }

        public String getRestaurantType() {
            return RestaurantType;
        }

        public void setRestaurantType(String RestaurantType) {
            this.RestaurantType = RestaurantType;
        }

        public int getRestaurantDistance() {
            return RestaurantDistance;
        }

        public void setRestaurantDistance(int RestaurantDistance) {
            this.RestaurantDistance = RestaurantDistance;
        }

        public String getRestaurantAddress() {
            return RestaurantAddress;
        }

        public void setRestaurantAddress(String RestaurantAddress) {
            this.RestaurantAddress = RestaurantAddress;
        }

        public String getRestaurantPhone() {
            return RestaurantPhone;
        }

        public void setRestaurantPhone(String RestaurantPhone) {
            this.RestaurantPhone = RestaurantPhone;
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

        public List<FoodRecipeBean> getFoodRecipe() {
            return FoodRecipe;
        }

        public void setFoodRecipe(List<FoodRecipeBean> FoodRecipe) {
            this.FoodRecipe = FoodRecipe;
        }

        public static class FoodRecipeBean {
            /**
             * RecipeId : 1
             * RecipeItemName : 主食
             * ListFood : [{"FoodId":54,"FoodName":"萝卜","FoodWeight":"270g","WhetherLike":0}]
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
                 * FoodId : 54
                 * FoodName : 萝卜
                 * FoodWeight : 270g
                 * WhetherLike : 0
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
