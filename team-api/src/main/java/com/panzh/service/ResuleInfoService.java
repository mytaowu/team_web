package com.panzh.service;

import com.github.pagehelper.PageInfo;
import com.panzh.entity.ResuleInfo;
import com.panzh.vo.ResuleInfoVO;

import java.util.List;

public interface ResuleInfoService {
    List<ResuleInfoVO> getAllresuleInfoVoByTypeId(Integer typeId)throws Exception;

    List<ResuleInfoVO> getAllresuleInfoVoForEach(String keyword )throws  Exception;

    void addStudyInfo(ResuleInfo resuleInfo)throws  Exception;

    int ResuleInfoFindCommonName(String rName)throws Exception;

    PageInfo<ResuleInfoVO> getResuleInfoVoByUserStid(int pageNo, Integer pageSize, String userStid);

    void deleteResultInfoById(String resultId);

    ResuleInfo getResultInfoByResultId(String resultId);

    Integer modityStudyInfo(ResuleInfo resuleInfo);

    List<ResuleInfoVO> getResuleInfoVoForCount(Integer count);
}
