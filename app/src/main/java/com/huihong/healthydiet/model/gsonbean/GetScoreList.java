package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/15
 */

public class GetScoreList extends HttpBaseInfo{


    private List<ListDataBean> ListData;

    public List<ListDataBean> getListData() {
        return ListData;
    }

    public void setListData(List<ListDataBean> ListData) {
        this.ListData = ListData;
    }

    public static class ListDataBean {
        /**
         * ScoreId : 3
         * ScoreType : sleep
         * ScoreNum : 0.5
         * Time : 2017-08-15T00:00:00
         * Content : 在23点之前睡觉
         */

        private int ScoreId;
        private String ScoreType;
        private double ScoreNum;
        private String Time;
        private String Content;

        public int getScoreId() {
            return ScoreId;
        }

        public void setScoreId(int ScoreId) {
            this.ScoreId = ScoreId;
        }

        public String getScoreType() {
            return ScoreType;
        }

        public void setScoreType(String ScoreType) {
            this.ScoreType = ScoreType;
        }

        public double getScoreNum() {
            return ScoreNum;
        }

        public void setScoreNum(double ScoreNum) {
            this.ScoreNum = ScoreNum;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }
    }
}
