package com.huihong.healthydiet.bean;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/24
 */

public class RecipeItemInfoForPay {

    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"id":7,"name":"食谱4","ConstitutionPercentage":-2147483648,"Constitution":["平和质","气郁质","阴虚质"],"Tags":["清蒸"],"images":["http://106.14.218.31:8081/img//recipe//recipe7-19818599.jpg","http://106.14.218.31:8081/img//recipe//recipe7-19829097.jpg"],"price":"11.00","sales":11}]
     * ListData2 : [{"id":98,"name":"餐厅7","images":["http://106.14.218.31:8081/img//restaurant//restaurant98-198550116.jpg"],"address":"杭州市拱墅区浙大网新智慧立方","phone":"18857120152","distance":0}]
     */

    private int HttpCode;
    private String Message;
    private List<ListDataBean> ListData;
    private List<ListData2Bean> ListData2;

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

    public List<ListData2Bean> getListData2() {
        return ListData2;
    }

    public void setListData2(List<ListData2Bean> ListData2) {
        this.ListData2 = ListData2;
    }

    public static class ListDataBean {
        /**
         * id : 7
         * name : 食谱4
         * ConstitutionPercentage : -2147483648
         * Constitution : ["平和质","气郁质","阴虚质"]
         * Tags : ["清蒸"]
         * images : ["http://106.14.218.31:8081/img//recipe//recipe7-19818599.jpg","http://106.14.218.31:8081/img//recipe//recipe7-19829097.jpg"]
         * price : 11.00
         * sales : 11
         */

        private int id;
        private String name;
        private int ConstitutionPercentage;
        private String price;
        private int sales;
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

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
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
    }

    public static class ListData2Bean {
        /**
         * id : 98
         * name : 餐厅7
         * images : ["http://106.14.218.31:8081/img//restaurant//restaurant98-198550116.jpg"]
         * address : 杭州市拱墅区浙大网新智慧立方
         * phone : 18857120152
         * distance : 0
         */

        private int id;
        private String name;
        private String address;
        private String phone;
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
