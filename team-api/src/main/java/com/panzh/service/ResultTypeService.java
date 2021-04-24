package com.panzh.service;

import com.panzh.entity.ResultType;

import java.util.List;

public interface ResultTypeService {
    List<ResultType> showAllResuleType() throws Exception;

    void addResultType(ResultType resultType)throws Exception;

    void deleteResultTypeById(Integer typeId)throws Exception;

    int getTypeCount(String str)throws Exception;

    int findCommonName(String str) throws Exception;
}
