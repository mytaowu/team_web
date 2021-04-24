package com.panzh.mapper;

import com.panzh.entity.NewType;
import java.util.List;

public interface NewTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(NewType record);

    NewType selectByPrimaryKey(Integer typeId);

    List<NewType> selectAll();

    int updateByPrimaryKey(NewType record);
}