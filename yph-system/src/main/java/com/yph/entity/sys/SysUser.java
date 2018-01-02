package com.yph.entity.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private String password;

    private String email;

    private String mobile;

    private Byte status;

    private Long createUserId;

    private Date createTime;
}