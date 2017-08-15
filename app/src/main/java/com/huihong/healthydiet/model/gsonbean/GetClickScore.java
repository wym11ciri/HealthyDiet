package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.model.httpmodel.LeafInfo;

/**
 * Created by zangyi_shuai_ge on 2017/8/15
 */

public class GetClickScore extends HttpBaseInfo {

    /**
     * HttpCode : 200
     * Message : 数据成功返回
     * Model1 : {"Score":1.1,"ListId":",20","ScoreType":"sleep","ScoreContent":null}
     * Model2 : {"Score":1.1,"ListId":",20","ScoreType":"sleep","ScoreContent":null}
     * Model3 : {"Score":1.1,"ListId":",20","ScoreType":"sleep","ScoreContent":null}
     */

    private LeafInfo Model1;
    private LeafInfo Model2;
    private LeafInfo Model3;

    public LeafInfo getModel1() {
        return Model1;
    }

    public void setModel1(LeafInfo Model1) {
        this.Model1 = Model1;
    }

    public LeafInfo getModel2() {
        return Model2;
    }

    public void setModel2(LeafInfo Model2) {
        this.Model2 = Model2;
    }

    public LeafInfo getModel3() {
        return Model3;
    }

    public void setModel3(LeafInfo Model3) {
        this.Model3 = Model3;
    }

}
