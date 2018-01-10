package com.yph.service.sys;

import com.github.pagehelper.PageInfo;
import com.yph.entity.sys.SysUser;
import java.util.HashMap;

/**
 *  系统用户
 */
public interface ISysUserService {


    /**
     *  保存系统用户
     * @param sysUser
     * @return
     */
    int saveSysUser(SysUser sysUser);

    /**
     *  更新系统用户
     * @param sysUser
     * @return
     */
    int updateSysUser(SysUser sysUser);

    /**
     *  删除系统用户
     * @param sysUser
     * @return
     */
    int delSysUser(SysUser sysUser);

    /**
     *  通过用户名和密码 查询用户
     * @param user
     * @return
     */
    SysUser findUserByNameAndPassword(SysUser user);


    /**
     *  分页查找用户
     * @param params
     * @return
     */
    PageInfo findSysUserListByPage(HashMap<String, Object> params,int pageNum,int pageSize);

}
