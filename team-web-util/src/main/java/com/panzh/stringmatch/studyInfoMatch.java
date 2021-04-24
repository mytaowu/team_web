package com.panzh.stringmatch;

import com.alibaba.dubbo.config.annotation.Reference;
import com.panzh.service.StudyInfoService;
import org.springframework.stereotype.Component;

@Component
public class studyInfoMatch  extends BookClassficationNameAbstractMarch{

    @Reference
    private StudyInfoService studyInfoService;


    @Override
    public boolean match(String str) {
        try {
            if (studyInfoService.studyInfoFindCommonName(str) == 1){
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
