package com.sayeah.servictest;

import com.sayeah.service.function.DicomFunction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by BIG-JIAN on 2017/6/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceFunctionTest {

    @Autowired
    DicomFunction dicomFunction;

    public static final String DICOMFILE_PATH = "D:\\datatest\\618Test\\618ctTest.dcm";
    public static final String STUDY_PATH = "D:\\datatest\\618Test\\ct";

    @Test
    public void testMappingStudyFile(){
        dicomFunction.mappingStudyFile(STUDY_PATH);
    }


}
