## Spring 基于注解的配置

---
>本系列的spring教程参考[w3cschool-spring教程](https://www.w3cschool.cn/wkspring/).<br/>
本系列教程只做简单讲解，并不对spring做深度剖析，若想深入了解请等待源码解析系列<br/>
spring版本使用 **4.1.6**, jdk使用 **1.8**, ide使用 **IDEA**.
---

spring支持使用一些注解，帮助完善bean的配置，下面我们分别来介绍相关的几个注解的使用，开始注解配置要在Beans.xml配置文件中，添加

```
    <context:annotation-config></context:annotation-config>
```

### 一、@Required注解

@Required注解作用在类某个属性的setter方法上面，表明在bean配置注入时，这个属性的值是必须要设置的。

>新建People类，并在name和address属性的setter方法上添加@Required注解

```
package net.youzule.spring.chapter05.app1;

import org.springframework.beans.factory.annotation.Required;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/5 15:46
 **/

public class People {
    private String name;
    private String gender;

    private String address;

    public People() {
    }

    public String getName() {
        return name;
    }

    @Required
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    @Required
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

```

>新建Beans.xml配置文件,只设置name和gender属性的值

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config></context:annotation-config>

    <bean id="people" class="net.youzule.spring.chapter05.app1.People">
        <property name="name" value="sean"></property>
        <property name="gender" value="m"></property>
    </bean>
</beans>
```

>运行程序

```
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter05/app1/Beans.xml");

        People people = (People) context.getBean("people");

        System.out.println(people);
    }
}
```

>输出结果,抛出异常

```
Property 'address' is required for bean 'people'
```

>address的setter方法上去掉@Required注解，输出

```
People{name='sean', gender='m', address='null'}
```

### 二、@Autowire注解

使用@Autowire注解可以免去我们手动注入依赖，它可以作用在属性、setter方法和构造函数上面，自动根据类型(byType)来注入。

#### 2.1 作用在属性上面

>新建Car类和Wheel类

```
/*Wheel类*/
public class Wheel {
    private int count;

    public Wheel() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "count=" + count +
                '}';
    }
}

/*Car类*/
public class Car {

    private String brand;

    @Autowired
    private Wheel wheel;

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", wheel=" + wheel +
                '}';
    }
}
```

>新建Beans.xml配置文件

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 开启注解配置 -->
    <context:annotation-config></context:annotation-config>

    <bean id="wheel" class="net.youzule.spring.chapter05.app2.Wheel">
        <property name="count" value="4"></property>
    </bean>

    <bean id="car" class="net.youzule.spring.chapter05.app2.Car">
        <property name="brand" value="bmw"></property>
    </bean>
</beans>
```

>运行程序

```
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter05/app2/Beans.xml");

        Car car = (Car) context.getBean("car");
        System.out.println(car);

    }
}
```

>运行结果

```
Car{brand='bmw', wheel=Wheel{count=4}}
```

#### 2.2 作用在setter方法上面

>修改Car类，将@Autowire注解放到setWheel方法上面

```
/*Car类*/
public class Car {

    private String brand;

/*
    @Autowired
*/
    private Wheel wheel;

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Wheel getWheel() {
        return wheel;
    }

    @Autowired
    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", wheel=" + wheel +
                '}';
    }
}
```

>运行结果是一样的

```
Car{brand='bmw', wheel=Wheel{count=4}}
```

#### 2.3 作用在构造函数上面

```
 /*新增构造函数，使用@Autowire注解*/
    @Autowired
    public Car(Wheel wheel) {
        this.wheel = wheel;
    }
```

>运行结果是一样的

```
Car{brand='bmw', wheel=Wheel{count=4}}
```

使用了@Autowire注解之后，我们只管配置bean，要注入到那里就放到哪个类的属性或者setter方法即可。

### 三、@Qualifier注解

如果我们相同的类，注入了不同的bean,即id不同，那使用注解自动注入依赖时该如何做呢？我们可以使用@Qualifier注解来指定特定id的依赖。

**在上面基础上修改**
>新的Beans.xml配置文件

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config></context:annotation-config>

    <bean id="wheel1" class="net.youzule.spring.chapter05.app3.Wheel">
        <property name="count" value="2"></property>
    </bean>

    <bean id="wheel2" class="net.youzule.spring.chapter05.app3.Wheel">
        <property name="count" value="4"></property>
    </bean>

    <bean id="car" class="net.youzule.spring.chapter05.app3.Car">
        <property name="brand" value="bmw"></property>
    </bean>
</beans>
```

>Car类上修改内容

```
    @Autowired
    @Qualifier("wheel2")
    private Wheel wheel;
```

>输出

```
Car{brand='bmw', wheel=Wheel{count=4}}
```

### 四、基于java的配置

我们可是将@Configuration和@Bean配合使用来配置bean。

#### 4.1 基本使用
>新建Dog类

```
public class Dog {
    private String name;

    private int age;

    public Dog() {
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
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
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

>新建DogConfig类

```
@Configuration
public class DogConfig {

    @Bean
    public Dog dog(){
        return new Dog("dog",11);
    }
}
```

>运行程序，注意使用AnnotationConfigApplicationContext

```
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DogConfig.class);


        context.refresh();

        Dog dog = (Dog) context.getBean(Dog.class);
        System.out.println(dog);

      
    }
}
```

>运行结果

```
Dog{name='dog', age=11}
```

#### 4.2 注册多个配置类

AnnotationConfigApplicationContext可以注册多个配置类。

>另外新建Cat类和CatConfig类

```
/*Cat类*/
public class Cat {

    private String type;

    private String home;

    public Cat() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "type='" + type + '\'' +
                ", home='" + home + '\'' +
                '}';
    }
}

/*CatConfig类*/
@Configuration
public class CatConfig {

    @Bean(name = "cat1")
    public Cat cat(){
        Cat cat = new Cat();
        cat.setHome("jy");
        cat.setType("ju");
        return cat;
    }
}
```

>修改App类

```
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DogConfig.class);
        context.register(CatConfig.class);

        context.refresh();

        Dog dog = (Dog) context.getBean(Dog.class);
        System.out.println(dog);

        Cat cat = (Cat) context.getBean("cat1");
        System.out.println(cat);
    }
}
```

>运行结果

```
Dog{name='dog', age=11}
Cat{type='ju', home='jy'}
```

我们可以同时通过byName和byType来获取bean，例如上面Dog bean获取是通过Dog.class,Cat bean是通过 "cat1"。

