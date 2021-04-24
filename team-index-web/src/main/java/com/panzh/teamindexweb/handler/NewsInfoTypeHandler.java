package com.panzh.teamindexweb.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.panzh.annotaion.RequireRole;
import com.panzh.emun.RoleConst;
import com.panzh.entity.LabelInfo;
import com.panzh.entity.NewType;
import com.panzh.entity.StudyType;
import com.panzh.myconst.CommonConst;
import com.panzh.service.NewsInfoTypeService;
import com.panzh.stringmatch.BookClassficationNameChain;
import com.panzh.stringmatch.NewInfoTypeCountMatch;
import com.panzh.stringmatch.NewInfoTypeNameMatch;
import com.panzh.utilbean.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Table;
import java.awt.*;
import java.util.List;

/**
 * @author: TMingYi
 * @date: 2020/5/9 10:05
 * 林荣杭负责
 */
@RestController
@Api("我们操作新闻分类的类！")
public class NewsInfoTypeHandler {

    @Reference
    private NewsInfoTypeService newsInfoTypeService;

    @Autowired
    private BookClassficationNameChain bookClassficationNameChain;


    @RequestMapping(value = "show/all/new/type",method = RequestMethod.GET)
    @ApiOperation(value = "展示所有的新闻分类", notes = "展示所有的新闻分类")
    public ResultVO<List<NewType>> showAllNewType() {
        List<NewType> newTypes =  newsInfoTypeService.getAllNewInfoType();
        return new  ResultVO<List<NewType>>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS,newTypes);
    }

    @RequestMapping(value = "show/all/new/tag",method = RequestMethod.GET)
    @ApiOperation(value = "展示所有的新闻标签", notes = "展示所有的新闻标签")
    public ResultVO<List<LabelInfo>> showAllNewTag() {

        return null;
    }


    @RequireRole(RoleConst.ROLE_TYPE_ADMIN)
    @RequestMapping(value = "add/new/type",method = RequestMethod.POST)
    @ApiOperation(value = "增加新闻分类", notes = "增加新闻分类")
    public ResultVO<String> addNewType(@RequestBody NewType newType) {
        //添加的时候检查是否有重名的，可以做一个限定，如果数据库中的数据大于多少条，就拒绝插入
         if (bookClassficationNameChain.Match(newType.getTypeName(), NewInfoTypeCountMatch.class, NewInfoTypeNameMatch.class)){
             //执行插入的操作；
             newsInfoTypeService.addNewTypeInfo(newType);
             return new  ResultVO<String>(ResultVO.SUCCESS, CommonConst.INFORMATION_UPDATED_SUCCESSFULLY,ResultVO.NO_DATA);
         }else {
             return new  ResultVO<String>(ResultVO.FAILED, CommonConst.CALSSFICATION_TO_LONG_OR_ISEXIST,ResultVO.NO_DATA);
         }
    }

    @RequireRole(RoleConst.ROLE_TYPE_ADMIN)
    @RequestMapping(value = "add/new/tag",method = RequestMethod.POST)
    @ApiOperation(value = "增加新闻标签", notes = "增加新闻标签")
    public ResultVO<String> addNewTag(@RequestBody LabelInfo labelInfo) {
        //添加的时候检查是否有重名的，可以做一个限定，如果数据库中的数据大于多少条，就拒绝插入
        return null;
    }


    @RequireRole(RoleConst.ROLE_TYPE_ADMIN)
    @RequestMapping(value = "delete/new/type/{typeId}",method = RequestMethod.POST)
    @ApiOperation(value = "删除新闻分类", notes = "删除新闻分类")
    public ResultVO<String> deleteNewTypeById(@PathVariable("typeId")String typeId) {
        newsInfoTypeService.deleteNewInfoType(typeId);
        return new  ResultVO<String>(ResultVO.SUCCESS, CommonConst.DELETE_INFORMATION_SUCCESS,ResultVO.NO_DATA);
    }

    @RequireRole(RoleConst.ROLE_TYPE_ADMIN)
    @RequestMapping(value = "delete/new/tag/{tagId}",method = RequestMethod.POST)
    @ApiOperation(value = "删除新闻标签", notes = "删除新闻标签")
    public ResultVO<String> deleteNewTagById(@PathVariable("tagId")String tagId) {
//        这里要检查数据库中是否还有新闻引用了这个标签，如果有，则拒绝删除
        return null;
    }
}
