package com.sayeah;

import com.sayeah.dao.DicomFileDAO;
import com.sayeah.model.DicomFile;
import com.sayeah.service.DicomFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DicomFileTests {

    @Autowired
    DicomFileDAO dicomFileDAO;

    @Autowired
    DicomFileService dicomFileService;

    @Test
    public void insertDicom() {
        Random random = new Random();
        for (int i = 0; i < 12; ++i) {
            DicomFile dicomFile = new DicomFile();
            dicomFile.setUserId(1);
            dicomFile.setDicomName("1.2.3." + random.nextInt(100));
            dicomFile.setStudyDate(new Date());
            dicomFile.setModality("CT");
            dicomFile.setBigFileId(i);
            dicomFile.setFileSize(random.nextInt(10000));
            dicomFile.setSopUid("1.1.1.1");
            dicomFile.setSeriesUid("2.2.2.2");
            dicomFile.setStudyUid("1.2.3." + random.nextInt(100));
            dicomFile.setPatientUid("3.3.3.3");
            dicomFile.setCreatedDate(new Date());
            dicomFileDAO.addDicomFile(dicomFile);
        }
    }

    @Test
    public void selectByDicomName() {
        DicomFile dicomFile = dicomFileDAO.selectByName("1.2.3.64");
        DicomFile dicomFile1 = dicomFileDAO.selectByName2("1.2.3.64");
        System.out.println(dicomFile.toString());
        System.out.println(dicomFile1.toString());
    }

    @Test
    public void insertDicomWithSameName() {
        Random random = new Random();
        for (int i = 0; i < 11; ++i) {
            DicomFile dicomFile = new DicomFile();
            dicomFile.setUserId(2);
            dicomFile.setDicomName("1.2.3." + random.nextInt(100));
            dicomFile.setStudyDate(new Date());
            dicomFile.setModality("CT");
            dicomFile.setBigFileId(i);
            dicomFile.setFileSize(random.nextInt(10000));
            dicomFile.setSopUid("1.1.1." + i);
            dicomFile.setSeriesUid("2.2.2.2");
            dicomFile.setStudyUid("1.2.3.4");
            dicomFile.setPatientUid("3.3.3.3");
            dicomFile.setCreatedDate(new Date());
            dicomFileDAO.addDicomFile(dicomFile);
        }
    }

    @Test
    public void selectSopListFromStudy() {
        List<DicomFile> commentsByEntity = dicomFileService.getSopListFromStudy("1.2.3.4");
        for (DicomFile sop:commentsByEntity) {
            System.out.println(sop.getSopUid());
        }
    }

}
