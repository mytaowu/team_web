package com.panzh.teamindexweb.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.panzh.annotaion.RequireRole;
import com.panzh.emun.RoleConst;
import com.panzh.entity.DyNam;
import com.panzh.myconst.CommonConst;
import com.panzh.myconst.ProjectParames;
import com.panzh.service.DyNamService;
import com.panzh.utilbean.ResultVO;
import com.panzh.vo.DyNamVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: TMingYi
 * @date: 2020/5/8 16:42
 * 林荣杭负责
 */
@RestController
@Api(value = "动态操作类")
public class DyNamHandler {

    @Reference
    DyNamService dyNamService;

    @RequestMapping(value = "get/all/dy/nam/info/vo/{page}",method = RequestMethod.GET)
    @ApiOperation(value = "查询到所有的动态", notes = "查询到所有的动态")
    public ResultVO<PageInfo<DyNamVO>> getAllDyNamInfo(@PathVariable("page") String page) {
        //分页查询所有的动态
        int pageno = Integer.parseInt(page);
        ResultVO<PageInfo<DyNamVO>> resultVO = new ResultVO<>();
        PageInfo<DyNamVO> pageInfo = new PageInfo<>();
        try {
            pageInfo = dyNamService.getall(pageno, CommonConst.DEFAULT_PAGE_SIZE);
        }catch (Exception e){
            e.printStackTrace();
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setResult("搜索失败");
            return resultVO;
        }

        resultVO.setData(pageInfo);
        resultVO.setResult(ResultVO.SUCCESS);
        return resultVO;
    }


    @RequestMapping(value = "get/dy/nam/info/vo/{page}/{userStid}",method = RequestMethod.GET)
    @ApiOperation(value = "查询指定用户的所有动态", notes = "查询到所有的动态")
    public ResultVO<PageInfo<DyNamVO>> getDyNamInfoByUserStid(
            @PathVariable("page") String page,
            @PathVariable("userStid") String userStid) {
        //分页查询所有的动态
        int pageno = Integer.parseInt(page);
        ResultVO<PageInfo<DyNamVO>> resultVO = new ResultVO<>();
        PageInfo<DyNamVO> pageInfo = new PageInfo<>();
        try {
            pageInfo = dyNamService.getallByUserStid(pageno,CommonConst.DEFAULT_PAGE_SIZE,userStid);
        }catch (Exception e){
            e.printStackTrace();
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setResult("搜索失败");
            return resultVO;
        }

        resultVO.setData(pageInfo);
        resultVO.setResult(ResultVO.SUCCESS);
        return resultVO;
    }


    @RequestMapping(value = "get/dy/nam/info/vo/item/{dyNamId}",method = RequestMethod.GET)
    @ApiOperation(value = "根据动态的Id查询到指定的动态", notes = "查询到所有的动态")
    public ResultVO<DyNamVO> getDyNamInfoById(@PathVariable("dyNamId") String dyNamId) {

        int id = Integer.parseInt(dyNamId);
        ResultVO<DyNamVO> resultVO = new ResultVO();
        DyNamVO dyNamVO = dyNamService.getbyid(id);
        if(dyNamVO==null){
            resultVO.setMessage(ResultVO.FAILED);
            resultVO.setResult("搜索失败");
        }else {
            resultVO.setData(dyNamVO);
            resultVO.setMessage(ResultVO.SUCCESS);
        }
        return resultVO;

    }

    @RequestMapping(value = "get/dy/nam/info/vo/{count}",method = RequestMethod.GET)
    @ApiOperation(value = "查询到数量为count的动态", notes = "查询到数量为count的动态,用户推荐")
    public ResultVO<List<DyNamVO>> getCountDyNamInfo(@PathVariable("count") String count) {

        int  i = Integer.parseInt(count);
        ResultVO<List<DyNamVO>> resultVO = new ResultVO<>();
        List<DyNamVO> list = new ArrayList<>();
        try {
           list = dyNamService.getbycounts(i);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setResult("搜索失败");
            return resultVO;
        }
        resultVO.setMessage(ResultVO.SUCCESS);
        resultVO.setData(list);
        return resultVO;
    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "add/dy/nam",method = RequestMethod.POST)
    @ApiOperation(value = "添加动态", notes = "添加动态")
    public ResultVO<String> addDyNam(@RequestBody DyNam dyNam) {

        ResultVO resultVO = new ResultVO();
        try {
           dyNamService.setDyNam(dyNam);
        }catch (Exception e){
            e.printStackTrace();
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setMessage("添加失败");
            return resultVO;
        }
            resultVO.setMessage("插入成功");
            resultVO.setResult(ResultVO.SUCCESS);
        return resultVO;
    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "delete/dy/nam/by/{dyNamId}",method = RequestMethod.GET)
    @ApiOperation(value = "删除动态", notes = "删除动态")
    public ResultVO<String> deleteDyNamById(@PathVariable("dyNamId") String dyNamId) {

        int id = Integer.parseInt(dyNamId);
        ResultVO<String> resultVO = new ResultVO<>();
        try {
            dyNamService.deletedynam(id);
        }catch (Exception e){
            e.printStackTrace();
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setResult("删除失败");
            return resultVO;
        }
        resultVO.setMessage("删除成功");
        resultVO.setResult(ResultVO.SUCCESS);
        return resultVO;

    }


    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "motify/dy/nam",method = RequestMethod.POST)
    @ApiOperation(value = "修改动态", notes = "修改动态")
    public ResultVO<String> motifyDyNam(@RequestBody DyNam dyNam) {

        ResultVO<String> resultVO = new ResultVO<>();
        try {
            Integer resullCode = dyNamService.updatadynam(dyNam);
            if (resullCode.equals(-1)){
                resultVO.setResult(ResultVO.FAILED);
                resultVO.setResult("动态已经存在，请修改动态名称！");
                return resultVO;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setResult("修改失败");
            return resultVO;
        }
        resultVO.setMessage("修改成功");
        resultVO.setResult(ResultVO.SUCCESS);
        return resultVO;

    }

}
