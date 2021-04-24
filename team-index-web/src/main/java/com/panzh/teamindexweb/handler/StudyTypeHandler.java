package com.panzh.teamindexweb.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.panzh.annotaion.RequireRole;
import com.panzh.emun.RoleConst;
import com.panzh.entity.LabelInfo;
import com.panzh.entity.NewType;
import com.panzh.entity.StudyType;
import com.panzh.myconst.CommonConst;
import com.panzh.service.StudyTypeService;
import com.panzh.stringmatch.BookClassficationNameChain;
import com.panzh.stringmatch.StudyTypeCountMatch;
import com.panzh.stringmatch.StudyTypeNameMatch;
import com.panzh.utilbean.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: TMingYi
 * @date: 2020/5/9 10:23
 * 刘世雄负责
 */
@RestController
@Api(value = "学习资源分类操作表")
public class StudyTypeHandler {

    @Reference
    private StudyTypeService studyTypeService;

    @Autowired(required = false)
    private BookClassficationNameChain bookClassficationNameChain;

    @RequestMapping(value = "show/all/study/type",method = RequestMethod.GET)
    @ApiOperation(value = "展示所有的学习资源分类", notes = "展示所有的学习资源分类")
    public ResultVO<List<StudyType>> showAllstudyType() {

        ResultVO resultVO = new ResultVO();
        List<StudyType> studyTypes=null;
        try {
            studyTypes = studyTypeService.showAllstudyType();
            resultVO.setData(studyTypes);
        } catch (Exception e) {
            e.printStackTrace();
            return new  ResultVO<List<StudyType>>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED,studyTypes);
        }
        return new  ResultVO<List<StudyType>>(ResultVO.SUCCESS, CommonConst.GET_INFORMATION_SUCCESS,studyTypes);
    }

    @RequireRole(RoleConst.ROLE_TYPE_ADMIN)
    @RequestMapping(value = "add/study/type/type",method = RequestMethod.POST)
    @ApiOperation(value = "增加学习资源分类", notes = "增加学习资源分类")
    public ResultVO<String> addStudyType(@RequestBody StudyType studyType) {
        //添加的时候检查是否有重名的，可以做一个限定，如果数据库中的数据大于多少条，就拒绝插入
        boolean match = bookClassficationNameChain.Match(studyType.getTypeName(), StudyTypeCountMatch.class, StudyTypeNameMatch.class);
        if (match==false){
            return new ResultVO<String>(ResultVO.FAILED,CommonConst.FAILED_TO_ADD_RESOURCE_CLASS,ResultVO.NO_DATA);
        }else{
            try {
                studyTypeService.addStudyType(studyType);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultVO<String>(ResultVO.FAILED,CommonConst.FAILED_TO_ADD_RESOURCE_CLASS,ResultVO.NO_DATA);
            }
        }
        return new ResultVO<String>(ResultVO.SUCCESS,CommonConst.SUCCESS_TO_ADD_RESOURCE_CLASS,ResultVO.NO_DATA);
    }

    @RequireRole(RoleConst.ROLE_TYPE_ADMIN)
    @RequestMapping(value = "delete/study/type/{typeId}",method = RequestMethod.POST)
    @ApiOperation(value = "删除学习资源分类", notes = "删除学习资源分类")
    public ResultVO<String> deleteStudyTypeById(@PathVariable("typeId")String typeId) {
        Integer parseInt = Integer.parseInt(typeId);
        try {
            studyTypeService.deleteStudyTypeById(parseInt);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<String>(ResultVO.FAILED,CommonConst.DELETE_INFORMATION_EXCEPTION,ResultVO.NO_DATA);
        }
        return new ResultVO<String>(ResultVO.SUCCESS,CommonConst.DELETE_INFORMATION_SUCCESS,ResultVO.NO_DATA);
    }

}
