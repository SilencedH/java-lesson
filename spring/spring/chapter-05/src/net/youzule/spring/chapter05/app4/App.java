package net.youzule.spring.chapter05.app4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/6 18:36
 **/

public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DogConfig.class);
        context.register(CatConfig.class);

        context.refresh();

        Dog dog = (Dog) context.getBean(Dog.class);
        System.out.println(dog);

        Cat cat = (Cat) context.getBean("cat1");
        System.out.println(cat);
    }
}
