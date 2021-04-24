package com.panzh.teamindexservice.mapper;

import com.panzh.entity.StudyInfo;
import com.panzh.vo.StudyInfoVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface StudyInfoMapper extends Mapper<StudyInfo> {


    List<StudyInfo> getAllStudyInfoByTypeId(@Param("typeId") Integer typeId)throws Exception;

    @Select("select * from study_info where study_value like concat('%',#{keyword},'%') or study_name like concat('%',#{keyword},'%') ")
    List<StudyInfo> getAllStudyInfoForEach(String keyword)throws  Exception;

    @Delete("delete from study_info where study_id=#{studyId}")
    void deleteStudyInfoById(Integer studyId)throws Exception;

    @Select("select count(*) from study_info where study_name= #{sName}")
    int studyInfoFindCommonName(String sName);

    @Select("select * from study_info where study_id=#{id} ")
    String getStudyTimeById(int id);

    @Update("update study_info set study_value=#{studyValue},study_name=#{studyName},imge_add=#{imgeAdd},study_link=#{studyLink},user_stid=#{userStid},type_id=#{typeId} where study_id=#{studyId}")
    void modityStudyInfo(StudyInfo studyInfo);

    List<StudyInfoVO> selectStudyInfoVoByUserStid(@Param("userStid") String userStid);
}