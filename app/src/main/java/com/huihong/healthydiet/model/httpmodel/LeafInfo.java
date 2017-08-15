package com.huihong.healthydiet.model.httpmodel;

/**
 * Created by zangyi_shuai_ge on 2017/8/15
 */

public class LeafInfo {
    /**
     * Score : 1.1
     * ListId : ,20
     * ScoreType : sleep
     * ScoreContent : null
     */

    private double Score;
    private String ListId;
    private String ScoreType;
    private String ScoreContent;

    public double getScore() {
        return Score;
    }

    public void setScore(double Score) {
        this.Score = Score;
    }

    public String getListId() {
        return ListId;
    }

    public void setListId(String ListId) {
        this.ListId = ListId;
    }

    public String getScoreType() {
        return ScoreType;
    }

    public void setScoreType(String ScoreType) {
        this.ScoreType = ScoreType;
    }

    public String getScoreContent() {
        return ScoreContent;
    }

    public void setScoreContent(String ScoreContent) {
        this.ScoreContent = ScoreContent;
    }
}
