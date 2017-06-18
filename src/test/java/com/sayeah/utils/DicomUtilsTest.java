package com.sayeah.utils;

import com.sayeah.model.common.SOPInstanceModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        SOPInstanceModel sopInstanceModel = DicomUtils.getSopInstanceModel(DICOMFILE_PATH);
        System.out.println(sopInstanceModel.toString());
    }

    @Test
    public void testTraversalStudy() {
        List<SOPInstanceModel> sopInstanceModels = DicomUtils.TraversalStudy(STUDY_PATH);
        System.out.println(sopInstanceModels.size());
    }
}
