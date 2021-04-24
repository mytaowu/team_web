package com.panzh.service;

import com.github.pagehelper.PageInfo;

import com.panzh.entity.DyNam;
import com.panzh.utilbean.ResultVO;
import com.panzh.vo.DyNamVO;

import java.util.List;

public interface DyNamService {
    PageInfo<DyNamVO> getall(int pageno, int pagesize) throws Exception;

    DyNamVO getbyid(int id);

    List<DyNamVO> getbycounts(int conut) throws Exception;

    int setDyNam(DyNam dyNam) throws Exception;

    int deletedynam(int dynamid) throws Exception;

    int updatadynam(DyNam dyNam) throws Exception;

    PageInfo<DyNamVO> getallByUserStid(int pageno, int pagesize, String userStid);
}
