package net.youzule.spring.chapter06.app2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/9 14:55
 **/

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter06/app2/Beans.xml");

        Student student = (Student) context.getBean("student");

        student.test(student);
        }
}
