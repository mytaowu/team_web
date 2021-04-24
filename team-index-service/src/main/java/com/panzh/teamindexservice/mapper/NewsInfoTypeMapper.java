package com.panzh.teamindexservice.mapper;


import com.panzh.entity.NewType;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: TMingYi
 * @date: 2020/6/6 18:18
 */

public interface NewsInfoTypeMapper extends Mapper<NewType> {
    @Select("select count(*) from new_type")
    Integer getCount();

    @Select("select count(*) from new_type where type_name={str}")
    Integer getCommonNameCount(String str);
}
