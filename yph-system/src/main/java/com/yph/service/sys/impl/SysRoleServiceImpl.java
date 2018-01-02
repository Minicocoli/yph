package com.yph.service.sys.impl;

import com.yph.mapper.sys.SysRoleMapper;
import com.yph.service.sys.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统角色
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/2
 **/
@Slf4j
@Service
@Transactional
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;



}
