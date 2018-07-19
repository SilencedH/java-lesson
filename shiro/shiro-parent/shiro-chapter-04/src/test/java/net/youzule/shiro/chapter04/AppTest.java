package net.youzule.shiro.chapter04;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AppTest {


    @Test
    public void test1() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //设置authenticator
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        securityManager.setAuthenticator(authenticator);
        //设置autorizer
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        authorizer.setPermissionResolver(new WildcardPermissionResolver());
        securityManager.setAuthorizer(authorizer);

        //设置Realm
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(druidDataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);
        securityManager.setRealms(Arrays.asList((Realm) jdbcRealm));

        //将SecurityMananger设置到SecurityUtils 方便全局使用
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang", "1233");
        subject.login(usernamePasswordToken);
        Assert.assertTrue(subject.isAuthenticated());
        System.out.println("已经授权成功！");
    }

    @Test
    public void test2() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "ss");
        map.put("age", 11);
        map.put("object", "ss");
        User user = JSON.parseObject(JSON.toJSONString(map), User.class);
        user.setData(map);
        System.out.println(user);
    }

    @Test
    public void test3() {
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-config.ini");
        SecurityManager securityManager = factory.getInstance();
        //将SecurityManager设置到SecurityUtils 方便全局使用
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1233");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
        System.out.println("授权成功!");
    }
}