package com.yph.service.sys.impl;

import com.yph.entity.sys.SysRoleMenu;
import com.yph.entity.sys.vo.SysRoleMenuVo;
import com.yph.mapper.sys.SysRoleMenuMapper;
import com.yph.service.sys.ISysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     *  更新角色菜单 通过List
     * @param params
     * @return
     */
    @Override
    public int updateRoleMenuByList(HashMap<String, Object> params) {

        // 新增的角色菜单
        String addMenuArr = (String) params.get("addMenuList");
        String role_Id = (String) params.get("roleId");
        Long roleId =Long.valueOf(role_Id);

        // 移除的角色菜单
        String removeMenuArr = (String) params.get("removeMenuList");

        String[] addArr = null;
        String[] removeArr = null;
        SysRoleMenu sysRoleMenu = null;


        if(!StringUtils.isBlank(addMenuArr)){
            log.info("[ 添加角色菜单 --------------------------------->  ]");
            addArr = addMenuArr.split(",");
            // 添加
            for (int i = 0; i < addArr.length; i++) {
                sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(Long.valueOf(addArr[i]));
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.setFlag(new Byte("0"));
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }


        if(!StringUtils.isBlank(removeMenuArr)){
            removeArr = removeMenuArr.split(",");
            log.info("[ 删除角色菜单 --------------------------------->  ]");
            // 更新 移除
            for (int i = 0; i < removeArr.length; i++) {
                sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(Long.valueOf(removeArr[i]));
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.setFlag(new Byte("1"));
                sysRoleMenuMapper.delSysRoleMenuByMenuId(sysRoleMenu);
            }

        }
        return 1;
    }


}

