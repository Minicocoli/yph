package com.yph.entity.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysMenu implements Serializable {

    /**
     *  Id
     */
    private Long id;

    /**
     *  上级id
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     *  菜单地址
     */
    private String url;

    /**
     *  权限
     */
    private String perms;

    /**
     * 类型
     */
    private Integer type;

    /**
     *  排序
     */
    private Integer sort;

    /**
     *  标记
     */
    private Byte flag;

    private Date createTime;

    private static final long serialVersionUID = 1L;

}