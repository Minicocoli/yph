package com.yph.controller.sys;

import com.yph.service.sys.ISysRoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 系统权限控制器
 * @author : Administrator Hzhan
 * @create ：2018/1/2
 **/
@Slf4j
@Controller
@Api(description="系统权限控制器")
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;


}
