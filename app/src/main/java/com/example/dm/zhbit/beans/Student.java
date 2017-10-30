package com.example.dm.zhbit.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/4/13.
 */

public class Student extends BmobObject {
    String stunum;
    String stuname;
    String stusex;
    String stuclass;
    String stumajor;

    public Student(String stunum, String stuname, String stusex,String stumajor,String stuclass) {
        this.stunum= stunum;
        this.stuname = stuname;
        this.stusex = stusex;
        this.stuclass = stuclass;
        this.stumajor= stumajor;
    }
    public Student(){}
    public String getStunum() {
        return stunum;
    }
    public void setStunum(String stunum) {
        stunum= stunum;
    }
    public String getStuname() {
        return stuname;
    }
    public void setStuname(String stuname) {
        stuname = stuname;
    }
    public String getStusex() {
        return stusex;
    }
    public void setStusex(String stusex) {
        stusex = stusex;
    }
    public String getStuclass() {
        return stuclass;
    }
    public void setStuclass(String stuclass) {
        stuclass = stuclass;
    }
    public String getStumajor() {
        return stumajor;
    }
    public void setStumajor(String stumajor) {
        stumajor = stumajor;
    }


}
