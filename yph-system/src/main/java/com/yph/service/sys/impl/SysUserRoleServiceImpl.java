package com.yph.service.sys.impl;

import com.yph.common.annotation.RedisCache;
import com.yph.entity.sys.SysUser;
import com.yph.entity.sys.SysUserRole;
import com.yph.mapper.sys.SysUserRoleMapper;
import com.yph.service.sys.ISysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户角色
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Slf4j
@Service
@Transactional
public class SysUserRoleServiceImpl implements ISysUserRoleService {


    private static final String ONE_KEY = "";


    private static final String LIST_KEY = "";


    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;


    /**
     * 保存 用户角色关系
     *
     * @param sysUserRole
     * @return
     */
    @Override
    public int saveUserRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insert(sysUserRole);
    }

    /**
     * 更新 用户角色关系
     *
     * @param sysUserRole
     * @return
     */
    @Override
    public int updateUserRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insert(sysUserRole);
    }

    /**
     * 删除 用户角色关系
     *
     * @param sysUserRole
     * @return
     */
    @Override
    public int delUserRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.updateByPrimaryKeySelective(sysUserRole);
    }

    @Override
    @RedisCache(type = SysUserRole.class)
    public SysUserRole findSysUserRoleByUserId(Long sysUserId) {
        return sysUserRoleMapper.findSysUserRoleByUserId(sysUserId);
    }


}
