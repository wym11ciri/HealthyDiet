package com.huihong.healthydiet.bean;

import com.huihong.healthydiet.model.ArticleInfo;
import com.huihong.healthydiet.model.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/9
 */

public class SearchVagueRestaurant extends HttpBaseInfo{

    /**
     * HttpCode : 200
     * Message : 数据成功返回
     * ListData : [{"id":3,"name":"餐厅2","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant3-19834101.jpg","address":"杭州市拱墅区拱墅区","phone":"18857120152","category":"3","sales":88811,"consumption":251,"discount":"优惠2|优惠3","distance":41262},{"id":91,"name":"餐厅4","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant91-19863208.jpg","address":"杭州市拱墅区北部软件园","phone":"18857120152","category":"5","sales":654,"consumption":321,"discount":"满100减200","distance":39889},{"id":96,"name":"餐厅5","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant96-198197843.jpg","address":"杭州市拱墅区中国智慧信息产业园","phone":"18857120152","category":"5","sales":1,"consumption":2,"discount":"满100减200","distance":41262},{"id":97,"name":"餐厅6","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant97-198144654.jpg","address":"杭州市拱墅区方正荷塘月色","phone":"18857120152","category":"5","sales":111,"consumption":222,"discount":"满100减200","distance":41262},{"id":98,"name":"餐厅7","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant98-198550116.jpg","address":"杭州市拱墅区浙大网新智慧立方","phone":"18857120152","category":"5","sales":5,"consumption":7,"discount":"满100减200|满100减200","distance":32363},{"id":99,"name":"餐厅8","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant99-19823844.jpg","address":"杭州市拱墅区杭州微盘信息技术有限公司","phone":"18857120152","category":"5","sales":111,"consumption":2,"discount":"满100减200","distance":41262},{"id":100,"name":"餐厅9","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant100-19812692.jpg","address":"杭州市拱墅区杭州运河广告产业大厦","phone":"18857120152","category":"5","sales":2,"consumption":2,"discount":"满100减200","distance":40005},{"id":102,"name":"餐厅11","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant102-19818599.jpg","address":"杭州市西湖区西溪印象城","phone":"18857120152","category":"5","sales":6,"consumption":6,"discount":"满100减200","distance":27964},{"id":105,"name":"餐厅14","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant105-198197843.jpg","address":"杭州市拱墅区拱墅皮肤病医院","phone":"18857120152","category":"5","sales":14,"consumption":14,"discount":"满100减200","distance":41262},{"id":106,"name":"餐厅15","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant106-19818599.jpg","address":"杭州市拱墅区拱墅区住房和城市建设局","phone":"18857120152","category":"5","sales":15,"consumption":15,"discount":"满100减200","distance":38331}]
     * ListData2 : [{"ArticleId":103,"title":"文章1","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/201720283328.jpg","tags":["爆炒"],"ConstitutionPercentage":30,"cilckCount":131,"loveCount":34,"aTime":"2017-07-27T16:32:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=103","PointPraise":false},{"ArticleId":104,"title":"文章2","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/20172028365.jpg","tags":["红烧"],"ConstitutionPercentage":50,"cilckCount":27,"loveCount":5,"aTime":"2017-07-27T16:31:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=104","PointPraise":true},{"ArticleId":147,"title":"文章3","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/201720783852.jpg","tags":["爆炒"],"ConstitutionPercentage":30,"cilckCount":35,"loveCount":4,"aTime":"2017-07-27T15:21:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=147","PointPraise":true},{"ArticleId":149,"title":"文章4","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/2017208124738.jpg","tags":["红烧"],"ConstitutionPercentage":50,"cilckCount":25,"loveCount":2,"aTime":"2017-07-27T12:48:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=149","PointPraise":true},{"ArticleId":153,"title":"文章5","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/201720815161.jpg","tags":["乱炖"],"ConstitutionPercentage":50,"cilckCount":15,"loveCount":5,"aTime":"2017-07-27T15:16:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=153","PointPraise":true},{"ArticleId":154,"title":"文章6","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/2017208151627.jpg","tags":["油焖"],"ConstitutionPercentage":80,"cilckCount":14,"loveCount":2,"aTime":"2017-07-27T15:17:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=154","PointPraise":true},{"ArticleId":155,"title":"文章7","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/2017208151651.jpg","tags":["油焖"],"ConstitutionPercentage":80,"cilckCount":11,"loveCount":3,"aTime":"2017-07-27T15:17:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=155","PointPraise":true},{"ArticleId":156,"title":"文章8","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/2017208151724.jpg","tags":["红烧"],"ConstitutionPercentage":50,"cilckCount":3,"loveCount":1,"aTime":"2017-07-27T15:17:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=156","PointPraise":true},{"ArticleId":157,"title":"文章9","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/2017208151747.jpg","tags":["简餐","乱炖"],"ConstitutionPercentage":40,"cilckCount":3,"loveCount":0,"aTime":"2017-07-27T15:18:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=157","PointPraise":false},{"ArticleId":158,"title":"文章10","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/201720815189.jpg","tags":["清蒸","简餐","爆炒","非油炸","简餐"],"ConstitutionPercentage":58,"cilckCount":2,"loveCount":1,"aTime":"2017-07-27T15:18:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=158","PointPraise":true}]
     * ListData3 : [{"id":1,"name":"食谱1","titleImage":"http://106.14.218.31:8081/img//recipe//recipe1-1986270.jpg","ConstitutionPercentage":65,"sales":12,"price":"66.00","Tags":["清蒸","爆炒"],"Restaurant_Name":"餐厅2","Restaurant_Address":"杭州市拱墅区拱墅区","distance":41262,"category":"3"},{"id":2,"name":"食谱21","titleImage":"http://106.14.218.31:8081/img//recipe//recipe2-19829097.jpg","ConstitutionPercentage":50,"sales":2,"price":"99.00","Tags":["红烧"],"Restaurant_Name":"咖啡1","Restaurant_Address":"北京市朝阳区k酷时尚广场","distance":1162574,"category":"3"},{"id":4,"name":"食谱5","titleImage":"http://106.14.218.31:8081/img//recipe//recipe4-19823844.jpg","ConstitutionPercentage":75,"sales":11,"price":"11.00","Tags":["清蒸","红烧"],"Restaurant_Name":"餐厅4","Restaurant_Address":"杭州市拱墅区北部软件园","distance":39889,"category":"5"},{"id":5,"name":"食谱2","titleImage":"http://106.14.218.31:8081/img//recipe//recipe5-19812692.jpg","ConstitutionPercentage":30,"sales":99,"price":"99.00","Tags":["爆炒"],"Restaurant_Name":"餐厅5","Restaurant_Address":"杭州市拱墅区中国智慧信息产业园","distance":41262,"category":"5"},{"id":6,"name":"食谱3","titleImage":"http://106.14.218.31:8081/img//recipe//recipe6-19863208.jpg","ConstitutionPercentage":50,"sales":11,"price":"11.00","Tags":["红烧"],"Restaurant_Name":"餐厅6","Restaurant_Address":"杭州市拱墅区方正荷塘月色","distance":41262,"category":"5"},{"id":7,"name":"食谱4","titleImage":"http://106.14.218.31:8081/img//recipe//recipe7-19818599.jpg","ConstitutionPercentage":100,"sales":11,"price":"11.00","Tags":["清蒸"],"Restaurant_Name":"餐厅7","Restaurant_Address":"杭州市拱墅区浙大网新智慧立方","distance":32363,"category":"5"},{"id":8,"name":"食谱6","titleImage":"http://106.14.218.31:8081/img//recipe//recipe8-198144654.jpg","ConstitutionPercentage":100,"sales":22,"price":"22.00","Tags":["清蒸"],"Restaurant_Name":"餐厅8","Restaurant_Address":"杭州市拱墅区杭州微盘信息技术有限公司","distance":41262,"category":"5"},{"id":9,"name":"食谱7","titleImage":"http://106.14.218.31:8081/img//recipe//recipe9-19813850.jpg","ConstitutionPercentage":30,"sales":22,"price":"11.00","Tags":["爆炒"],"Restaurant_Name":"餐厅9","Restaurant_Address":"杭州市拱墅区杭州运河广告产业大厦","distance":40005,"category":"5"},{"id":10,"name":"食谱8","titleImage":"http://106.14.218.31:8081/img//recipe//recipe10-19823844.jpg","ConstitutionPercentage":100,"sales":22,"price":"11.00","Tags":["清蒸"],"Restaurant_Name":"江浙1","Restaurant_Address":"","distance":33718,"category":"2"},{"id":11,"name":"食谱9","titleImage":"http://106.14.218.31:8081/img//recipe//recipe11-19823844.jpg","ConstitutionPercentage":50,"sales":33,"price":"22.00","Tags":["红烧"],"Restaurant_Name":"江浙1","Restaurant_Address":"","distance":33718,"category":"2"}]
     */


