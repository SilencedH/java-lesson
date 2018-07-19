package net.youzule.shrio.chapter05.app1;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/16 18:04
 **/

public class MyRealm extends AuthorizingRealm {

    private PasswordService passwordService;

    public void setPasswordService(PasswordService passwordService){
        this.passwordService = passwordService;
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return new SimpleAuthenticationInfo("wu",passwordService.encryptPassword("123"),getName());
    }
}
