package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.model.httpmodel.PersonalAllInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/29
 */

public class GetUserBodyInfo extends HttpBaseInfo{

    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"UserId":16,"name":"","phone":"15168212330","height":"","weight":"","sex":false,"BirthDay":"0001-01-01T00:00:00","labourIntensity":"","HeadImage":"","constitution":"","score":0,"age":"","Token":null}]
     */


    private List<PersonalAllInfo> ListData;


    public List<PersonalAllInfo> getListData() {
        return ListData;
    }

    public void setListData(List<PersonalAllInfo> ListData) {
        this.ListData = ListData;
    }

}
