package com.yph.realm;

import com.yph.entity.sys.SysUser;
import com.yph.service.sys.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户授权
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService sysUserService;


    /**
     * 授权 ---->
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("[ 进行授权 ] ---------->");
        // 授权业务逻辑 --->
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }



    /**
     * 认证 ---->
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 在这里进行登录授权
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        log.info("{} 登录认证。。。。。", username);

        SysUser selectSysUser = new SysUser();
        selectSysUser.setUserName(username);
        selectSysUser.setPassword(password);
        SysUser sysUser = sysUserService.findUserByNameAndPassword(selectSysUser);

        // 判断是否存在该用户
        if (sysUser == null) {
            throw new UnknownAccountException("帐号或者密码不正确");
        }

//        // 密码错误
//        if (!password.equals(user.getPassword())) {
//            throw new IncorrectCredentialsException("账号或密码不正确");
//        }
//
//        // 账户被删除
//        if ("1".equals(user.getFlag())) {
//            throw new LockedAccountException("账号已被锁定,请联系管理员");
//        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, password, getName());

        return info;
    }
}
