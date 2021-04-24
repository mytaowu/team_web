package com.panzh.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panzh.entity.UserInfo;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: TMingYi
 * @date: 2020/5/8 17:55
 */
public class NewsInfoVO implements Serializable {
    private Integer newId;

    private String newTitle;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date newTime;

    private String newImge;

    private Integer newTimes;

    private String userStid;

    private Integer typeId;

    private String newValue;

    private UserInfo userInfo;

    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public Date getNewTime() {
        return newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
    }

    public String getNewImge() {
        return newImge;
    }

    public void setNewImge(String newImge) {
        this.newImge = newImge;
    }

    public Integer getNewTimes() {
        return newTimes;
    }

    public void setNewTimes(Integer newTimes) {
        this.newTimes = newTimes;
    }

    public String getUserStid() {
        return userStid;
    }

    public void setUserStid(String userStid) {
        this.userStid = userStid;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
