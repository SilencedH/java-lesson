package net.youzule.spring.chapter09;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/10 14:28
 **/

public class App {

    static Log log = LogFactory.getLog(App.class);

    public static void main(String[] args) {
       /* log.info("启动程序");

        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter09/Beans.xml");

        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
        log.info(helloWorld.getMessage());*/

        System.out.println("hello");
        Map<String,String> map = new HashMap<>();

        String name =  map.get("name");
        System.out.println(name);

    }
}
