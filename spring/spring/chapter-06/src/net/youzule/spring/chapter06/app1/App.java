package net.youzule.spring.chapter06.app1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/7 17:03
 **/

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter06/app1/Aop.xml");

        Student student = (Student) context.getBean("student");

        student.test(student);
    }
}