    private List<ListDataBean> ListData;
    private List<ArticleInfo> ListData2;
    private List<ListData3Bean> ListData3;



    public List<ListDataBean> getListData() {
        return ListData;
    }

    public void setListData(List<ListDataBean> ListData) {
        this.ListData = ListData;
    }

    public List<ArticleInfo> getListData2() {
        return ListData2;
    }

    public void setListData2(List<ArticleInfo> ListData2) {
        this.ListData2 = ListData2;
    }

    public List<ListData3Bean> getListData3() {
        return ListData3;
    }

    public void setListData3(List<ListData3Bean> ListData3) {
        this.ListData3 = ListData3;
    }

    public static class ListDataBean {
        /**
         * id : 3
         * name : 餐厅2
         * titleImage : http://106.14.218.31:8081/img//restaurant//restaurant3-19834101.jpg
         * address : 杭州市拱墅区拱墅区
         * phone : 18857120152
         * category : 3
         * sales : 88811
         * consumption : 251
         * discount : 优惠2|优惠3
         * distance : 41262
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



    public static class ListData3Bean {
        /**
         * id : 1
         * name : 食谱1
         * titleImage : http://106.14.218.31:8081/img//recipe//recipe1-1986270.jpg
         * ConstitutionPercentage : 65
         * sales : 12
         * price : 66.00
         * Tags : ["清蒸","爆炒"]
         * Restaurant_Name : 餐厅2
         * Restaurant_Address : 杭州市拱墅区拱墅区
         * distance : 41262
         * category : 3
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
