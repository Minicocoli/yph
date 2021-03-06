package com.yph.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yph.common.annotation.RedisCache;
import com.yph.common.cache.IRedisService;
import com.yph.entity.sys.SysMenu;
import com.yph.entity.sys.SysUserRole;
import com.yph.entity.sys.vo.SysMenuVo;
import com.yph.entity.tree.TreeVo;
import com.yph.entity.tree.ZtreeVo;
import com.yph.mapper.sys.SysMenuMapper;
import com.yph.service.sys.ISysMenuService;
import com.yph.service.sys.ISysUserRoleService;
import com.yph.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 菜单服务
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Slf4j
@Service
@Transactional
public class SysMenuServiceImpl implements ISysMenuService {

    // 单个缓存key
    private static final String ONE_KEY = "SYS_MENU_ONE#";
    // list 缓存key
    private static final String LIST_KEY = "SYS_MENU_LIST#";


    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private ISysUserRoleService sysUserRoleService;


    /**
     * 保存菜单
     *
     * @param sysMenu
     * @return
     */
    @Override
    public Long saveSysMenu(SysMenu sysMenu) {
        sysMenu.setFlag(new Byte("0"));
        sysMenu.setCreateTime(new Date());
        if (sysMenu.getParentId() == null) {
            sysMenu.setParentId(0L);
        }
        // 插入数据 返回主键Id
        return sysMenuMapper.insert(sysMenu);
    }

    /**
     * 更新菜单
     *
     * @param sysMenu
     * @return
     */
    @Override
    public int updateSysMenu(SysMenu sysMenu) {
        return sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
    }

    /**
     * 删除菜单
     *
     * @param sysMenu
     * @return
     */
    @Override
    public int delSysMenu(SysMenu sysMenu) {
        return sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
    }

    /**
     * 获取菜单列表 通过类型
     *
     * @param sysMenu
     * @return
     */
    @Override
    @RedisCache(type = SysMenuVo.class)
    public List<SysMenuVo> findSysMenuList(SysMenu sysMenu) {
        return sysMenuMapper.findSysMenuList(sysMenu);
    }

    /**
     * 获取系统所有的菜单 ---> 超级管理员使用。
     *
     * @return
     */
    @Override
    @RedisCache(type = SysMenuVo.class)
    public List<SysMenuVo> findAllSysMenuList() {
        List<SysMenuVo> list = new ArrayList<>();
        SysMenu sysMenu = new SysMenu();
        sysMenu.setParentId(0L);

        //1、获取一级导航菜单 ----> 比如 订单系统 财务系统。
        List<SysMenuVo> navList = sysMenuMapper.findSysMenuList(sysMenu);

        SysMenu selectSysMenu = new SysMenu();
        List<SysMenuVo> sysMenuList = null;
        List<SysMenuVo> sysMenuList2 = null;

        // 2、获取二级导航菜单 -----> 比如 订单设置  系统设置
        for (int i = 0; i < navList.size(); i++) {
            selectSysMenu.setParentId(navList.get(i).getId());
            sysMenuList = sysMenuMapper.findSysMenuList(selectSysMenu);

            if (sysMenuList != null) {
                //3、获取三级导航菜单 ----> 比如订单管理 里面的 订单列表 订单统计
                for (int j = 0; j < sysMenuList.size(); j++) {
                    selectSysMenu.setParentId(sysMenuList.get(j).getId());
                    sysMenuList2 = sysMenuMapper.findSysMenuList(selectSysMenu);
                    if (sysMenuList2 != null) {
                        sysMenuList.get(j).setMenuList(sysMenuList2);
                    }
                }
            }
            navList.get(i).setMenuList(sysMenuList);
        }
        return navList;
    }


