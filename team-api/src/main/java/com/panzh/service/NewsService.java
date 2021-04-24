package com.panzh.service;

import com.github.pagehelper.PageInfo;
import com.panzh.entity.NewsInfo;
import com.panzh.vo.NewsInfoVO;
import com.panzh.vo.SimpleNewsInfoVO;

import java.util.List;

public interface NewsService {
    PageInfo<NewsInfoVO> getpage(int pageno, int pagesize, int typeid) throws Exception;

    NewsInfoVO  getDyNamInfoVoById(Integer id)throws Exception;

    List<NewsInfoVO> getbycounts(int conut) throws  Exception;

    int setnewinfo(NewsInfo newsInfo) throws  Exception;

    int deletenew(int id)throws Exception;

    int updatanew(NewsInfo newsInfo)throws Exception;

    PageInfo<NewsInfoVO> getPageByUserStid(int parseInt, String userStid);

    List<SimpleNewsInfoVO> getCountSimpleNewInfo(int parseInt);

    void addFindTime(int newId);
}
