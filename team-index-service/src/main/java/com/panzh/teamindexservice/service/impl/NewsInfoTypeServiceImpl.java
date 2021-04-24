package com.panzh.teamindexservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.panzh.entity.NewType;
import com.panzh.service.NewsInfoTypeService;
import com.panzh.teamindexservice.mapper.NewsInfoTypeMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: TMingYi
 * @date: 2020/6/6 18:18
 */
@Service
public class NewsInfoTypeServiceImpl implements NewsInfoTypeService {

    @Autowired
    private NewsInfoTypeMapper newsInfoTypeMapper;

    @Override
    public Integer newInfoFindCommonName(String str) {
        return newsInfoTypeMapper.getCommonNameCount(str);
    }

    @Override
    public Integer getCount() {
        return newsInfoTypeMapper.getCount();
    }

    @Override
    public void deleteNewInfoType(String typeId) {
        NewType type = new NewType();
        type.setTypeId(Integer.parseInt(typeId));
        newsInfoTypeMapper.deleteByPrimaryKey(type);
    }

    @Override
    public void addNewTypeInfo(NewType newType) {
        newsInfoTypeMapper.insert(newType);
    }

    @Override
    public List<NewType> getAllNewInfoType() {
        return newsInfoTypeMapper.selectAll();
    }
}
