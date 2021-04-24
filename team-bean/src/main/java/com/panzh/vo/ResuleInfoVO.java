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
 * @date: 2020/5/8 17:52
 */
public class ResuleInfoVO implements Serializable {

    private Integer resultId;

    private String resultName;

    private String resultImge;

    private String resultLink;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date resultTime;

    private String userStid;

    private Integer typeId;

    private String resultValue;

    private UserInfo userInfo;

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public String getResultImge() {
        return resultImge;
    }

    public void setResultImge(String resultImge) {
        this.resultImge = resultImge;
    }

    public String getResultLink() {
        return resultLink;
    }

    public void setResultLink(String resultLink) {
        this.resultLink = resultLink;
    }

    public Date getResultTime() {
        return resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
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

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
