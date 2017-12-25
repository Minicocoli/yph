package com.yph.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private String password;

    private String sex;

    private String flag;

    private Date createTime;

    private Date modifyTime;

}