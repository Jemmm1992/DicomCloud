package com.sayeah.daotest;

import com.sayeah.dao.StudyDAO;
import com.sayeah.model.gen.StudyFile;
import com.sayeah.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by BIG-JIAN on 2017/6/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("/init-schema.sql")
public class StudyDAOTest {
    @Autowired
    StudyDAO studyDAO;

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
        sf.setStudyFileSize("1000000000");
        sf.setCreateDate(new Date());
        sf.setIshdfs(1);
        studyDAO.addStudyFile(sf);
    }
}
