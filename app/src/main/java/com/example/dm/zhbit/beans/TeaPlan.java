package com.example.dm.zhbit.beans;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2017/4/11.
 */

public class TeaPlan extends BmobObject {
    private String tea_name;
    private String teacourse_name;//教授课程
    private BmobFile plan_file;//教学方案文件
    private String teaplan;


public TeaPlan(){
}

    public TeaPlan(String teacourse_name,BmobFile plan_file){
        this.teacourse_name =teacourse_name;
        this.plan_file = plan_file;
    }
    public String getTea_name() {
        return tea_name;
    }

    public void setTea_name(String tea_name) {
        this.tea_name = tea_name;
    }
    public String getTeacourse_name() {
        return teacourse_name;
    }

    public void setTeacourse_name(String teacourse_name) {
        this.teacourse_name = teacourse_name;
    }
    public String getTeaplan() {
        return teaplan;
    }

    public void setTeaplan(String teaplan) {
        this.teaplan = teaplan;
    }

    public BmobFile getPlan_file() {
        return plan_file;
    }

    public void setPlan_file(BmobFile plan_file) {
        this.plan_file = plan_file;
    }
}
