package com.huihong.healthydiet.model.gsonbean;

import com.huihong.healthydiet.model.httpmodel.HttpBaseInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/29
 */

public class Login extends HttpBaseInfo{


    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"UserId":16,"name":"","phone":"15168212330","height":"","weight":"","sex":false,"BirthDay":"0001-01-01T00:00:00","labourIntensity":"","HeadImage":"","constitution":"","score":0,"age":"","Token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJNRDUifQ==.eyJleHAiOiIyMDE3LzgvMSA5OjQ4OjM3IiwiVXNlcklEIjoiMTYiLCJVc2VyTmFtZSI6bnVsbH0=.D69587483D34C63D7E009F06B864D55D"}]
     */


    private List<ListDataBean> ListData;


    public List<ListDataBean> getListData() {
        return ListData;
    }

    public void setListData(List<ListDataBean> ListData) {
        this.ListData = ListData;
    }

    public static class ListDataBean {
        /**
         * UserId : 16
         * name :
         * phone : 15168212330
         * height :
         * weight :
         * sex : false
         * BirthDay : 0001-01-01T00:00:00
         * labourIntensity :
         * HeadImage :
         * constitution :
         * score : 0
         * age :
         * Token : eyJ0eXAiOiJKV1QiLCJhbGciOiJNRDUifQ==.eyJleHAiOiIyMDE3LzgvMSA5OjQ4OjM3IiwiVXNlcklEIjoiMTYiLCJVc2VyTmFtZSI6bnVsbH0=.D69587483D34C63D7E009F06B864D55D
         */

        private int UserId;
        private String name;
        private String phone;
        private String height;
        private String weight;
        private boolean sex;
        private String BirthDay;
        private String labourIntensity;
        private String HeadImage;
        private String constitution;
        private int score;
        private String age;
        private String Token;

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public boolean isSex() {
            return sex;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        public String getBirthDay() {
            return BirthDay;
        }

        public void setBirthDay(String BirthDay) {
            this.BirthDay = BirthDay;
        }

        public String getLabourIntensity() {
            return labourIntensity;
        }

        public void setLabourIntensity(String labourIntensity) {
            this.labourIntensity = labourIntensity;
        }

        public String getHeadImage() {
            return HeadImage;
        }

        public void setHeadImage(String HeadImage) {
            this.HeadImage = HeadImage;
        }

        public String getConstitution() {
            return constitution;
        }

        public void setConstitution(String constitution) {
            this.constitution = constitution;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }
    }
}
