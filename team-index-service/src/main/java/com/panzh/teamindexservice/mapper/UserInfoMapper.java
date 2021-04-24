package com.panzh.teamindexservice.mapper;

import com.panzh.entity.UserInfo;
import com.panzh.vo.ModifyUserVO;
import com.panzh.vo.UserInfoVO;
import com.panzh.vo.UserRegisterVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserInfoMapper extends Mapper<UserInfo> {
    @Select("select * from user_info where user_stid = #{stid}")
    UserInfo selectbystid(String stid);

    List<UserInfoVO> getAllUserInfoVo();

    List<UserInfoVO> getAllUserInfoVoProhibit();

    UserInfoVO selectUserInfoVoByUserStid(String userStid);

    void savaUserInfoByRegisterVO(UserRegisterVO userRegisterVO);

    void modifyUserInfo(ModifyUserVO modifyUserVO);

    void setUserInfoToAdmin(Integer userId);

    void passValidation(String userId);

    void reSetPass(@Param("userId") String userId, @Param("defaultPassword")String defaultPassword);

    Integer getAllReaderNumByUserStid(String userStid);

    Integer allNewInfoTotalNum(String userStid);

    Integer getStudyInfoTotalNum(String userStid);

    Integer getResuleInfoNum(String userStid);

    void setAdminToUser(Integer userId);

    void updateImageAndInDataBase(@Param("path") String path, @Param("userId")Integer userId);
}