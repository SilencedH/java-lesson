##spring IOC容器中的Bean

---
>本系列的spring教程参考[w3cschool-spring教程](https://www.w3cschool.cn/wkspring/).<br/>
本系列教程只做简单讲解，并不对spring做深度剖析，若想深入了解请等待源码解析系列<br/>
spring版本使用 **4.1.6**, jdk使用 **1.8**, ide使用 **IDEA**.
---

spring提供了两种IOC容器，分别是 **BeanFactory** 容器和 **ApplicationContext** 容器.

容器名称|容器描述|
-|-
BeanFactory|它是最简单的容器，给DI提供了基本的支持，它用org.springframework.beans.factory.BeanFactory 接口来定义。BeanFactory或者相关的接口,如BeanFactoryAware,InitializingBean,DisposableBean,在Spring中仍然存在具有大量的与Spring整合的第三方框架的反向兼容性的目的。
ApplicationContext|该容器添加了更多的企业特定的功能,例如从一个属性文件中解析文本信息的能力,发布应用程序事件给感兴趣的事件监听器的能力,该容器是由org.springframework.context.ApplicationContext 接口定义。

ApplicationContext容器包含了BeanFactory容器的所有功能，所以通常建议使用ApplicationContext容器。但是BeanFactory 仍然可以用于轻量级的应用程序，如移动设备或基于 applet 的应用程序，其中它的数据量和速度是显著。

我们主要讲解ApplicationContext容器。

###一、ApplicationContext容器

>ApplicationContext容器有种实很多实现，我们主要使用两种**ClassPathXmlApplicationContext**和**FileSystemXmlApplicationContext**，其他实现我们在用到时会提起。

####1.1 ClassPathXmlApplicationContext

>新建一个类People

```
public class People {
    /**
    *name
    **/
    private String name ;

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
}
```

>新建一个spring bean配置文件 PeopleBean.xml

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

        <bean id="people" class="net.youzule.spring.chapter02.ApplicationContext.ClassPath.People">
            <property name="name" value="Sean"></property>
            <property name="age" value="11"></property>
        </bean>
    </beans>

>创建App类运行程序

```
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter02/ApplicationContext/ClassPath/PeopleBean.xml");
        People people = (People) context.getBean("people");
        System.out.println(people.getAge());
        System.out.println(people.getName());
    }
}
```

####1.2 FileSystemXmlApplicationContext
>前面的过程和XmlApplicationContext一样，只是在App类中，加载配置文件方式不同，如下所示。

```
public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("C:\\Users\\13673\\Documents\\workspace\\github\\SilencedH\\java-lesson\\spring\\spring\\chapter-02\\src\\net\\youzule\\spring\\chapter02\\ApplicationContext\\FileSystemXml\\PeopleBean.xml");
        People people = (People) applicationContext.getBean("people");
        System.out.println(people.getAge());
        System.out.println(people.getName());
    }
}
```

###二、Bean的生命周期

####2.1 Bean懒加载

在spring容器中，Bean的创建默认跟着容器一起的，就是项目启动完成后bean也已经创建完成。不过我们可以设置bea在被调用的时候被创建，即懒加载。

下面创建项目测试spring容器的懒加载机制。
>新建一个包Bean,新建两个类Dog 和 Cat,并在构造方法中添加信息

Dog类
```
public class Dog {

    private String mouse;

    private String eyes;

    public Dog() {
        System.out.println("dog is initializing... ");
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "mouse='" + mouse + '\'' +
                ", eyes='" + eyes + '\'' +
                '}';
    }
}
```

Cat类
```
public class Cat {
    private String hands;
    private String legs;

    public Cat() {
        System.out.println("cat is initializing...");
    }

    public String getHands() {
        return hands;
    }

    public void setHands(String hands) {
        this.hands = hands;
    }

    public String getLegs() {
        return legs;
    }

    public void setLegs(String legs) {
        this.legs = legs;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "hands='" + hands + '\'' +
                ", legs='" + legs + '\'' +
                '}';
    }
}
```

>新建Beans.xml配置文件,添加懒加载配置 **lazy-init="true"**

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dog" class="net.youzule.spring.chapter02.Bean.Dog" lazy-init="true">
        <property name="eyes" value="blue"></property>
        <property name="mouse" value="big"></property>
    </bean>

    <bean id="cat" class="net.youzule.spring.chapter02.Bean.Cat">
        <property name="hands" value="2"></property>
        <property name="legs" value="2"></property>
    </bean>

</beans>
```

>新建App类运行程序

```
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter02/Bean/Beans.xml");
        System.out.println("spring 容器加载完成。。。");

        Dog dog = (Dog) context.getBean("dog");
        System.out.println(dog);

        Cat cat = (Cat) context.getBean("cat");
        System.out.println(cat);
        System.out.println("===================================");

    }
}

