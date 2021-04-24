package com.panzh.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panzh.entity.UserInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: TMingYi
 * @date: 2020/5/8 17:55
 */
public class SimpleNewsInfoVO implements Serializable {
    private Integer newId;

    private String newTitle;

    private String newImge;

    private Integer newTimes;

    public SimpleNewsInfoVO() {

    }

    public SimpleNewsInfoVO(Integer newId, String newTitle, String newImge, Integer newTimes) {
        this.newId = newId;
        this.newTitle = newTitle;
        this.newImge = newImge;
        this.newTimes = newTimes;
    }

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
}
