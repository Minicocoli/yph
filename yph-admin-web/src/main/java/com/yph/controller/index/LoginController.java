package com.yph.controller.index;

import com.yph.common.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *  账户 登录
 * @author : Administrator Hzhan
 * @create ：2017/12/25
 **/
@Slf4j
@Controller
public class LoginController {

    /**
     *
     * @param request
     * @param userName
     * @param validCode
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public CommonResult login(HttpServletRequest request, String userName, String validCode, String password) {
        log.info("[ 登录 ] -----> userName : {}  validCode : {}  password : {}",userName,validCode ,password);

        //  先模拟 ---> 处理业务逻辑 ..
        HttpSession session = request.getSession();
        String sessionValidCode = (String) session.getAttribute("validCode");
        if(!StringUtils.isBlank(sessionValidCode) &&  sessionValidCode.equals(validCode)){
            return  CommonResult.SUCCESS();
        }
        return CommonResult.SUCCESS();
    }

    /**
     *  退出登录
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request){
        return "login";
    }

}
