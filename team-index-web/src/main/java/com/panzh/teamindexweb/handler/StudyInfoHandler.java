package com.panzh.teamindexweb.handler;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.panzh.annotaion.RequireRole;
import com.panzh.emun.RoleConst;
import com.panzh.entity.StudyInfo;
import com.panzh.myconst.CommonConst;
import com.panzh.service.StudyInfoService;
import com.panzh.stringmatch.BookClassficationNameChain;
import com.panzh.stringmatch.studyInfoMatch;
import com.panzh.utilbean.ResultVO;
import com.panzh.vo.StudyInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: TMingYi
 * @date: 2020/5/8 17:21
 * 刘世雄负责
 */
@RestController
@Api(value = "操纵学习资源的类")
public class StudyInfoHandler {

    @Reference
    private StudyInfoService studyInfoService;

    @Autowired(required = false)
    private BookClassficationNameChain bookClassficationNameChain;

    @RequestMapping(value = "get/all/study/info/vo/{typeId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询到学习资源根据分类")
    public ResultVO<List<StudyInfoVO>> getAllStudyInfoByTypeId(@PathVariable("typeId") String typeId) {
        //默认（默认为0）查找全部分类，即按照时间id倒叙（后发布的先显示），
        Integer parseInt = Integer.parseInt(typeId);
        ResultVO resultVO = new ResultVO();
        List<StudyInfoVO> list = null;
        try {
            list = studyInfoService.getAllStudyInfoByTypeId(parseInt);
            resultVO.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<List<StudyInfoVO>>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, list);
        }
        return new ResultVO<List<StudyInfoVO>>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, list);
    }


    @RequestMapping(value = "get/study/info/vo/{page}/{userStid}", method = RequestMethod.GET)
    @ApiOperation(value = "根据page和userStid查找资源", notes = "根据page和userStid查找资源")
    public ResultVO<PageInfo<StudyInfoVO>> getResuleInfoVoByUserStid(@PathVariable("page") String pageNo, @PathVariable("userStid") String userStid) throws Exception {
        PageInfo<StudyInfoVO> studyInfoVOPageInfo = null;
        try {
            studyInfoVOPageInfo = studyInfoService.getStudyInfoVoByUserStid(Integer.parseInt(pageNo), CommonConst.DEFAULT_PAGE_SIZE, userStid);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<PageInfo<StudyInfoVO>>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, studyInfoVOPageInfo);
        }
        return new ResultVO<PageInfo<StudyInfoVO>>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, studyInfoVOPageInfo);
    }


    @RequestMapping(value = "get/study/info/vo/for/sereach", method = RequestMethod.GET)
    @ApiOperation(value = "关键字搜索学习资源", notes = "关键字搜索学习资源")
    public ResultVO<List<StudyInfoVO>> getAllStudyInfoForEach(@RequestParam("keyword") String keyword) {
        //根据关键字进行模糊匹配我们需要的数据！
        ResultVO resultVO = new ResultVO();
        List<StudyInfoVO> list = null;
        try {
            list = studyInfoService.getAllStudyInfoForEach(keyword);
            resultVO.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<List<StudyInfoVO>>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, list);
        }
        return new ResultVO<List<StudyInfoVO>>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, list);
    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "add/study/info", method = RequestMethod.POST)
    @ApiOperation(value = "添加学习资源", notes = "添加学习资源")
    public ResultVO<String> addStudyInfo(@RequestBody StudyInfo studyInfo) {
        //日期字段需要后端自动生成;
        boolean match = bookClassficationNameChain.Match(studyInfo.getStudyName(), studyInfoMatch.class);
        if (match == false) {
            return new ResultVO<String>(ResultVO.FAILED, CommonConst.STUDY_ALREADY_EXIST, ResultVO.NO_DATA);
        } else {
            try {
                studyInfoService.addStudyInfo(studyInfo);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultVO<String>(ResultVO.FAILED, CommonConst.DATA_SAVING_FAILED, ResultVO.NO_DATA);
            }
            return new ResultVO<String>(ResultVO.SUCCESS, CommonConst.INFORMATION_SAVED_SUCCESSFULLY, ResultVO.NO_DATA);
        }
    }
    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "delete/study/info/{studyId}", method = RequestMethod.GET)
    @ApiOperation(value = "删除学习资源", notes = "添加学习资源")
    public ResultVO<String> deleteStudyInfoById(@PathVariable("studyId") String studyId) {

        Integer parseInt = Integer.parseInt(studyId);
        try {
            studyInfoService.deleteStudyInfoById(parseInt);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<String>(ResultVO.FAILED, CommonConst.DELETE_INFORMATION_EXCEPTION, ResultVO.NO_DATA);
        }
        return new ResultVO<String>(ResultVO.SUCCESS, CommonConst.DELETE_INFORMATION_SUCCESS, ResultVO.NO_DATA);
    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "modity/study/info", method = RequestMethod.POST)
    @ApiOperation(value = "修改学习资源", notes = "添加学习资源")
    public ResultVO<String> modityStudyInfo(@RequestBody StudyInfo studyInfo) {
        //注意，我们所有的修改，日期字段是不变的！！
        try {
            Integer resultCode = studyInfoService.modityStudyInfo(studyInfo);
            if (resultCode.equals(-1)) {
                return new ResultVO<String>(ResultVO.FAILED, CommonConst.INFORMATION_ALREADY_EXISTS, ResultVO.NO_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<String>(ResultVO.FAILED, CommonConst.INFORMATION_UPDATED_FAILED, ResultVO.NO_DATA);
        }
        return new ResultVO<String>(ResultVO.SUCCESS, CommonConst.INFORMATION_UPDATED_SUCCESSFULLY, ResultVO.NO_DATA);
    }

    public ResultVO<StudyInfo> getStudyInfoByStudyId(String studyId) {
        StudyInfo studyInfo = studyInfoService.getStudyInfoByStudyId(studyId);
        return new ResultVO<StudyInfo>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, studyInfo);
    }
}
