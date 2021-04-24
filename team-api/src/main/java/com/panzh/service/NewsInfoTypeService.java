package com.panzh.service;

import com.panzh.entity.NewType;

import java.util.List;

/**
 * @author: TMingYi
 * @date: 2020/6/6 18:02
 */
public interface NewsInfoTypeService {
    Integer newInfoFindCommonName(String str);

    Integer getCount();

    void deleteNewInfoType(String typeId);

    void addNewTypeInfo(NewType newType);

    List<NewType> getAllNewInfoType();
}
