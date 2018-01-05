package com.yph.entity.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysMenu implements Serializable {

    private Long id;

    private Long parentId;

    private String name;

    private String url;

    private String perms;

    private Integer type;

    private Integer sort;

    private Byte flag;

    private Date createTime;

    private static final long serialVersionUID = 1L;

}