package com.panzh.teamindexservice.mapper;

import com.panzh.entity.ResultType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ResultTypeMapper extends Mapper<ResultType> {
    @Select("select * from result_type")
    List<ResultType> showAllResuleType()throws Exception;


    @Insert("insert into result_type(type_id,type_name) select #{typeId},#{typeName} from DUAL where not exists(select type_id from result_type where type_name=#{typeName}) ")
    void addResultType(ResultType resultType)throws Exception;

    @Delete("delete from result_type where type_id=#{typeId}")
    void deleteResultTypeById(Integer typeId)throws Exception;

    @Select("select count(*) from result_type")
    int getTypeCount(String str);

    @Select("select count(*) from result_type where type_name=#{typeName}")
    int findCommonName(String typeName);
}