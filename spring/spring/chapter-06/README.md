## Spring AOP

---
>本系列的spring教程参考[w3cschool-spring教程](https://www.w3cschool.cn/wkspring/).<br/>
本系列教程只做简单讲解，并不对spring做深度剖析，若想深入了解请等待源码解析系列<br/>
spring版本使用 **4.1.6**, jdk使用 **1.8**, ide使用 **IDEA**.
---

### 一、什么是AOP
AOP(Aspect Oriented Programming) 是指面向切面编程。什么是面向切面编程呢？

假如我们想统一给项目中Controller层增加一个日志，包括Controller调用之前和调用之后的入参、出参，未使用aop的话我们需要给每个Controller的方法增加两句日志打印，这样很麻烦也容易出纰漏，使用AOP的思想，我们找到所有Controller的切点，只需要少量的代码就可实现。

#### 1.1 下面我们了解关于AOP的几个术语

项|描述
:-|:-
Aspect|一个模块具有一组提供横切需求的 APIs。例如，一个日志模块为了记录日志将被 AOP 方面调用。应用程序可以拥有任意数量的方面，这取决于需求。
Join point|在你的应用程序中它代表一个点，你可以在插件AOP方面。你也能说，它是在实际的应用程序中，其中一个操作将使用 Spring AOP 框架。
Advice|这是实际行动之前或之后执行的方法。这是在程序执行期间通过Spring AOP框架实际被调用的代码。
Introduction|引用允许你添加新方法或属性到现有的类中。
Target object|被一个或者多个方面所通知的对象，这个对象永远是一个被代理对象。也称为被通知对象。
Weaving|Weaving 把方面连接到其它的应用程序类型或者对象上，并创建一个被通知的对象。这些可以在编译时，类加载时和运行时完成。

#### 1.2 通知的类型
通知 | 描述
:-|:-
前置通知|在一个方法执行之前，执行通知。
后置通知|在一个方法执行之后，不考虑其结果，执行通知。
返回后通知|在一个方法执行之后，只有在方法成功完成时，才能执行通知。
抛出异常后通知|在一个方法执行之后，只有在方法退出抛出异常时，才能执行通知。
环绕通知|在建议方法调用之前和之后，执行通知。

### 二、基于XML的Spring AOP配置

>新建Logging类

```
public class Logging {

    public void beforeAdvice(){
        System.out.println("before advice...");
    }

    public void afterAdvice(){
        System.out.println("after advice");
    }

    public void afterReturningAdvice(Object returnObject){
        System.out.println("after returning advice: " + returnObject);
    }

    public void exceptionAdvice(IllegalArgumentException e){
        System.out.println("exception advice:" + e.toString());
    }
}
```

>新建一个Student类，写一个test方法

```
public class Student {

    private String name;

    private int age;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Student test(Student student){
        System.out.println("student test method...");
        return student;
    }
}
```

>新建Aop.xml配置类

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--注入logging bean-->
    <bean id="logging" class="net.youzule.spring.chapter06.app1.Logging"></bean>

    <!--注入student bean-->
    <bean id="student" class="net.youzule.spring.chapter06.app1.Student">
        <property name="name" value="sean"></property>
        <property name="age" value="11"></property>
    </bean>

    <!--aop配置-->
    <aop:config>
        <aop:aspect id="log" ref="logging">

            <!--横切关注点，要在哪里进行aop,这里是在Student类的test方法上进行-->
            <aop:pointcut id="pc" expression="execution(* net.youzule.spring.chapter06.app1.Student.test(..))" />

            <aop:before method="beforeAdvice" pointcut-ref="pc" />
            <aop:after method="afterAdvice" pointcut-ref="pc" />
            <aop:after-returning method="afterReturningAdvice" pointcut-ref="pc" returning="returnObject" />
            <aop:after-throwing method="exceptionAdvice" pointcut-ref="pc" throwing="e" />
        </aop:aspect>

    </aop:config>
</beans>
```

>运行程序

```
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter06/app1/Aop.xml");

        Student student = (Student) context.getBean("student");

        student.test(student);
    }
}
```

>运行结果

```
before advice...
student test method...
after advice
after returning advice: Student{name='sean', age=11}
```

我们生命的切入点是student的test方法，所以前置通知、后置通知和返回通知都可以看到有日志记录。

### 三、基于@Aspect注解的AOP

使用@Aspect注解的方式更直观、更清晰，只需要添加几个注解并将这个类注入spring容器即可。

>新建一个Student类，写一个test方法

```
public class Student {
    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Student test(Student student){
        System.out.println("student test method...");
        return student;
    }
}
```

>新建LogApo类，使用@Aspect注解进行配置

```
@Aspect
public class LogAop {

    @Pointcut("execution(* net.youzule.spring.chapter06.app2.Student.test(..))")
    public void pc(){

    }

    @Before("pc()")
    public void beforeAdvice(){
        System.out.println("before advice...");
    }

    @After("pc()")
    public void afterAdvice(){
        System.out.println("after advice...");
    }

    @AfterReturning(pointcut = "pc()",returning = "retVal")
    public void afterReturningAdvice(Object retVal){
        System.out.println(retVal);
    }

    @AfterThrowing(pointcut = "pc()",throwing = "ex")
    public void afterThrowExeception(IllegalArgumentException ex){
        System.out.println("throw exception :" + ex);
    }
}
```

>配置Beans.xml文件

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <aop:aspectj-autoproxy/>

    <bean id="student" class="net.youzule.spring.chapter06.app2.Student">
        <property name="age" value="11"></property>
        <property name="name" value="13"></property>
    </bean>

    <bean id="logAop" class="net.youzule.spring.chapter06.app2.LogAop">

    </bean>
</beans>
```

>运行程序

```
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter06/app2/Beans.xml");

        Student student = (Student) context.getBean("student");

        student.test(student);
        }
}
```

>运行结果

```
before advice...
student test method...
after advice...
Student{name='13', age=11}
```

---

--完--