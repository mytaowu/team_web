package com.panzh.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ApiModel(value = "新闻标签中间连接表")
public class LabelLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "连接表Id", dataType = "Integer")
    private Integer lableLinkId;

    @ApiModelProperty(name = "标签Id", dataType = "Integer")
    private Integer lableId;

    @ApiModelProperty(name = "新闻Id", dataType = "Integer")
    private Integer newId;

    public Integer getLableLinkId() {
        return lableLinkId;
    }

    public void setLableLinkId(Integer lableLinkId) {
        this.lableLinkId = lableLinkId;
    }

    public Integer getLableId() {
        return lableId;
    }

    public void setLableId(Integer lableId) {
        this.lableId = lableId;
    }

    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
    }
}