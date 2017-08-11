package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/24
 */

public class RecipeItemInfoForPay extends HttpBaseInfo{


    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"id":8,"name":"食谱6","ConstitutionPercentage":30,"Constitution":["气虚质","阴虚质","痰湿质"],"Tags":["清蒸"],"images":["http://106.14.218.31:8081/img//recipe//recipe8-198144654.jpg","http://106.14.218.31:8081/img//recipe//recipe8-198197843.jpg"],"price":"22.00","sales":22}]
     * ListData2 : [{"id":99,"name":"餐厅8","titleImage":"http://106.14.218.31:8081/img//restaurant//restaurant99-19823844.jpg","address":"杭州市拱墅区杭州微盘信息技术有限公司","phone":"18857120152","distance":0}]
     */


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
         * id : 8
         * name : 食谱6
         * ConstitutionPercentage : 30
         * Constitution : ["气虚质","阴虚质","痰湿质"]
         * Tags : ["清蒸"]
         * images : ["http://106.14.218.31:8081/img//recipe//recipe8-198144654.jpg","http://106.14.218.31:8081/img//recipe//recipe8-198197843.jpg"]
         * price : 22.00
         * sales : 22
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
         * id : 99
         * name : 餐厅8
         * titleImage : http://106.14.218.31:8081/img//restaurant//restaurant99-19823844.jpg
         * address : 杭州市拱墅区杭州微盘信息技术有限公司
         * phone : 18857120152
         * distance : 0
         */

        private int id;
        private String name;
        private String titleImage;
        private String address;
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
