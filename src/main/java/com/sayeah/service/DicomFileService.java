package com.sayeah.service;

import com.sayeah.dao.DicomFileDAO;
import com.sayeah.model.DicomFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BIG-JIAN on 2017/6/14.
 */
@Service
public class DicomFileService {

    @Autowired
    DicomFileDAO dicomFileDAO;

    // 根据StudyUid名称得到sopList列表
    public List<DicomFile> getSopListFromStudy(String dicomName) {
        return dicomFileDAO.selectByStudyUid(dicomName);
    }

    // 遍历一个Study文件，添加所有dicom文件的
    public int addDicomFile(DicomFile dicomFile){
        return dicomFileDAO.addDicomFile(dicomFile);
    }

}
