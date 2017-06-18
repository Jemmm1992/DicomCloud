package com.sayeah.utils;

import com.sayeah.model.gen.DicomFile;
import com.sayeah.service.function.DicomFunction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by BIG-JIAN on 2017/6/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DicomUtilsTest {
    public static final String DICOMFILE_PATH = "D:\\datatest\\618Test\\618ctTest.dcm";
    public static final String STUDY_PATH = "D:\\datatest\\618Test\\ct";

    @Test
    public void testReadDcmFile() {
        DicomFile sopInstanceModel = DicomFunction.getDicomFile(DICOMFILE_PATH);
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

        DicomFile sopInstanceModel = DicomFunction.getDicomFile(fileInputStream, file.getName(), file.length());
        System.out.println(sopInstanceModel.toString());
    }

    @Test
    public void testTraversalStudy() {
        List<DicomFile> sopInstanceModels = DicomFunction.TraversalStudy(STUDY_PATH);
        System.out.println(sopInstanceModels.size());
    }
}
