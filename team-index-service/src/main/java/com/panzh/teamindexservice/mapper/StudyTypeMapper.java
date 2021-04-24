package com.panzh.teamindexservice.mapper;

import com.panzh.entity.StudyType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudyTypeMapper extends Mapper<StudyType> {

    @Select("select * from study_type")
    List<StudyType> showAllstudyType()throws  Exception;

    @Select("select count(*) from study_type")
    int getStudyTypeCount(String typeName)throws Exception;

    @Select("select count(*) from study_type where type_name=#{typeName}")
    int findCommonName(String typeName)throws Exception;

    @Delete("delete from study_type where type_id=#{typeId}")
    void deleteStudyTypeById(Integer typeId)throws Exception;
}