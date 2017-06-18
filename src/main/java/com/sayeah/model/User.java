package com.sayeah.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by BIG-JIAN on 2017/5/31.
 */

@Setter
@Getter
@ToString
public class User {

    private int id;
    private String username;
    private String password;
    private String name;
    private String salt;
    private String headUrl;
    private int status;
}
