package com.panzh.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: TMingYi
 * @date: 2020/5/8 17:40
 */
public class UserInfoVO implements Serializable {
    private Integer userId;

    private String userName;

    private String userSex;

    private Integer userPower;

    private String userClass;

    private String userCollege;

    private String userStid;

    private Integer userUse;

    private String userPh;

    private String usertImge;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date userCreatetime;

    public Integer getUserPower() {
        return userPower;
    }

    public void setUserPower(Integer userPower) {
        this.userPower = userPower;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public Integer getUserUse() {
        return userUse;
    }

    public void setUserUse(Integer userUse) {
        this.userUse = userUse;
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

    public Date getUserCreatetime() {
        return userCreatetime;
    }

    public void setUserCreatetime(Date userCreatetime) {
        this.userCreatetime = userCreatetime;
    }
}
