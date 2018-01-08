package com.yph.mapper.sys;

import com.yph.entity.sys.SysRole;
import com.yph.entity.sys.vo.SysRoleVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SysRoleMapper {

    /**
     * 通过id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  保存系统角色
     * @param record
     * @return
     */
    int insert(SysRole record);

    /**
     *
     * @param record
     * @return
     */
    int insertSelective(SysRole record);

    /**
     *
     * @param id
     * @return
     */
    SysRole selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(SysRole record);

    /**
     *
     * @param params
     * @return
     */
    List<SysRoleVo> findSysRoleListByPage(Map<String, Object> params);

    /**
     *
     * @param id
     * @return
     */
    int delSysRoleById(Long id);

    /**
     *
     * @param params
     * @return
     */
    List<SysRole> findSysRoleList(HashMap<String, Object> params);


}