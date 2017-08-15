package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/17
 * 餐厅详情上面那部分餐厅信息
 */

public class GetRestaurantInfoById extends HttpBaseInfo {


    private List<ListDataBean> ListData;

    public List<ListDataBean> getListData() {
        return ListData;
    }

    public void setListData(List<ListDataBean> ListData) {
        this.ListData = ListData;
    }

    public static class ListDataBean {
        /**
         * id : 100
         * name : 餐厅9
         * images : ["http://106.14.218.31:8081/img//restaurant//restaurant100-19812692.jpg","http://106.14.218.31:8081/img//restaurant//restaurant100-198550116.jpg"]
         * address : 杭州市拱墅区杭州运河广告产业大厦
         * phone : 18857120152
         * category : 西餐
         * sales : 2
         * consumption : 2
         * discount : 满100减200
         * distance : 541
         * cusLikeOrNot : false
         */

        private int id;
        private String name;
        private String address;
        private String phone;
        private String category;
        private int sales;
        private int consumption;
        private String discount;
        private int distance;
        private boolean cusLikeOrNot;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public int getConsumption() {
            return consumption;
        }

        public void setConsumption(int consumption) {
            this.consumption = consumption;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public boolean isCusLikeOrNot() {
            return cusLikeOrNot;
        }

        public void setCusLikeOrNot(boolean cusLikeOrNot) {
            this.cusLikeOrNot = cusLikeOrNot;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
