package net.youzule.spring.chapter08.app2;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/9 19:13
 **/

public class StudentJdbcTemplate implements StudentDao {

    private JdbcTemplate jdbcTemplate;


    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String name, Integer age, Integer marks, Integer year) {

        try{
            String sql1 = "insert into student(name,age) values(?,?) ";
            jdbcTemplate.update(sql1,name,age);
            String sql2 = "select max(id) from student";
            int maxId = jdbcTemplate.queryForObject(sql2,Integer.class);
            System.out.println("最大id是:" + maxId);

            String sql3 = "insert into marks (sid,marks,year) values(?,?,?)";

            if(true){
                throw new RuntimeException("失败，回滚啦");
            }
            jdbcTemplate.update(sql3,maxId,marks,year);
            //提交事务
        }catch (Exception e){
            System.out.println("error" + e);
            throw e;
        }
    }

    @Override
    public List<StudentMarks> getStudentMarks() {
        String sql = "select * from student,marks where student.id = marks.sid";
        List<StudentMarks> students = jdbcTemplate.query(sql,new StudentMarksMapper());
        return students;
    }
}
