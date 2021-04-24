package com.panzh.teamindexservice.mapper;

import com.panzh.entity.NewsInfo;
import com.panzh.vo.NewsInfoVO;
import com.panzh.vo.SimpleNewsInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface NewsInfoMapper extends Mapper<NewsInfo> {
    List<NewsInfoVO> selecAllNewsVO(@Param("typeId") Integer typeId);

    NewsInfoVO selecNewsVObyid(Integer id);

    List<NewsInfoVO> selecNewsVOByUserStid(@Param("userStid")String userStid);

    List<SimpleNewsInfoVO> selectSimpleNewInfoByCount(int count);

    @Update("update news_info set news_info.new_times = news_info.new_times + 1 where news_info.new_id = #{newId}")
    void addFindTime(int newId);
}