package net.youzule.spring.chapter08.app2;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/9 19:10
 **/

public class StudentMarksMapper implements RowMapper<StudentMarks>{

    @Override
    public StudentMarks mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentMarks studentMarks = new StudentMarks();
        studentMarks.setId(rs.getInt("id"));
        studentMarks.setAge(rs.getInt("age"));
        studentMarks.setMarks(rs.getInt("marks"));
        studentMarks.setName(rs.getString("name"));
        studentMarks.setSid(rs.getInt("sid"));
        studentMarks.setYear(rs.getInt("year"));
        return studentMarks;
    }
}
