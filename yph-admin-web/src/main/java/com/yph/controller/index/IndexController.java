package com.yph.controller.index;

import com.google.code.kaptcha.Producer;
import com.yph.common.annotation.SysLog;
import com.yph.common.result.CommonResult;
import com.yph.entity.BaseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
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



    /**
     *  首页
     * @param request
     * @return
     */
    @ApiOperation(value = "跳转到首页",notes = "首页跳转")
    @SysLog("登陆日志")
    @RequestMapping("index")
    public String index(HttpServletRequest request) {

        String[] menus = {"系统设置",
                "用户管理",
                "销售系统",
                "营销系统",
                "订单系统",
                "财务系统",
                "日志系统"};
        List<BaseEntity> topMenuList = new ArrayList<>();

        BaseEntity baseEntity = null;
        for (int i = 0; i < menus.length; i++) {
            baseEntity = new BaseEntity();
            baseEntity.setName(menus[i]);
            baseEntity.setType(i);
            baseEntity.setUrl("changeLeftMenus.htm?type=" + i);
            topMenuList.add(baseEntity);
        }
        request.setAttribute("topMenuList", topMenuList);

        String type = request.getParameter("type");
        if (StringUtils.isEmpty(type)) {
            type = "0";
        }
        ArrayList<Object> leftMenuList = new ArrayList<>();
        HashMap<String, Object> parentMap = new HashMap<>();
        String[] leftMenu = {"菜单管理", "权限管理", "日志管理", "基础数据管理", "邮件通知设置", "推送信息设置", "登录提醒设置"};
        parentMap.put("menus", leftMenu[Integer.valueOf(type)]);
        leftMenuList.add(parentMap);
        request.setAttribute("leftMenus", leftMenuList);
        return "begin";
    }

    /**
     *  更改左侧菜单
     * @param request
     */
    @RequestMapping("changeLeftMenus")
    public void changeLeftMenus(HttpServletRequest request) {
        String type = request.getParameter("type");
        if (StringUtils.isEmpty(type)) {
            type = "0";
        }
        ArrayList<Object> leftMenuList = new ArrayList<>();
        HashMap<String, Object> parentMap = new HashMap<>();
        String[] leftMenu = {"菜单管理", "权限管理", "日志管理", "基础数据管理", "邮件通知设置", "推送信息设置", "登录提醒设置"};
        parentMap.put("menus", leftMenu[Integer.valueOf(type)]);
        leftMenuList.add(parentMap);
        request.setAttribute("leftMenus", leftMenuList);
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