package com.yph.mapper.sys;

import com.yph.entity.sys.SysRoleMenu;
import com.yph.entity.sys.vo.SysRoleMenuVo;

import java.util.List;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);

    List<SysRoleMenuVo> findRoleMenuList(Long roleId);

    int delSysRoleMenuByMenuId(SysRoleMenu sysRoleMenu);
}