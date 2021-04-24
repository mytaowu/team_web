package com.panzh.teamindexweb.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.panzh.annotaion.RequireRole;
import com.panzh.emun.RoleConst;
import com.panzh.entity.ResultType;
import com.panzh.entity.StudyType;
import com.panzh.myconst.CommonConst;
import com.panzh.service.ResultTypeService;
import com.panzh.stringmatch.BookClassficationNameChain;
import com.panzh.stringmatch.TypeLengthMatch;
import com.panzh.stringmatch.TypeNameMatch;
import com.panzh.utilbean.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: TMingYi
 * @date: 2020/5/9 10:49
 * 刘世雄负责
 */
@RestController
@Api(value = "成果分类表的操作")
public class ResultTypeHandler {

    @Reference
    private ResultTypeService resultTypeService;

    @Autowired(required = false)
    private BookClassficationNameChain bookClassficationNameChain;

    @RequestMapping(value = "show/all/resule/type",method = RequestMethod.GET)
    @ApiOperation(value = "展示所有的成果分类", notes = "展示所有的成果分类")
    public ResultVO<List<ResultType>> showAllResuleType() {
        ResultVO resultVO = new ResultVO();
        List<ResultType> resultTypes =null;
        try {
             resultTypes = resultTypeService.showAllResuleType();
            resultVO.setData(resultTypes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<List<ResultType>>(ResultVO.FAILED, CommonConst.GET_INFORMATION_FAILED,resultTypes);
        }
            return new ResultVO<List<ResultType>>(ResultVO.SUCCESS,CommonConst.GET_INFORMATION_SUCCESS,resultTypes);
    }

    @RequireRole(RoleConst.ROLE_TYPE_ADMIN)
    @RequestMapping(value = "add/result/type",method = RequestMethod.POST)
    @ApiOperation(value = "增加成果分类", notes = "增加学习成果分类")
    public ResultVO<String> addResultType(@RequestBody ResultType resultType) {
        //添加的时候检查是否有重名的，可以做一个限定，如果数据库中的数据大于多少条，就拒绝插入
        boolean match = bookClassficationNameChain.Match(resultType.getTypeName(), TypeLengthMatch.class, TypeNameMatch.class);
        if (match==false){
            return new ResultVO<String>(ResultVO.FAILED,CommonConst.DATA_SAVING_FAILED,ResultVO.NO_DATA);
        }else {
            try {
                resultTypeService.addResultType(resultType);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultVO<String>(ResultVO.FAILED,CommonConst.DATA_SAVING_FAILED,ResultVO.NO_DATA);
            }
            return new ResultVO<String>(ResultVO.SUCCESS,CommonConst.INFORMATION_SAVED_SUCCESSFULLY,ResultVO.NO_DATA);
        }
    }

    @RequireRole(RoleConst.ROLE_TYPE_ADMIN)
    @RequestMapping(value = "delete/result/type/{typeId}",method = RequestMethod.POST)
    @ApiOperation(value = "删除成果分类", notes = "删除成果分类")
    public ResultVO<String> deleteResultTypeById(@PathVariable("typeId")String typeId) {
        Integer parseInt = Integer.parseInt(typeId);
        try {
            resultTypeService.deleteResultTypeById(parseInt);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO<String>(ResultVO.FAILED,CommonConst.DELETE_INFORMATION_EXCEPTION,ResultVO.NO_DATA);
        }
        return new ResultVO<String>(ResultVO.SUCCESS,CommonConst.DELETE_INFORMATION_SUCCESS,ResultVO.NO_DATA);
    }
}
