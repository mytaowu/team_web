package com.panzh.teamindexweb.handler;

import com.panzh.annotaion.AddFindTime;
import com.panzh.annotaion.RequireRole;
import com.panzh.emun.RoleConst;
import com.panzh.entity.NewsInfo;
import com.panzh.entity.ResuleInfo;
import com.panzh.entity.StudyInfo;
import com.panzh.entity.UserInfo;
import com.panzh.myconst.CommonConst;
import com.panzh.myconst.ProjectParames;
import com.panzh.myconst.UserContst;
import com.panzh.service.UserInfoService;
import com.panzh.util.DateUtil;
import com.panzh.util.StringUtil;
import com.panzh.util.UserInfoUtil;
import com.panzh.utilbean.ResultVO;
import com.panzh.vo.DyNamVO;
import com.panzh.vo.NewsInfoVO;
import com.panzh.vo.PersonalHonorsVO;
import com.panzh.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.description.field.FieldDescription;
import org.aspectj.weaver.Dump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpSession;

/**
 * @author: TMingYi
 * @date: 2020/5/16 10:40
 */
@Controller
@Api("此类主要是书写页面modelandview的操作")
public class IndexHandler {

    @Autowired
    private DyNamHandler dyNamHandler;

    @Autowired
    private UserInfoHandler userInfoHandler;

    @Autowired
    private NewsInfoHandler newsInfoHandler;

    @Autowired
    private StudyInfoHandler studyInfoHandler;

    @Autowired
    private ResuleInfoHandler resuleInfoHandler;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("/")
    public String index2(){
        return index();
    }

    @RequestMapping("/index.html")
    public String index3(){
        return index();
    }

    @RequestMapping("team_dyname")
    public String team_dyname(){
        return "team_dyname";
    }


    @RequestMapping("loginpage")
    public String loginpage(){
        return "login_page";
    }


    @RequestMapping("learning_resource")
    public String learning_resource(){
        return "learning_resource";
    }


    @RequestMapping("team_achievements")
    public String team_achievements(){
        return "team_achievements";
    }


    @RequestMapping("team_news")
    public String team_news(){
        return "team_news";
    }

    @RequestMapping("registerpage")
    public String registerpage(){
        return "register_page";
    }


    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping("add_dyname")
    public String add_dyname(){
        return "add_dyname";
    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping("add_newinfo")
    public String add_newinfo(){
        return "add_newinfo";
    }


    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping("add_studyPage")
    public String add_studyPage(){
        return "add_studyPage";
    }


    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping("add_resultPage")
    public String add_resultPage(){
        return "add_resultPage";
    }


    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping("modity/image")
    public String getModityImage(Model modelAndView,HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(UserContst.USER_INFO);
        modelAndView.addAttribute("usertImge", userInfo.getUsertImge());
        modelAndView.addAttribute("userId", userInfo.getUserId());
        return "modity_image";
    }


    @RequestMapping("show_dy_info.do/{dyId}")
    public String showDyInfo(@PathVariable("dyId") String dyId, Model modelAndView){
        ResultVO<DyNamVO> info = dyNamHandler.getDyNamInfoById(dyId);
        if (info.getMessage() == ResultVO.FAILED){
            //会自动定位错误页面；
            throw new RuntimeException(CommonConst.GET_INFORMATION_FAILED);
        }
        modelAndView.addAttribute("dyinfo", info.getData());
        return "data_info";
    }

    @AddFindTime
    @RequestMapping("show_new_info.do/{newId}")
    public String showNewInfoInfo(@PathVariable("newId") String newId, Model modelAndView){
        ResultVO<NewsInfoVO> info = newsInfoHandler.getNewInfoById(newId);
        if (info.getMessage() == ResultVO.FAILED){
            //会自动定位错误页面；
            throw new RuntimeException(CommonConst.GET_INFORMATION_FAILED);
        }
        NewsInfoVO vo = info.getData();
        vo.setNewImge(DateUtil.formatDate(vo.getNewTime()));
        modelAndView.addAttribute("newInfo", vo);
        return "new_info";
    }

    @RequireRole(RoleConst.ROLE_TYPE_TOMADMIN)
    @RequestMapping("modity/user/info/{userStid}")
    public String modityUserInfo(@PathVariable("userStid") String userStid, Model modelAndView , HttpSession session){
        UserInfoVO userInfoVO = userInfoHandler.findUserInfoVoByUserStid(userStid).getData();
        modelAndView.addAttribute("userInfoVO",userInfoVO);
        return "modity_userInfo";
    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping("get/user/info")
    public String showDyInfo(Model modelAndView , HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(UserContst.USER_INFO);
        modelAndView.addAttribute("userInfoVO",userInfoHandler.findUserInfoVoByUserStid(userInfo.getUserStid()).getData());
        PersonalHonorsVO honorsVO = userInfoHandler.viewPersonalHonors(userInfo.getUserStid()).getData();
        modelAndView.addAttribute("personalHonorsVO", UserInfoUtil.notNullObject(honorsVO));
        return "user_info";
    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping("modity/dyname/{dyNamId}")
    public String modityDyname(@PathVariable("dyNamId") String dyNamId,Model model){
        ResultVO<DyNamVO> info = dyNamHandler.getDyNamInfoById(dyNamId);
        model.addAttribute(ProjectParames.DY_NAM_INFO,info.getData());
        return "modity_dyname";
    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping("modity/new/info/{newId}")
    public String modityNewInfo(@PathVariable("newId") String newId,Model model){
        ResultVO<NewsInfoVO> info = newsInfoHandler.getNewInfoById(newId);
        model.addAttribute(ProjectParames.NEWS_INFO,info.getData());
        return "modity_newinfo";
    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping("modity/result/{resultId}")
    public String modityResult(@PathVariable("resultId") String resultId,Model model){
        ResultVO<ResuleInfo> info = resuleInfoHandler.getResultInfoByResultId(resultId);
        model.addAttribute(ProjectParames.RESYLE_INFO,info.getData());
        return "modity_resultPage";
    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping("modity/study/{studyId}")
    public String modityStudy(@PathVariable("studyId") String studyId,Model model){
        ResultVO<StudyInfo> info = studyInfoHandler.getStudyInfoByStudyId(studyId);
        model.addAttribute(ProjectParames.STUDY_INFO,info.getData());
        return "modity_studyPage";
    }


    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "do/login/out")
    @ApiOperation(value = "用户退出登录", notes = "用户退出登录")
    public String dologinOut(HttpSession session) {
        session.removeAttribute(UserContst.USER_INFO);
        return "index";
    }

}
