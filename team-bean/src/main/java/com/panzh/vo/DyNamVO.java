package com.panzh.vo;

import com.panzh.entity.UserInfo;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author: TMingYi
 * @date: 2020/5/9 10:00
 */
public class DyNamVO implements Serializable {

    private Integer dyNamId;

    private String dyNamTitle;

    private String imgeAdd;

    private String userStid;

    private String dyNamValue;

    private UserInfo userInfo;

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
        this.dyNamTitle = dyNamTitle;
    }

    public String getImgeAdd() {
        return imgeAdd;
    }

    public void setImgeAdd(String imgeAdd) {
        this.imgeAdd = imgeAdd;
    }

    public String getUserStid() {
        return userStid;
    }

    public void setUserStid(String userStid) {
        this.userStid = userStid;
    }

    public String getDyNamValue() {
        return dyNamValue;
    }

    public void setDyNamValue(String dyNamValue) {
//        此处判断，不妥
//        if (dyNamValue.length() >=  200){
//            dyNamValue = dyNamValue.substring(0,200);
//        }
        this.dyNamValue = dyNamValue;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
