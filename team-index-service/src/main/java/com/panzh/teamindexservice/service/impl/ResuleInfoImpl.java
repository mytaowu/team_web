package com.panzh.teamindexservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panzh.entity.ResuleInfo;
import com.panzh.myconst.CommonConst;
import com.panzh.service.ResuleInfoService;
import com.panzh.teamindexservice.mapper.ResuleInfoMapper;
import com.panzh.teamindexservice.mapper.UserInfoMapper;
import com.panzh.vo.NewsInfoVO;
import com.panzh.vo.ResuleInfoVO;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ResuleInfoImpl implements ResuleInfoService {

    @Autowired(required = false)
    private ResuleInfoMapper resuleInfoMapper;

    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;

    private ResuleInfoVO getResultVo(ResuleInfo resuleInfo){
        ResuleInfoVO resuleInfoVO = new ResuleInfoVO();
        resuleInfoVO.setResultId(resuleInfo.getResultId());
        resuleInfoVO.setResultImge(resuleInfo.getResultImge());
        resuleInfoVO.setResultLink(resuleInfo.getResultLink());
        resuleInfoVO.setResultName(resuleInfo.getResultName());
        resuleInfoVO.setResultTime(resuleInfo.getResultTime());
        resuleInfoVO.setResultValue(resuleInfo.getResultValue());
        resuleInfoVO.setTypeId(resuleInfo.getTypeId());
        resuleInfoVO.setUserInfo(userInfoMapper.selectbystid(resuleInfo.getUserStid()));
        resuleInfoVO.setUserStid(resuleInfo.getUserStid());
        return  resuleInfoVO;
    }


    @Override
    public List<ResuleInfoVO> getAllresuleInfoVoByTypeId(Integer typeId) throws Exception{
       List<ResuleInfo> list = resuleInfoMapper.getAllresuleInfoVoByTypeId(typeId);
        List<ResuleInfoVO> list1 = new ArrayList<>();
        for (ResuleInfo resultinfo:list) {
            ResuleInfoVO resultVo = getResultVo(resultinfo);
            list1.add(resultVo);
        }
        return list1;
    }

    @Override
    public List<ResuleInfoVO> getAllresuleInfoVoForEach(String keyword) throws Exception {
    List<ResuleInfo> list = resuleInfoMapper.getAllresuleInfoVoForEach(keyword);
    List<ResuleInfoVO> resuleInfoVOList = new ArrayList<>();
        for (ResuleInfo resultinfo:list) {
            ResuleInfoVO resultVo = getResultVo(resultinfo);
            resuleInfoVOList.add(resultVo);
            System.err.println(resultVo);
        }
        return resuleInfoVOList;
    }

    @Override
    public void addStudyInfo(ResuleInfo resuleInfo) {
        resuleInfo.setResultTime(new Date());
        resuleInfoMapper.insert(resuleInfo);
    }

    @Override
    public int ResuleInfoFindCommonName(String rName) throws Exception {
        return resuleInfoMapper.ResuleInfoFindCommonName(rName);
    }

    @Override
    public PageInfo<ResuleInfoVO> getResuleInfoVoByUserStid(int pageNo, Integer pageSize, String userStid) {
        PageHelper.startPage(pageNo,pageSize);
        List<ResuleInfoVO> page = resuleInfoMapper.selectResultInfoVoByUserStid(userStid);
        PageInfo<ResuleInfoVO> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    @Override
    public void deleteResultInfoById(String resultId) {
        ResuleInfo info = new ResuleInfo();
        info.setResultId(Integer.parseInt(resultId));
        resuleInfoMapper.deleteByPrimaryKey(info);
    }

    @Override
    public ResuleInfo getResultInfoByResultId(String resultId) {
        ResuleInfo info = new ResuleInfo();
        info.setResultId(Integer.parseInt(resultId));
        return resuleInfoMapper.selectOne(info);
    }

    @Override
    public Integer modityStudyInfo(ResuleInfo resuleInfo) {
        ResuleInfo info = new ResuleInfo();
        info.setResultName(resuleInfo.getResultName());
        List<ResuleInfo> infos = resuleInfoMapper.select(resuleInfo);
        resuleInfo.setResultTime(new Date());

        if (infos.size() >= 1){
            if (Objects.equals(resuleInfo.getResultId(),infos.get(0).getResultId())){
                // 进行信息的更新
                resuleInfoMapper.updateByPrimaryKey(resuleInfo);
                return 1;
            }
            return -1;
        }
        resuleInfoMapper.updateByPrimaryKey(resuleInfo);
        return 1;
    }

    @Override
    public List<ResuleInfoVO> getResuleInfoVoForCount(Integer count) {
        return resuleInfoMapper.getResuleInfoVoForCount(count);
    }
}
