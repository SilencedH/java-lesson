## Spring Bean的依赖注入
---
>本系列的spring教程参考[w3cschool-spring教程](https://www.w3cschool.cn/wkspring/).<br/>
本系列教程只做简单讲解，并不对spring做深度剖析，若想深入了解请等待源码解析系列<br/>
spring版本使用 **4.1.6**, jdk使用 **1.8**, ide使用 **IDEA**.
---
通俗的讲，依赖注入就是说假如我有一个类Car，类里面有一个属性是另外一个类Wheel，我们在生成Car类的实例时，Wheel类实例就是Car类的依赖，我们要找个合适的方式将Wheel类注入到Car类所在的spring容器中。
### 一、基于构造函数的注入
基于构造函数的依赖注入，是指添加将依赖作为参数的构造函数，然后初始化时指定要注入的依赖bean。

#### 1.1 初步使用
>新建一个Wheel类、Frame类、Car类，Car类依赖Wheel和Frame

```
/**Wheel类**/
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
```

```
 /*Frame类*/
public class Frame {
    private String name;

    public Frame() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

```
/*Car类*/
public class Car {
    private String brand;

    private Wheel wheel;

    private Frame frame;

    public Car() {
    }

    public Car(Wheel wheel, Frame frame) {
        this.wheel = wheel;
        this.frame = frame;
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

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", wheel=" + wheel +
                ", frame=" + frame +
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

    <bean id="car" class="net.youzule.spring.chapter03.app01.Car">
        <property name="brand" value="bmw"></property>
        <constructor-arg ref="wheel"></constructor-arg>
        <constructor-arg ref="frame"></constructor-arg>
    </bean>

    <bean id="wheel" class="net.youzule.spring.chapter03.app01.Wheel">
        <property name="count" value="4"></property>
    </bean>

    <bean id="frame" class="net.youzule.spring.chapter03.app01.Frame">
        <property name="name" value="frame"></property>
    </bean>
</beans>
```

>App类运行程序

```
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter03/app01/Beans.xml");

        Car car = (Car) context.getBean("car");
        System.out.println(car);
    }
}
```

>输出

```
Car{brand='bmw', wheel=Wheel{count=4}, frame=Frame{name='frame'}}
```

#### 1.2 注入顺序

其实这里有一个问题，如果构造函数参数有很多那么spring是怎么解析顺序的呢？

##### 1.2.1 改变frame和wheel注入顺序

我们将Beans.xml配置文件中依赖注入顺序改变。

```
    <bean id="car" class="net.youzule.spring.chapter03.app01.Car">
        <property name="brand" value="bmw"></property>
        <constructor-arg ref="frame"></constructor-arg><!--交换顺序-->
        <constructor-arg ref="wheel"></constructor-arg>
    </bean>
```

>输出

```
Car{brand='bmw', wheel=Wheel{count=4}, frame=Frame{name='frame'}}
```

发现输出结果并没有改变，就是说spring自动帮我们将类型顺序匹配。
然而我们这个类型是容易区别的，我们要进一步实验。
##### 1.2.2 使用int 和 String类型

>修改Car类新增int 属性，新增构造函数

```
/*新Car类*/
public class Car {
    private String brand;

    private int capacity;

    private Wheel wheel;

    private Frame frame;

    public Car() {
    }

    public Car(String brand, int capacity) {
        this.brand = brand;
        this.capacity = capacity;
    }

    public Car(Wheel wheel, Frame frame) {
        this.wheel = wheel;
        this.frame = frame;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", capacity=" + capacity +
                ", wheel=" + wheel +
                ", frame=" + frame +
                '}';
    }
}
```

>修改Beans.xml配置文件,并故意写错构造顺序。将bmw写前，33写后能够正确赋值。

```
<bean id="car" class="net.youzule.spring.chapter03.app01.Car">
        <constructor-arg value="33"></constructor-arg>
        <constructor-arg value="bmw"></constructor-arg>
        <!--<constructor-arg ref="frame"></constructor-arg>
        <constructor-arg ref="wheel"></constructor-arg>-->
    </bean>
```

>运行结果,报错

```
Error creating bean with name 'car' defined in class path resource
```


此次结果我们可以得出两个结论：

① 使用int 、String等java类型时也可以通过构造函数方式注入 

② 注入时我们需要解决参数类型和顺序的问题,默认按照构造函数的参数顺序

##### 1.2.3 解决构造参数类型和顺序

>通过指定类型

```
    <bean id="car" class="net.youzule.spring.chapter03.app01.Car">
        <constructor-arg type="int" value="33"></constructor-arg>
        <constructor-arg type="java.lang.String" value="ee"></constructor-arg>
        <!--<constructor-arg ref="frame"></constructor-arg>
        <constructor-arg ref="wheel"></constructor-arg>-->
    </bean>
```

>输出结果

```
Car{brand='ee', capacity=33, wheel=null, frame=null}
```

>通过指定索引

```
    <bean id="car" class="net.youzule.spring.chapter03.app01.Car">
        <constructor-arg index="1" value="33"></constructor-arg>
        <constructor-arg index="0" type="java.lang.String" value="ee"></constructor-arg>
        <!--<constructor-arg ref="frame"></constructor-arg>
        <constructor-arg ref="wheel"></constructor-arg>-->
    </bean>
```

>输出结果

```
Car{brand='ee', capacity=33, wheel=null, frame=null}
```

还有一些问题，比如有多个构造函数参数不等，类型交叉，注入时怎么识别不同的构造函数等等，大家可以自行实验。

### 二、基于设值的方式注入(推荐使用)

基于设值的方式注入很简单，只是将property中value 改为 ref配置就可以了。

>新建包app02，新建类Wheel

```
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

```

>新建类Car

```
public class Car {
    private String brand;

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
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


    <bean id="wheel" class="net.youzule.spring.chapter03.app02.Wheel">
        <property name="count" value="4"></property>
    </bean>

    <bean id="car" class="net.youzule.spring.chapter03.app02.Car">
        <property name="brand" value="bmw"></property>
        <property name="wheel" ref="wheel"></property>
    </bean>

</beans>
```

>运行程序

```
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter03/app02/Beans.xml");
        Car car =(Car) context.getBean("car");
        System.out.println(car);
    }
}
```

>输出结果

```
Car{brand='bmw', wheel=Wheel{count=4}}
```

你应该注意定义在基于构造函数注入和基于设值函数注入中的 Beans.xml 文件的区别。唯一的区别就是在基于构造函数注入中，我们使用的是〈bean〉标签中的〈constructor-arg〉元素，而在基于设值函数的注入中，我们使用的是〈bean〉标签中的〈property〉元素。

第二个你需要注意的点是，如果你要把一个引用传递给一个对象，那么你需要使用 标签的 ref 属性，而如果你要直接传递一个值，那么你应该使用 value 属性。

### 三、注入内部Bean

>内部bean的注入方式如下

```
<bean id="outerBean" class="...">
      <property name="target">
         <bean id="innerBean" class="..."/>
      </property>
   </bean>

