package net.youzule.spring.chapter08.app2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/10 11:23
 **/

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter08/app2/Beans.xml");

        StudentDao studentDao = (StudentDao) context.getBean("studentJdbcTemplate");

        studentDao.create("sean",11,90,2018);

    }
}
