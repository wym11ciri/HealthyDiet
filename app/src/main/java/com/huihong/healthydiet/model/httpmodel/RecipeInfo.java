package com.huihong.healthydiet.model.httpmodel;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/10
 * 食谱
 */

public class RecipeInfo {
    /**
     * id : 1
     * name : 食谱1
     * titleImage : http://106.14.218.31:8081/img//recipe//recipe1-1986270.jpg
     * ConstitutionPercentage : 0
     * sales : 12
     * price : 66.00
     * Tags : ["清蒸","爆炒"]
     * Restaurant_Name : 餐厅2
     * Restaurant_Address : 杭州市拱墅区拱墅区
     * distance : null
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
    private Object distance;
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

    public Object getDistance() {
        return distance;
    }

    public void setDistance(Object distance) {
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
