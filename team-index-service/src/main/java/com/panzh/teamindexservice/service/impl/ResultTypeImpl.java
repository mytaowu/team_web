package com.panzh.teamindexservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.panzh.entity.ResultType;
import com.panzh.service.ResultTypeService;
import com.panzh.teamindexservice.mapper.ResultTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ResultTypeImpl implements ResultTypeService {
    @Autowired
    private ResultTypeMapper resultTypeMapper;

    @Override
    public List<ResultType> showAllResuleType() throws Exception {
        List<ResultType> resultTypes = resultTypeMapper.showAllResuleType();
        return resultTypes;
    }

    @Override
    public void addResultType(ResultType resultType) throws Exception {
        resultTypeMapper.addResultType(resultType);
    }

    @Override
    public void deleteResultTypeById(Integer typeId) throws Exception {
        resultTypeMapper.deleteResultTypeById(typeId);
    }

    @Override
    public int getTypeCount(String str) throws Exception {
        return resultTypeMapper.getTypeCount(str);
    }

    @Override
    public int findCommonName(String str) throws Exception {
        return resultTypeMapper.findCommonName(str);
    }
}
