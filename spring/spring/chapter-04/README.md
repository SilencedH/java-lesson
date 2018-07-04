## Spring 依赖Bean的自动装配

---
>本系列的spring教程参考[w3cschool-spring教程](https://www.w3cschool.cn/wkspring/).<br/>
本系列教程只做简单讲解，并不对spring做深度剖析，若想深入了解请等待源码解析系列<br/>
spring版本使用 **4.1.6**, jdk使用 **1.8**, ide使用 **IDEA**.
---

我们在注入依赖bean时，如果数量很多则需要配置大量的xml属性，spring为我们提供了依赖bean的自动装配。自动装配的方式有以下几种。

模式 | 描述
:- | :-
no | 这是默认的设置，它意味着没有自动装配，你应该使用显式的bean引用来连线。你不用为了连线做特殊的事。在依赖注入章节你已经看到这个了。
byName | 由属性名自动装配。Spring 容器看到在 XML 配置文件中 bean 的自动装配的属性设置为 byName。然后尝试匹配，并且将它的属性与在配置文件中被定义为相同名称的 beans 的属性进行连接。
byType|由属性数据类型自动装配。Spring 容器看到在 XML 配置文件中 bean的自动装配的属性设置为 byType。然后如果它的类型匹配配置文件中的一个确切的 bean 名称，它将尝试匹配和连接属性的类型。如果存在不止一个这样的 bean，则一个致命的异常将会被抛出。
constructor|类似于byType，但该类型适用于构造函数参数类型。如果在容器中没有一个构造函数参数类型的 bean，则一个致命错误将会发生。
autodetect | Spring首先尝试通过 constructor 使用自动装配来连接，如果它不执行，Spring 尝试通过 byType 来自动装配。

可以使用 byType 或者 constructor 自动装配模式来连接数组和其他类型的集合。

### 一、通过byName自动装配

####1.1 基本使用

>新建Wheel类、Car类

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

    private Wheel wheel;

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
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="wheel" class="net.youzule.spring.chapter04.app1.Wheel">
        <property name="count" value="4"></property>
    </bean>

    <!--不自动装配方式-->
    <!--<bean id="car" class="net.youzule.spring.chapter04.app1.Car">
        <property name="wheel" ref="wheel"></property>
        <property name="brand" value="bmw"></property>
    </bean>-->

    <!--根据名字自动装配-->
    <bean id="car" class="net.youzule.spring.chapter04.app1.Car" autowire="byName">
        <property name="brand" value="bmw"></property>
    </bean>
</beans>
```

>输出

```
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter04/app1/Beans.xml");

        Car car = (Car) context.getBean("car");
        System.out.println(car);

         /*
         输出结果
         Car{brand='bmw', wheel=Wheel{count=4}}
        */
    }
}


```

**我们会想到一个问题，这个根据名字注入，名字是怎么定的呢**

其实这么名字，就是Car类里面 *private Wheel wheel;*中属性的名字

#### 1.2 验证byName 的Name

>修改Wheel bean注入时的名字为wheels

```
 <bean id="wheels" class="net.youzule.spring.chapter04.app1.Wheel">
        <property name="count" value="4"></property>
    </bean>
```

>运行程序

```
Car{brand='bmw', wheel=null}
```

wheel为null。

我们注入spring容器的名字是wheels,而car自动注入找的是以wheel为名字的bean，所以为null。

### 通过byType自动装配

### 构造函数和自动装配组合使用




---
** 自动装配的局限性 **

当自动装配始终在同一个项目中使用时，它的效果最好。如果通常不使用自动装配，它可能会使开发人员混淆的使用它来连接只有一个或两个 bean 定义。不过，自动装配可以显著减少需要指定的属性或构造器参数，但你应该在使用它们之前考虑到自动装配的局限性和缺点。

限制|描述
:-|:-
重写的可能性|你可以使用总是重写自动装配的<constructor-arg\>和 <property\>设置来指定依赖关系。
原始数据类型|你不能自动装配所谓的简单类型包括基本类型，字符串和类。
混乱的本质|自动装配不如显式装配精确，所以如果可能的话尽可能使用显式装配。

--完--