package com.sayeah.daotest;

import com.sayeah.dao.DicomFileDAO;
import com.sayeah.model.gen.DicomFile;
import com.sayeah.service.function.DicomFileService;
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
    public void selectByDicomName() {
        DicomFile dicomFile = dicomFileDAO.selectByName("1.2.3.64");
//        DicomFile dicomFile1 = dicomFileDAO.selectByName2("1.2.3.64");
        System.out.println(dicomFile.toString());
//        System.out.println(dicomFile1.toString());
    }

    @Test
    public void addDicomWithSameName() {
        Random random = new Random();
        for (int i = 0; i < 11; ++i) {
            DicomFile dicomFile = new DicomFile();
            dicomFile.setUserId(2);
            dicomFile.setStudyFileId(1);
            dicomFile.setDicomName("1.2.3." + random.nextInt(100) + "我是dicom文件");
            dicomFile.setFileSize(random.nextInt(10000));
            dicomFile.setSopUid("1.1.1." + i);
            dicomFile.setSeriesUid("2.2.2.2");
            dicomFile.setCreatedDate(new Date());
            dicomFile.setIshdfs(1);
            dicomFileDAO.addDicomFile(dicomFile);
        }
    }

    @Test
    public void selectSopListFromStudy() {
        List<DicomFile> commentsByEntity = dicomFileService.getSopListFromStudy("1.2.3.4");
        for (DicomFile sop : commentsByEntity) {
            System.out.println(sop.getSopUid());
        }
    }

}
