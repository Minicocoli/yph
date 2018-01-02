package com.yph.service.sys;

import com.yph.entity.sys.SysMenu;

import java.util.List;
import java.util.Map;

public interface ISysMenuService {

    int saveSysMenu(SysMenu sysMenu);

    int delSysMenu(SysMenu sysMenu);

    int updateSysMenu(SysMenu sysMenu);

    List<SysMenu> findSysMenusList(Map<String,Object> params);

}
