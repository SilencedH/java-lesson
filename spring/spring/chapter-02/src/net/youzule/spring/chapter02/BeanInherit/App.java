package net.youzule.spring.chapter02.BeanInherit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.portlet.context.XmlPortletApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/3 10:04
 **/

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter02/BeanInherit/Beans.xml");

        Parent parent = (Parent) context.getBean("parent");
        System.out.println(parent);

        Son son = (Son) context.getBean("son");
        System.out.println(son);
    }

}
