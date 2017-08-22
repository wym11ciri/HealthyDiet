package com.huihong.healthydiet.model.mybean;

/**
 * Created by zangyi_shuai_ge on 2017/8/22
 */

public class LevelInfo {
    private String currentScore;
    private String nextScore;
    private String currentName;
    private int currentLv;

    public String getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(String currentScore) {
        this.currentScore = currentScore;
    }

    public String getNextScore() {
        return nextScore;
    }

    public void setNextScore(String nextScore) {
        this.nextScore = nextScore;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public int getCurrentLv() {
        return currentLv;
    }

    public void setCurrentLv(int currentLv) {
        this.currentLv = currentLv;
    }
}
