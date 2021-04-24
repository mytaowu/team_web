package com.panzh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@ApiModel(value = "用户信息表")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "用户Id，自增长", dataType = "Integer")
    private Integer userId;

    @ApiModelProperty(name = "用户名称", dataType = "String")
    private String userName;

    @ApiModelProperty(name = "用户性别", dataType = "String")
    private String userSex;

    @ApiModelProperty(name = "用户年级班级", dataType = "String")
    private String userClass;

    @ApiModelProperty(name = "用户学院", dataType = "String")
    private String userCollege;

    @ApiModelProperty(name = "用户学号（唯一标识）", dataType = "String")
    private String userStid;

    @ApiModelProperty(name = "用户权限（0位普通用户，1为管理员）", dataType = "Integer")
    private Integer userPower;

    @ApiModelProperty(name = "用户密码", dataType = "String")
    private String userPass;

    @ApiModelProperty(name = "用户是否可用（0可用，1禁用）", dataType = "Integer")
    private Integer userUse;

    @ApiModelProperty(name = "用户电话", dataType = "String")
    private String userPh;

    @ApiModelProperty(name = "用户头像", dataType = "String")
    private String usertImge;

    @ApiModelProperty(name = "学习资料分类Id", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date userCreatetime;

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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass == null ? null : userClass.trim();
    }

    public String getUserCollege() {
        return userCollege;
    }

    public void setUserCollege(String userCollege) {
        this.userCollege = userCollege == null ? null : userCollege.trim();
    }

    public String getUserStid() {
        return userStid;
    }

    public void setUserStid(String userStid) {
        this.userStid = userStid == null ? null : userStid.trim();
    }

    public Integer getUserPower() {
        return userPower;
    }

    public void setUserPower(Integer userPower) {
        this.userPower = userPower;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
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
        this.userPh = userPh == null ? null : userPh.trim();
    }

    public String getUsertImge() {
        return usertImge;
    }

    public void setUsertImge(String usertImge) {
        this.usertImge = usertImge == null ? null : usertImge.trim();
    }

    public Date getUserCreatetime() {
        return userCreatetime;
    }

    public void setUserCreatetime(Date userCreatetime) {
        this.userCreatetime = userCreatetime;
    }
}