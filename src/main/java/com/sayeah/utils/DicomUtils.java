package com.sayeah.utils;

import com.sayeah.model.common.SOPInstanceModel;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BIG-JIAN on 2017/6/18.
 */
public class DicomUtils {
    /**
     * 遍历一次检查下的所有dicom文件
     */
    public static List<SOPInstanceModel> TraversalStudy(String studyPath) {
        File studyFile = new File(studyPath);
        List<SOPInstanceModel> list = new ArrayList<>();
        if (studyFile != null) {
            File[] sopFileList = studyFile.listFiles();
            for (File f : sopFileList) {
                SOPInstanceModel sopInstanceModel = getSopInstanceModel(f);
                list.add(sopInstanceModel);
            }
        }
        return list;
    }

    /**
     * 读取dcm文件,返回SOPInstanceModel对象，里面包含dcm文件的相关属性
     */
    public static SOPInstanceModel getSopInstanceModel(File file) {
        SOPInstanceModel sopInstanceModel = new SOPInstanceModel();
        try {
            Attributes dcm = readAttributes(file);
            sopInstanceModel.setPatientID(dcm.getString(Tag.PatientID));
            sopInstanceModel.setStudyInstaceUID(dcm.getString(Tag.StudyInstanceUID));
            sopInstanceModel.setSeriesInstanceUID(dcm.getString(Tag.SeriesInstanceUID));
            sopInstanceModel.setSopInstanceUID(dcm.getString(Tag.SOPInstanceUID));
            sopInstanceModel.setModality(dcm.getString(Tag.Modality));
            sopInstanceModel.setStudyDate(dcm.getString(Tag.StudyDate));

            sopInstanceModel.setFileName(file.getName());
            sopInstanceModel.setFileSize(file.length());
            //byte[] bytes = dcm.getBytes(Tag.PixelData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sopInstanceModel;
    }

    public static SOPInstanceModel getSopInstanceModel(String filePath) {
        return getSopInstanceModel(new File(filePath));
    }

    /**
     * 读取dcm文件，返回byte[]
     */
    public static byte[] getBinaryFile(File file) {
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

    public static byte[] readBinaryFile(String filePath) {
        return getBinaryFile(new File(filePath));
    }

    /**
     * 读取Dcm文件，并返回Attributes
     */
    private static Attributes readAttributes(File file) throws IOException {
        DicomInputStream in = new DicomInputStream(file);
        try {
            return in.readDataset(-1, -1);
        } finally {
            in.close();
        }
    }

}
