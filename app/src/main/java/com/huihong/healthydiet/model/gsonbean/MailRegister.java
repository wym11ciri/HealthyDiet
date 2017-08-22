package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.model.httpmodel.PersonalAllInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/27
 */

public class MailRegister extends HttpBaseInfo{


    private List<PersonalAllInfo> ListData;

    public List<PersonalAllInfo> getListData() {
        return ListData;
    }

    public void setListData(List<PersonalAllInfo> ListData) {
        this.ListData = ListData;
    }


}
