package com.panzh.service;

import com.panzh.entity.StudyType;

import java.util.List;

public interface StudyTypeService {

    List<StudyType> showAllstudyType()throws  Exception;

    void addStudyType(StudyType studyType)throws Exception;

    int getStudyTypeCount(String typeName)throws Exception;

    int findCommonName(String typeName)throws Exception;

    void deleteStudyTypeById(Integer typeId)throws Exception;
}
