package com.panzh.util;

import com.panzh.vo.PersonalHonorsVO;
import org.springframework.util.Assert;

/**
 * @author: TMingYi
 * @date: 2020/5/30 9:37
 */
public class UserInfoUtil {

    public static PersonalHonorsVO notNullObject(PersonalHonorsVO vo){
        Assert.notNull(vo,"PersonalHonorsVO be mush hava value");
        vo.setResuleInfoNum(vo.getResuleInfoNum() == null ? 0 : vo.getResuleInfoNum());
        vo.setNewInfoTotalNum(vo.getNewInfoTotalNum() == null ? 0 : vo.getNewInfoTotalNum());
        vo.setArticleTotalNum(vo.getArticleTotalNum() == null ? 0 : vo.getArticleTotalNum());
        vo.setStudyInfoTotalNum(vo.getStudyInfoTotalNum() == null ? 0 : vo.getStudyInfoTotalNum());
        return vo;
    }

}