    /**
     * 通过角色获取菜单
     *
     * @return
     */
    @Override
    @RedisCache(type = SysMenuVo.class)
    public List<SysMenuVo> findSysMenuListByRole(Long roleId) {
        // 系统超级管理员
        if (1 == roleId) {
            return findAllSysMenuList();
        } else {
            HashMap<String, Object> params = new HashMap<>();
            params.put("parentId", 0);
            params.put("roleId", roleId);

            List<SysMenuVo> navMenuList = sysMenuMapper.findSysMenuListByRole(params);

            List<SysMenuVo> menuList1 = null;
            List<SysMenuVo> menuList2 = null;
            for (int i = 0; i < navMenuList.size(); i++) {
                params.put("parentId", navMenuList.get(i).getId());
                menuList1 = sysMenuMapper.findSysMenuListByRole(params);
                if (menuList1 != null) {
                    for (int j = 0; j < menuList1.size(); j++) {
                        menuList2 = sysMenuMapper.findSysMenuListByRole(params);
                        if (menuList2 != null) {
                            menuList1.get(i).setMenuList(menuList2);
                        }
                    }
                }
                navMenuList.get(i).setMenuList(menuList1);
            }
            return navMenuList;
        }
    }


    /**
     * 获取菜单数据到树形节点
     *
     * @return
     */
    @Override
    @RedisCache(type = SysMenuVo.class)
    public List<TreeVo> findAllMenu2TreeList() {
        List<TreeVo> parentTreeList = sysMenuMapper.findSysMenuTreeList(0L);
        List<TreeVo> menu2List = null;
        List<TreeVo> menu3List = null;
        for (int i = 0; i < parentTreeList.size(); i++) {
            menu2List = sysMenuMapper.findSysMenuTreeList(parentTreeList.get(i).getId());
            if (menu2List != null) {
                for (int j = 0; j < menu2List.size(); j++) {
                    menu3List = sysMenuMapper.findSysMenuTreeList(menu2List.get(j).getId());
                    if (menu3List != null) {
                        menu2List.get(j).setNodes(menu3List);
                    }
                }
            }
            parentTreeList.get(i).setNodes(menu2List);
        }
        return parentTreeList;
    }


    /**
     * 查询菜单  分页
     *
     * @param params
     * @return
     */
    @Override
    @RedisCache(type = PageInfo.class)
    public PageInfo findMenuListByPage(HashMap<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy(" id desc");
        List<SysMenu> list = sysMenuMapper.findMenuListByPage(params);
        return new PageInfo(list);
    }

    /**
     * 删除菜单 通过Id
     *
     * @return
     */
    @Override
    public int delSysMenuById(Long id) {
        return sysMenuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 通过菜单获取类别
     *
     * @param type
     * @return
     */
    @Override
    @RedisCache(type = SysMenu.class)
    public List<SysMenu> findSysMenuListByType(Integer type, Long parentId) {
        log.info(" [ 获取菜单类型 ----->  数据库获取   type : {}  ] ", type);
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("parentId", parentId);
        List<SysMenu> list = sysMenuMapper.findSysMenuListByParams(params);
        return list;
    }

    @Override
    public int batchDelSysMenuByIds(List<Long> list) {
        return sysMenuMapper.batchDelSysMenuByIds(list);
    }

    /**
     * 获取ztree 数据
     *
     * @param params
     * @return
     */
    @Override
    @RedisCache(type = ZtreeVo.class)
    public List<ZtreeVo> findListByZtree(HashMap<String, Object> params) {
        List<ZtreeVo> list = null;
        Long parentId = null;
        Long roleId = null;
        if (params.get("id") == null) {
            parentId = 0L;
        } else {
            parentId = Long.valueOf(params.get("id") + "");
        }

        if (params.get("roleId") == null) {
            SysUserRole sysUserRole = sysUserRoleService.findSysUserRoleByUserId(ShiroUtils.getUserId());
            roleId = sysUserRole.getRoleId();
        } else {
            roleId = Long.valueOf(params.get("roleId") + "");
        }
        params.put("parentId", parentId);
        list = sysMenuMapper.findListByZtree(params);
        return list;
    }

}
