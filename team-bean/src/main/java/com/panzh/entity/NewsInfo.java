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
@ApiModel(value = "新闻表")
public class NewsInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "新闻表Id", dataType = "Integer")
    private Integer newId;

    @ApiModelProperty(name = "新闻标题", dataType = "String")
    private String newTitle;

    @ApiModelProperty(name = "新闻的日期", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date newTime;

    @ApiModelProperty(name = "新闻图片的地址", dataType = "String")
    private String newImge;

    @ApiModelProperty(name = "新闻访问的次数", dataType = "Integer")
    private Integer newTimes;

    @ApiModelProperty(name = "新闻的发布者学号", dataType = "String")
    private String userStid;

    @ApiModelProperty(name = "新闻的分类Id", dataType = "Integer")
    private Integer typeId;

    @ApiModelProperty(name = "新闻的具体内容", dataType = "String")
    private String newValue;

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
        this.newTitle = newTitle == null ? null : newTitle.trim();
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
        this.newImge = newImge == null ? null : newImge.trim();
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
        this.userStid = userStid == null ? null : userStid.trim();
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
        this.newValue = newValue == null ? null : newValue.trim();
    }


}