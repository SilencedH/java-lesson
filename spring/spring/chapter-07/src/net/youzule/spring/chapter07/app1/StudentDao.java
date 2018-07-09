package net.youzule.spring.chapter07.app1;

import javax.sql.DataSource;
import java.util.List;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/9 17:24
 **/

public interface StudentDao {

    void setDataSource(DataSource dataSource);

    void create(String name,Integer age);

    Student getStudent(Integer id);

    List<Student> listStudents();

    void delete(Integer id);

    void update(Integer id,Integer age);
}
