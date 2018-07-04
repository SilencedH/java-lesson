package net.youzule.spring.chapter04.app1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/4 18:20
 **/

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter04/app1/Beans.xml");

        Car car = (Car) context.getBean("car");
        System.out.println(car);

        /*输出结果
        Car{brand='bmw', wheel=Wheel{count=4}}
        */

    }
}
