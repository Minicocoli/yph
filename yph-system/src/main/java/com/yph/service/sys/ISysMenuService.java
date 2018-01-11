package com.yph.service.sys;

import com.github.pagehelper.PageInfo;
import com.yph.entity.sys.SysMenu;
import com.yph.entity.sys.vo.SysMenuVo;
import com.yph.entity.tree.TreeVo;
import com.yph.entity.tree.ZtreeVo;
import io.swagger.models.auth.In;

import java.util.HashMap;
import java.util.List;

public interface ISysMenuService {


    Long saveSysMenu(SysMenu sysMenu);

    int updateSysMenu(SysMenu sysMenu);

    int delSysMenu(SysMenu sysMenu);

    List<SysMenuVo> findSysMenuList(SysMenu sysMenu);

    List<SysMenuVo> findAllSysMenuList();

    List<SysMenuVo> findSysMenuListByRole(Long roleId);

    List<TreeVo> findAllMenu2TreeList();

    PageInfo findMenuListByPage(HashMap<String, Object> params, int pageNum, int pageSize);

    int delSysMenuById(Long id);


    List<SysMenu> findSysMenuListByType(Integer type,Long parentId);

    int batchDelSysMenuByIds(List<Long> list);

    List<ZtreeVo> findListByZtree(HashMap<String, Object> params);
}
