package com.yph.utils;

import com.yph.entity.sys.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * shiro 工具类
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
public class ShiroUtils {
    /**
     *  获取 session
     * @return
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     *
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     *  获取当前用户
     * @return
     */
    public static SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     *  获取用户Id
     * @return
     */
    public static Long getUserId() {
        return getUser().getId();
    }

    /**
     *  在session 设置值
     * @param key
     * @param value
     */
    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     *  获取Session 信息
     * @param key
     * @return
     */
    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     *   是否登录
     * @return
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    /**
     *  退出登录
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     *  获取验证码
     * @param key
     * @return
     */
    public static String getKaptcha(String key) {
        String kaptcha = getSessionAttribute(key).toString();
        getSession().removeAttribute(key);
        return kaptcha;
    }

}
