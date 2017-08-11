package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.ArticleInfo;
import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/27
 */

public class GetArticleListInfo extends HttpBaseInfo{


    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"ArticleId":103,"title":"文章1","content":"","TitleImage":"http://106.14.218.31:8081/img/article/201720283328.jpg","tags":["爆炒"],"ConstitutionPercentage":30,"cilckCount":0,"loveCount":0,"aTime":"2017-07-27T16:32:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=103","PointPraise":false},{"ArticleId":104,"title":"文章2","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/20172028365.jpg","tags":["红烧"],"ConstitutionPercentage":50,"cilckCount":0,"loveCount":0,"aTime":"2017-07-27T16:31:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=104","PointPraise":false},{"ArticleId":147,"title":"文章3","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/201720783852.jpg","tags":["爆炒"],"ConstitutionPercentage":30,"cilckCount":0,"loveCount":0,"aTime":"2017-07-27T15:21:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=147","PointPraise":false},{"ArticleId":149,"title":"文章4","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/2017208124738.jpg","tags":["红烧"],"ConstitutionPercentage":50,"cilckCount":0,"loveCount":0,"aTime":"2017-07-27T12:48:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=149","PointPraise":false},{"ArticleId":153,"title":"文章5","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/201720815161.jpg","tags":["乱炖"],"ConstitutionPercentage":50,"cilckCount":0,"loveCount":0,"aTime":"2017-07-27T15:16:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=153","PointPraise":false},{"ArticleId":154,"title":"文章6","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/2017208151627.jpg","tags":["油焖"],"ConstitutionPercentage":80,"cilckCount":0,"loveCount":0,"aTime":"2017-07-27T15:17:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=154","PointPraise":false},{"ArticleId":155,"title":"文章7","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/2017208151651.jpg","tags":["油焖"],"ConstitutionPercentage":80,"cilckCount":0,"loveCount":0,"aTime":"2017-07-27T15:17:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=155","PointPraise":false},{"ArticleId":156,"title":"文章8","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/2017208151724.jpg","tags":["红烧"],"ConstitutionPercentage":50,"cilckCount":0,"loveCount":0,"aTime":"2017-07-27T15:17:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=156","PointPraise":false},{"ArticleId":157,"title":"文章9","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/2017208151747.jpg","tags":["简餐","乱炖"],"ConstitutionPercentage":40,"cilckCount":0,"loveCount":0,"aTime":"2017-07-27T15:18:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=157","PointPraise":false},{"ArticleId":158,"title":"文章10","content":null,"TitleImage":"http://106.14.218.31:8081/img/article/201720815189.jpg","tags":["清蒸","简餐","爆炒","非油炸","简餐"],"ConstitutionPercentage":58,"cilckCount":0,"loveCount":0,"aTime":"2017-07-27T15:18:00","url":"http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=158","PointPraise":false}]
     * ListData2 : [{"constitution":"气虚质","SuitEat":"只要是肉就行","NotSuitEat":"别吃素"}]
     */


    private List<ListDataBean> ListData;
    private List<ListData2Bean> ListData2;



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

    public static class ListDataBean extends ArticleInfo {

    }

    public static class ListData2Bean {
        /**
         * constitution : 气虚质
         * SuitEat : 只要是肉就行
         * NotSuitEat : 别吃素
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
