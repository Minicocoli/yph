package com.yph.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yph.common.annotation.RedisCache;
import com.yph.entity.sys.SysRole;
import com.yph.entity.sys.SysUser;
import com.yph.entity.sys.vo.SysRoleVo;
import com.yph.mapper.sys.SysRoleMapper;
import com.yph.service.sys.ISysRoleService;
import com.yph.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统角色
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Slf4j
@Service
@Transactional
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     *  保存系统角色
     * @param sysRole
     * @return
     */
    @Override
    public int saveSysRole(SysRole sysRole) {
        sysRole.setCreateTime(new Date());
        sysRole.setFlag(new Byte("0"));
        sysRole.setCreateUserId(ShiroUtils.getUserId());
        return sysRoleMapper.insert(sysRole);
    }

    /**
     *  更新系统角色
     * @param sysRole
     * @return
     */
    @Override
    public int updateSysRole(SysRole sysRole) {
        return sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    /**
     *  删除系统角色
     * @param sysRole
     * @return
     */
    @Override
    public int delSysRole(SysRole sysRole) {
        return sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }



    /**
     *  获取系统角色列表
     * @param params
     * @return
     */
    @Override
    @RedisCache(type = PageInfo.class)
    public PageInfo findSysRoleListByPage(Map<String,Object> params,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysRoleVo> list= sysRoleMapper.findSysRoleListByPage(params);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int delSysRoleById(Long id) {
        return sysRoleMapper.delSysRoleById(id);
    }

    /**
     *  查询用户
     * @param id
     * @return
     */
    @Override
    @RedisCache(type = SysRole.class)
    public SysRole findSysRoleById(Long id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    @RedisCache(type = SysRole.class)
    public List<SysRole> findSysRoleList(HashMap<String, Object> params) {
        return sysRoleMapper.findSysRoleList(params);
    }

    @Override
    public int batchDelSysRoleByIds(List<Long> list) {
        return 0;
    }

}
