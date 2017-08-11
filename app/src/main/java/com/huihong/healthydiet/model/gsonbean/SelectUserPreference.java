package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/29
 */

public class SelectUserPreference extends HttpBaseInfo{

    /**
     * HttpCode : 200
     * Message : null
     * ListData : [{"FoodId":154,"FoodName":"小米","FoodWeight":null,"WhetherLike":null},{"FoodId":148,"FoodName":"鸭肉","FoodWeight":null,"WhetherLike":null}]
     */


    private List<ListDataBean> ListData;



    public List<ListDataBean> getListData() {
        return ListData;
    }

    public void setListData(List<ListDataBean> ListData) {
        this.ListData = ListData;
    }

    public static class ListDataBean {
        /**
         * FoodId : 154
         * FoodName : 小米
         * FoodWeight : null
         * WhetherLike : null
         */

        private int FoodId;
        private String FoodName;

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
    }
}
