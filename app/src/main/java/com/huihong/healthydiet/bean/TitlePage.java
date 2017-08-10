package com.huihong.healthydiet.bean;

import com.huihong.healthydiet.model.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/31
 */

public class TitlePage extends HttpBaseInfo {


    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"id":102,"name":"餐厅11","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant102-19818599.jpg","address":"杭州市西湖区西溪印象城","phone":"18857120152","category":"5","sales":6,"consumption":6,"discount":"满100减200","distance":27964},{"id":103,"name":"咖啡2","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant103-19829097.jpg","address":"","phone":"18857120152","category":"3","sales":12,"consumption":12,"discount":"满100减200","distance":31855},{"id":98,"name":"餐厅7","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant98-198550116.jpg","address":"杭州市拱墅区浙大网新智慧立方","phone":"18857120152","category":"5","sales":5,"consumption":7,"discount":"满100减200|满100减200","distance":32363},{"id":104,"name":"西餐1","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant104-19823844.jpg","address":"","phone":"18857120152","category":"5","sales":13,"consumption":13,"discount":"满100减200","distance":32760},{"id":101,"name":"江浙1","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant101-19812692.jpg","address":"","phone":"18857120152","category":"2","sales":5,"consumption":5,"discount":"满100减200","distance":33718},{"id":110,"name":"江浙2","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant110-19834101.jpg","address":"","phone":"18857120152","category":"2","sales":12,"consumption":12,"discount":"满100减200","distance":34797},{"id":107,"name":"餐厅16","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant107-19822338.jpg","address":"杭州市拱墅区拱墅区行政服务中心","phone":"18857120152","category":"5","sales":12,"consumption":12,"discount":"满100减200","distance":36589},{"id":106,"name":"餐厅15","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant106-19818599.jpg","address":"杭州市拱墅区拱墅区住房和城市建设局","phone":"18857120152","category":"5","sales":15,"consumption":15,"discount":"满100减200","distance":38331},{"id":113,"name":"西餐3","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant113-19863208.jpg","address":"杭州市拱墅区祥园路-道路","phone":"18857120152","category":"5","sales":111,"consumption":22,"discount":"满100减200","distance":39103}]
     * ListData2 : [{"id":44,"name":"食谱-餐厅11","titleImage":"http://106.14.218.31:8081/img//recipe//recipe44-21225655.jpg","ConstitutionPercentage":65,"sales":66,"price":"66.00","Tags":["油焖","红烧"],"Restaurant_Name":"餐厅11","Restaurant_Address":"杭州市西湖区西溪印象城","distance":27964,"category":"5"},{"id":45,"name":"食谱-咖啡2","titleImage":"http://106.14.218.31:8081/img//recipe//recipe45-21216321.jpg","ConstitutionPercentage":75,"sales":99,"price":"99.00","Tags":["乱炖","清蒸"],"Restaurant_Name":"咖啡2","Restaurant_Address":"","distance":31855,"category":"3"},{"id":7,"name":"食谱4","titleImage":"http://106.14.218.31:8081/img//recipe//recipe7-19818599.jpg","ConstitutionPercentage":100,"sales":11,"price":"11.00","Tags":["清蒸"],"Restaurant_Name":"餐厅7","Restaurant_Address":"杭州市拱墅区浙大网新智慧立方","distance":32363,"category":"5"},{"id":46,"name":"食谱-西餐1","titleImage":"http://106.14.218.31:8081/img//recipe//recipe46-21224482.jpg","ConstitutionPercentage":40,"sales":222,"price":"11.00","Tags":["简餐","红烧"],"Restaurant_Name":"西餐1","Restaurant_Address":"","distance":32760,"category":"5"},{"id":10,"name":"食谱8","titleImage":"http://106.14.218.31:8081/img//recipe//recipe10-19823844.jpg","ConstitutionPercentage":100,"sales":22,"price":"11.00","Tags":["清蒸"],"Restaurant_Name":"江浙1","Restaurant_Address":"","distance":33718,"category":"2"}]
     * ListData3 : []
     * ListData4 : []
     */


    private List<ListDataBean> ListData;
    private List<ListData2Bean> ListData2;
    private List<?> ListData3;
    private List<?> ListData4;



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

    public List<?> getListData3() {
        return ListData3;
    }

    public void setListData3(List<?> ListData3) {
        this.ListData3 = ListData3;
    }

    public List<?> getListData4() {
        return ListData4;
    }

    public void setListData4(List<?> ListData4) {
        this.ListData4 = ListData4;
    }

    public static class ListDataBean {
        /**
         * id : 102
         * name : 餐厅11
         * titleImage : http://106.14.218.31:8081/img//restaurant//restaurant102-19818599.jpg
         * address : 杭州市西湖区西溪印象城
         * phone : 18857120152
         * category : 5
         * sales : 6
         * consumption : 6
         * discount : 满100减200
         * distance : 27964
         */

        private int id;
        private String name;
        private String titleImage;
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

        public String getTitleImage() {
            return titleImage;
        }

        public void setTitleImage(String titleImage) {
            this.titleImage = titleImage;
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

    public static class ListData2Bean {
        /**
         * id : 44
         * name : 食谱-餐厅11
         * titleImage : http://106.14.218.31:8081/img//recipe//recipe44-21225655.jpg
         * ConstitutionPercentage : 65
         * sales : 66
         * price : 66.00
         * Tags : ["油焖","红烧"]
         * Restaurant_Name : 餐厅11
         * Restaurant_Address : 杭州市西湖区西溪印象城
         * distance : 27964
         * category : 5
         */

        private int id;
        private String name;
        private String titleImage;
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

        public String getTitleImage() {
            return titleImage;
        }

        public void setTitleImage(String titleImage) {
            this.titleImage = titleImage;
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
