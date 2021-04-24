package com.panzh.service;

import com.github.pagehelper.PageInfo;
import com.panzh.entity.StudyInfo;
import com.panzh.vo.StudyInfoVO;

import java.util.Date;
import java.util.List;

public interface StudyInfoService {

    List<StudyInfoVO> getAllStudyInfoByTypeId(Integer typeId)throws Exception;

    List<StudyInfoVO> getAllStudyInfoForEach(String keyword)throws Exception;

    void addStudyInfo(StudyInfo studyInfo)throws Exception;

    void deleteStudyInfoById(Integer studyId)throws Exception;

    int studyInfoFindCommonName(String sName);

    String getStudyTimeById(int id);

    Integer modityStudyInfo(StudyInfo studyInfo);

    PageInfo<StudyInfoVO> getStudyInfoVoByUserStid(int parseInt, Integer defaultPageSize, String userStid);

    StudyInfo getStudyInfoByStudyId(String studyId);
}
