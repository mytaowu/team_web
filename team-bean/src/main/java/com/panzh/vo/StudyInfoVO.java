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
 * @date: 2020/5/8 17:49
 */
public class StudyInfoVO implements Serializable {
    private Integer studyId;
    private String studyValue;
    private String studyName;
    private String imgeAdd;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date studyTime;
    private String studyLink;
    private String userStid;
    private Integer typeId;

    private UserInfo userInfo;

    public Integer getStudyId() {
        return studyId;
    }

    public void setStudyId(Integer studyId) {
        this.studyId = studyId;
    }

    public String getStudyValue() {
        return studyValue;
    }

    public void setStudyValue(String studyValue) {
        this.studyValue = studyValue;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public String getImgeAdd() {
        return imgeAdd;
    }

    public void setImgeAdd(String imgeAdd) {
        this.imgeAdd = imgeAdd;
    }

    public Date getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Date studyTime) {
        this.studyTime = studyTime;
    }

    public String getStudyLink() {
        return studyLink;
    }

    public void setStudyLink(String studyLink) {
        this.studyLink = studyLink;
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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
