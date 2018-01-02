package com.yph.entity.sys;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long roleId;

    private Long menuId;




}