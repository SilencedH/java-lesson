package net.youzule.spring.chapter08.app1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/9 18:56
 **/

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter08/app1/Beans.xml");

        StudentJdbcTemplate studentJdbcTemplate = (StudentJdbcTemplate) context.getBean("studentJdbcTemplate");

        studentJdbcTemplate.create("sean",11,90,2018);
        studentJdbcTemplate.create("dh",11,100,2018);
        studentJdbcTemplate.create("ss",11,90,2018);
        studentJdbcTemplate.create("dd",11,90,2018);

    }
}
