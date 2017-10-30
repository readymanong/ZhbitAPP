package com.example.dm.zhbit.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/18.
 */

    public class Suggestion extends BmobObject {
        private String comment_tea;
    private String comment_course;
        private String SuggestionContent;

        public String getComment_tea() {
            return comment_tea;
        }

        public void setComment_tea(String comment_tea) {
            this.comment_tea = comment_tea;
        }

        public String getComment_course() {
            return comment_course;
        }

        public void setComment_course(String comment_course) {
            this.comment_course = comment_course;
        }

        public String getSuggestionContent() {
            return SuggestionContent;
        }

        public void setSuggestionContent(String SuggestionContent) {
            this.SuggestionContent = SuggestionContent;
        }
    }

