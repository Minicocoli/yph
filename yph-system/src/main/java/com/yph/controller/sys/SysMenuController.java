package com.yph.controller.sys;

import com.yph.common.result.CommonResult;
import com.yph.entity.sys.SysMenu;
import com.yph.service.sys.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/2
 **/
@Slf4j
@Api(description="菜单控制器")
@Controller
@RequestMapping("/sys/menus")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     *  保存系统菜单
     * @param sysMenu
     * @return
     */
    @ApiOperation("保存系统菜单")
    @RequestMapping("saveSysMenu")
    public CommonResult saveSysMenu(SysMenu sysMenu){
        sysMenuService.saveSysMenu(sysMenu);
        return  CommonResult.SUCCESS();
    }

    /**
     *  删除系统菜单
     * @param sysMenu
     * @return
     */
    @ApiOperation("删除系统菜单")
    @RequestMapping("delSysMenu")
    public CommonResult delSysMenu(SysMenu sysMenu){
        sysMenuService.delSysMenu(sysMenu);
        return  CommonResult.SUCCESS();
    }


    /**
     *  更新系统菜单
     * @param sysMenu
     * @return
     */
    @ApiOperation("更新系统菜单")
    @RequestMapping("updateSysMenu")
    public CommonResult updateSysMenu(SysMenu sysMenu){
        sysMenuService.updateSysMenu(sysMenu);
        return  CommonResult.SUCCESS();
    }

}
