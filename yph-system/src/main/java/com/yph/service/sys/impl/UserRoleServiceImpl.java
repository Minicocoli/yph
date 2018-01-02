package com.yph.service.sys.impl;

import com.yph.entity.sys.vo.SysMenuVo;
import com.yph.mapper.sys.SysUserRoleMapper;
import com.yph.service.sys.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户角色
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/2
 **/
@Slf4j
@Service
@Transactional
public class UserRoleServiceImpl implements IUserRoleService{

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;



}
