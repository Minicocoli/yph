package com.yph.controller.index;

import com.yph.common.annotation.SysLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 *
 * @author rator Hzhan
 * @create ：2017/12/22
 **/

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "user/login";
    }

}
