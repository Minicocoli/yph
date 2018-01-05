package com.yph.entity.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class SysUser implements Serializable {

    private Long id;

    private String userName;

    private String password;

    private String email;

    private String mobile;

    private Byte status;

    private Long createUserId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

}