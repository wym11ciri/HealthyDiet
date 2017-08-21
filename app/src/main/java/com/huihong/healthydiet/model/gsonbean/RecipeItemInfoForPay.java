package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/24
 */

public class RecipeItemInfoForPay extends HttpBaseInfo{


    private List<ListDataBean> ListData;
    private List<ListData2Bean> ListData2;

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
         * id : 6
         * name : 食谱5
         * ConstitutionPercentage : 0
         * Constitution : ["特禀质","血瘀质","气虚质"]
         * Tags : ["红烧"]
         * images : ["http://106.14.218.31:8081/img//recipe//recipe-201708181705200.jpg","http://106.14.218.31:8081/img//recipe//recipe-201708181705201.jpg","http://106.14.218.31:8081/img//recipe//recipe-201708181705202.jpg"]
         * price : 1.00
         * sales : 2
         * RestaurantId : 100
         */

        private int id;
        private String name;
        private int ConstitutionPercentage;
        private String price;
        private int sales;
        private int RestaurantId;
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

        public int getRestaurantId() {
            return RestaurantId;
        }

        public void setRestaurantId(int RestaurantId) {
            this.RestaurantId = RestaurantId;
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
         * id : 100
         * name : 餐厅9
         * titleImage : http://106.14.218.31:8081/img//restaurant//restaurant100-19812692.jpg
         * address : 杭州市拱墅区杭州运河广告产业大厦
         * category : 5
         * phone : 18857120152
         * distance : 0
         */

        private int id;
        private String name;
        private String titleImage;
        private String address;
        private String category;
        private String phone;
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

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
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
    }
}
