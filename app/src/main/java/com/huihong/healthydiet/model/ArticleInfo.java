package com.huihong.healthydiet.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/8/10
 * 文章列表Item
 */

public class ArticleInfo implements Serializable {

    /**
     * ArticleId : 103
     * title : 文章1
     * content : null
     * TitleImage : http://106.14.218.31:8081/img/article/201720283328.jpg
     * tags : ["爆炒"]
     * ConstitutionPercentage : 30
     * cilckCount : 131
     * loveCount : 34
     * aTime : 2017-07-27T16:32:00
     * url : http://106.14.218.31:8081/../webs/ArticleUrl.aspx?id=103
     * PointPraise : false
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
    private boolean PointPraise;
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

    public boolean isPointPraise() {
        return PointPraise;
    }

    public void setPointPraise(boolean PointPraise) {
        this.PointPraise = PointPraise;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


}
