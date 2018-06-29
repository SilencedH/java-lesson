package net.youzule.java.lesson.spring.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @title:
 * @description:
 * @company:
 * @author:zhaodahai
 * @date:2018/6/28 17:10
 **/

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:net/youzule/java/lesson/spring/spring/HelloBean.xml");
        Hello hello = (Hello) applicationContext.getBean("hello");
        System.out.println(hello.getMessage());

    }
}
