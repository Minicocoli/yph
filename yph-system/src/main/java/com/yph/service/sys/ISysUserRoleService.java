package com.yph.service.sys;

import com.yph.entity.sys.SysUserRole;

/**
 * 系统用户角色
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
public interface ISysUserRoleService {

    int saveUserRole(SysUserRole sysUserRole);


    int updateUserRole(SysUserRole sysUserRole);


    int delUserRole(SysUserRole sysUserRole);


    SysUserRole findSysUserRoleByUserId(Long sysUserId);

}
