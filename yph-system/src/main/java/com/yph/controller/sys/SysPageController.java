package com.yph.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/2
 **/
@Slf4j
@Controller
@Api(description="系统页面跳转控制器")
@RequestMapping("/sys/view/")
public class SysPageController {

    /**
     * 跳转到编辑系统菜单页面
     *
     * @return
     */
    @ApiOperation(value = "编辑系统菜单页面", notes = "编辑系统菜单页面")
    @RequestMapping("toEditSysMenus")
    public String toEditSysMenus() {
        return "";
    }

    /**
     * 跳转到编辑权限页面
     *
     * @return
     */
    @ApiOperation("跳转到编辑权限页面")
    @RequestMapping("toEditSysRole")
    public String toEditSysRole() {
        return "";
    }


}
