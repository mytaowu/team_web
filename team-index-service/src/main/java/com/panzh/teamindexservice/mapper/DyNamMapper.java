package com.panzh.teamindexservice.mapper;

import com.panzh.entity.DyNam;
import com.panzh.vo.DyNamVO;
import com.panzh.entity.UserInfo;
import com.panzh.vo.DyNamVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DyNamMapper extends Mapper<DyNam> {
    List<DyNamVO> selectAllDyNameVO();

    DyNamVO selectoneDyNameVO(Integer id);

    List<DyNamVO> selectDyNameVOByUserStid(@Param("userStid") String userStid);

    List<DyNamVO> selectDyNameVoByCount(int conut);
}