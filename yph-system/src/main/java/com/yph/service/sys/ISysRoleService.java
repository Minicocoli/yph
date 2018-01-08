package com.yph.service.sys;

import com.github.pagehelper.PageInfo;
import com.yph.entity.sys.SysRole;
import com.yph.entity.sys.vo.SysRoleVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ISysRoleService {

    /**
     *  保存系统角色
     * @param sysRole
     * @return
     */
    int saveSysRole(SysRole sysRole);

    /**
     *  更新系统角色
     * @param sysRole
     * @return
     */
    int updateSysRole(SysRole sysRole);

    /**
     *  删除系统角色
     * @param sysRole
     * @return
     */
    int delSysRole(SysRole sysRole);

    /**
     *  获取系统角色列表
     * @param params
     * @return
     */
    PageInfo findSysRoleListByPage(Map<String,Object> params,int pageNum,int pageSize);

    int delSysRoleById(Long id);

    SysRole findSysRoleById(Long id);

    List<SysRole> findSysRoleList(HashMap<String, Object> params);

    int batchDelSysRoleByIds(List<Long> list);
}
