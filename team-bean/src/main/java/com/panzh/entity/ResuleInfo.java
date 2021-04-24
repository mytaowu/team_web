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
@ApiModel(value = "用户成果表")
public class ResuleInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "成果表Id", dataType = "Integer")
    private Integer resultId;

    @ApiModelProperty(name = "成果名称", dataType = "String")
    private String resultName;

    @ApiModelProperty(name = "成果图片", dataType = "String")
    private String resultImge;

    @ApiModelProperty(name = "成果连接", dataType = "String")
    private String resultLink;

    @ApiModelProperty(name = "成果发布时间", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date resultTime;

    @ApiModelProperty(name = "成果发布者的学号", dataType = "String")
    private String userStid;

    @ApiModelProperty(name = "成果分类Id", dataType = "Integer")
    private Integer typeId;

    @ApiModelProperty(name = "成果内容", dataType = "String")
    private String resultValue;

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
        this.resultName = resultName == null ? null : resultName.trim();
    }

    public String getResultImge() {
        return resultImge;
    }

    public void setResultImge(String resultImge) {
        this.resultImge = resultImge == null ? null : resultImge.trim();
    }

    public String getResultLink() {
        return resultLink;
    }

    public void setResultLink(String resultLink) {
        this.resultLink = resultLink == null ? null : resultLink.trim();
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
        this.userStid = userStid == null ? null : userStid.trim();
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
        this.resultValue = resultValue == null ? null : resultValue.trim();
    }
}