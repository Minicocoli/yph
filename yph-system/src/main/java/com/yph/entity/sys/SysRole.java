package com.yph.entity.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class SysRole implements Serializable {
    private Long id;

    private String roleName;

    private String remark;

    private Long createUserId;

    private Date createTime;

    private Byte flag;

    private static final long serialVersionUID = 1L;


}