package com.example.dm.zhbit.beans;

import android.widget.EditText;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/5/7.
 */

public class SelectCourse  extends BmobObject {
    AppUser select_user;
    String select_tea;
    String select_cou;

    public  AppUser getSelect_user(){return  select_user;}
    public void setSelect_user(AppUser select_user){
        this.select_user=select_user;
    }
    public String getSelect_tea() {
        return select_tea;
    }

    public void setSelect_tea(String select_tea) {
        this.select_tea = select_tea;
    }
    public String getSelect_cou() {
        return select_cou;
    }

    public void setSelect_cou(String select_cou) {
        this.select_cou = select_cou;
    }

}
