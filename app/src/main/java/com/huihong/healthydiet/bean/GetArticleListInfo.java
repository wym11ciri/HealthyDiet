package com.huihong.healthydiet.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/27
 */

public class GetArticleListInfo {


    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"ArticleId":103,"title":"三大跑","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/201720283328.jpg","tags":["爆炒"],"ConstitutionPercentage":-2147483648,"cilckCount":0,"loveCount":0,"aTime":"2017-07-21T08:34:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=103"},{"ArticleId":104,"title":"葡式蛋挞","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/20172028365.jpg","tags":["红烧"],"ConstitutionPercentage":-2147483648,"cilckCount":0,"loveCount":0,"aTime":"2017-07-21T08:36:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=104"},{"ArticleId":147,"title":"第四篇","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/201720783852.jpg","tags":["爆炒"],"ConstitutionPercentage":-2147483648,"cilckCount":0,"loveCount":0,"aTime":"2017-07-26T08:39:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=147"},{"ArticleId":149,"title":"文章4","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/2017208124738.jpg","tags":["红烧"],"ConstitutionPercentage":-2147483648,"cilckCount":0,"loveCount":0,"aTime":"2017-07-27T12:48:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=149"}]
     * ListData2 : [{"constitution":"平和质","SuitEat":"","NotSuitEat":""}]
     */

    private int HttpCode;
    private String Message;
    private List<ListDataBean> ListData;
    private List<ListData2Bean> ListData2;

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

    public List<ListData2Bean> getListData2() {
        return ListData2;
    }

    public void setListData2(List<ListData2Bean> ListData2) {
        this.ListData2 = ListData2;
    }

    public static class ListDataBean implements Serializable {
        /**
         * ArticleId : 103
         * title : 三大跑
         * content : null
         * TitleImage : http://106.14.218.31:8081/img/article/201720283328.jpg
         * tags : ["爆炒"]
         * ConstitutionPercentage : -2147483648
         * cilckCount : 0
         * loveCount : 0
         * aTime : 2017-07-21T08:34:00
         * url : http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=103
         */

        private int ArticleId;
        private String title;
        private Object content;
        private String TitleImage;
        private int ConstitutionPercentage;
        private int cilckCount;
        private int loveCount;
        private String aTime;
        private String url;
        private List<String> tags;

        public int getArticleId() {
            return ArticleId;
        }

        public void setArticleId(int ArticleId) {
            this.ArticleId = ArticleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String getTitleImage() {
            return TitleImage;
        }

        public void setTitleImage(String TitleImage) {
            this.TitleImage = TitleImage;
        }

        public int getConstitutionPercentage() {
            return ConstitutionPercentage;
        }

        public void setConstitutionPercentage(int ConstitutionPercentage) {
            this.ConstitutionPercentage = ConstitutionPercentage;
        }

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

        public String getATime() {
            return aTime;
        }

        public void setATime(String aTime) {
            this.aTime = aTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }

    public static class ListData2Bean {
        /**
         * constitution : 平和质
         * SuitEat :
         * NotSuitEat :
         */

        private String constitution;
        private String SuitEat;
        private String NotSuitEat;

        public String getConstitution() {
            return constitution;
        }

        public void setConstitution(String constitution) {
            this.constitution = constitution;
        }

        public String getSuitEat() {
            return SuitEat;
        }

        public void setSuitEat(String SuitEat) {
            this.SuitEat = SuitEat;
        }

        public String getNotSuitEat() {
            return NotSuitEat;
        }

        public void setNotSuitEat(String NotSuitEat) {
            this.NotSuitEat = NotSuitEat;
        }
    }
}
