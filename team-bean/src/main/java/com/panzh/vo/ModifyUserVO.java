package com.panzh.vo;

import java.io.Serializable;

/**
 * @author: TMingYi
 * @date: 2020/5/8 17:47
 */
public class ModifyUserVO implements Serializable {

    private Integer userId;

    private String userClass;

    private String userCollege;

    private String userPh;

    private String usertImge;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getUserPh() {
        return userPh;
    }

    public void setUserPh(String userPh) {
        this.userPh = userPh;
    }

    public String getUsertImge() {
        return usertImge;
    }

    public void setUsertImge(String usertImge) {
        this.usertImge = usertImge;
    }
}
