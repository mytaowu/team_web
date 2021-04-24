package com.panzh.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@ApiModel(value = "用户动态类")
public class DyNam implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "动态Id", dataType = "Integer")
    private Integer dyNamId;

    @ApiModelProperty(name = "动态标题", dataType = "String")
    private String dyNamTitle;

    @ApiModelProperty(name = "动态图片地址", dataType = "String")
    private String imgeAdd;

    @ApiModelProperty(name = "发布动态的作者", dataType = "String")
    private String userStid;

    @ApiModelProperty(name = "动态的内容", dataType = "String")
    private String dyNamValue;

    public Integer getDyNamId() {
        return dyNamId;
    }

    public void setDyNamId(Integer dyNamId) {
        this.dyNamId = dyNamId;
    }

    public String getDyNamTitle() {
        return dyNamTitle;
    }

    public void setDyNamTitle(String dyNamTitle) {
        this.dyNamTitle = dyNamTitle == null ? null : dyNamTitle.trim();
    }

    public String getImgeAdd() {
        return imgeAdd;
    }

    public void setImgeAdd(String imgeAdd) {
        this.imgeAdd = imgeAdd == null ? null : imgeAdd.trim();
    }

    public String getUserStid() {
        return userStid;
    }

    public void setUserStid(String userStid) {
        this.userStid = userStid == null ? null : userStid.trim();
    }

    public String getDyNamValue() {
        return dyNamValue;
    }

    public void setDyNamValue(String dyNamValue) {
        this.dyNamValue = dyNamValue == null ? null : dyNamValue.trim();
    }
}