package com.yph.entity.sys;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 3271934866059957660L;

    /**
     *  菜单Id
     */
    private Long menuId;

    /**
     *  上级Id
     */
    private Long parentId;

    /**
     *  菜单名称
     */
    private String name;
    /**
     *   菜单连接
     */
    private String url;
    /**
     *
     */
    private String perms;
    /**
     *
     */
    private Integer type;
    /**
     *
     */
    private Integer orderNum;

}