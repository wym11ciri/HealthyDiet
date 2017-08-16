package com.huihong.healthydiet.model.httpmodel;

/**
 * Created by zangyi_shuai_ge on 2017/8/16
 */

public class RankInfo {
    /**
     * Current_Score : 6.1
     * Next_Score : 8.9
     * Current_Name : 健健康康
     */

    private double Current_Score;
    private double Next_Score;
    private String Current_Name;
    private int Current_Lv;

    public int getCurrent_Lv() {
        return Current_Lv;
    }

    public void setCurrent_Lv(int Current_Lv) {
        this.Current_Lv = Current_Lv;
    }

    public double getCurrent_Score() {
        return Current_Score;
    }

    public void setCurrent_Score(double Current_Score) {
        this.Current_Score = Current_Score;
    }

    public double getNext_Score() {
        return Next_Score;
    }

    public void setNext_Score(double Next_Score) {
        this.Next_Score = Next_Score;
    }

    public String getCurrent_Name() {
        return Current_Name;
    }

    public void setCurrent_Name(String Current_Name) {
        this.Current_Name = Current_Name;
    }

}
