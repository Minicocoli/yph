package com.yph.mapper.sys;

import com.yph.entity.sys.SysRole;
import com.yph.entity.sys.vo.SysRoleVo;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRoleVo> findSysRoleListByPage(Map<String, Object> params);

}