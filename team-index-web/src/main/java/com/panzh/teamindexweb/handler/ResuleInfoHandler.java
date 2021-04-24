package com.panzh.teamindexweb.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.panzh.annotaion.RequireRole;
import com.panzh.emun.RoleConst;
import com.panzh.entity.ResuleInfo;
import com.panzh.entity.ResultType;
import com.panzh.myconst.CommonConst;
import com.panzh.service.ResuleInfoService;
import com.panzh.stringmatch.BookClassficationNameChain;
import com.panzh.stringmatch.resuleInfoMatch;
import com.panzh.utilbean.ResultVO;
import com.panzh.vo.ResuleInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author: TMingYi
 * @date: 2020/5/8 17:34
 * 刘世雄负责
 */
@RestController
@Api(value = "操纵成果的类")
public class ResuleInfoHandler {

    @Reference
    private ResuleInfoService resuleInfoService;

    @Autowired(required = false)
    private BookClassficationNameChain bookClassficationNameChain;

    @RequestMapping(value = "get/all/resule/info/vo/{typeId}", method = RequestMethod.GET)
    @ApiOperation(value = "根据分类查找成果", notes = "根据分类查找成果")
    public ResultVO<List<ResuleInfoVO>> getAllresuleInfoVoByTypeId(@PathVariable("typeId") String typeId) throws Exception {
        //默认（默认为0）查找全部分类，即按照时间id倒叙（后发布的先显示），
        ResultVO resuleInfoVO = new ResultVO();
        Integer parseInt = Integer.parseInt(typeId);
        List<ResuleInfoVO> ResuleInfoVO = null;
        try {
            ResuleInfoVO = resuleInfoService.getAllresuleInfoVoByTypeId(parseInt);
            resuleInfoVO.setData(ResuleInfoVO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<List<ResuleInfoVO>>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, ResuleInfoVO);
        }
        return new ResultVO<List<ResuleInfoVO>>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, ResuleInfoVO);
    }


    @RequestMapping(value = "get/resule/info/vo/{page}/{userStid}", method = RequestMethod.GET)
    @ApiOperation(value = "根据page和userStid查找成果", notes = "根据page和userStid查找成果")
    public ResultVO<PageInfo<ResuleInfoVO>> getResuleInfoVoByUserStid(@PathVariable("page") String pageNo, @PathVariable("userStid") String userStid) throws Exception {
        //默认（默认为0）查找全部分类，即按照时间id倒叙（后发布的先显示），
        ResultVO resuleInfoVO = new ResultVO();
        PageInfo<ResuleInfoVO> ResuleInfoVO = null;
        try {
            ResuleInfoVO = resuleInfoService.getResuleInfoVoByUserStid(Integer.parseInt(pageNo), CommonConst.DEFAULT_PAGE_SIZE, userStid);
            resuleInfoVO.setData(ResuleInfoVO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<PageInfo<ResuleInfoVO>>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, ResuleInfoVO);
        }
        return new ResultVO<PageInfo<ResuleInfoVO>>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, ResuleInfoVO);
    }

    @RequestMapping(value = "get/resule/info/vo/for/sereach", method = RequestMethod.GET)
    @ApiOperation(value = "关键字搜索成果", notes = "关键字搜索成果")
    public ResultVO<List<ResuleInfoVO>> getAllresuleInfoVoForEach(@RequestParam("keyword") String keyword) {
        //根据关键字进行模糊匹配我们需要的数据！
        ResultVO resuleVO = new ResultVO();
        List<ResuleInfoVO> ResuleInfoVO = null;
        try {
            ResuleInfoVO = resuleInfoService.getAllresuleInfoVoForEach(keyword);
            resuleVO.setData(ResuleInfoVO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<List<ResuleInfoVO>>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, ResuleInfoVO);
        }
        return new ResultVO<List<ResuleInfoVO>>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, ResuleInfoVO);
    }


    @RequestMapping(value = "get/resule/info/vo/for/count/{count}", method = RequestMethod.GET)
    @ApiOperation(value = "获得指定数量的count", notes = "获得指定数量的count")
    public ResultVO<List<ResuleInfoVO>> getResuleInfoVoForCount(@PathVariable("count") Integer count) {
        ResultVO resuleVO = new ResultVO();
        List<ResuleInfoVO> ResuleInfoVO = null;
        try {
            ResuleInfoVO = resuleInfoService.getResuleInfoVoForCount(count);
            resuleVO.setData(ResuleInfoVO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<List<ResuleInfoVO>>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED, ResuleInfoVO);
        }
        return new ResultVO<List<ResuleInfoVO>>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, ResuleInfoVO);
    }

    @RequestMapping(value = "add/result/info", method = RequestMethod.POST)
    @ApiOperation(value = "添加成果", notes = "添加成果")
    public ResultVO<String> addStudyInfo(@RequestBody ResuleInfo resuleInfo) {
        boolean match = bookClassficationNameChain.Match(resuleInfo.getResultName(), resuleInfoMatch.class);
        if (match == false) {
            return new ResultVO<String>(ResultVO.FAILED, CommonConst.RESULTS_ALREADY_EXIST, ResultVO.NO_DATA);
        } else {
            try {
                resuleInfoService.addStudyInfo(resuleInfo);
                //日期字段需要后端自动生成;
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultVO<String>(ResultVO.FAILED, CommonConst.ADD_ACHIEVEMENT_FAILED, ResultVO.NO_DATA);
            }
            return new ResultVO<String>(ResultVO.SUCCESS, CommonConst.ADD_ACHIEVEMENT_SUCCESS, ResultVO.NO_DATA);
        }
    }


    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "modity/result/info", method = RequestMethod.POST)
    @ApiOperation(value = "修改成果", notes = "修改成果")
    public ResultVO<String> modityStudyInfo(@RequestBody ResuleInfo resuleInfo) {
        try {
            Integer resultCode = resuleInfoService.modityStudyInfo(resuleInfo);
            if (Objects.equals(resuleInfo, -1)) {
                return new ResultVO<String>(ResultVO.FAILED, CommonConst.RESULTS_ALREADY_EXIST, ResultVO.NO_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<String>(ResultVO.FAILED, CommonConst.DATA_SAVING_FAILED, ResultVO.NO_DATA);
        }
        return new ResultVO<String>(ResultVO.SUCCESS, CommonConst.INFORMATION_UPDATED_SUCCESSFULLY, ResultVO.NO_DATA);
    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "delete/result/info/{resultId}", method = RequestMethod.GET)
    @ApiOperation(value = "删除成果", notes = "删除成果")
    public ResultVO<String> deleteStudyInfo(@PathVariable("resultId") String resultId) {
        try {
            resuleInfoService.deleteResultInfoById(resultId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<>(ResultVO.FAILED, CommonConst.DELETE_INFORMATION_EXCEPTION, ResultVO.NO_DATA);
        }
        return new ResultVO<>(ResultVO.FAILED, CommonConst.DELETE_INFORMATION_SUCCESS, ResultVO.NO_DATA);
    }

    public ResultVO<ResuleInfo> getResultInfoByResultId(String resultId) {
        ResuleInfo resuleInfo = resuleInfoService.getResultInfoByResultId(resultId);
        return new ResultVO<ResuleInfo>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS, resuleInfo);

    }
}
