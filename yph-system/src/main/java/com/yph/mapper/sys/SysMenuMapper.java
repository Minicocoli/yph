package com.yph.mapper.sys;

import com.yph.entity.sys.SysMenu;
import com.yph.entity.sys.vo.SysMenuVo;
import com.yph.entity.tree.TreeVo;
import com.yph.entity.tree.ZtreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 *
 *  create by Hzhan 2018-01-09
 */
public interface SysMenuMapper {

    int deleteByPrimaryKey(Long id);

    Long insert(SysMenu record);

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

    List<ZtreeVo> findListByZtree(HashMap<String, Object> params);

}