package net.youzule.spring.chapter02.ApplicationContext.ClassPath;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/6/30 17:45
 **/

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter02/ApplicationContext/ClassPath/PeopleBean.xml");
        People people = (People) context.getBean("people");
        System.out.println(people.getAge());
        System.out.println(people.getName());
    }
}
