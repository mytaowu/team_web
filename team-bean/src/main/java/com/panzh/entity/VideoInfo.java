package com.panzh.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ApiModel(value = "视频信息表")
public class VideoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "视频的编号", dataType = "Integer")
    private Integer videoId;

    @ApiModelProperty(name = "视频的编号", dataType = "String")
    private String videoName;

    @ApiModelProperty(name = "视频的地址", dataType = "String")
    private String videoAdderss;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
    }

    public String getVideoAdderss() {
        return videoAdderss;
    }

    public void setVideoAdderss(String videoAdderss) {
        this.videoAdderss = videoAdderss == null ? null : videoAdderss.trim();
    }
}