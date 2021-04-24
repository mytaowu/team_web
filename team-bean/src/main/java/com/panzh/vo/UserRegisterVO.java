package com.panzh.vo;

import java.io.Serializable;

/**
 * @author: TMingYi
 * @date: 2020/5/8 17:42
 */
public class UserRegisterVO implements Serializable {

    private String userName;

    private String userSex;

    private String userClass;

    private String userCollege;

    private String userStid;

    private String userPass;

    private String userPh;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getUserCollege() {
        return userCollege;
    }

    public void setUserCollege(String userCollege) {
        this.userCollege = userCollege;
    }

    public String getUserStid() {
        return userStid;
    }

    public void setUserStid(String userStid) {
        this.userStid = userStid;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserPh() {
        return userPh;
    }

    public void setUserPh(String userPh) {
        this.userPh = userPh;
    }
}
