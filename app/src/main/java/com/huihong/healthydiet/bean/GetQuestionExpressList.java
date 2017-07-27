package com.huihong.healthydiet.bean;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/27
 */

public class GetQuestionExpressList {


    /**
     * HttpCode : 200
     * Message :
     * ListData : [{"QuestionId":1,"QuestionContent":"你的精神状况如何，有如下状况吗？","Options":[{"OptionId":207,"OptionContent":"情绪稳定，性格平和"},{"OptionId":208,"OptionContent":"敏感喜欢猜忌"},{"OptionId":209,"OptionContent":"经常闷闷不乐"},{"OptionId":210,"OptionContent":"多愁善感，焦虑不安"},{"OptionId":211,"OptionContent":"精神总是紧张"},{"OptionId":212,"OptionContent":"容易疲劳"},{"OptionId":213,"OptionContent":"没有或不知道"}]},{"QuestionId":13,"QuestionContent":"你的大小便状况如何？有如下状况吗？","Options":[{"OptionId":14,"OptionContent":"便秘或大便干燥"},{"OptionId":16,"OptionContent":"大便溏薄或者黏腻、不成形"},{"OptionId":18,"OptionContent":"喝了冰的东西容易腹泻"},{"OptionId":19,"OptionContent":"没有或不知道"}]},{"QuestionId":55,"QuestionContent":"你的五官状况如何，有如下状况吗？","Options":[{"OptionId":56,"OptionContent":"眼睛经常出现红疹发痒和血丝"},{"OptionId":57,"OptionContent":"经常生痤疮"},{"OptionId":58,"OptionContent":"嘴里有异味"},{"OptionId":59,"OptionContent":"经常长痘痘"},{"OptionId":60,"OptionContent":"女性白带颜色发黄"},{"OptionId":61,"OptionContent":"容易呼吸短促，接不上气"},{"OptionId":62,"OptionContent":"没有或不知道"}]},{"QuestionId":63,"QuestionContent":"你的皮肤状况如何，有如下状况吗？","Options":[{"OptionId":64,"OptionContent":"皮肤一抓就红"},{"OptionId":65,"OptionContent":"容易出汗"},{"OptionId":66,"OptionContent":"面颊潮红或偏红"},{"OptionId":67,"OptionContent":"肌肤不滋润，干燥"},{"OptionId":68,"OptionContent":"没有或不知道"}]},{"QuestionId":69,"QuestionContent":"你的适应能力抵抗力如何？","Options":[{"OptionId":70,"OptionContent":"无故鼻塞流涕、打喷嚏"},{"OptionId":71,"OptionContent":"容易过敏"},{"OptionId":72,"OptionContent":"身体莫名其妙疼痛"},{"OptionId":73,"OptionContent":"经常手脚发凉"},{"OptionId":74,"OptionContent":"没有或不知道"}]},{"QuestionId":75,"QuestionContent":"你身体的整体状况如何？","Options":[{"OptionId":76,"OptionContent":"精力充沛"},{"OptionId":77,"OptionContent":"面色红润、目光有神"},{"OptionId":78,"OptionContent":"食欲很好"},{"OptionId":79,"OptionContent":"容易感冒"},{"OptionId":80,"OptionContent":"经常头昏眼花"},{"OptionId":81,"OptionContent":"经常上火，口干舌燥"},{"OptionId":82,"OptionContent":"经常腰酸腿软"},{"OptionId":83,"OptionContent":"没有或不知道"}]},{"QuestionId":84,"QuestionContent":"你还有哪些状况？","Options":[{"OptionId":85,"OptionContent":"总是觉得很累"},{"OptionId":86,"OptionContent":"特别容易掉头发"},{"OptionId":87,"OptionContent":"脸色晦暗、长色斑"},{"OptionId":88,"OptionContent":"额头总是油油的"},{"OptionId":89,"OptionContent":"嘴巴里有黏的感觉"},{"OptionId":90,"OptionContent":"女性朋友白带多"},{"OptionId":91,"OptionContent":"没有或不知道"}]}]
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
         * QuestionId : 1
         * QuestionContent : 你的精神状况如何，有如下状况吗？
         * Options : [{"OptionId":207,"OptionContent":"情绪稳定，性格平和"},{"OptionId":208,"OptionContent":"敏感喜欢猜忌"},{"OptionId":209,"OptionContent":"经常闷闷不乐"},{"OptionId":210,"OptionContent":"多愁善感，焦虑不安"},{"OptionId":211,"OptionContent":"精神总是紧张"},{"OptionId":212,"OptionContent":"容易疲劳"},{"OptionId":213,"OptionContent":"没有或不知道"}]
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
             * OptionId : 207
             * OptionContent : 情绪稳定，性格平和
             */

            private int OptionId;
            private String OptionContent;
            private boolean isClick=false;

            public boolean isClick() {
                return isClick;
            }

            public void setClick(boolean click) {
                isClick = click;
            }

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
