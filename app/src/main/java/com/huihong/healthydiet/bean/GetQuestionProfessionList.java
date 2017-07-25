package com.huihong.healthydiet.bean;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/25
 */

public class GetQuestionProfessionList {

    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"QuestionId":12,"QuestionContent":"你精力充沛吗？","Options":[{"OptionId":-1,"OptionContent":"完全不"},{"OptionId":-2,"OptionContent":"有一点"},{"OptionId":-3,"OptionContent":"非常"}]},{"QuestionId":93,"QuestionContent":"你是否经常面色红润、目光有神","Options":[{"OptionId":-1,"OptionContent":"完全不"},{"OptionId":-2,"OptionContent":"有一点"},{"OptionId":-3,"OptionContent":"非常"}]}]
     */

    private int HttpCode;
    private String Message;
    private List<ListDataBean> ListData;

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

    public static class ListDataBean {
        /**
         * QuestionId : 12
         * QuestionContent : 你精力充沛吗？
         * Options : [{"OptionId":-1,"OptionContent":"完全不"},{"OptionId":-2,"OptionContent":"有一点"},{"OptionId":-3,"OptionContent":"非常"}]
         */

        private int QuestionId;
        private String QuestionContent;
        private List<OptionsBean> Options;

        public int getQuestionId() {
            return QuestionId;
        }

        public void setQuestionId(int QuestionId) {
            this.QuestionId = QuestionId;
        }

        public String getQuestionContent() {
            return QuestionContent;
        }

        public void setQuestionContent(String QuestionContent) {
            this.QuestionContent = QuestionContent;
        }

        public List<OptionsBean> getOptions() {
            return Options;
        }

        public void setOptions(List<OptionsBean> Options) {
            this.Options = Options;
        }

        public static class OptionsBean {
            /**
             * OptionId : -1
             * OptionContent : 完全不
             */

            private int OptionId;
            private String OptionContent;

            public int getOptionId() {
                return OptionId;
            }

            public void setOptionId(int OptionId) {
                this.OptionId = OptionId;
            }

            public String getOptionContent() {
                return OptionContent;
            }

            public void setOptionContent(String OptionContent) {
                this.OptionContent = OptionContent;
            }
        }
    }
}
