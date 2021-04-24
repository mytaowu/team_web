package com.panzh.service;

import com.github.pagehelper.PageInfo;
import com.panzh.entity.UserInfo;
import com.panzh.vo.ModifyUserVO;
import com.panzh.vo.PersonalHonorsVO;
import com.panzh.vo.UserInfoVO;
import com.panzh.vo.UserRegisterVO;

import java.util.List;

/**
 * @author: TMingYi
 * @date: 2020/5/8 15:51
 */
public interface UserInfoService {
    PageInfo<UserInfoVO> getAllUserInfoVoPage(int parseInt, Integer pageSize);

    List<UserInfoVO> getAllUserInfoVo();

    PageInfo<UserInfoVO> getAllUserInfoVOProhibit(int parseInt, Integer pageSize);

    UserInfoVO findUserInfoVoByUserStid(String userStid);

    UserInfo getUserByUserStid(String userStid);

    void savaUserInfoByRegisterVO(UserRegisterVO userRegisterVO);

    PersonalHonorsVO getViewPersonalHonors(String userStid);

    void modifyUserInfo(ModifyUserVO modifyUserVO);

    void deleteUserInfoById(String userId);

    void setUserInfoToAdmin(String userId);

    void passValidation(String userId);

    void reSetPass(String userId);

    void setAdminToUser(String userId);

    void updateImageAndInDataBase(String path, Integer userId);
}
