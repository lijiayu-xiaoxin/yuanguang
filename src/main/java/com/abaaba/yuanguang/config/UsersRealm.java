package com.abaaba.yuanguang.config;

import com.abaaba.yuanguang.pojo.Users;
import com.abaaba.yuanguang.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersRealm extends AuthorizingRealm {

    @Autowired
    UsersService usersService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        info.addStringPermission(users.getUsers_autho());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 在数据库中获取该用户
        Users users = usersService.getAUsers(token.getUsername());
        if (users==null){
            return null;
        }
        return new SimpleAuthenticationInfo(users,users.getUsers_password(),"");
    }
}
