package com.panzh.stringmatch;

import com.alibaba.dubbo.config.annotation.Reference;
import com.panzh.service.NewsInfoTypeService;
import com.panzh.service.ResuleInfoService;
import org.springframework.stereotype.Component;

/**
 * 判断我们的新闻分类名称是否有重复的
 */
@Component
public class NewInfoTypeNameMatch extends BookClassficationNameAbstractMarch {
    @Reference
    private NewsInfoTypeService newsInfoTypeService;

    @Override
    public boolean match(String str) {
        try {
            if (newsInfoTypeService.newInfoFindCommonName(str) == 1){
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
