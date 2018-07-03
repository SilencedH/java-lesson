package net.youzule.spring.chapter02.Bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/6/30 18:02
 **/

public class App {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter02/Bean/Beans.xml");
        System.out.println("spring 容器加载完成。。。");

        Monkey monkey = (Monkey) context.getBean("monkey");
        System.out.println(monkey);

        Duck duck = (Duck) context.getBean("duck");
        System.out.println(duck);

        context.registerShutdownHook();


       /*Cat cat = (Cat) context.getBean("cat");
        System.out.println(cat);*/



        /*Lion lion = (Lion) context.getBean("lion");

        System.out.println(lion);
        context.registerShutdownHook();*/

        /*Tiger tiger = (Tiger) context.getBean("tiger");
        System.out.println(tiger);
        context.registerShutdownHook();

       Dog dog = (Dog) context.getBean("dog");
        System.out.println(dog);

        Cat cat = (Cat) context.getBean("cat");
        System.out.println(cat);

        System.out.println("===================================");*/


    }
}
