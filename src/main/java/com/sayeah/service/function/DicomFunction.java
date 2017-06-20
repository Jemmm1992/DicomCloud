package com.sayeah.service.function;

import com.sayeah.dao.DicomFileDAO;
import com.sayeah.dao.StudyDAO;
import com.sayeah.model.gen.DicomFile;
import com.sayeah.model.gen.StudyFile;
import com.sayeah.utils.DateUtils;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BIG-JIAN on 2017/6/18.
 */
@Service
public class DicomFunction {
    @Autowired
    StudyDAO studyDAO;

    @Autowired
    DicomFileDAO dicomFileDAO;



    public void mappingStudyFile(String studyPath){
        StudyFile studyFile = new StudyFile();
        // TODO: 2017/6/18 插入用户名的id
        studyFile.setUserId(1);
        studyFile.setBigFileId(1);

        File file = new File(studyPath);
        studyFile.setFolderName(file.getName());

        List<DicomFile> dicomFiles = TraversalStudy(studyPath);

        if(dicomFiles!=null){
            DicomFile dicomFile = dicomFiles.get(0);
            studyFile.setStudyDate(dicomFile.getStudyDate());
            studyFile.setModality(dicomFile.getModality());
            studyFile.setStudyUid(dicomFile.getStudyUid());
            studyFile.setPatientUid(dicomFile.getPatientUid());
            studyFile.setStudyFileSize(0L);
            studyFile.setCreateDate(new Date());
            studyFile.setIshdfs(0);
        }
        String str = DateUtils.dateToString(studyFile.getStudyDate());
        studyFile.setUrl(str+"_"+studyFile.getModality());
        studyDAO.addStudyFile(studyFile);

        //插入数据库后获取到主键ID
        studyFile.getId();

        for (DicomFile dicomFile:dicomFiles) {
            dicomFile.setUserId(studyFile.getUserId());
            dicomFile.setStudyFileId(studyFile.getId());
            dicomFile.setCreatedDate(new Date());
            dicomFileDAO.addDicomFile(dicomFile);
        }
    }


    /**
     * 遍历一次检查下的所有dicom文件
     */
    public List<DicomFile> TraversalStudy(String studyPath) {
        File studyFile = new File(studyPath);
        List<DicomFile> list = new ArrayList<>();
        if (studyFile != null) {
            File[] sopFileList = studyFile.listFiles();
            for (File f : sopFileList) {
                DicomFile sopInstanceModel = getDicomFile(f);
                list.add(sopInstanceModel);
            }
        }
        return list;
    }

    /**
     * 读取dcm文件,返回DicomFile对象，里面包含dcm文件的相关属性
     */
    public DicomFile getDicomFile(File file) {
        DicomFile dicomFile = new DicomFile();
        try {
            Attributes dcm = readAttributes(file);
            dicomFile.setPatientUid(dcm.getString(Tag.PatientID));
            dicomFile.setStudyUid(dcm.getString(Tag.StudyInstanceUID));
            dicomFile.setSeriesUid(dcm.getString(Tag.SeriesInstanceUID));
            dicomFile.setSopUid(dcm.getString(Tag.SOPInstanceUID));
            dicomFile.setModality(dcm.getString(Tag.Modality));
            dicomFile.setStudyDate(DateUtils.stringToDate(dcm.getString(Tag.StudyDate)));

            dicomFile.setDicomName(file.getName());
            dicomFile.setFileSize(file.length());
            //byte[] bytes = dcm.getBytes(Tag.PixelData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dicomFile;
    }

    public DicomFile getDicomFile(InputStream inputStream, String fileName, long fileSize) {
        DicomFile dicomFile = new DicomFile();
        try {
            Attributes dcm = readAttributes(inputStream);
            dicomFile.setPatientUid(dcm.getString(Tag.PatientID));
            dicomFile.setStudyUid(dcm.getString(Tag.StudyInstanceUID));
            dicomFile.setSeriesUid(dcm.getString(Tag.SeriesInstanceUID));
            dicomFile.setSopUid(dcm.getString(Tag.SOPInstanceUID));
            dicomFile.setModality(dcm.getString(Tag.Modality));
            dicomFile.setStudyDate(DateUtils.stringToDate(dcm.getString(Tag.StudyDate)));

            dicomFile.setDicomName(fileName);
            dicomFile.setFileSize(fileSize);
            //byte[] bytes = dcm.getBytes(Tag.PixelData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dicomFile;
    }


    public DicomFile getDicomFile(String filePath) {
        return getDicomFile(new File(filePath));
    }

    /**
     * 读取dcm文件，返回byte[]
     */
    public byte[] getBinaryFile(File file) {
        BufferedInputStream bf = null;
        try {
            bf = new BufferedInputStream(new FileInputStream(file));
            byte[] data = new byte[bf.available()];
            bf.read(data);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public byte[] readBinaryFile(String filePath) {
        return getBinaryFile(new File(filePath));
    }

    /**
     * 读取Dcm文件，并返回Attributes
     */
    private Attributes readAttributes(File file) throws IOException {
        DicomInputStream in = new DicomInputStream(file);
        try {
            return in.readDataset(-1, -1);
        } finally {
            in.close();
        }
    }

    private Attributes readAttributes(InputStream inputStream) throws IOException {
        DicomInputStream in = new DicomInputStream(inputStream);
        try {
            return in.readDataset(-1, -1);
        } finally {
            in.close();
        }
    }


}
