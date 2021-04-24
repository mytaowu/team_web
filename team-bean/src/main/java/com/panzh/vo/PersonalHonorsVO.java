package com.panzh.vo;

import java.io.Serializable;

/**
 * @author: TMingYi
 * @date: 2020/5/8 17:43
 */
public class PersonalHonorsVO implements Serializable {

    //文章总阅读数
    private Integer articleTotalNum;

    //发布的新闻数
    private Integer newInfoTotalNum;

    //上传的资源数
    private Integer studyInfoTotalNum;

    //个人成果数
    private Integer resuleInfoNum;

    public Integer getArticleTotalNum() {
        return articleTotalNum;
    }

    public void setArticleTotalNum(Integer articleTotalNum) {
        this.articleTotalNum = articleTotalNum;
    }

    public Integer getNewInfoTotalNum() {
        return newInfoTotalNum;
    }

    public void setNewInfoTotalNum(Integer newInfoTotalNum) {
        this.newInfoTotalNum = newInfoTotalNum;
    }

    public Integer getStudyInfoTotalNum() {
        return studyInfoTotalNum;
    }

    public void setStudyInfoTotalNum(Integer studyInfoTotalNum) {
        this.studyInfoTotalNum = studyInfoTotalNum;
    }

    public Integer getResuleInfoNum() {
        return resuleInfoNum;
    }

    public void setResuleInfoNum(Integer resuleInfoNum) {
        this.resuleInfoNum = resuleInfoNum;
    }
}
