package com.example.dm.zhbit.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/18.
 */

public class Evaluation extends BmobObject {
    private AppUser commentuser;
    private String evaluate_tea_name;
    private String evaluate_tea_course;
    private String evaluate_project1;
    private Integer project1_score;
    private String evaluate_project2;
    private Integer project2_score;
    private String evaluate_project3;
    private Integer project3_score;
    private String evaluate_project4;
    private Integer project4_score;
    private Integer totalscore;
    private String SuggestionContent;

    private  String id;
    private float avg_score;
    private String rank;
    private Integer evl_plan_num;
    private Integer evl_actual_num;
    private String evl_rate;

    public Evaluation(){
        super();
    }
    public Evaluation(String id, String evaluate_tea_course, String evaluate_tea_name, float avg_score,
                       String rank,Integer evl_plan_num,Integer evl_actual_num,String evl_rate) {
        super();
        this.id = id;
        this.evaluate_tea_course = evaluate_tea_course;
        this.evaluate_tea_name = evaluate_tea_name;
        this.avg_score = avg_score;
        this.rank = rank;
        this.evl_plan_num = evl_plan_num;
        this.evl_actual_num = evl_actual_num;
        this.evl_rate = evl_rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public float getAvg_score() {
        return avg_score;
    }

    public void setAvg_score(float avg_score) {
        this.avg_score = avg_score;
    }
    public String  getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    public Integer getEvl_plan_num() {
        return evl_plan_num;
    }

    public void setEvl_plan_num(Integer evl_plan_num) {
        this.evl_plan_num = evl_plan_num;
    }
    public Integer getEvl_actual_num() {
        return evl_actual_num;
    }

    public void setEvl_actual_num(Integer evl_actual_num) {
        this.evl_actual_num = evl_actual_num;
    }
    public String getEvl_rate() {
        return evl_rate;
    }

    public void setEvl_rate(String evl_rate) {
        this.evl_rate = evl_rate;
    }



    public AppUser getCommentuser() {
        return commentuser;
    }

    public void setCommentuser(AppUser commentuser) {
        this.commentuser = commentuser;
    }
    public String getEvaluate_tea_name() {
        return evaluate_tea_name;
    }

    public void setEvaluate_tea_name(String evaluate_tea_name) {
        this.evaluate_tea_name = evaluate_tea_name;
    }

    public String getEvaluate_tea_course() {
        return evaluate_tea_course;
    }

    public void setEvaluate_tea_course(String evaluate_tea_course) {
        this.evaluate_tea_course = evaluate_tea_course;
    }
    public String getEvaluate_project1() {
        return evaluate_project1;
    }

    public void setEvaluate_project1(String evaluate_project1) {
        this.evaluate_project1 = evaluate_project1;
    }
    public String getEvaluate_project2() {
        return evaluate_project2;
    }

    public void setEvaluate_project2(String evaluate_project2) {
        this.evaluate_project2 = evaluate_project2;
    }
    public String getEvaluate_project3() {
        return evaluate_project3;
    }

    public void setEvaluate_project3(String evaluate_project3) {
        this.evaluate_project3 = evaluate_project3;
    }
    public String getEvaluate_project4() {
        return evaluate_project4;
    }

    public void setEvaluate_project4(String evaluate_project4) {this.evaluate_project4 = evaluate_project4;}
    public Integer getProject4_score() {
        return project4_score;
    }

    public void setProject1_score(Integer project1_score) {
        this.project1_score = project1_score;
    }
    public Integer getProject1_score() {
        return project1_score;
    }
    public Integer getProject2_score() {
        return project2_score;
    }
    public void setProject2_score(Integer project2_score) {
        this.project2_score = project2_score;
    }
    public void setProject3_score(Integer project3_score) {
        this.project3_score = project3_score;
    }
    public Integer getProject3_score() {
        return project3_score;
    }


    public void setProject4_score(Integer project4_score) {
        this.project4_score = project4_score;
    }
    public Integer setProject4_score() {
        return  project4_score;
    }
    public Integer getTotalscore() {
        return totalscore;
    }
    public void setTotalscore(Integer totalscore) {
        this.totalscore = totalscore;
    }




    public String getSuggestionContent() {
        return SuggestionContent;
    }

    public void setSuggestionContent(String SuggestionContent) {
        this.SuggestionContent = SuggestionContent;
    }

}
