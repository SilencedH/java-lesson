package net.youzule.shiro.chapter02;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AppTest {

    @Test
    public void testJdbcRealm(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang","1233");

        try {
            subject.login(usernamePasswordToken);
            System.out.println("登录成功");
        }catch (AuthenticationException e){
            System.out.println("认证失败" + e.getMessage());
        }
        subject.logout();
        System.out.println("登出");
    }
}