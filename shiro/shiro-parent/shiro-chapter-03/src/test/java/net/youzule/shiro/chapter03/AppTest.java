package net.youzule.shiro.chapter03;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppTest {

    public Subject getSubject(String iniPath){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniPath);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        return subject;
    }

    @Test
    public void test1(){
        Subject subject = getSubject("classpath:shiro-role.ini");

        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang","123");
            subject.login(usernamePasswordToken);
            System.out.println("登录成功");

            /*subject.checkRole("role4");

            subject.checkRoles();
            if (subject.hasRole("role4")){
                System.out.println("has role4");
            }else {
                System.out.println("no role4");
            }*/

            if (subject.isPermitted("user:creates")){
                System.out.println("拥有权限:user:create");
            }else {
                System.out.println("没有权限:user:create");
            }

        }catch (Exception e){
            System.out.println("登录异常" + e);
        }
        subject.logout();

    }

    @Test
    public void test2(){
        Subject subject = getSubject("classpath:shiro-role.ini");
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("wang","123");
            subject.login(usernamePasswordToken);
            subject.checkPermission("sys:user:update");
        }catch (Exception e){
            System.out.println("登录异常" + e);
        }

    }

    @Test
    public void test3(){
        List<String> list = null;
        System.out.println(list.isEmpty());
    }
}