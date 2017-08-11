package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/3
 */

public class GetArticleItemInfo extends HttpBaseInfo{

    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=103","ArticleId":103,"title":"文章1","content":"","TitleImage":"http://106.14.218.31:8081/img/article/201720283328.jpg","tags":["爆炒"],"ConstitutionPercentage":0,"cilckCount":43,"loveCount":35,"aTime":"2017-07-27T16:32:00","PointPraise":false}]
     */


    private List<ListDataBean> ListData;



    public List<ListDataBean> getListData() {
        return ListData;
    }

    public void setListData(List<ListDataBean> ListData) {
        this.ListData = ListData;
    }

    public static class ListDataBean   implements Serializable {
        /**
         * url : http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=103
         * ArticleId : 103
         * title : 文章1
         * content :
         * TitleImage : http://106.14.218.31:8081/img/article/201720283328.jpg
         * tags : ["爆炒"]
         * ConstitutionPercentage : 0
         * cilckCount : 43
         * loveCount : 35
         * aTime : 2017-07-27T16:32:00
         * PointPraise : false
         */

        private int cilckCount;
        private int loveCount;
        private boolean PointPraise;

        public int getCilckCount() {
            return cilckCount;
        }

        public void setCilckCount(int cilckCount) {
            this.cilckCount = cilckCount;
        }

        public int getLoveCount() {
            return loveCount;
        }

        public void setLoveCount(int loveCount) {
            this.loveCount = loveCount;
        }

        public boolean isPointPraise() {
            return PointPraise;
        }

        public void setPointPraise(boolean PointPraise) {
            this.PointPraise = PointPraise;
        }
    }
}
