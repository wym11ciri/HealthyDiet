package com.huihong.healthydiet.bean;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/17
 * 餐厅详情上面那部分餐厅信息
 */

public class GetRestaurantInfoById {


    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"id":98,"name":"餐厅7","images":["http://106.14.218.31:8081/img//restaurant//restaurant98-198550116.jpg","http://106.14.218.31:8081/img//restaurant//restaurant98-19834101.jpg"],"address":"杭州市拱墅区浙大网新智慧立方","phone":"18857120152","category":"西餐","sales":5,"consumption":7,"discount":"满100减200|满100减200","distance":0}]
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
         * id : 98
         * name : 餐厅7
         * images : ["http://106.14.218.31:8081/img//restaurant//restaurant98-198550116.jpg","http://106.14.218.31:8081/img//restaurant//restaurant98-19834101.jpg"]
         * address : 杭州市拱墅区浙大网新智慧立方
         * phone : 18857120152
         * category : 西餐
         * sales : 5
         * consumption : 7
         * discount : 满100减200|满100减200
         * distance : 0
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

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
