package com.yph.service.sys;

import com.github.pagehelper.PageInfo;
import com.yph.entity.sys.SysMenu;
import com.yph.entity.sys.vo.SysMenuVo;
import com.yph.entity.tree.TreeVo;

import java.util.HashMap;
import java.util.List;

public interface ISysMenuService {


    int saveSysMenu(SysMenu sysMenu);

    int updateSysMenu(SysMenu sysMenu);

    int delSysMenu(SysMenu sysMenu);

    List<SysMenuVo> findSysMenuList(SysMenu sysMenu);

    List<SysMenuVo> findAllSysMenuList();

    List<SysMenuVo> findSysMenuListByRole(Long roleId);

    List<TreeVo> findAllMenu2TreeList();

    PageInfo findMenuListByPage(HashMap<String, Object> params, int pageNum, int pageSize);

    int delSysMenuById(Long id);


    List<SysMenu> findSysMenuListByType(int type);

    int batchDelSysMenuByIds(List<Long> list);

}
