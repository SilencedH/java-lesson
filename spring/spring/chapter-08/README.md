## Spring JDBC

---
>本系列的spring教程参考[w3cschool-spring教程](https://www.w3cschool.cn/wkspring/).<br/>
本系列教程只做简单讲解，并不对spring做深度剖析，若想深入了解请等待源码解析系列<br/>
spring版本使用 **4.1.6**, jdk使用 **1.8**, ide使用 **IDEA**.
---


### 一、编程式事务

>新建表

```
CREATE TABLE Student(
   ID   INT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(20) NOT NULL,
   AGE  INT NOT NULL,
   PRIMARY KEY (ID)
);

CREATE TABLE Marks(
   SID INT NOT NULL,
   MARKS  INT NOT NULL,
   YEAR   INT NOT NULL
);
```

>新建Student类,Marks类,StudentMarks类

```
/*Student类*/
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
/*Marks类*/
public class Marks {
    private Integer sid;

    private Integer marks;

    private Integer year;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}

/*StudentMarks类*/
public class StudentMarks {
    private Integer age;
    private String name;
    private Integer id;
    private Integer marks;
    private Integer year;
    private Integer sid;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "StudentMarks{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", marks=" + marks +
                ", year=" + year +
                ", sid=" + sid +
                '}';
    }
}

```

>新建StudentDao接口

```
public interface StudentDao {
    void setDataSource(DataSource dataSource);

    void create(String name,Integer age,Integer marks,Integer year);

    List<StudentMarks> getStudentMarks();

}
```

>新建StudentMarksMapper类

```
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
```

>新建StudentJdbcTemplate类，实现StudentDao接口

```
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
```

>新建Beans.xml配置文件

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
        <property name="url" value="jdbc:mysql://localhost:3306/test"></property>
        <property name="username" value="root"></property>
        <property name="password" value="123456"></property>
    </bean>

    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <bean id="studentJdbcTemplate" class="net.youzule.spring.chapter08.app1.StudentJdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
        <property name="platformTransactionManager" ref="transactionManager"></property>
    </bean>

</beans>
```

>运行程序

```
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
```

>运行结果,并可以在数据库中查询到记录

```
最大id是:1
添加marks数据成功
最大id是:2
添加marks数据成功
最大id是:3
添加marks数据成功
最大id是:4
添加marks数据成功
```

#### 二、声明式事务

声明式事务基于spring aop，允许我们使用注解或者xml配置事务，需要我们指定在哪里执行事务管理。

我们直接贴代码，基于上面的代码，只贴出修改的类。

>Beans.xml配置文件

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">


    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
        <property name="url" value="jdbc:mysql://localhost:3306/test"></property>
        <property name="username" value="root"></property>
        <property name="password" value="123456"></property>
    </bean>

    <!--定义一个‘增强’-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="create"/>
        </tx:attributes>
    </tx:advice>

    <!--aop配置-->
    <aop:config>
        <aop:pointcut id="pc" expression="execution(* net.youzule.spring.chapter08.app2.StudentJdbcTemplate.create(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="pc"/>
    </aop:config>

    <!--配置事务管理-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <bean id="studentJdbcTemplate" class="net.youzule.spring.chapter08.app2.StudentJdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

</beans>
```

>StudentJdbcTemplate类

```
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

            //我们修改true或false来验证事务提交与回滚操作
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
```

>App类,运行程序

```
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter08/app2/Beans.xml");

        StudentDao studentDao = (StudentDao) context.getBean("studentJdbcTemplate");

        studentDao.create("sean",11,90,2018);

    }
}
```

根据上面的true和false可以查看输出日志，并查看数据库记录。