package net.youzule.spring.chapter07.app1;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/9 17:29
 **/

public class StudentJdbcTemplate implements StudentDao {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String name, Integer age) {
        String sql = "insert into student (name,age) values (?,?)";
        jdbcTemplate.update(sql,name,age);
        System.out.println("create record name: " + name + " age: " + age);
    }

    @Override
    public Student getStudent(Integer id) {
        String sql = "select * from student where id = ?";
        Student student = jdbcTemplate.queryForObject(sql,new Object[]{id},new StudentMapper());
        return student;
    }

    @Override
    public List<Student> listStudents() {
        String sql = "select * from student";
        List<Student> students = jdbcTemplate.query(sql,new StudentMapper());
        return students;
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from student where id = ?";
        jdbcTemplate.update(sql,id);
        System.out.println("delete student where id = " + id);
    }

    @Override
    public void update(Integer id, Integer age) {
        String sql = "update student set age=? where id = ?";
        jdbcTemplate.update(sql,age,id);
        System.out.println("update student where id = " + id);
    }
}
