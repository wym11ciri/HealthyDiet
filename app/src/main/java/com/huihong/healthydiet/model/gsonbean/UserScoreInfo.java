package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.model.httpmodel.RankInfo;

/**
 * Created by zangyi_shuai_ge on 2017/8/16
 */

public class UserScoreInfo extends HttpBaseInfo {

    /**
     * Model1 : {"Current_Score":6.1,"Next_Score":8.9,"Current_Name":"健健康康"}
     */

    private RankInfo Model1;

    public RankInfo getModel1() {
        return Model1;
    }

    public void setModel1(RankInfo Model1) {
        this.Model1 = Model1;
    }



}
