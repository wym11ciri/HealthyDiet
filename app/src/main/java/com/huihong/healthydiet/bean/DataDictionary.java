package com.huihong.healthydiet.bean;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/15
 */

public class DataDictionary {

    /**
     * HttpCode : 200
     * Message : null
     * ListData : [{"id":2,"typeValue":"江浙菜"},{"id":3,"typeValue":"咖啡厅"},{"id":5,"typeValue":"西餐"}]
     */

    private int HttpCode;
    private Object Message;
    private List<ListDataBean> ListData;

    public int getHttpCode() {
        return HttpCode;
    }

    public void setHttpCode(int HttpCode) {
        this.HttpCode = HttpCode;
    }

    public Object getMessage() {
        return Message;
    }

    public void setMessage(Object Message) {
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
         * id : 2
         * typeValue : 江浙菜
         */

        private int id;
        private String typeValue;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(String typeValue) {
            this.typeValue = typeValue;
        }
    }
}
