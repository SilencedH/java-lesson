package net.youzule.spring.chapter08.app1;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/9 19:13
 **/

public class StudentJdbcTemplate implements StudentDao{

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private PlatformTransactionManager platformTransactionManager;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setPlatformTransactionManager(PlatformTransactionManager transactionManager){
        this.platformTransactionManager = transactionManager;
    }

    @Override
    public void create(String name, Integer age, Integer marks, Integer year) {
        TransactionDefinition definition = new DefaultTransactionDefinition();

        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(definition);

        try{
            String sql1 = "insert into student(name,age) values(?,?) ";
            jdbcTemplate.update(sql1,name,age);
            String sql2 = "select max(id) from student";
            int maxId = jdbcTemplate.queryForObject(sql2,Integer.class);
            System.out.println("最大id是:" + maxId);

            String sql3 = "insert into marks (sid,marks,year) values(?,?,?)";

            jdbcTemplate.update(sql3,maxId,marks,year);

            System.out.println("添加marks数据成功");
            //提交事务
            platformTransactionManager.commit(transactionStatus);


        }catch (Exception e){
            System.out.println("error" + e);
            platformTransactionManager.rollback(transactionStatus);
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
