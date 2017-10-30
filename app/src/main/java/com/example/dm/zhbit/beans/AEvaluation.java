package com.example.dm.zhbit.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/5/3.
 */

public class AEvaluation extends BmobObject {
    private String evaluate_tea_name;
    private String evaluate_tea_course;
    private  String id;
    private String avg_score;
    private String rank;
    private String evl_plan_num;
    private String evl_actual_num;
    private String evl_rate;
public  AEvaluation()
{

}
    public AEvaluation(String id, String evaluate_tea_course, String evaluate_tea_name, String avg_score,
                      String rank,String evl_plan_num,String evl_actual_num,String evl_rate) {
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAvg_score() {
        return avg_score;
    }

    public void setAvg_score(String avg_score) {
        this.avg_score = avg_score;
    }
    public String  getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getEvl_plan_num() {
        return evl_plan_num;
    }

    public void setEvl_plan_num(String evl_plan_num) {
        this.evl_plan_num = evl_plan_num;
    }
    public String getEvl_actual_num() {
        return evl_actual_num;
    }

    public void setEvl_actual_num(String evl_actual_num) {
        this.evl_actual_num = evl_actual_num;
    }
    public String getEvl_rate() {
        return evl_rate;
    }

    public void setEvl_rate(String evl_rate) {
        this.evl_rate = evl_rate;
    }


}
