package com.panzh.teamindexservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panzh.entity.StudyInfo;
import com.panzh.service.StudyInfoService;
import com.panzh.teamindexservice.mapper.StudyInfoMapper;
import com.panzh.teamindexservice.mapper.UserInfoMapper;
import com.panzh.vo.ResuleInfoVO;
import com.panzh.vo.StudyInfoVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class StudyInfoImpl implements StudyInfoService {

    @Autowired
    private StudyInfoMapper studyInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    private StudyInfoVO getStudyInfoVo(StudyInfo studyInfo) {
        StudyInfoVO studyInfoVO = new StudyInfoVO();
        studyInfoVO.setStudyId(studyInfo.getStudyId());
        studyInfoVO.setStudyValue(studyInfo.getStudyValue());
        studyInfoVO.setStudyName(studyInfo.getStudyName());
        studyInfoVO.setImgeAdd(studyInfo.getImgeAdd());
        studyInfoVO.setStudyTime(studyInfo.getStudyTime());
        studyInfoVO.setStudyLink(studyInfo.getStudyLink());
        studyInfoVO.setUserInfo(userInfoMapper.selectbystid(studyInfo.getUserStid()));
        studyInfoVO.setUserStid(studyInfo.getUserStid());
        studyInfoVO.setTypeId(studyInfo.getTypeId());

        return studyInfoVO;
    }

    @Override
    public List<StudyInfoVO> getAllStudyInfoByTypeId(Integer typeId) throws Exception {
        List<StudyInfo> allStudyInfoByTypeId = studyInfoMapper.getAllStudyInfoByTypeId(typeId);
        List<StudyInfoVO> studyInfoVOList = new ArrayList<>();
        for (StudyInfo studyinfos : allStudyInfoByTypeId) {
            StudyInfoVO studyInfoVO = getStudyInfoVo(studyinfos);
            studyInfoVOList.add(studyInfoVO);
        }
        return studyInfoVOList;
    }


    @Override
    public List<StudyInfoVO> getAllStudyInfoForEach(String keyword) throws Exception {
        List<StudyInfo> allStudyInfoForEach = studyInfoMapper.getAllStudyInfoForEach(keyword);
        List<StudyInfoVO> list = new ArrayList<>();
        for (StudyInfo studyInfos : allStudyInfoForEach
        ) {
            StudyInfoVO studyInfoVO = getStudyInfoVo(studyInfos);
            list.add(studyInfoVO);
        }
        return list;
    }

    @Override
    public void addStudyInfo(StudyInfo studyInfo) throws Exception {
        studyInfo.setStudyTime(new Date());
        studyInfoMapper.insert(studyInfo);
    }

    @Override
    public void deleteStudyInfoById(Integer studyId) throws Exception {
        studyInfoMapper.deleteStudyInfoById(studyId);
    }

    @Override
    public int studyInfoFindCommonName(String sName) {
        return studyInfoMapper.studyInfoFindCommonName(sName);
    }

    @Override
    public String getStudyTimeById(int id) {
        return studyInfoMapper.getStudyTimeById(id);
    }

    @Override
    public Integer modityStudyInfo(StudyInfo studyInfo) {
        StudyInfo info = new StudyInfo();
        info.setStudyName(studyInfo.getStudyName());
        List<StudyInfo> infos = studyInfoMapper.select(info);
        //进行属性的赋值；
        studyInfo.setStudyTime(new Date());
        if (infos.size() >= 1){
            if (Objects.equals(infos.get(0).getStudyId(),studyInfo.getStudyId())){
                studyInfoMapper.modityStudyInfo(studyInfo);
                return 1;
            }
            return -1;
        }
        studyInfoMapper.modityStudyInfo(studyInfo);
        return 1;

    }

    @Override
    public PageInfo<StudyInfoVO> getStudyInfoVoByUserStid(int pageNo, Integer pageSize, String userStid) {
        PageHelper.startPage(pageNo, pageSize);
        List<StudyInfoVO> page = studyInfoMapper.selectStudyInfoVoByUserStid(userStid);
        PageInfo<StudyInfoVO> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    @Override
    public StudyInfo getStudyInfoByStudyId(String studyId) {
        StudyInfo info = new StudyInfo();
        info.setStudyId(Integer.parseInt(studyId));
        return studyInfoMapper.selectOne(info);
    }
}
