package com.huihong.healthydiet.model.mybean;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/9
 */

public class GetSportList {


    /**
     * HttpCode : 200
     * Message : 数据成功返回
     * ListData : [{"date":"8.1","steps":0},{"date":"8.2","steps":0},{"date":"8.3","steps":0},{"date":"8.4","steps":0},{"date":"8.5","steps":0},{"date":"8.6","steps":0},{"date":"8.7","steps":9000},{"date":"8.8","steps":9000},{"date":"8.9","steps":0}]
     */

    private int HttpCode;
    private String Message;
    private List<ListDataBean> ListData;

    public int getHttpCode() {
        return HttpCode;
    }

    public void setHttpCode(int HttpCode) {
        this.HttpCode = HttpCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public List<ListDataBean> getListData() {
        return ListData;
    }

    public void setListData(List<ListDataBean> ListData) {
        this.ListData = ListData;
    }

    public static class ListDataBean {
        /**
         * date : 8.1
         * steps : 0
         */

        private String date;
        private int steps;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getSteps() {
            return steps;
        }

        public void setSteps(int steps) {
            this.steps = steps;
        }
    }
}
