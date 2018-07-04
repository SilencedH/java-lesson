package net.youzule.spring.chapter03.app02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/4 11:17
 **/

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter03/app02/Beans.xml");
        Car car =(Car) context.getBean("car");
        System.out.println(car);
    }
}
