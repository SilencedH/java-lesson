package net.youzule.spring.chapter02.ApplicationContext.FileSystemXml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/6/30 17:45
 **/

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("C:\\Users\\13673\\Documents\\workspace\\github\\SilencedH\\java-lesson\\spring\\spring\\chapter-02\\src\\net\\youzule\\spring\\chapter02\\ApplicationContext\\FileSystemXml\\PeopleBean.xml");
        People people = (People) applicationContext.getBean("people");
        System.out.println(people.getAge());
        System.out.println(people.getName());
    }
}
