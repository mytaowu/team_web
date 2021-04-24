package com.panzh.funcationInterface;

import com.github.pagehelper.PageInfo;
import com.panzh.utilbean.ResultVO;

import java.util.List;

public interface Handler<T> {

    //增加信息
    ResultVO<String> add(T t);

    //删除信息
    ResultVO<String> remove(Integer id);

    //查询信息
    ResultVO<T> findInfo(Integer id);


    //修改信息
    ResultVO<String> modify(T t);

    //查询信息
    ResultVO<List<T>> findAll();

    //分页查询信息
    ResultVO<PageInfo<T>> findAllByPage(Integer pageNo, String keyword);

}
