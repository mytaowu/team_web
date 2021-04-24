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
@ApiModel(value = "学习资源表")
public class StudyInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "学习资源Id", dataType = "Integer")
    private Integer studyId;

    @ApiModelProperty(name = "学习资源内容（描述）", dataType = "String")
    private String studyValue;

    @ApiModelProperty(name = "学习资源名称（标题）", dataType = "String")
    private String studyName;

    @ApiModelProperty(name = "学习资源图片", dataType = "String")
    private String imgeAdd;

    @ApiModelProperty(name = "学习资源发布日期", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date studyTime;

    @ApiModelProperty(name = "学习资源连接", dataType = "String")
    private String studyLink;

    @ApiModelProperty(name = "学习资源发布者学号", dataType = "String")
    private String userStid;

    @ApiModelProperty(name = "学习资源分类Id", dataType = "Integer")
    private Integer typeId;

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
        this.studyValue = studyValue == null ? null : studyValue.trim();
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName == null ? null : studyName.trim();
    }

    public String getImgeAdd() {
        return imgeAdd;
    }

    public void setImgeAdd(String imgeAdd) {
        this.imgeAdd = imgeAdd == null ? null : imgeAdd.trim();
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
        this.studyLink = studyLink == null ? null : studyLink.trim();
    }

    public String getUserStid() {
        return userStid;
    }

    public void setUserStid(String userStid) {
        this.userStid = userStid == null ? null : userStid.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}