</beans>
```


### 四、注入集合

Spring 提供了四种类型的集合的配置元素，如下所示：

元素 | 描述
: | :-
<list\>  | 它有助于连线，如注入一列值，允许重复。
<set\>   | 它有助于连线一组值，但不能重复。
<map\>   | 它可以用来注入名称-值对的集合，其中名称和值可以是任何类型。
<props\> | 它可以用来注入名称-值对的集合，其中名称和值都是字符串类型。

>新建CollectionsBean类

```
public class CollectionsBean {
    private List<String> list;
    private Set<String> set;
    private Map<String,String> map;

    private Properties properties;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "CollectionsBean{" +
                "list=" + list +
                ", set=" + set +
                ", map=" + map +
                ", properties=" + properties +
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

    <bean id="collectionsBean" class="net.youzule.spring.chapter03.app04.CollectionsBean">
        <property name="list">
            <list>
                <value>aa</value>
                <value>bb</value>
                <value>cc</value>
            </list>
        </property>
        <property name="map">
            <map>
                <entry key="name" value="sean"></entry>
                <entry key="age" value="11"></entry>
            </map>
        </property>
        <property name="set">
            <set>
                <value>12</value>
                <value>ee</value>
                <value>gg</value>
            </set>
        </property>
        <property name="properties">
            <props>
                <prop key="name">sss</prop>
                <prop key="age">11</prop>
            </props>
        </property>
    </bean>
</beans>
```

>运行程序

```
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/net/youzule/spring/chapter03/app04/Beans.xml");

        CollectionsBean collectionsBean = (CollectionsBean) context.getBean("collectionsBean");
        System.out.println(collectionsBean);
    }
}

```

>运行结果

```
CollectionsBean{list=[aa, bb, cc], set=[12, ee, gg], map={name=sean, age=11}, properties={age=11, name=sss}}
```

---

--完--