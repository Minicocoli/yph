package com.yph.controller.sys;

import com.github.pagehelper.PageInfo;
import com.yph.common.result.CommonResult;
import com.yph.entity.sys.SysUser;
import com.yph.entity.sys.vo.SysUserVo;
import com.yph.service.sys.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 用户控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Slf4j
@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     *  保存系统用户
     * @param sysUser
     * @return
     */
    @ResponseBody
    @RequestMapping("saveSysUser")
    public CommonResult saveSysUser(SysUser sysUser){
        int result = sysUserService.saveSysUser(sysUser);
        if(result<1){
            log.info("保存系统用户失败");
            return CommonResult.ERROR("保存系统用户失败!");
        }
        return CommonResult.SUCCESS();
    }

    /**
     *  删除系统用户
     * @param sysUser
     * @return
     */
    @ResponseBody
    @RequestMapping("delSysUser")
    public CommonResult delSysUser(SysUser sysUser){
        int result = sysUserService.delSysUser(sysUser);
        if(result<1){
            log.info("删除系统用户失败");
            return CommonResult.ERROR("删除系统用户失败");
        }
        return CommonResult.SUCCESS();
    }

    /**
     *  更新系统用户
     * @param sysUser
     * @return
     */
    @ResponseBody
    @RequestMapping("updateSysUser")
    public CommonResult updateSysUser(SysUser sysUser){
        int result = sysUserService.updateSysUser(sysUser);
        if(result<0){
            log.info("更新系统用户失败");
            return CommonResult.ERROR("更新系统用户失败!");
        }
        return CommonResult.SUCCESS();
    }

    /**
     *  查询用户信息
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("findSysUserListByPage")
    public CommonResult findSysUserListByPage(@RequestParam HashMap<String,Object> params,int pageNum,int pageSize){
        PageInfo pageInfo =  sysUserService.findSysUserListByPage(params,pageNum,pageSize);
        return CommonResult.SUCCESS(pageInfo);
    }

}
