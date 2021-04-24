package com.panzh.stringmatch;

import com.alibaba.dubbo.config.annotation.Reference;
import com.panzh.service.ResuleInfoService;
import org.springframework.stereotype.Component;

@Component
public class resuleInfoMatch extends BookClassficationNameAbstractMarch {
    @Reference
    private ResuleInfoService resuleInfoService;


    @Override
    public boolean match(String str) {
        try {
            if (resuleInfoService.ResuleInfoFindCommonName(str) == 1){
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
