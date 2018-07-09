## Spring JDBC

---
>本系列的spring教程参考[w3cschool-spring教程](https://www.w3cschool.cn/wkspring/).<br/>
本系列教程只做简单讲解，并不对spring做深度剖析，若想深入了解请等待源码解析系列<br/>
spring版本使用 **4.1.6**, jdk使用 **1.8**, ide使用 **IDEA**.
---

>新建一个表

```
CREATE TABLE Student(
   ID   INT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(20) NOT NULL,
   AGE  INT NOT NULL,
   PRIMARY KEY (ID)
);
```

>新建一个Student类

```
public class Student {
    private Integer age;
    private String name;
    private Integer id;
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
```

>新建一个StudentMapper类

```
public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setAge(rs.getInt("age"));
        student.setName(rs.getString("name"));
        return student;
    }
}
```

>新建一个StudentDao接口，包括增删改查等方法

```
public interface StudentDao {

    void setDataSource(DataSource dataSource);

    void create(String name,Integer age);

    Student getStudent(Integer id);

    List<Student> listStudents();

    void delete(Integer id);

    void update(Integer id,Integer age);
}
```



>新建一个StudentJdbcTemplate类，实现StudentMapper接口,添加数据源配置

```
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
```

>新建Beans.xml配置文件

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="url" value="jdbc:mysql://127.0.0.1:3306/test"></property>
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
        <property name="username" value="root"></property>
        <property name="password" value="123456"></property>
    </bean>

    <bean id="studentJdbcTemplate" class="net.youzule.spring.chapter07.app1.StudentJdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

</beans>
```


>新建App类，运行程序

```
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
```

>运行结果

```
create record name: sean age: 11
create record name: yn age: 12
create record name: yq age: 13
查询到一个student:Student{age=11, name='sean', id=1}
[Student{age=11, name='sean', id=1}, Student{age=12, name='yn', id=2}, Student{age=13, name='yq', id=3}]
update student where id = 1
delete student where id = 1
```

我们可以从数据库中看到结果，如果运行正确的话有两条记录。

---

--完--