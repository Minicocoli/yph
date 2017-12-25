package com.yph.realm;

import com.yph.entity.user.User;
import com.yph.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Shiro Realm
 *
 * @author : Administrator Hzhan
 * @create ：2017/12/25
 **/
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    /**
     *  授权 ---->
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
     *  认证 ---->
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 在这里进行登录授权
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        log.info("{} 登录认证。。。。。",username);

        // 这里处理登陆 帐号逻辑
        User user = userService.findUserByUserNameAndPassword(username, password);

        if(user!=null){


        }


        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
