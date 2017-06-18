package com.sayeah.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by BIG-JIAN on 2017/6/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DateUtilsTest {
    @Test
    public void TestDateToString() {
        String s = DateUtils.dateToString(new Date());
        System.out.println(s);
    }

    @Test
    public void TestStringToDate(){
        Date date = DateUtils.stringToDate("2017-06-18");
        System.out.println(date);
    }

}
