package com.panzh.teamindexservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panzh.entity.UserInfo;
import com.panzh.myconst.ProjectParames;
import com.panzh.myconst.UserContst;
import com.panzh.service.UserInfoService;
import com.panzh.teamindexservice.mapper.UserInfoMapper;
import com.panzh.util.StringUtil;
import com.panzh.vo.ModifyUserVO;
import com.panzh.vo.PersonalHonorsVO;
import com.panzh.vo.UserInfoVO;
import com.panzh.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author: TMingYi
 * @date: 2020/5/9 11:02
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public PageInfo<UserInfoVO> getAllUserInfoVoPage(int parseInt, Integer pageSize) {
        PageHelper.startPage(parseInt,pageSize);
        List<UserInfoVO> userInfoVOList = userInfoMapper.getAllUserInfoVo();
        return new PageInfo<>(userInfoVOList);
    }

    @Override
    public List<UserInfoVO> getAllUserInfoVo() {
        //该方法一定是返回的未被禁用的信息，也就是正常使用的账号;
        return userInfoMapper.getAllUserInfoVo();
    }

    @Override
    public PageInfo<UserInfoVO> getAllUserInfoVOProhibit(int parseInt, Integer pageSize) {
        PageHelper.startPage(parseInt,pageSize);
        //禁用列表
        List<UserInfoVO> userInfoVOList = userInfoMapper.getAllUserInfoVoProhibit();
        return new PageInfo<>(userInfoVOList);
    }

    @Override
    public UserInfoVO findUserInfoVoByUserStid(String userStid) {
        return userInfoMapper.selectUserInfoVoByUserStid(userStid);
    }

    @Override
    public UserInfo getUserByUserStid(String userStid) {
        return userInfoMapper.selectbystid(userStid);
    }

    @Override
    public void savaUserInfoByRegisterVO(UserRegisterVO userRegisterVO) {
        userRegisterVO.setUserPass(StringUtil.md5(userRegisterVO.getUserPass()));
        userInfoMapper.savaUserInfoByRegisterVO(userRegisterVO);
    }

    @Override
    public PersonalHonorsVO getViewPersonalHonors(String userStid) {
        return getPersonalHonorsVOByUserId(userStid);
    }

    @Override
    public void modifyUserInfo(ModifyUserVO modifyUserVO) {
        userInfoMapper.modifyUserInfo(modifyUserVO);
    }

    @Override
    public void deleteUserInfoById(String userId) {
        UserInfo info = new UserInfo();
        info.setUserId(Integer.parseInt(userId));
        userInfoMapper.deleteByPrimaryKey(info);

    }

    @Override
    public void setUserInfoToAdmin(String userId) {
        userInfoMapper.setUserInfoToAdmin(Integer.parseInt(userId));
    }

    @Override
    public void passValidation(String userId) {
        userInfoMapper.passValidation(userId);
    }

    @Override
    public void reSetPass(String userId) {
        userInfoMapper.reSetPass(userId, StringUtil.md5(UserContst.DEFAULT_PASSWORD));
    }

    @Override
    public void setAdminToUser(String userId) {
        userInfoMapper.setAdminToUser(Integer.parseInt(userId));
    }

    @Override
    public void updateImageAndInDataBase(String path, Integer userId) {
        userInfoMapper.updateImageAndInDataBase(path,userId);
    }

    //多线程测试方法，学以致用嘛
    private PersonalHonorsVO getPersonalHonorsVOByUserId(String userStid){
        final CountDownLatch latch = new CountDownLatch(4);
        PersonalHonorsVO vo = new PersonalHonorsVO();
        new Thread(()->{
            Integer readerNum = userInfoMapper.getAllReaderNumByUserStid(userStid);
            vo.setArticleTotalNum(readerNum);
            latch.countDown();
        },"总阅读数！").start();

        new Thread(()->{
            Integer newInfoTotalNum = userInfoMapper.allNewInfoTotalNum(userStid);
            vo.setNewInfoTotalNum(newInfoTotalNum);
            latch.countDown();
        },"新闻总数！").start();


        new Thread(()->{
            Integer studyInfoTotalNum = userInfoMapper.getStudyInfoTotalNum(userStid);
            vo.setStudyInfoTotalNum(studyInfoTotalNum);
            latch.countDown();
        },"资源总数！").start();

        new Thread(()->{
            Integer resuleInfoNum = userInfoMapper.getResuleInfoNum(userStid);
            vo.setResuleInfoNum(resuleInfoNum);
            latch.countDown();
        },"成果总数").start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return vo;
    }
}
