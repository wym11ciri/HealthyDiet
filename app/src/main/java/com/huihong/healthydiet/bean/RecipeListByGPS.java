package com.huihong.healthydiet.bean;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/24
 */

public class RecipeListByGPS {


    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"id":1,"name":"食谱1","images":"http://106.14.218.31:8081/img//recipe//recipe1-1986270.jpg","ConstitutionPercentage":-2147483648,"sales":12,"price":"66.00","Tags":["清蒸","爆炒"],"Restaurant_Name":"餐厅2","Restaurant_Address":"杭州市拱墅区拱墅区","distance":8949,"category":"3"},{"id":5,"name":"食谱2","images":"http://106.14.218.31:8081/img//recipe//recipe5-19812692.jpg","ConstitutionPercentage":-2147483648,"sales":99,"price":"99.00","Tags":["爆炒"],"Restaurant_Name":"餐厅5","Restaurant_Address":"杭州市拱墅区中国智慧信息产业园","distance":8949,"category":"5"},{"id":6,"name":"食谱3","images":"http://106.14.218.31:8081/img//recipe//recipe6-19863208.jpg","ConstitutionPercentage":-2147483648,"sales":11,"price":"11.00","Tags":["红烧"],"Restaurant_Name":"餐厅6","Restaurant_Address":"杭州市拱墅区方正荷塘月色","distance":8949,"category":"5"},{"id":8,"name":"食谱6","images":"http://106.14.218.31:8081/img//recipe//recipe8-198144654.jpg","ConstitutionPercentage":-2147483648,"sales":22,"price":"22.00","Tags":["清蒸"],"Restaurant_Name":"餐厅8","Restaurant_Address":"杭州市拱墅区杭州微盘信息技术有限公司","distance":8949,"category":"5"},{"id":2,"name":"食谱21","images":"http://106.14.218.31:8081/img//recipe//recipe2-19829097.jpg","ConstitutionPercentage":-2147483648,"sales":2,"price":"99.00","Tags":["红烧"],"Restaurant_Name":"咖啡1","Restaurant_Address":"北京市朝阳区k酷时尚广场","distance":1137394,"category":"3"}]
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
         * images : http://106.14.218.31:8081/img//recipe//recipe1-1986270.jpg
         * ConstitutionPercentage : -2147483648
         * sales : 12
         * price : 66.00
         * Tags : ["清蒸","爆炒"]
         * Restaurant_Name : 餐厅2
         * Restaurant_Address : 杭州市拱墅区拱墅区
         * distance : 8949
         * category : 3
         */

        private int id;
        private String name;
        private String images;
        private int ConstitutionPercentage;
        private int sales;
        private String price;
        private String Restaurant_Name;
        private String Restaurant_Address;
        private int distance;
        private String category;
        private List<String> Tags;

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

        public int getConstitutionPercentage() {
            return ConstitutionPercentage;
        }

        public void setConstitutionPercentage(int ConstitutionPercentage) {
            this.ConstitutionPercentage = ConstitutionPercentage;
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

        public String getRestaurant_Name() {
            return Restaurant_Name;
        }

        public void setRestaurant_Name(String Restaurant_Name) {
            this.Restaurant_Name = Restaurant_Name;
        }

        public String getRestaurant_Address() {
            return Restaurant_Address;
        }

        public void setRestaurant_Address(String Restaurant_Address) {
            this.Restaurant_Address = Restaurant_Address;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public List<String> getTags() {
            return Tags;
        }

        public void setTags(List<String> Tags) {
            this.Tags = Tags;
        }
    }
}
