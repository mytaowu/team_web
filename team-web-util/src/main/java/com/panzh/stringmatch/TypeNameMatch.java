package com.panzh.stringmatch;

import com.alibaba.dubbo.config.annotation.Reference;
import com.panzh.service.ResuleInfoService;
import com.panzh.service.ResultTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: TMingYi
 * @date: 2020/5/13 16:47
 */
@Component
public class TypeNameMatch extends BookClassficationNameAbstractMarch {

    @Reference
    private ResultTypeService resultTypeService;

    @Override
    public boolean match(String str) {
        try {
            if (resultTypeService.findCommonName(str) == 1){
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