```

>运行结果

```
cat is initializing...
spring 容器加载完成。。。
dog is initializing... 
Dog{mouse='big', eyes='blue'}
Cat{hands='2', legs='2'}
===================================
```

从运行结果我们可以看出，cat实例在spring容器加载完成之前就已经实例化了，而配置了懒加载的dog则是在调用实例化的时候实例化的。

#### 2.2 Bean的作用域
Bean的作用域只需要配置scrope属性即可。

```
<!-- A bean definition with singleton scope -->
<bean id="..." class="..." scope="singleton">
    <!-- collaborators and configuration for this bean go here -->
</bean>
```

scrope属性值及其作用范围

作用域|描述
:-|:-
singleton|在spring IoC容器仅存在一个Bean实例，Bean以单例方式存在，默认值
prototype|每次从容器中调用Bean时，都返回一个新的实例，即每次调用getBean()时，相当于执行newXxxBean()
request|每次HTTP请求都会创建一个新的Bean，该作用域仅适用于WebApplicationContext环境
session|同一个HTTPSession共享一个Bean，不同Session使用不同的Bean，仅适用于WebApplicationContext环境
global-session|一般用于Portlet应用环境，改作用于仅适用于WebApplicationContext环境

####2.3 Bean的初始化与销毁回调

我们在初始化完一个bea实例之后，可以对其做一些回调操作，在销毁之后也可以做回调操作。我们有两种方式实现回调，分别是实现 **InitializingBean,DisposableBean**接口和自定义回调函数。

#####2.3.1实现**InitializingBean,DisposableBean**接口

>新建Tiger类

```
public class Tiger implements InitializingBean,DisposableBean {

    private String legs;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("initialize call back");
    }

    public String getLegs() {
        return legs;
    }

    public void setLegs(String legs) {
        this.legs = legs;
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "legs='" + legs + '\'' +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destory call back");
    }
}
```

>在Beans.xml添加Tiger注入

```
  <bean id="tiger" class="net.youzule.spring.chapter02.Bean.Tiger">
        <property name="legs" value="4"></property>
  </bean>
```

>运行App类

spring中bean的初始化随着程序启动而创建实例，但是销毁操作需要我们在程序结束之前手动操作。那么我们的IOC容器要使用 AbstractApplicationContext实现，关闭时调用**registerShutdownHook()**方法。

```
public class App {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter02/Bean/Beans.xml");
        System.out.println("spring 容器加载完成。。。");

        Tiger tiger = (Tiger) context.getBean("tiger");
        System.out.println(tiger);

        context.registerShutdownHook();
    }
}
```

>运行结果

```
initializing a tiger...
initialize call back
spring 容器加载完成。。。
Tiger{legs='4'}
destory call back
```

#####2.3.2 自定义回调函数

使用自定义回调函数的方式更加灵活，也是推荐使用的方式。只需要在配置文件中指定回调函数方法即可。

>新建Lion类,增加两个方法 **init()** 和 **destroy()**

```
public class Lion {
    private String lion;

    public Lion() {
        System.out.println("initialize liong...");
    }

    public String getLion() {
        return lion;
    }

    public void setLion(String lion) {
        this.lion = lion;
    }

    public void init(){
        System.out.println("initialize call back...");
    }

    public void destory(){
        System.out.println("destroy call back...");
    }

    @Override
    public String toString() {
        return "Lion{" +
                "lion='" + lion + '\'' +
                '}';
    }
}
```

>配置lion bean，增加 **init-method** 和 **destroy-method**配置

```
 <bean id="lion" class="net.youzule.spring.chapter02.Bean.Lion" init-method="init" destroy-method="destory">
        <property name="lion" value="lion"></property>
 </bean>
```

>运行程序

```
public class App {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter02/Bean/Beans.xml");
        System.out.println("spring 容器加载完成。。。");

        Lion lion = (Lion) context.getBean("lion");
        context.registerShutdownHook();
    }
}

    /**运行结果
    initialize liong...
    initialize call back...
    spring 容器加载完成。。。
    Lion{lion='lion'}
    destroy call back...
    **/
