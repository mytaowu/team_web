package com.panzh.stringmatch;


import com.alibaba.dubbo.config.annotation.Reference;
import com.panzh.myconst.ProjectParames;
import com.panzh.service.StudyTypeService;
import org.springframework.stereotype.Component;

@Component
public class StudyTypeCountMatch extends BookClassficationNameAbstractMarch{

    @Reference
    private StudyTypeService studyTypeService;

    @Override
    public boolean match(String str) {
        try {
            if (studyTypeService.getStudyTypeCount(str) >= ProjectParames.PAGE_SIZE){
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
