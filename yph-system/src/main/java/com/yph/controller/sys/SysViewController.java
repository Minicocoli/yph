package com.yph.controller.sys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统视图控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Slf4j
@Controller
@RequestMapping("/sys/view")
public class SysViewController {

    /**
     *  跳转到菜单编辑页面
     * @return
     */
    @RequestMapping("eidtSysMenuView")
    public String eidtSysMenuView(){
        return "sys/edit_menu";
    }

    /**
     *
     * @return
     */
    @RequestMapping("eidtSysUserView")
    public String eidtSysUserView(){
        return "";
    }

}
