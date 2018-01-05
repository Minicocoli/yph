package com.yph.service.sys.impl;

import com.yph.entity.sys.SysRoleMenu;
import com.yph.entity.sys.vo.SysRoleMenuVo;
import com.yph.mapper.sys.SysRoleMenuMapper;
import com.yph.service.sys.ISysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统角色菜单
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Slf4j
@Service
@Transactional
public class SysRoleMenuServiceImpl implements ISysRoleMenuService{

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     *  保存角色菜单
     * @param sysRoleMenu
     * @return
     */
    @Override
    public int saveRoleMenu(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.insert(sysRoleMenu);
    }

    /**
     *  更新角色菜单
     * @param sysRoleMenu
     * @return
     */
    @Override
    public int updateRoleMenu(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.updateByPrimaryKeySelective(sysRoleMenu) ;
    }

    /**
     *  删除角色菜单
     * @param sysRoleMenu
     * @return
     */
    @Override
    public int delRoleMenu(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.updateByPrimaryKeySelective(sysRoleMenu);
    }

    /**
     *  通过角色获取菜单列表
     * @param roleId
     * @return
     */
    @Override
    public List<SysRoleMenuVo> findRoleMenuList(Long roleId) {
       return sysRoleMenuMapper.findRoleMenuList(roleId);
    }


}

