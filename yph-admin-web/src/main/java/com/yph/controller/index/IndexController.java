package com.yph.controller.index;

import com.yph.common.annotation.SysLog;
import com.yph.entity.sys.SysUser;
import com.yph.entity.sys.SysUserRole;
import com.yph.entity.sys.vo.SysMenuVo;
import com.yph.entity.sys.vo.SysRoleMenuVo;
import com.yph.service.sys.ISysMenuService;
import com.yph.service.sys.ISysRoleMenuService;
import com.yph.service.sys.ISysRoleService;
import com.yph.service.sys.ISysUserRoleService;
import com.yph.utils.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : Administrator Hzhan
 * @create ：2017/12/23
 **/
@Api(description = "首页跳转控制器")
@Slf4j
@Controller
public class IndexController {

    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     *  首页
     * @param request
     * @return
     */
    @ApiOperation(value = "跳转到首页",notes = "首页跳转")
    @SysLog("登陆日志")
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        SysUser sysUser = ShiroUtils.getUser();
        SysUserRole sysUserRole = sysUserRoleService.findSysUserRoleByUserId(sysUser.getId());
        List<SysMenuVo> sysMenuList = sysMenuService.findSysMenuListByRole(sysUserRole.getRoleId());
        request.setAttribute("menuList",sysMenuList);
        request.getSession().setAttribute("user",sysUser);
        return "begin";
    }


    /**
     *  跳到登录页面
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }

}