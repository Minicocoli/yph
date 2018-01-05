package com.yph.controller.sys;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.yph.common.result.CommonResult;
import com.yph.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

/**
 * 登录控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Slf4j
@Controller
@RequestMapping("/sys/user")
public class LoginController {

    @Autowired
    private Producer producer;


    /**
     * 系统用户登录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public CommonResult login(String userName, String password, String validCode) {
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!kaptcha.equalsIgnoreCase(validCode)) {
            return CommonResult.ERROR("验证码不正确！");
        }
        try {
            Subject subject = ShiroUtils.getSubject();
            password = new Sha256Hash(password).toHex();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            log.info("帐号不存在");
            return CommonResult.ERROR(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return CommonResult.ERROR(e.getMessage());
        } catch (LockedAccountException e) {
            return CommonResult.ERROR(e.getMessage());
        } catch (Exception e) {
            return CommonResult.ERROR(e.getMessage());
        }
        return CommonResult.SUCCESS();
    }

    /**
     * 图片验证码
     *
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("captcha")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        HttpSession session = request.getSession();
        session.setAttribute("validCode", text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("loginOut")
    public CommonResult loginOut() {
        ShiroUtils.logout();
        return CommonResult.SUCCESS();
    }

}
