package com.panzh.teamindexweb.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.panzh.annotaion.RequireRole;
import com.panzh.emun.RoleConst;
import com.panzh.entity.ResultType;
import com.panzh.entity.UserInfo;
import com.panzh.myconst.CommonConst;
import com.panzh.myconst.ProjectParames;
import com.panzh.myconst.UserContst;
import com.panzh.service.UserInfoService;
import com.panzh.util.StringUtil;
import com.panzh.utilbean.ResultVO;
import com.panzh.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author: TMingYi
 * @date: 2020/5/8 15:49
 * 吴涛负责
 */

@RestController
@Api(value = "操作用户的类")
public class UserInfoHandler {

    @Reference
    private UserInfoService userInfoService;

    @Autowired
    private UpdataHandler updataHandler;

    @RequireRole(RoleConst.ROLE_TYPE_TOMADMIN)
    @RequestMapping(value = "get/all/user/info/vo/page/{page}", method = RequestMethod.GET)
    @ApiOperation(value = "查询到所有的UserInfoVO（分页）", notes = "获取所有的UserInfoVO（分页）")
    public ResultVO<PageInfo<UserInfoVO>> findAllUserInfoVOByPage(@PathVariable(value = "page") String page) {
        PageInfo<UserInfoVO> pageInfo = null;
        try {
            //该方法一定是返回的被禁用的信息，也就是返回需要进行认证的用户
            pageInfo = userInfoService.getAllUserInfoVoPage(Integer.parseInt(page), ProjectParames.PAGE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<PageInfo<UserInfoVO>>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, pageInfo);
        }
        return new ResultVO<PageInfo<UserInfoVO>>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, pageInfo);
    }

    @RequireRole(RoleConst.ROLE_TYPE_TOMADMIN)
    @RequestMapping(value = "get/all/user/info/vo", method = RequestMethod.GET)
    @ApiOperation(value = "查询到所有的UserInfoVO", notes = "获取所有的UserInfoVO")
    public ResultVO<List<UserInfoVO>> findAllUserInfoVO() {
        List<UserInfoVO> userInfoVOList = null;
        try {
            userInfoVOList = userInfoService.getAllUserInfoVo();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<List<UserInfoVO>>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, userInfoVOList);
        }
        //这里查询的是未被禁用的，也就是通过审核的用户；
        return new ResultVO<List<UserInfoVO>>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, userInfoVOList);
    }

    @RequireRole(RoleConst.ROLE_TYPE_TOMADMIN)
    @RequestMapping(value = "get/user/info/vo/prohibit/{page}", method = RequestMethod.GET)
    @ApiOperation(value = "查询到所有正常使用的用户", notes = "获取所有的UserInfoVO")
    public ResultVO<PageInfo<UserInfoVO>> findAllUserInfoVOProhibit(@PathVariable(value = "page") String page) {
        PageInfo<UserInfoVO> pageInfo = null;
        try {
            pageInfo = userInfoService.getAllUserInfoVOProhibit(Integer.parseInt(page), ProjectParames.PAGE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<PageInfo<UserInfoVO>>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, pageInfo);
        }
        return new ResultVO<PageInfo<UserInfoVO>>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, pageInfo);
    }


    /**
     * paramType：表示参数放在哪个地方
     * header-->请求参数的获取：@RequestHeader(代码中接收注解)
     * query-->请求参数的获取：@RequestParam(代码中接收注解)
     * path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
     * body-->请求参数的获取：@RequestBody(代码中接收注解)
     * form（不常用）
     */
    @RequireRole(RoleConst.ROLE_TYPE_TOMADMIN)
    @RequestMapping(value = "get/all/user/info/vo/{userStid}", method = RequestMethod.GET)
    @ApiOperation(value = "查询到指定用户的UserInfoVO", notes = "获取指定用户所有的UserInfoVO")
    @ApiImplicitParam(name = "userStid", value = "指定用户的学号", required = true, dataType = "String", paramType = "path")
    public ResultVO<UserInfoVO> findUserInfoVoByUserStid(@PathVariable("userStid") String userStid) {
        //这里查询的是未被禁用的，也就是通过审核的用户；
        UserInfoVO userInfoVO = null;
        try {
            userInfoVO = userInfoService.findUserInfoVoByUserStid(userStid);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<UserInfoVO>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, userInfoVO);
        }
        if (userInfoVO != null) {
            return new ResultVO<UserInfoVO>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_FAILED, userInfoVO);

        }
        return new ResultVO<UserInfoVO>(ResultVO.FAILED, CommonConst.DATA_SAVING_FAILED, userInfoVO);
    }


    @RequestMapping(value = "do/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParam(name = "userStid", value = "指定用户的学号", required = true, dataType = "String", paramType = "query")
    public ResultVO dologin(@RequestParam("userStid") String userStid,
                            @RequestParam("userPass") String userPass,
                            HttpSession session) {

        //账号存在，密码不正确的情况下；
        UserInfo userByAcct = userInfoService.getUserByUserStid(userStid);
        if (userByAcct == null){
            return new ResultVO<String>(ResultVO.FAILED, UserContst.ACCOUNT_DOES_NOT_EXIST, ResultVO.NO_DATA);
        }
        Integer use = userByAcct.getUserUse();
        if (Objects.equals(0,use)){
            return new ResultVO<String>(ResultVO.FAILED, UserContst.USER_IS_DISABLE, ResultVO.NO_DATA);
        }

        //对密码进行md5加密；
        userPass = StringUtil.md5(userPass);

        //取出数据库的原串；
        String sourceLoginPwd = userByAcct.getUserPass();
        if (userPass.equals(sourceLoginPwd)) {
            //放入session中;
            session.setAttribute(UserContst.USER_INFO, userByAcct);
            return new ResultVO<Integer>(ResultVO.SUCCESS, ResultVO.NO_MSG, userByAcct.getUserPower());
        } else {
            return new ResultVO<String>(ResultVO.FAILED, UserContst.PASSWORD_ERROR, ResultVO.NO_DATA);
        }
    }


    @RequestMapping(value = "do/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public synchronized ResultVO<String> doRegister(@RequestBody UserRegisterVO userRegisterVO) {
        UserInfo userByAcct = userInfoService.getUserByUserStid(userRegisterVO.getUserStid());
        //插入用户表，但是设置禁用！！，等后台管理员进行审核之后，方可登录;
        if (userByAcct == null) {
            try {
                userInfoService.savaUserInfoByRegisterVO(userRegisterVO);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultVO<String>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, ResultVO.NO_DATA);
            }
            return new ResultVO<String>(ResultVO.SUCCESS, CommonConst.INFORMATION_SAVED_SUCCESSFULLY, ResultVO.NO_DATA);
        } else {
            return new ResultVO<String>(ResultVO.FAILED, UserContst.ACCOUNT_DOES_EXIST, ResultVO.NO_DATA);
        }

    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "view/personal/honors/{userStid}", method = RequestMethod.POST)
    @ApiOperation(value = "展示用户的荣誉", notes = "展示用户的荣誉")
    @ApiImplicitParam(name = "userStid", value = "用户学号", required = true, dataType = "String", paramType = "query")
    public ResultVO<PersonalHonorsVO> viewPersonalHonors(@PathVariable("userStid") String userStid) {
        PersonalHonorsVO p = null;
        try {
            p = userInfoService.getViewPersonalHonors(userStid);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<PersonalHonorsVO>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, p);
        }
        return new ResultVO<PersonalHonorsVO>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, p);
    }


    @RequireRole(RoleConst.ROLE_TYPE_TOMADMIN)
    @RequestMapping(value = "modify/user/info", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户的信息", notes = "修改用户的信息")
    public ResultVO<String> modifyUserInfo(@RequestBody ModifyUserVO modifyUserVO) {
        PersonalHonorsVO p = null;
        try {
            userInfoService.modifyUserInfo(modifyUserVO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<String>(ResultVO.FAILED, CommonConst.DATA_SAVING_FAILED, ResultVO.NO_DATA);
        }
        return new ResultVO<String>(ResultVO.SUCCESS, CommonConst.INFORMATION_SAVED_SUCCESSFULLY, ResultVO.NO_DATA);
    }


    @RequireRole(RoleConst.ROLE_TYPE_TOMADMIN)
    @RequestMapping(value = "delete/user/info/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息")
    public ResultVO<String> deleteUserInfoById(@PathVariable("userId") String userId) {
        PersonalHonorsVO p = null;
        try {
            userInfoService.deleteUserInfoById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<String>(ResultVO.FAILED, CommonConst.DELETE_INFORMATION_EXCEPTION, ResultVO.NO_DATA);
        }
        return new ResultVO<String>(ResultVO.SUCCESS, CommonConst.DELETE_INFORMATION_SUCCESS, ResultVO.NO_DATA);
    }

    @RequireRole(RoleConst.ROLE_TYPE_TOMADMIN)
    @RequestMapping(value = "set/user/info/to/admin/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "设置普通用户为管理员", notes = "设置普通用户为管理员")
    public ResultVO<String> setUserInfoToAdmin(@PathVariable("userId") String userId) {
        try {
            userInfoService.setUserInfoToAdmin(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<String>(ResultVO.FAILED, CommonConst.DATA_SAVING_FAILED, ResultVO.NO_DATA);
        }
        return new ResultVO<String>(ResultVO.SUCCESS, CommonConst.INFORMATION_SAVED_SUCCESSFULLY, ResultVO.NO_DATA);
    }

    @RequireRole(RoleConst.ROLE_TYPE_TOMADMIN)
    @RequestMapping(value = "set/admin/info/to/user/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "设置管理员为普通用户", notes = "设置普通用户为管理员")
    public ResultVO<String> setAdminInfoToUser(@PathVariable("userId") String userId) {
        try {
            userInfoService.setAdminToUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<String>(ResultVO.FAILED, CommonConst.DATA_SAVING_FAILED, ResultVO.NO_DATA);
        }
        return new ResultVO<String>(ResultVO.SUCCESS, CommonConst.INFORMATION_SAVED_SUCCESSFULLY, ResultVO.NO_DATA);
    }


    @RequireRole(RoleConst.ROLE_TYPE_TOMADMIN)
    @RequestMapping(value = "pass/validation/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "通过注册的验证", notes = "通过注册的验证")
    public ResultVO<String> passValidation(@PathVariable("userId") String userId) {
//        把禁用的字段改成不禁用就好了
        try {
            userInfoService.passValidation(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<String>(ResultVO.FAILED, CommonConst.DATA_SAVING_FAILED, ResultVO.NO_DATA);
        }
        return new ResultVO<String>(ResultVO.SUCCESS, CommonConst.INFORMATION_SAVED_SUCCESSFULLY, ResultVO.NO_DATA);
    }

    @RequireRole(RoleConst.ROLE_TYPE_TOMADMIN)
    @RequestMapping(value = "re/set/pass/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "重置用户密码", notes = "重置用户密码")
    public ResultVO<String> reSetPass(@PathVariable("userId") String userId) {
        try {
            userInfoService.reSetPass(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<String>(ResultVO.FAILED, CommonConst.RESET_PASSWORD_FAILED, ResultVO.NO_DATA);
        }
        return new ResultVO<String>(ResultVO.SUCCESS, CommonConst.INFORMATION_SAVED_SUCCESSFULLY, ResultVO.NO_DATA);
    }


    //    '/upLoadImage/'+userId
    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping("upLoadImage/{userId}")
    public ResultVO<String> updateImageAndInDataBase(
            @RequestParam("file") MultipartFile img,
            @PathVariable("userId") Integer userId,
            HttpServletRequest request){
        String path = null;
        try {
            ImageInfoVO vo = updataHandler.updateImage(img);
            path = vo.getFile_path();
            userInfoService.updateImageAndInDataBase(path,userId);
            // 更新session中的数据。
            // 一定要更新session中的数据。
            UserInfo sessionInfo = (UserInfo) request.getSession().getAttribute(UserContst.USER_INFO);
            sessionInfo.setUsertImge(vo.getFile_path());
            request.getSession().setAttribute(UserContst.USER_INFO,sessionInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<>(ResultVO.FAILED,ResultVO.NO_MSG,ResultVO.NO_DATA);
        }
        return new ResultVO<>(ResultVO.SUCCESS,ResultVO.NO_MSG,path);
    }
}
