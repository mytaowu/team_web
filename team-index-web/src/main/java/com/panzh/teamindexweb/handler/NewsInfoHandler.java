package com.panzh.teamindexweb.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.panzh.annotaion.RequireRole;
import com.panzh.emun.RoleConst;
import com.panzh.entity.DyNam;
import com.panzh.entity.NewType;
import com.panzh.entity.NewsInfo;
import com.panzh.myconst.CommonConst;
import com.panzh.service.NewsService;
import com.panzh.utilbean.ResultVO;
import com.panzh.vo.DyNamVO;
import com.panzh.vo.NewsInfoVO;
import com.panzh.vo.SimpleNewsInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: TMingYi
 * @date: 2020/5/8 16:52
 * 林荣杭负责
 */
@RestController
@Api(value = "新闻的操作类")
public class NewsInfoHandler {

    @Reference
    NewsService newsService;

    @RequestMapping(value = "get/all/new/info/vo/{page}/{typeId}",method = RequestMethod.GET)
    @ApiOperation(value = "查询到所有的新闻（分页）", notes = "查询到所有的新闻（分页）")
    public ResultVO<PageInfo<NewsInfoVO>>getAllDyNamInfo(@PathVariable("page") String page,
                                                          @PathVariable("typeId") String typeId) {
        //默认的page为1，默认为0查找全部分类，即按id倒叙（后发布的先显示）
        ResultVO<PageInfo<NewsInfoVO>> resultVO = new ResultVO<>();
        PageInfo<NewsInfoVO> pageInfo;
        try {
            pageInfo = newsService.getpage(Integer.parseInt(page), CommonConst.DEFAULT_PAGE_SIZE,Integer.parseInt(typeId));
        }catch (Exception e){
            e.printStackTrace();
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setResult("搜索失败");
            return resultVO;
        }
        resultVO.setData(pageInfo);
        resultVO.setMessage(ResultVO.SUCCESS);
        return resultVO;
    }


    @RequestMapping(value = "get/new/info/vo/{page}/{userStid}",method = RequestMethod.GET)
    @ApiOperation(value = "查询到指定用户的新闻（分页）", notes = "查询到所有的新闻（分页）")
    public ResultVO<PageInfo<NewsInfoVO>>getDyNamInfoByUserStid(@PathVariable("page") String page,
                                                         @PathVariable("userStid") String userStid) {
        //默认的page为1，默认为0查找全部分类，即按id倒叙（后发布的先显示）
        ResultVO<PageInfo<NewsInfoVO>> resultVO = new ResultVO<>();
        PageInfo<NewsInfoVO> pageInfo;
        try {
            pageInfo = newsService.getPageByUserStid(Integer.parseInt(page),userStid);
        }catch (Exception e){
            e.printStackTrace();
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setResult("搜索失败");
            return resultVO;
        }
        resultVO.setData(pageInfo);
        resultVO.setMessage(ResultVO.SUCCESS);
        return resultVO;
    }

    @RequestMapping(value = "get/new/info/vo/item/{newId}",method = RequestMethod.GET)
    @ApiOperation(value = "根据新闻的Id查询到指定的新闻", notes = "查询到所有的新闻")
    public ResultVO<NewsInfoVO> getNewInfoById(@PathVariable("newId") String newId) {
         int id = Integer.parseInt(newId);
         ResultVO<NewsInfoVO> resultVO = new ResultVO<>();
         NewsInfoVO newsInfoVO =new NewsInfoVO();
        try {
           newsInfoVO = newsService.getDyNamInfoVoById(id);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setResult("搜索失败");
            return resultVO;
        }
        resultVO.setData(newsInfoVO);
        resultVO.setMessage(ResultVO.SUCCESS);
        return resultVO;
    }

    @RequestMapping(value = "get/new/info/vo/{count}",method = RequestMethod.GET)
    @ApiOperation(value = "查询到数量为count的新闻", notes = "查询到数量为count的新闻,用做用户推荐!按照访问数量进行排序！")
    public ResultVO<List<NewsInfoVO>> getCountDyNamInfo(@PathVariable("count") String count) {
        int  i = Integer.parseInt(count);
        ResultVO<List<NewsInfoVO>> resultVO = new ResultVO<>();
        List<NewsInfoVO> list = new ArrayList<>();
        try {
            list = newsService.getbycounts(i);
        }catch (Exception e){
            e.printStackTrace();
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setResult("搜索失败");
            return resultVO;
        }
        resultVO.setMessage(ResultVO.SUCCESS);
        resultVO.setData(list);
        return resultVO;
    }

    @RequestMapping(value = "get/simple/new/info/vo/{count}",method = RequestMethod.GET)
    @ApiOperation(value = "查询到数量为count的简单新闻", notes = "查询到数量为count的简单新闻,用做用户推荐!按照访问数量进行排序！")
    public ResultVO<List<SimpleNewsInfoVO>> getCountSimpleNewInfo(@PathVariable("count") String count) {
        List<SimpleNewsInfoVO> list = null;
        try {
            list = newsService.getCountSimpleNewInfo(Integer.parseInt(count));
        }catch (Exception e){
            e.printStackTrace();
            return new ResultVO<>(ResultVO.FAILED,ResultVO.NO_MSG,null);
        }
        return new ResultVO<>(ResultVO.SUCCESS,ResultVO.NO_MSG,list);
    }



    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "add/new/info",method = RequestMethod.POST)
    @ApiOperation(value = "添加新闻", notes = "添加新闻")
    public ResultVO<String> addNewInfo(@RequestBody NewsInfo newsInfo) {

        //日期字段需要后端自动生成;
        ResultVO resultVO = new ResultVO();
        try {
            Integer resultCode = newsService.setnewinfo(newsInfo);
            if (resultCode == -1){
                resultVO.setResult(ResultVO.FAILED);
                resultVO.setMessage("已经存在同名的新闻，请修改名称后提交！");
                return resultVO;
            }
        }catch (Exception e){
            e.printStackTrace();
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setMessage("插入失败！");
            return resultVO;
        }
        resultVO.setMessage("插入成功");
        resultVO.setResult(ResultVO.SUCCESS);
        return resultVO;
    }

    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "delete/new/info/by/{newId}",method = RequestMethod.GET)
    @ApiOperation(value = "删除新闻", notes = "删除新闻")
    public ResultVO<String> deleteNewInfoById(@PathVariable("newId") String newId) {
        int id = Integer.parseInt(newId);
        ResultVO<String> resultVO = new ResultVO<>();
        try {
            newsService.deletenew(id);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setResult("删除失败");
        }
        resultVO.setMessage("删除成功");
        resultVO.setResult(ResultVO.SUCCESS);
        return resultVO;
    }


    @RequireRole(RoleConst.ROLE_TYPE_USER)
    @RequestMapping(value = "motify/new/info",method = RequestMethod.POST)
    @ApiOperation(value = "修改新闻", notes = "修改新闻")
    public ResultVO<String> motifyDyNam(@RequestBody NewsInfo newsInfo) {
        ResultVO<String> resultVO = new ResultVO<>();
        try {
          Integer resultCode = newsService.updatanew(newsInfo);
            if (resultCode == -1){
                resultVO.setResult(ResultVO.FAILED);
                resultVO.setMessage("已经存在同名的新闻，请修改名称后提交！");
                return resultVO;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
            resultVO.setResult(ResultVO.FAILED);
            resultVO.setMessage("修改失败");
            return resultVO;
        }
        resultVO.setMessage("修改成功");
        resultVO.setResult(ResultVO.SUCCESS);
        return resultVO;
    }


}
