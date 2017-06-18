package com.sayeah.service.function;

import com.sayeah.model.gen.DicomFile;
import com.sayeah.utils.DateUtils;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BIG-JIAN on 2017/6/18.
 */
public class DicomFunction {
    /**
     * 遍历一次检查下的所有dicom文件
     */
    public static List<DicomFile> TraversalStudy(String studyPath) {
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
    public static DicomFile getDicomFile(File file) {
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

    public static DicomFile getDicomFile(InputStream inputStream, String fileName, long fileSize) {
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


    public static DicomFile getDicomFile(String filePath) {
        return getDicomFile(new File(filePath));
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

    private static Attributes readAttributes(InputStream inputStream) throws IOException {
        DicomInputStream in = new DicomInputStream(inputStream);
        try {
            return in.readDataset(-1, -1);
        } finally {
            in.close();
        }
    }


}
