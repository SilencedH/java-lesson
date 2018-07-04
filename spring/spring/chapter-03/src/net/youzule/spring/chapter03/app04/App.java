package net.youzule.spring.chapter03.app04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/4 12:01
 **/

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter03/app04/Beans.xml");

        CollectionsBean collectionsBean = (CollectionsBean) context.getBean("collectionsBean");
        System.out.println(collectionsBean);
    }
}
