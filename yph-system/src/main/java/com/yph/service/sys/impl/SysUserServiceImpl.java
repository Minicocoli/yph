package com.yph.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yph.common.annotation.RedisCache;
import com.yph.entity.sys.SysUser;
import com.yph.entity.sys.vo.SysUserVo;
import com.yph.mapper.sys.SysUserMapper;
import com.yph.service.sys.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 *  用户服务
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Slf4j
@Service
@Transactional
public class SysUserServiceImpl implements ISysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     *  保存系统用户
     * @param sysUser
     * @return
     */
    @Override
    public int saveSysUser(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }


    /**
     *  删除系统用户
     * @param sysUser
     * @return
     */
    @Override
    public int delSysUser(SysUser sysUser) {
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     *  更新系统用户
     * @param sysUser
     * @return
     */
    @Override
    public int updateSysUser(SysUser sysUser){
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     *  通过用户名 密码查找用户
     * @param user
     * @return
     */
    @Override
    @RedisCache(type = SysUser.class)
    public SysUser findUserByNameAndPassword(SysUser user) {
        return  sysUserMapper.findUserByNameAndPassword(user);
    }

    /**
     *  查询系统用户
     * @param params
     * @return
     */
    @Override
    @RedisCache(type = PageInfo.class)
    public PageInfo findSysUserListByPage(HashMap<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUserVo> list = sysUserMapper.findSysUserListByPage(params);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

}
