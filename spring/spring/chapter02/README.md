###spring IOC容器
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

####BeanFactory容器