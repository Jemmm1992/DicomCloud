package com.sayeah.service.api.impl;

import com.sayeah.dao.DicomFileDAO;
import com.sayeah.dao.StudyDAO;
import com.sayeah.model.common.ReturnMsgVo;
import com.sayeah.model.gen.DicomFile;
import com.sayeah.service.api.IDicomFileApi;
import com.sayeah.service.function.DicomFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * Created by BIG-JIAN on 2017/6/17.
 */
@Service
public class DicomFileApi implements IDicomFileApi {
    @Autowired
    DicomFunction dicomFunction;

    @Autowired
    StudyDAO studyDAO;

    @Autowired
    DicomFileDAO dicomFileDAO;

    public ReturnMsgVo<Object> test(String studyPath) {
        List<DicomFile> dicomFiles = dicomFunction.TraversalStudy(studyPath);
        return null;
    }

    @Override
    public ReturnMsgVo<Object> uploadFile(InputStream inputStream, String name) {

        return null;
    }
}
