package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

/**
 * Created by zangyi_shuai_ge on 2017/7/28
 */

public class GetSubmitExpressQuestion extends HttpBaseInfo {

    /**
     * Model1 : 特禀体质的人会出现打喷嚏、流清涕，就是因为卫气虚不能抵御外邪所致。中医认为，“肾为先天之本”，特禀质养生时就应以健脾、补肾气为主，以增强卫外功能。特禀质，这是一类体质特殊的人群。 ⑤对外界环境适应能力：适应能力差，如过敏体质者对过敏季节适应能力差，易引发宿疾。 体质分析 由于先天禀赋不足、遗传等因素，或环境因素，药物因素等的不同影响，故特异质的形体特征、心理特征、常见表现、发病倾向等方面存在诸多差异，病机各异。
     */

    private String Model1;

    public String getModel1() {
        return Model1;
    }

    public void setModel1(String Model1) {
        this.Model1 = Model1;
    }
}
