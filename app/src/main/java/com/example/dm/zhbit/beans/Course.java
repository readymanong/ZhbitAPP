package com.example.dm.zhbit.beans;


import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/23.
 */

public class Course extends BmobObject {

    public String cname;
    public String ctea;
    public String ccontent;


    public Course(String cname, String ctea, String ccontent) {
        this.cname= cname;
        this.ctea = ctea;
        this.ccontent = ccontent;
    }
    public Course(){}
    public String getCname() {
        return cname;
    }
    public void setCname(String cname) {
        cname = cname;
    }
    public String getCtea() {
        return ctea;
    }
    public void setCtea(String ctea) {
        ctea = ctea;
    }
    public String getCcontent() {
        return ccontent;
    }
    public void setCcontent(String ccontent) {
        ccontent = ccontent;
    }


}
