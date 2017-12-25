package com.yph.controller.index;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.yph.common.result.CommonResult;
import com.yph.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
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
 *  账户 登录
 * @author : Administrator Hzhan
 * @create ：2017/12/25
 **/
@Slf4j
@Controller
public class LoginController {

    @Autowired
    private Producer producer;


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
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!kaptcha.equalsIgnoreCase(validCode)){
            return CommonResult.ERROR("验证码不正确！");
        }

        Subject subject = ShiroUtils.getSubject();
        //sha256加密
//        password = new Sha256Hash(password).toHex();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        subject.login(token);

        return CommonResult.SUCCESS();
    }

    /**
     *  图片验证码
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
        session.setAttribute("validCode",text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }



    /**
     *  退出登录
     * @return
     */
    @ResponseBody
    @RequestMapping("loginOut")
    public CommonResult loginOut(){
        ShiroUtils.logout();
        return CommonResult.SUCCESS();
    }

}
