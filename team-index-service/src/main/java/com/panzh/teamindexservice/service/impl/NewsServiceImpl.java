package com.panzh.teamindexservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panzh.entity.*;
import com.panzh.myconst.CommonConst;
import com.panzh.myconst.ProjectParames;
import com.panzh.service.NewsService;
import com.panzh.teamindexservice.mapper.LabelLinkMapper;
import com.panzh.teamindexservice.mapper.NewsInfoMapper;
import com.panzh.teamindexservice.mapper.UserInfoMapper;
import com.panzh.vo.DyNamVO;
import com.panzh.vo.NewsInfoVO;
import com.panzh.vo.SimpleNewsInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsInfoMapper newsInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    LabelLinkMapper labelLinkMapper;


     //按照id降序排序，按照类型搜索
    public PageInfo<NewsInfoVO> getpage(int pageno, int pagesize, int typeid) throws Exception{
        PageHelper.startPage(pageno,pagesize);
        List<NewsInfoVO> page = newsInfoMapper.selecAllNewsVO(typeid);
        for (NewsInfoVO newsInfoVO : page) {
            //进行内容的处理
            newsInfoVO.setNewValue(newsInfoVO.getNewValue().substring(0,newsInfoVO.getNewValue().length() >= 300 ? 300 : newsInfoVO.getNewValue().length()));
        }
        PageInfo<NewsInfoVO> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }


    public NewsInfoVO  getDyNamInfoVoById(Integer id)throws Exception{
        NewsInfoVO newsInfoVO = newsInfoMapper.selecNewsVObyid(id);
        return newsInfoVO;
    }

    public List<NewsInfoVO> getbycounts(int conut) throws  Exception{
       List<NewsInfoVO> newsInfoVOS = newsInfoMapper.selecAllNewsVO(ProjectParames.DEFULT_CLASSFICATION);
       List<NewsInfoVO> voList = new ArrayList<>();
       if(conut > newsInfoVOS.size()){
           throw  new Exception();
       }else {
           for(int i = 0 ; i < conut ; i++)
               voList.add(newsInfoVOS.get(i));
       }
        return voList;
    }

    public int setnewinfo(NewsInfo newsInfo) throws  Exception{
        NewsInfo info = new NewsInfo();
        info.setNewTitle(newsInfo.getNewTitle());
        //判断是否出现同名的;
        List<NewsInfo> infos = newsInfoMapper.select(info);
        if (infos.size() >= 1){
            return -1;
        }
        Date date = new Date();
        newsInfo.setNewTime(date);
        int i = newsInfoMapper.insert(newsInfo);
        return i;
    }

    public int deletenew(int id)throws Exception{
        //删除新闻会删除他在连接表中所有的信息
        LabelLink labelLink = new LabelLink();
        labelLink.setNewId(id);
        labelLinkMapper.delete(labelLink);

        NewsInfo news = new NewsInfo();
        news.setNewId(id);
        int i = newsInfoMapper.delete(news);

        return i;
    }

    public int updatanew(NewsInfo newsInfo)throws Exception{
        NewsInfo info = new NewsInfo();
        //title唯一，不可能出现多个；
        info.setNewTitle(newsInfo.getNewTitle());
        newsInfo.setNewTime(new Date());
        //判断是否出现同名的;
        List<NewsInfo> infos = newsInfoMapper.select(info);
        if (infos.size() >= 1){
            if (Objects.equals(infos.get(0).getNewId(),newsInfo.getNewId())){
                int i =  newsInfoMapper.updateByPrimaryKey(newsInfo);
                return i;
            }
            return -1;
        }
        int i =  newsInfoMapper.updateByPrimaryKey(newsInfo);
        return i;
    }

    @Override
    public PageInfo<NewsInfoVO> getPageByUserStid(int pageno, String userStid) {
        PageHelper.startPage(pageno, CommonConst.DEFAULT_PAGE_SIZE);
        List<NewsInfoVO> page = newsInfoMapper.selecNewsVOByUserStid(userStid);
        PageInfo<NewsInfoVO> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    @Override
    public List<SimpleNewsInfoVO> getCountSimpleNewInfo(int count) {
        return newsInfoMapper.selectSimpleNewInfoByCount(count);
    }

    @Override
    public void addFindTime(int newId) {
        newsInfoMapper.addFindTime(newId);
    }

}
