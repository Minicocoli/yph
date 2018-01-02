package com.yph.service.sys.impl;

import com.yph.entity.sys.SysRoleMenu;
import com.yph.service.sys.ISysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色菜单
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/2
 **/
@Slf4j
@Service
@Transactional
public class SysRoleMenuServiceImpl implements ISysRoleMenuService{

    @Autowired
    private SysRoleMenu sysRoleMenu;


}
