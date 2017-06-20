package com.sayeah.daotest;

import com.sayeah.dao.StudyDAO;
import com.sayeah.model.gen.DicomFile;
import com.sayeah.model.gen.StudyFile;
import com.sayeah.service.function.DicomFunction;
import com.sayeah.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

/**
 * Created by BIG-JIAN on 2017/6/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("/init-schema.sql")
public class StudyDAOTest {
    public static final String DICOMFILE_PATH = "D:\\datatest\\618Test\\618ctTest.dcm";
    public static final String STUDY_PATH = "D:\\datatest\\618Test\\ct";

    @Autowired
    StudyDAO studyDAO;

    @Autowired
    DicomFunction dicomFunction;

    @Test
    public void TestAddStudyFile() {
        StudyFile sf = new StudyFile();
        sf.setUserId(1);
        sf.setBigFileId(1);
        sf.setFolderName("我是study文件");
        sf.setUrl("20170618_CT");
        sf.setStudyDate(DateUtils.stringToDate("2017-06-18"));
        sf.setModality("CT");

        sf.setStudyUid("1.1.1.1");
        sf.setPatientUid("123456");
        sf.setStudyFileSize(1000000000);
        sf.setCreateDate(new Date());
        sf.setIshdfs(1);
        int i = studyDAO.addStudyFile(sf);
        System.out.println(sf.getId());
        System.out.println("i： " + i);
    }



    @Test
    public void testReadDcmFile() {
        DicomFile sopInstanceModel = dicomFunction.getDicomFile(DICOMFILE_PATH);
        System.out.println(sopInstanceModel.toString());
    }

    @Test
    public void testReadDcmFileInputStream() {
        File file = new File(DICOMFILE_PATH);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DicomFile sopInstanceModel = dicomFunction.getDicomFile(fileInputStream, file.getName(), file.length());
        System.out.println(sopInstanceModel.toString());
    }

    @Test
    public void testTraversalStudy() {
        List<DicomFile> sopInstanceModels = dicomFunction.TraversalStudy(STUDY_PATH);
        System.out.println(sopInstanceModels.size());
    }
}
