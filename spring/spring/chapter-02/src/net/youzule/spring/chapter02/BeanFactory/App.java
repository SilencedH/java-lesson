package net.youzule.spring.chapter02.BeanFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/6/30 15:52
 **/

public class App {
    public static void main(String[] args) {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("net/youzule/spring/chapter02/BeanFactory/UserBean.xml"));
        User user = (User) xmlBeanFactory.getBean("user");
        System.out.println(user.getName());
        }
}
