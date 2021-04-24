package com.panzh.stringmatch;

import com.alibaba.dubbo.config.annotation.Reference;
import com.panzh.myconst.ProjectParames;
import com.panzh.service.NewsInfoTypeService;
import org.springframework.stereotype.Component;

/**
 * 判断我们的新闻分类数是否超过长度
 */
@Component
public class NewInfoTypeCountMatch extends BookClassficationNameAbstractMarch {
    @Reference
    private NewsInfoTypeService newsInfoTypeService;

    @Override
    public boolean match(String str) {
        try {
            if (newsInfoTypeService.getCount() >= ProjectParames.PAGE_SIZE){
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (next != null){
            return  next.match(str);
        }else {
            return true;
        }
    }
}
