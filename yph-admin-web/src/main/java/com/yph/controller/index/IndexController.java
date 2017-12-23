package com.yph.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页跳转控制器
 *
 * @author : Administrator Hzhan
 * @create ：2017/12/23
 **/
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}