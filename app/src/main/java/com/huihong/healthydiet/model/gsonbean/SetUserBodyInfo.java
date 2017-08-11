package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;
import com.huihong.healthydiet.model.httpmodel.PersonalAllInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/28
 */

public class SetUserBodyInfo extends HttpBaseInfo{

    /**
     * HttpCode : 200
     * Message : 修改成功
     * ListData : [{"UserId":16,"name":"臧艺","phone":"15168212330","height":"173","weight":"22","sex":true,"BirthDay":"2017-07-16T00:00:00","labourIntensity":"优秀","HeadImage":null,"constitution":"气虚质","score":0,"age":"0","Token":null}]
     */
    private List<PersonalAllInfo> ListData;
    public List<PersonalAllInfo> getListData() {
        return ListData;
    }
    public void setListData(List<PersonalAllInfo> ListData) {
        this.ListData = ListData;
    }

}
