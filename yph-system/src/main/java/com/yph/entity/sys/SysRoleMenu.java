package com.yph.entity.sys;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysRoleMenu implements Serializable {
    private Long id;

    private Long roleId;

    private Long menuId;

    private Byte flag;

    private static final long serialVersionUID = 1L;

}