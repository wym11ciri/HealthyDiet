package com.huihong.healthydiet.model;

/**
 * Created by zangyi_shuai_ge on 2017/8/10
 * can
 */

public class RestaurantInfo {

    /**
     * id : 113
     * name : 西餐3
     * titleImage : http://106.14.218.31:8081/img//restaurant//restaurant113-19863208.jpg
     * address : 杭州市拱墅区祥园路-道路
     * phone : 18857120152
     * category : 5
     * sales : 111
     * consumption : 22
     * discount : 满100减200
     * distance : 0
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
