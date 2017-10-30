package com.example.dm.zhbit.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/17.
 */

public class TeacherMessage extends BmobObject {
    private String tid;
    private String tname;
    private String tsex;
    private String tcourse;
    private String tintroduce;

    public TeacherMessage() {
    }

    public TeacherMessage(String tid, String tname, String tsex, String tcourse, String tintroduce) {
        this.tid = tid;
        this.tname = tname;
        this.tsex = tsex;
        this.tcourse = tcourse;
        this.tintroduce = tintroduce;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public void setTsex(String tsex) {
        this.tsex = tsex;
    }

    public void setTcourse(String tcourse) {
        this.tcourse = tcourse;
    }

    public void setTintroduce(String tintroduce) {
        this.tintroduce = tintroduce;
    }

    public String getTid() {

        return tid;
    }

    public String getTname() {

        return tname;
    }

    public String getTsex() {

        return tsex;
    }

    public String getTcourse() {

        return tcourse;
    }

    public String getTintroduce() {

        return tintroduce;
    }

}
