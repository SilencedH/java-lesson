package net.youzule.spring.chapter05.app1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/5 15:46
 **/

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter05/app1/Beans.xml");

        People people = (People) context.getBean("people");

        System.out.println(people);
    }
}
