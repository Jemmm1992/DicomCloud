package com.sayeah.dao;

import com.sayeah.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by BIG-JIAN on 2017/5/31.
 */
@Mapper
public interface UserDAO {
    String TABLE_NAME = "user";
    String INSET_FIELDS = " username, password, name, salt, head_url, status ";
    String SELECT_FIELDS = " id, username, password, name, salt, head_url, status ";

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{username},#{password},#{name},#{salt},#{headUrl},#{status})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    User selectById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    User selectByName(String name);

    @Update({"update ", TABLE_NAME, " set password=#{password} where id=#{id}"})
    void updatePassword(User user);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);
}
