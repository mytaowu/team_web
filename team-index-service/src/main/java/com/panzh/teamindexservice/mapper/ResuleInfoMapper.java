package com.panzh.teamindexservice.mapper;

import com.panzh.entity.ResuleInfo;
import com.panzh.vo.ResuleInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ResuleInfoMapper extends Mapper<ResuleInfo> {


    List<ResuleInfo>  getAllresuleInfoVoByTypeId(@Param("typeId") Integer typeId)throws Exception;

    @Select("select * from resule_info where result_name like concat('%',#{keyword},'%') or result_value like concat('%',#{keyword},'%')")
    List<ResuleInfo> getAllresuleInfoVoForEach(String keyword)throws Exception;

    @Select("select count(*) from resule_info where result_name={rName}")
    int ResuleInfoFindCommonName(String rName)throws Exception;

    List<ResuleInfoVO> selectResultInfoVoByUserStid(@Param("userStid")String userStid);

    List<ResuleInfoVO> getResuleInfoVoForCount(Integer count);
}