package net.youzule.spring.chapter08.app2;

import javax.sql.DataSource;
import java.util.List;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/9 19:02
 **/

public interface StudentDao {
    void setDataSource(DataSource dataSource);

    void create(String name, Integer age, Integer marks, Integer year);

    List<StudentMarks> getStudentMarks();

}
