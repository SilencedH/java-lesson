package net.youzule.spring.chapter07.app1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/9 15:39
 **/

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter07/app1/Beans.xml");
        StudentJdbcTemplate studentJdbcTemplate = (StudentJdbcTemplate) context.getBean("studentJdbcTemplate");
        studentJdbcTemplate.create("sean",11);
        studentJdbcTemplate.create("yn",12);
        studentJdbcTemplate.create("yq",13);

        Student student = studentJdbcTemplate.getStudent(1);

        System.out.println("查询到一个student:" + student);



        List<Student> students = studentJdbcTemplate.listStudents();
        System.out.println(students);


        studentJdbcTemplate.update(1,22);

        studentJdbcTemplate.delete(1);

    }
}
