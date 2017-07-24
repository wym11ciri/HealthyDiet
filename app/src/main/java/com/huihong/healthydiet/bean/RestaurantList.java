package com.huihong.healthydiet.bean;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/15
 * 餐厅列表
 */

public class RestaurantList {


    /**
     * HttpCode : 200
     * Message : null
     * ListData : [{"id":98,"name":"餐厅7","images":"http://106.14.218.31:8081/img//restaurant//restaurant98-198550116.jpg","address":"杭州市拱墅区浙大网新智慧立方","phone":"18857120152","category":"5","sales":5,"consumption":7,"discount":"满100减200|满100减200","distance":0},{"id":110,"name":"江浙2","images":"http://106.14.218.31:8081/img//restaurant//restaurant110-19834101.jpg","address":"","phone":"18857120152","category":"2","sales":12,"consumption":12,"discount":"满100减200","distance":3116},{"id":107,"name":"餐厅16","images":"http://106.14.218.31:8081/img//restaurant//restaurant107-19822338.jpg","address":"杭州市拱墅区拱墅区行政服务中心","phone":"18857120152","category":"5","sales":12,"consumption":12,"discount":"满100减200","distance":4228},{"id":104,"name":"西餐1","images":"http://106.14.218.31:8081/img//restaurant//restaurant104-19823844.jpg","address":"","phone":"18857120152","category":"5","sales":13,"consumption":13,"discount":"满100减200","distance":4835},{"id":101,"name":"江浙1","images":"http://106.14.218.31:8081/img//restaurant//restaurant101-19812692.jpg","address":"","phone":"18857120152","category":"2","sales":5,"consumption":5,"discount":"满100减200","distance":5152},{"id":106,"name":"餐厅15","images":"http://106.14.218.31:8081/img//restaurant//restaurant106-19818599.jpg","address":"杭州市拱墅区拱墅区住房和城市建设局","phone":"18857120152","category":"5","sales":15,"consumption":15,"discount":"满100减200","distance":6104},{"id":103,"name":"咖啡2","images":"http://106.14.218.31:8081/img//restaurant//restaurant103-19829097.jpg","address":"","phone":"18857120152","category":"3","sales":12,"consumption":12,"discount":"满100减200","distance":7255},{"id":108,"name":"餐厅17","images":"http://106.14.218.31:8081/img//restaurant//restaurant108-198550116.jpg","address":"杭州市拱墅区杭州拱墅万达广场","phone":"18857120152","category":"5","sales":1111,"consumption":1,"discount":"满100减200","distance":7631},{"id":109,"name":"餐厅18","images":"http://106.14.218.31:8081/img//restaurant//restaurant109-19812692.jpg","address":"杭州市拱墅区杭州拱墅万达广场","phone":"18857120152","category":"5","sales":12,"consumption":12,"discount":"满100减200","distance":7631},{"id":100,"name":"餐厅9","images":"http://106.14.218.31:8081/img//restaurant//restaurant100-19812692.jpg","address":"杭州市拱墅区杭州运河广告产业大厦","phone":"18857120152","category":"5","sales":2,"consumption":2,"discount":"满100减200","distance":8466}]
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
         * id : 98
         * name : 餐厅7
         * images : http://106.14.218.31:8081/img//restaurant//restaurant98-198550116.jpg
         * address : 杭州市拱墅区浙大网新智慧立方
         * phone : 18857120152
         * category : 5
         * sales : 5
         * consumption : 7
         * discount : 满100减200|满100减200
         * distance : 0
         */

        private int id;
        private String name;
        private String images;
        private String address;
        private String phone;
        private String category;
        private int sales;
        private int consumption;
        private String discount;
        private int distance;

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
    }
}
