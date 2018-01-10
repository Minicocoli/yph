package com.yph.controller.sys;

import com.yph.common.result.CommonResult;
import com.yph.service.sys.ISysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * 角色菜单
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/10
 **/
@Slf4j
@Controller
@RequestMapping("/sys/role/menu")
public class SysRoleMenuController {

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;


    /**
     *  将角色菜单更新
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("updateRoleMenuByList")
    public CommonResult updateRoleMenuByList(@RequestParam HashMap<String,Object> params){
       int reuslt = sysRoleMenuService.updateRoleMenuByList(params);
       if(reuslt<1){
           return CommonResult.ERROR("更新角色菜单失败!");
       }
       return CommonResult.SUCCESS();
    }

}
