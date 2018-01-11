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
import javax.servlet.http.HttpSession;
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
     *  首次进入系统
     * @return
     */
    @RequestMapping("/")
    public String gotIndex(){
        if(ShiroUtils.getSession()!=null){
            log.info("[校验登录]  已登录 ");
            return "begin";
        }else{
            return "user/login";
        }
    }


    /**
     *  首页
     * @param request
     * @return
     */
    @ApiOperation(value = "跳转到首页",notes = "首页跳转")
    @SysLog("登陆日志")
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
//        if(request.getSession().getAttribute("menuList")!=null){
//            log.info("[ 菜单从 request 域获取 ]");
//            return "begin";
//        }
        HttpSession session = request.getSession();
        SysUser sysUser = ShiroUtils.getUser();
        SysUserRole sysUserRole = sysUserRoleService.findSysUserRoleByUserId(sysUser.getId());
        if(sysUserRole!=null){
            List<SysMenuVo> sysMenuList = sysMenuService.findSysMenuListByRole(sysUserRole.getRoleId());
            session.setAttribute("menuList",sysMenuList);
        }
        session.setAttribute("user",sysUser);
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