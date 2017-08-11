package com.huihong.healthydiet.model.mybean;

/**
 * Created by zangyi_shuai_ge on 2017/7/25
 * 专业测评答案
 */

public class MajorAnswer {
    private int QuestionId;
    private int answer;

    public int getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(int questionId) {
        QuestionId = questionId;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
