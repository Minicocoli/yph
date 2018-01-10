package com.yph.mapper.sys;

import com.yph.entity.sys.SysUser;
import com.yph.entity.sys.vo.SysUserVo;

import java.util.HashMap;
import java.util.List;

public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findUserByNameAndPassword(SysUser user);

    List<SysUserVo> findSysUserListByPage(HashMap<String, Object> params);
}