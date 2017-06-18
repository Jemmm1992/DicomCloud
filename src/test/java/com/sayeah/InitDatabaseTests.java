package com.sayeah;


import com.sayeah.dao.UserDAO;
import com.sayeah.model.gen.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("/init-schema.sql")
public class InitDatabaseTests {
    @Autowired
    UserDAO userDAO;

    @Test
    public void contextLoads() {
        Random random = new Random();
        for (int i = 0; i < 11; ++i) {
            User user = new User();
            user.setUsername(String.format("USER%d@qq.com", i+1));
            user.setPassword("123");
            user.setName(String.format("USER%d", i+1));
            user.setSalt("");
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setStatus(1);
            userDAO.addUser(user);

            user.setPassword("newpassword");
            userDAO.updatePassword(user);
        }
        Assert.assertEquals("newpassword",userDAO.selectById(1).getPassword());
//        userDAO.deleteById(1);
//        Assert.assertNull(userDAO.selectById(1));
    }

    @Test
    public void justTest(){
        User user1 = userDAO.selectById(1);
        Assert.assertEquals("USER1",user1.getName());
    }

    @Test
    public void updateUser(){
        User user = userDAO.selectById(1);
        user.setPassword("newpassword1");
        userDAO.updatePassword(user);
        Assert.assertEquals("newpassword1",userDAO.selectById(1).getPassword());
    }
}
