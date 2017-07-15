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
     * ListData : [{"id":113,"name":"餐厅22","images":null,"address":"654","phone":"5454","category":"江浙菜","sales":111,"consumption":22,"discount":" 11","distance":1.1825961614213644E7},{"id":96,"name":"餐厅5","images":null,"address":"余杭","phone":"188","category":"西餐","sales":1,"consumption":2,"discount":" 减100","distance":1.1891278499339357E7},{"id":105,"name":"餐厅14","images":null,"address":"14","phone":"14","category":"西餐","sales":14,"consumption":14,"discount":" 14","distance":1.1897140168988906E7},{"id":108,"name":"餐厅17","images":null,"address":"12","phone":"12","category":"西餐","sales":1111,"consumption":1,"discount":" 11","distance":1.1901423500846999E7},{"id":3,"name":"餐厅2","images":["img//restaurant//restaurant3-1964553.jpg"],"address":"拱墅区","phone":"18857120151","category":"咖啡厅","sales":888,"consumption":25,"discount":"优惠2|非常可疑","distance":1.1901423500846999E7},{"id":112,"name":"餐厅21","images":null,"address":"111","phone":"111","category":"西餐","sales":12,"consumption":12,"discount":" 12","distance":1.1907871482317626E7},{"id":103,"name":"餐厅12","images":null,"address":"12","phone":"12","category":"西餐","sales":12,"consumption":12,"discount":" 12","distance":1.1907871482317626E7},{"id":91,"name":"餐厅4","images":null,"address":"下城区","phone":"654613","category":"西餐","sales":654,"consumption":321,"discount":"123|123123","distance":1.1908807138780251E7},{"id":98,"name":"餐厅7","images":null,"address":"2","phone":"3","category":"西餐","sales":5,"consumption":7,"discount":" 22","distance":1.1909603034940908E7},{"id":101,"name":"餐厅10","images":null,"address":"5","phone":"5","category":"西餐","sales":5,"consumption":5,"discount":" 5","distance":1.1909603034940908E7}]
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
         * id : 113
         * name : 餐厅22
         * images : null
         * address : 654
         * phone : 5454
         * category : 江浙菜
         * sales : 111
         * consumption : 22
         * discount :  11
         * distance : 1.1825961614213644E7
         */

        private int id;
        private String name;
        private Object images;
        private String address;
        private String phone;
        private String category;
        private int sales;
        private int consumption;
        private String discount;
        private double distance;

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

        public Object getImages() {
            return images;
        }

        public void setImages(Object images) {
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

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }
    }
}
