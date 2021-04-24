package com.panzh.teamindexservice.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panzh.entity.DyNam;
import com.panzh.entity.NewsInfo;
import com.panzh.entity.UserInfo;
import com.panzh.service.DyNamService;
import com.panzh.teamindexservice.mapper.DyNamMapper;
import com.panzh.teamindexservice.mapper.UserInfoMapper;
import com.panzh.utilbean.ResultVO;
import com.panzh.vo.DyNamVO;
import com.panzh.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.concurrent.CountDownLatch;


@Service
public class DyNamSerxiceImpl implements DyNamService {

    @Autowired
    private DyNamMapper dyNamMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;


    //搜索全部
    public PageInfo<DyNamVO> getall(int pageno, int pagesize) throws Exception {
        PageHelper.startPage(pageno, pagesize);
        List<DyNamVO> dataBaseList = dyNamMapper.selectAllDyNameVO();
        //记录返回数据的集合：
        List<DyNamVO> resultList = new ArrayList<>();
        Iterator<DyNamVO> iterator = dataBaseList.iterator();

        while (iterator.hasNext()) {
            DyNamVO vo = iterator.next();
            if (vo.getDyNamValue().length() >= 300) {
                vo.setDyNamValue(vo.getDyNamValue().substring(0, 300));
            }
            resultList.add(vo);
        }
        dataBaseList.clear();
        for (DyNamVO dyNamVO : resultList) {
            dataBaseList.add(dyNamVO);
        }

        PageInfo<DyNamVO> pageInfo = new PageInfo<>(dataBaseList);
        return pageInfo;
    }

    //通过id
    public DyNamVO getbyid(int id) {
        DyNamVO dyNamVO = dyNamMapper.selectoneDyNameVO(id);
        if (dyNamVO == null) return null;
        return dyNamVO;
    }

    //根据返回输入数量返回显示条数
    public List<DyNamVO> getbycounts(int conut) throws Exception {
        List<DyNamVO> list = dyNamMapper.selectDyNameVoByCount(conut);
        Iterator<DyNamVO> iterator = list.iterator();
        List<DyNamVO> resultList = new ArrayList<>();

        while (iterator.hasNext()) {
            DyNamVO vo = iterator.next();
            vo.setDyNamValue(" ");
            vo.setUserInfo(null);
            resultList.add(vo);
        }
        return list;
    }

    //插入动态
    public int setDyNam(DyNam dyNam) throws Exception {
        int i = dyNamMapper.insert(dyNam);
        return i;
    }

    public int deletedynam(int dynamid) throws Exception {

        DyNam dyNam = new DyNam();
        dyNam.setDyNamId(dynamid);
        int i = dyNamMapper.delete(dyNam);

        return i;
    }

    public int updatadynam(DyNam dyNam) throws Exception {
        DyNam nam = new DyNam();
        //title唯一，不可能出现多个；
        nam.setDyNamTitle(dyNam.getDyNamTitle());
        //判断是否出现同名的;
        List<DyNam> infos = dyNamMapper.select(nam);
        if (infos.size() >= 1) {
            if (Objects.equals(infos.get(0).getDyNamId(), dyNam.getDyNamId())) {
                int i = dyNamMapper.updateByPrimaryKey(dyNam);
                return i;
            }
            return -1;
        }
        int i = dyNamMapper.updateByPrimaryKey(dyNam);
        return i;
    }

    @Override
    public PageInfo<DyNamVO> getallByUserStid(int pageno, int pagesize, String userStid) {
        PageHelper.startPage(pageno, pagesize);
        List<DyNamVO> dataBaseList = dyNamMapper.selectDyNameVOByUserStid(userStid);
        //节约网络传输的流量
        for (DyNamVO dyNamVO : dataBaseList) {
            dyNamVO.setDyNamValue(null);
        }
        PageInfo<DyNamVO> pageInfo = new PageInfo<>(dataBaseList);
        return pageInfo;
    }

}
