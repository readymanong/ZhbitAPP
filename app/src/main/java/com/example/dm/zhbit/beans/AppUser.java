package com.example.dm.zhbit.beans;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;

/**
 * app用户javabeans
 */
public class AppUser extends BmobUser implements Serializable {
    private String userAvatarUrl;
    private String userLoginnum;
    private String userName;
    private String userMessage;
    private String userRole;
    public String getUserLoginnum() {
        return userLoginnum;
    }

    public void setUserLoginnum(String userLoginnum) {
        this.userLoginnum = userLoginnum;
    }
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }


    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
