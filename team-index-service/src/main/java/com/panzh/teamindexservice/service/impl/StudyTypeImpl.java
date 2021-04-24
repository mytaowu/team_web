package com.panzh.teamindexservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.panzh.entity.StudyType;
import com.panzh.service.StudyTypeService;
import com.panzh.teamindexservice.mapper.StudyTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class StudyTypeImpl implements StudyTypeService {

    @Autowired
    private StudyTypeMapper studyTypeMapper;

    @Override
    public List<StudyType> showAllstudyType() throws Exception {
        List<StudyType> studyTypes = studyTypeMapper.showAllstudyType();
        return studyTypes;
    }

    @Override
    public void addStudyType(StudyType studyType) throws Exception {
        studyTypeMapper.insert(studyType);
    }

    @Override
    public int getStudyTypeCount(String typeName) throws Exception {
        return studyTypeMapper.getStudyTypeCount(typeName);
    }

    @Override
    public int findCommonName(String typeName) throws Exception {
        return studyTypeMapper.findCommonName(typeName);
    }

    @Override
    public void deleteStudyTypeById(Integer typeId) throws Exception {
        studyTypeMapper.deleteStudyTypeById(typeId);
    }
}
