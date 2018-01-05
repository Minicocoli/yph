package com.yph.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yph.entity.sys.SysRole;
import com.yph.entity.sys.vo.SysRoleVo;
import com.yph.mapper.sys.SysRoleMapper;
import com.yph.service.sys.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PageInfo findSysRoleListByPage(Map<String,Object> params) {
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        PageHelper.startPage(pageNum,pageSize);
        List<SysRoleVo> list= sysRoleMapper.findSysRoleListByPage(params);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

}
