package com.panzh.stringmatch;

import com.alibaba.dubbo.config.annotation.Reference;
import com.panzh.myconst.ProjectParames;
import com.panzh.service.ResultTypeService;
import org.springframework.stereotype.Component;

/**
 * @author: TMingYi
 * @date: 2020/5/13 16:49
 */
@Component
public class TypeLengthMatch extends BookClassficationNameAbstractMarch {

    @Reference
    private ResultTypeService resultTypeService;

    @Override
    public boolean match(String str) {
        try {
            if (resultTypeService.getTypeCount(str) >= ProjectParames.PAGE_SIZE){
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
