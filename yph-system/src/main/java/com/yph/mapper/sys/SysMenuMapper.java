package com.yph.mapper.sys;

import com.yph.entity.sys.SysMenu;
import com.yph.entity.sys.vo.SysMenuVo;
import com.yph.entity.tree.TreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SysMenuMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenuVo> findSysMenuList(SysMenu sysMenu);

    List<SysMenuVo> findSysMenuListByRole(HashMap<String, Object> params);

    List<TreeVo> findSysMenuTreeList(@Param("parentId") Long parentId);

    List<SysMenu>  findMenuListByPage(HashMap<String, Object> params);

    List<SysMenu> findSysMenuListByParams(HashMap<String, Object> params);

    int batchDelSysMenuByIds(List<Long> list);

}