```

####2.4 Bean后置处理器
BeanPostProcessor接口定义回调方法，你可以实现该方法来提供自己的实例化逻辑，依赖解析逻辑等。你也可以在 Spring 容器通过插入一个或多个 BeanPostProcessor 的实现来完成实例化，配置和初始化一个bean之后实现一些自定义逻辑回调方法。

你可以配置多个 BeanPostProcesso r接口，通过设置 BeanPostProcessor 实现的 Ordered 接口提供的 order 属性来控制这些 BeanPostProcessor 接口的执行顺序。

BeanPostProcessor 可以对 bean（或对象）实例进行操作，这意味着 Spring IoC 容器实例化一个 bean 实例，然后 BeanPostProcessor 接口进行它们的工作。

ApplicationContext 会自动检测由 BeanPostProcessor 接口的实现定义的 bean，注册这些 bean 为后置处理器，然后通过在容器中创建 bean，在适当的时候调用它。

>新建一个Monkey类

```
public class Monkey {

    private String name;

    public Monkey() {
        System.out.println("monkey constructor method...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init(){
        System.out.println("init method...");
    }

    public void destroy(){
        System.out.println("destroy method...");
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "name='" + name + '\'' +
                '}';
    }

}
```

>新建一个InitializeMonkey后置处理器

```
public class InitializeMonkey implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean monkeyPostProcessBeforeInitialization-beanName:" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean monkeyPostProcessAfterInitialization:" + beanName);
        return bean;
    }
}

```

>在App中输出

```
public class App {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter02/Bean/Beans.xml");
        System.out.println("spring 容器加载完成。。。");

        Monkey monkey = (Monkey) context.getBean("monkey");
        System.out.println(monkey);

        context.registerShutdownHook();

    }
}


```

>输出结果

```
 monkey constructor method...
 bean monkeyPostProcessBeforeInitialization-beanName:monkey
 init method...
 bean monkeyPostProcessAfterInitialization:monkey
 spring 容器加载完成。。。
 Monkey{name='monkey'}
 destroy method...
```

####2.5 Bean继承

bean 定义可以包含很多的配置信息，包括构造函数的参数，属性值，容器的具体信息例如初始化方法，静态工厂方法名，等等。

子 bean 的定义继承父定义的配置数据。子定义可以根据需要重写一些值，或者添加其他值。

Spring Bean 定义的继承与 Java 类的继承无关，但是继承的概念是一样的。你可以定义一个父 bean 的定义作为模板和其他子 bean 就可以从父 bean 中继承所需的配置。

当你使用基于 XML 的配置元数据时，通过使用父属性，指定父 bean 作为该属性的值来表明子 bean 的定义。

#####2.5.1 基本使用

>新建一个Parent类

```
public class Parent {
    private String name;
    private int age;
    private String gender;

    public Parent() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
```

>新建Son类

```
public class Son {
    private String name;
    private int age;
    private String gender;

    public Son() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Son{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
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

    <bean id="parent" class="net.youzule.spring.chapter02.BeanInherit.Parent">
        <property name="name" value="parentName"></property>
        <property name="age" value="11"></property>
        <property name="gender" value="f"></property>
    </bean>

    <bean id="son" class="net.youzule.spring.chapter02.BeanInherit.Son" parent="parent">
        <property name="gender" value="m"></property>
    </bean>

</beans>
```

>运行程序

```
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter02/BeanInherit/Beans.xml");

        Parent parent = (Parent) context.getBean("parent");
        System.out.println(parent);

        Son son = (Son) context.getBean("son");
        System.out.println(son);
    }

}
```

>运行结果

```
Parent{name='parentName', age=11, gender='f'}
Son{name='parentName', age=11, gender='m'}
```

#####2.5.2 属性模板

父 bean 自身不能被实例化，因为它是不完整的，而且它也被明确地标记为抽象的。当一个定义是抽象的，它仅仅作为一个纯粹的模板 bean 定义来使用的，充当子定义的父定义使用。

>增加一个bean配置，并修改 son 的parent值

```
    <bean id="beanTemplate" abstract="true">
        <property name="name" value="beanTemplateName"></property>
        <property name="age" value="11"></property>
    </bean>

    <bean id="son" class="net.youzule.spring.chapter02.BeanInherit.Son" parent="beanTemplate">
        <property name="gender" value="m"></property>
    </bean>
```

>输出son

```
Son{name='beanTemplateName', age=11, gender='m'}
```

**注意:这样的继承并不是类的继承，而是属性的继承**

---
--完--
