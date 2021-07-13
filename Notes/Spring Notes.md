# Spring

## 1.Spring是什么

### 1.1

Spring 是一个开源框架（容器）

Spring 是为简化企业级应用开发而生，使用Spring可以使简单的javabean实现以前只有EJ 才能实现的功能

Spring是一个IOD(DI) 和AOP 容器框架

### 1.2

轻量级: Spring 是非侵入性的

控制反转 （IOC-Inversion of Control）

依赖注入（DI - dependency injection)

面向切面编程（AOP-aspecy oriented programming）

容器 : Spring是一个容器，因为他包含管理应用对象的生命周期

框架：Spring实现了简单的组件配置组合成一个复杂的应用。

一站式：在IOC和AOP的基础上可以整合各种企业应用的开源框架和优秀的第三方库

总结：Spring 是一个轻量级的控制反转(IOC) 和面向切面编程的(AOP)的框架

### 1.3 Spring 的组成

![1569934897811.png](https://blog.kuangstudy.com/usr/uploads/2019/10/3294472123.png)

- **核心容器**：核心容器提供 Spring 框架的基本功能。核心容器的主要组件是 `BeanFactory`，它是工厂模式的实现。`BeanFactory` 使用*控制反转*（IOC） 模式将应用程序的配置和依赖性规范与实际的应用程序代码分开。
- **Spring 上下文**：Spring 上下文是一个配置文件，向 Spring 框架提供上下文信息。Spring 上下文包括企业服务，例如 JNDI、EJB、电子邮件、国际化、校验和调度功能。
- **Spring AOP**：通过配置管理特性，Spring AOP 模块直接将面向切面的编程功能 , 集成到了 Spring 框架中。所以，可以很容易地使 Spring 框架管理任何支持 AOP的对象。Spring AOP 模块为基于 Spring 的应用程序中的对象提供了事务管理服务。通过使用 Spring AOP，不用依赖组件，就可以将声明性事务管理集成到应用程序中。
- **Spring DAO**：JDBC DAO 抽象层提供了有意义的异常层次结构，可用该结构来管理异常处理和不同数据库供应商抛出的错误消息。异常层次结构简化了错误处理，并且极大地降低了需要编写的异常代码数量（例如打开和关闭连接）。Spring DAO 的面向 JDBC 的异常遵从通用的 DAO 异常层次结构。
- **Spring ORM**：Spring 框架插入了若干个 ORM 框架，从而提供了 ORM 的对象关系工具，其中包括 JDO、Hibernate 和 iBatis SQL Map。所有这些都遵从 Spring 的通用事务和 DAO 异常层次结构。
- **Spring Web 模块**：Web 上下文模块建立在应用程序上下文模块之上，为基于 Web 的应用程序提供了上下文。所以，Spring 框架支持与 Jakarta Struts 的集成。Web 模块还简化了处理多部分请求以及将请求参数绑定到域对象的工作。
- **Spring MVC 框架**：MVC 框架是一个全功能的构建 Web 应用程序的 MVC 实现。通过策略接口，MVC 框架变成为高度可配置的，MVC 容纳了大量视图技术，其中包括 JSP、Velocity、Tiles、iText 和 POI。



Spring Maven 架包：

<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>



<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>

### 1.4 拓展

![1569935107155.png](https://blog.kuangstudy.com/usr/uploads/2019/10/2419223029.png)



- Springboot

  一个快速开发的脚手架

  基于Springboot可以快速开发单个微服务

  约定大于配置

- SpringCloud

  SpringCloud是基于Sprinboot实现



因为现在大部分公司都在使用SpringBoot进行快速开发，学习Springboot的前提，需要完全掌握Spring及SpringMVC- 承上启下的作用



Spring的弊端：发展了太久之后，违背了原来的理念 配置十分繁琐 简称 “配置地狱”



## 2. IOC 理论推导

### 2.1 传统开发： 

1.UserDao 接口

2.UserDaoImpl 实现类

3.UserService 业务接口

4.UserServiceImpl业务实现



在我们的业务中，用户的需求可能会影响我们原来的代码，我们需要根据用户的需求去修改源代码，如果程序代码量十分大，修改一次的成本很昂贵。

我们使用一个set接口实现：

```java
private UserDao userDao;
// 利用set进行动态实现值的注入！
public void setUserDao(UserDao userDao){    
  this.userDao = userDao;}
```

 之前：程序是主动创建对象，控制权在程序员手上

使用set注入之后，程序不再具有主动性，而是一个被接受对象,从而由程序员自行控制创建对象，变成把主动权交给了调用者(用户)，程序不用管怎么去创建和实现，他只负责去提供一个接口



**控制反转**：就是讲一个程序主动的创建对象变成被动的创建对象。 这种思想，从本质上解决了程序员不再去管理对象的创建了，系统的耦合性大大的降低，从而使程序员更加专注的在业务的实现上。**这是IOC的原型**



## 3.IOC本质

**控制反转（Inversion of control）是一种设计思想，DI(依赖注入)是实现IOC的一种方法。**没有IOC的程序中， 我们使用面向对象编程，对象的创建与对象间的依赖关系 完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓控制反转就是：获取依赖对象的方式反转了。

![1569935285010.png](https://blog.kuangstudy.com/usr/uploads/2019/10/71929266.png)



**IoC是Spring框架的核心内容**，使用多种方式完美的实现了IoC，可以使用XML配置，也可以使用注解，新版本的Spring也可以零配置实现IoC。

Spring容器在初始化时先读取配置文件，根据配置文件或元数据创建与组织对象存入容器中，程序使用时再从Ioc容器中取出需要的对象。

![container magic](https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/images/container-magic.png)





采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。

**控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（Dependency Injection,DI）。**



## 4.HelloSpring

### 4.1 Hello对象是由谁创建的？

hello对象是由Spring创建的

### 4.2 Hello对象的属性是怎么设置的？

hello对象的属性是由Spring容器设置的



![image-20191210173507711](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191210173507711.png)



这个过程就叫控制反转 :

控制 : 谁来控制对象的创建 , 传统应用程序的对象是由程序本身控制创建的 , 使用Spring后 , 对象是由Spring来创建的 .

反转 : 程序本身不创建对象 , 而变成被动的接收对象 .

依赖注入 : 就是利用set方法来进行注入的.

IOC是一种编程思想 , 由主动的编程变成被动的接收 .

**OK , 到了现在 , 我们彻底不用再程序中去改动了 , 要实现不同的操作 , 只需要在xml配置文件中进行修改 , 所谓的IoC,一句话搞定 : 对象由Spring 来创建 , 管理 , 装配 !**



## 5.IOC创建对象的方式



1.使用无参构造的方法来创建对象（默认实现）

2.假设我们要使用有参构造方式来创建对象

```xml
            1.第一种 下标赋值
    <bean id="user" class="study.projo.User" >
        <property name="name" value="Spring"/>
        <constructor-arg index="0" value="Spring" />
    </bean>

    2.第二种 通过类型赋值(当有多个同类型是 无法识别 所以不建议使用）
    <bean id="user" class="study.projo.User">
        <constructor-arg type="java.lang.String" value="String"/>
    </bean>

    3. 第三种 通过参数名赋值 （推荐）
    <bean id="user" class="study.projo.User">
        <constructor-arg name="name" value="Xin"/>
    </bean>
```



Spring容器就相当于一个婚介所，所有的人都被在婚介所注册了的（相当于所有对象都是被创建了的），当需要使用时 直接get就行



总结：在配置文件加载的时候，容器中的管理对象就已经被初始化了



## 6.Spring 配置

### 6.1 别名

```
别名：如果添加了别名 也可以用别名获取对象
```

```xml
<alias name="user" alias="userA"/>
```

### 6.2 Bean的配置

id：bean的唯一标识符，也就是相当于我们学的对象名

class：bean 对象所对应的全限定名：包名+类型

name：也是别名 而且name 可以同时取多个别名（通过逗号分隔）

### 6.3 import

一般用于团队开发使用， 他可以将多个配置文件导入合并为一个

假设，现在项目中有多人开发，这三个人负责不同的类开发，不同的类需要注册在不同的bean中我们可以利用import将所有人的beans.xml 合并为一个总的

- 张三

- 李四

- 王五

  使用的时候，直接就可以使用总的配置。

  

```xml
<import resource="beans.xml"/><import resource="beans1.xml"/><import resource="beans2.xml"/>
```





## 7 依赖注入



### 7.1 构造器注入

第五章中所讲的

### 7.2 Set方式注入【重点】

- 依赖注入：Set注入！
  - 依赖：bean对象的创建依赖于容器
  - 注入：bean对象中的所有属性，由容器来注入！

【环境搭建】

1. 复杂类型
2. 真实测试对象
3. beans.xml
4. 测试类

程序代码例子：spring-04-di

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="address" class="study.projo.Address">
        <property name="address" value="成都"/>
    </bean>

    <bean id="student" class="study.projo.Student" >
        <!--    1.第一种 普通方式注入 value-->
        <property name="name" value="Xin"/>

        <!--        2.第二种 复杂类型 bean注入 ref-->
        <property name="address" ref="address" />

        <!--        3.第三种 数组注入 -->
        <property name="books">
            <array>
                <value>红楼梦</value>
                <value>西游记</value>
                <value>水浒传</value>
                <value>三国演义</value>
            </array>
        </property>

        <!--        4.List类型注入-->
        <property name="hobbys">
            <list>
                <value>听歌</value>
                <value>敲代码</value>
                <value>看电影</value>
            </list>
        </property>

        <!--        5.Map类型注入-->
        <property name="card">
            <map>
                <entry key="身份证" value="1111111111"/>
                <entry key="银行卡" value="2222222222"/>
            </map>
        </property>

        <!--        6 Set类型注入-->
        <property name="games">
            <set>
                <value>LOL</value>
                <value>COC</value>
                <value>BOB</value>
            </set>
        </property>

        <!--        Null的注入-->
            <!--        注入为空值 wife = “”-->
<!--        <property name="wife" value="" />   -->

            <!--        注入为空串 wife = “null”-->
        <property name="wife">
            <null/>
        </property>

        <!--        properties类型的注入
                    keu=value
                    key=value
            -->
        <property name="info">
            <props>
                <prop key="学号">163020206</prop>
                <prop key="性别">man</prop>
                <prop key="学号">小明</prop>
            </props>
        </property>

    </bean>

</beans>
~~~

### 7.3 拓展方式注入

1. XML shortcut with the p-namespace （p命名空间）

   需要借用外部约束 和 junit中的test

2. XML Shortcut with the c-namespace （c命名空间）

   使用构造器注入（使用在有参构造中）



使用：

```xml
<beans xmlns="http://www.springframework.org/schema/beans"       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      <!--    p 和 c 命名空间的xml约束->   
xmlns:p="http://www.springframework.org/schema/p"       xmlns:c="http://www.springframework.org/schema/c"       xsi:schemaLocation="http://www.springframework.org/schema/beans        https://www.springframework.org/schema/beans/spring-beans.xsd">    
    <!--    p 命名空间注入 可以直接注入属性的值：property-->    
    <bean id="user" class="study.projo.User" p:name="XIN" p:age="18"/>        
    <!--    c 命名空间注入 通过构造器注入：constructs-args -->    
    <bean id="user2" class="study.projo.User" c:age="18" c:name="TU"/>
</beans>
```



测试

```java
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.projo.Student;
import study.projo.User;
//    p 命名空间获取需要借助junit中的test来获取    
@Test    
public void test2()   
    {        
    ApplicationContext context = new ClassPathXmlApplicationContext("userBeans.xml");        User user2 = context.getBean("user2",User.class);
    System.out.println(user2);        
    User user = context.getBean("user",User.class);        
    System.out.println(user);    
	}
}
```



注意点：p命名和c命名空间不能直接使用 需要导入xml约束（官网找）



## 8.bean的作用域

### ![image-20191210224801708](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191210224801708.png)8.1 Singleton 单例模式（ Spring默认机制）

![singleton](https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/images/singleton.png)

```xml
<bean id="user2" class="study.projo.User" c:age="18" c:name="TU" scope="singleton"/>
```



### 8.2 原型模式

![prototype](https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/images/prototype.png)

```xml
<bean id="user2" class="study.projo.User" c:age="18" c:name="TU" scope="prototype"/>
```



### 8.3 其余的request，session，application 这几个只能在web开发中使用到



## 9 bean的自动装配

- 自动转配是Spring满足bean依赖的一种方式
- Spring会在上下文中自动寻找，并自动给bean装配属性

在Spring中有三种装配的方式

1. 在xml中显式的配置
2. 在java中显式配置
3. 隐式 的自动装配【重要】

### 9.1 测试

1. 环境搭建成果：一个人有两个宠物

   

### 9.2 Byname自动装配



```xml
<!--    byName: 会自动在容器上下文查找，和自己对象Set方面相对应的bean id-->    
<bean id="people" class="study.projo.People" autowire="byName">        
    <property name="name" value="XIN"/>
 </bean>
```



### 9.3 Bytype自动装配



```xml
<!--    byType: 会自动在容器上下文查找，和自己对象属性类型相同的bean-->
<bean  class="study.projo.Cat"/>
<bean  class="study.projo.Dog"/>
<bean id="people" class="study.projo.People" autowire="byType">   
	<property name="name" value="XIN"/>
</bean>
```



小结：

- Byname的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值一致
- Bytype的时候，需要保证所有bean的class唯一，并且这个bean需要和自动注入的属性的类型一致

### 9.4 注解实现自动装配

jdk1.5 支持的注解，Spring2.5支持开始支持注解

The introduction of annotation-based configuration raised the question of whether this approach is “better” than XML. 



要使用注解须知：

1. 导入约束 context 约束

2. 配置注解的支持 ：<context:annotation-config/> 【重要】

   

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd">
   
       <context:annotation-config/>
   
   </beans>
   ```

   **@Autowired**

   1. 直接在属性上使用即可！ 也可以在Set方式上使用
   2. 使用Autowired 我们可以不用编写Set方法了，前提是你这个自动装配的属性在IOC（Spring）容器中存在，且符号名称Byname

科普

```xml
@Nullable  字段标记了这个注解 说明这个字段可以为null;
```

~~~java
public @interface Autowired{
    boolean required() default true;
} 

~~~



测试代码：

~~~java
public class People {
    // 如果显示定义了Autowired的required属性为false，说明这个对象可以为null，否则不允许为空
    @Autowired(required = false)
    private Cat cat;
    @Autowired
    private Dog dog;
    private String name;
}
~~~



如果@Autowired自动装配的环境比较复杂，自动装配无法通过一个注解【@Autowired】完成的时候，我们可以使用@Qualifier（value = “xxx”）去配置@Autowired的使用，指定一个唯一的bean对象注入！

~~~java
public class People {
    @Autowired
    @Qualifier(value = "cat11")
    private Cat cat;
    
    @Autowired
    @Qualifier(value = "dog222")
    private Dog dog;
    private String name;
}
~~~



**@Resource 注解**

~~~java
public class People {
    @Resource(name = "cat111")
    private Cat cat;
    @Resource
    private Dog dog;
    private String name;
}
~~~



小结：

@ Resource和@ Autowired的区别：

- 都是用来自动装配的，都可以放在属性字段上
- @ Autowired 通过ByType的方式实现，而且必须要求这个对象存在【常用】
- @ Resource 默认通过Byname的方式实现， 如果找不到名字，则通过ByType的方式实现，如果两个都找不到的情况下，就报错【常用】
- 执行顺序不同：@ Autowired 通过ByType的方式实现；@ Resource 默认通过Byname的方式实现



## 10 使用注解开发

在Spring4 之后，要使用注解开发，必须要保证AOP的包导入了

![image-20191211142316430](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191211142316430.png)

使用注解 需要导入context约束，增加注解的支持

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"      xmlns:context="http://www.springframework.org/schema/context"     xsi:schemaLocation="http://www.springframework.org/schema/beans      https://www.springframework.org/schema/beans/spring-beans.xsd        http://www.springframework.org/schema/context        http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
~~~



### 10.1 bean

@ Component： 组件 放在类上 说明这个类被Spring管理了 就是bean！



~~~java
import org.springframework.stereotype.Component;

// 等价于 <bean id="user" class="study.projo.User"
// @Component 组件
@Component
public class User {
    public String name = "Xin";
}
~~~



### 10.2 属性如何注入

@Value 进行注入 ：可以适用于 简单类型的注入，复杂类型（比如MAP LIST 等 建议还是在配置文件中 使用Set注入）



~~~java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 等价于 <bean id="user" class="study.projo.User"
// @Component 组件
@Component
public class User {
    
    //相当于 <property name = 'name' value = 'TuXin')
    @Value("TuXin") //方法一
    public String name;

    @Value("TuXin2") //方法二
    public void setName(String name) {
        this.name = name;
    }
}
~~~

### 10.3 衍生的注解

@ Component 有几个衍生注解， 我们在WEB 开发中，会按照MVC三层架构分层

- dao 【@Repository】
- service 【@Service】
- controller  【 @Controller】

这四个注解功能都是一样的，都代表将某个类注册到Spring容器中，进行装配Bean

### 10.4 自动装配

- @Autowired： 自动装配通过类型，名字
  - 如果Autowired 不能唯一自动匹配上属性，则需要通过@Qualifier（value=“xxx）
- @Nullable : 字段标记了这个注解，说明这个字段为null
- @Resource：自动装配名字，类型

### 10.5 作用域

@Scope

~~~java
@Component
@Scope("singleton")
public class User {
    //相当于 <property name = 'name' value = 'TuXin')
    @Value("TuXin2")
    public void setName(String name) {
        this.name = name;
    }
}
~~~



### 10.6 小结

xml与注解：

- xml 更加万能，适用于任何场合，维护简单方便
- 注解 不是自己的类使用不了，维护相对复杂

xml与注解最佳实践：

- xml用来管理bean
- 注解只用来负责完成属性的注入

- 我们在使用的过程中，只需要注意一个问题：必须要让注解生效，就需要开启注解的支持

~~~xml
<!--    指定要扫描的包，这个包下的注解就会生效-->
    <context:component-scan base-package="study"/>
    <context:annotation-config/>
~~~





## 11 使用 JavaConfig 的方式配置Spring

我们现在要完全不适用Spring的xml配置了，全权来交给java来做

JavaConfig 是Spring的一个子项目，在Spring4 之后，他成为了一个核心功能

![image-20191211150920430](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191211150920430.png)



实体类：

~~~java
package study.projo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 这里这个注解的意思，就是说明这个类被Spring接管了，注册到了容器中
@Component
public class User {
    private String name;
    
    public String getName() {
        return name;
    }

    @Value("XIN") // 属性注入值
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

~~~



配置文件：

~~~java
package study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import study.projo.User;

@Configuration // 这个也会被Spring容器托管， 注册到容器中，因为他本来就是一个被Component注解的类
//@Configuration 代表这是一个配置类，就和我们之前看的beans.xml是一样的

@ComponentScan("study.projo")

@Import(MyConfig2.class) // 讲两个配置类导入到一个类
public class MyConfig {

    // 注册一个Bean 就相当与我们之前写的一个bean标签
    // 这个方法的名字，就相当与bean标签中的id属性
    // 这个方法的返回值，就相当于bean标签中的class属性
    @Bean
    public User getUser()
    {
        return new User(); // 就是返回要注入到bean的对象
    }
}
~~~



测试代码：

~~~java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.config.MyConfig;
import study.projo.User;

public class MyTest {
    public static void main(String[] args) {
        // 如果完全使用了配置类的方式去做，我们就只能通过 AnnotationConfig 上下文来获取容器，通过配置类的class对象加载
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        User getUser = context.getBean("getUser", User.class);
        System.out.println(getUser.getName());
    }
}
~~~



这种纯java的配置方法，在SpringBoot这种随处可见！



## 12 代理模式

### 12.1 为什么要学习代理模式

因为这就是Spring AOP 的底层 【SpringAOP 和SpringMVC】



代理模式的分类

- 静态代理
- 动态代理

![image-20191211155730142](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191211155730142.png)



### 12.2 静态代理

角色分析：

- 抽象角色：一般会使用接口或者抽象类来解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
- 客户：访问代理对象的人！



代码步骤：

1. 接口

   ~~~java
   package study.demo01;
   
   // 租房
   public interface Rent {
   
       public void rent();
   }
   ~~~

   

2. 真实角色

   ~~~java
   package study.demo01;
   
   // 房东
   public class Host implements Rent{
   
       public void rent() {
           System.out.println("房东要租出房子");
       }
   }
   
   ~~~

   

3. 代理角色

   ~~~java
   package study.demo01;
   
   public class Proxy {
       private Host host; // 使用组合而不是继承
   
       public Proxy() {
   
       }
   
       public Proxy(Host host) {
           this.host = host;
       }
   
       public void rent() {
           host.rent();
           seeHouse();
           fare();
           contract();
       }
   
       //看房
       public void seeHouse()
       {
           System.out.println("中介带你看房");
       }
   
       //收中介费
       public void fare()
       {
           System.out.println("中介收中介费");
       }
   
       //签租赁合同
       public void contract()
       {
           System.out.println("签租赁合同");
       }
   }
   
   ~~~

   

4. 客户端访问代理角色

   ~~~java
   package study.demo01;
   
   public class Proxy {
       private Host host; // 使用组合而不是继承
   
       public Proxy() {
   
       }
   
       public Proxy(Host host) {
           this.host = host;
       }
   
       public void rent() {
           host.rent();
           seeHouse();
           fare();
           contract();
       }
   
       //看房
       public void seeHouse()
       {
           System.out.println("中介带你看房");
       }
   
       //收中介费
       public void fare()
       {
           System.out.println("中介收中介费");
       }
   
       //签租赁合同
       public void contract()
       {
           System.out.println("签租赁合同");
       }
   }
   
   ~~~

   

代理模式的好处：

- 可以是真实角色的操作更加纯粹！不用去关注一些公共的业务
- 公共也就交给代理角色！实现了业务的分工，耦合性降低
- 公共业务发生扩展的时候，方便集中管理



缺点：

- 一个真实角色就会产生一个代理角色，代码量会翻倍，开发效率就会变低

  

### 12.3 加深理解

聊聊AOP

（代码 ： 对应 08-demo02）

![image-20191211173902658](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191211173902658.png)



### 12.4 动态代理

- 动态代理角色和静态代理角色一样
- 动态代理的代理类是动态生成的，不是我们直接写好的
- 动态代理可以分为两大类：基于接口的动态代理，基于类的动态代理
  - 基于接口--JDK 动态代理 
  - 基于类 - cglib
  - java字节码实现：JAVAssist



基于接口--JDK 动态代理 ：

需要了解两个类：Proxy，InvocationHandler（调用处理程序）

**proxyInvocationHandler**代码：

~~~java
package study.demo04;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 使用这个类来自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }


    //proxy类: 生成得到代理对象
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }

    // InvocationHandler类:处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 动态代理的本质，就是使用反射机制实现！
        log(method.getName());
        Object result = method.invoke(target,args);
        return result;
    }

    public void  log(String msg){
        System.out.println("执行了" + msg +"方法");
    }
}

~~~



代理：

~~~java
package study.demo04;
import study.demo02.UserService;
import study.demo02.UserServiceImpl;

public class Client {

    public static void main(String[] args) {

        //真实角色
        UserServiceImpl userService = new UserServiceImpl();

        //代理角色: 现在没有
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //通过调用程序处理角色来处理我们要调用的接口对象
        pih.setTarget(userService); // 设置要代理的对象

//        Object proxy = (Object) pih.getProxy(); // 这里的proxy就是动态生成的，我们并没有写
        UserService proxy = (UserService) pih.getProxy();
        proxy.delete();
    }
}
~~~





动态代理的好处：

- 可以是真实角色的操作更加纯粹！不用去关注一些公共的业务
- 公共也就交给代理角色！实现了业务的分工，耦合性降低
- 公共业务发生扩展的时候，方便集中管理
- 一个动态代理类代理的是一个接口，一般就是对应的一类业务
- 一个动态代理类可以代理多个类，只要是实现了同一个接口即可

## 13 AOP

### 13.1 什么是AOP

AOP（Aspect Oriented Programming）意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生泛型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发效率。

![image-20191211202527127](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191211202527127.png)

### 13.2 AOP 在Spring 中的作用

**提供声明式事务：允许用户自定义切面**

- 横切关注点：跨越应用程序多个模块的方法或功能，即是，与我们业务逻辑无关的，是我们需要关注的部分，就是横切关注点，如日志，安全，缓存，事务等等。

- 切面（ASPECT）：横切关注点被模块化的特殊对象，即 他是一个类

- 通知（Advice）：切面必须要完成的工作，即 他是类中的一个方法

- 目标（Target）：被通知对象

- 代理（Proxy）：向目标对象应用通知之后创建的对象

- 切入点（PointCut）：切面通知执行的”地点“的定义

- 连接点（JointPoint）：与切入点匹配的执行点

  

![image-20191211203202242](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191211203202242.png)



在SpringAOP中，通过Advice定义横切逻辑，Spring中支持5中类型的Advice：

![image-20191211204632644](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191211204632644.png)



### 13.3 使用Spring实现AOP

使用AOP植入，需要导入一个依赖包【重要】

~~~xml
<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.5</version>
</dependency>

~~~



方法一：使用Spring的API接口 【主要SpringAPI接口实现



接口：

UserService

~~~java
package study.service;

public interface UserService {
    public void add();
    public void delete();
    public void update();
    public void query();
}
~~~



接口实现：

~~~java
package study.service;

public class UserServiceImpl implements UserService{
    public void add(){
        System.out.println("增加了一个用户");
    }
    public void delete(){
        System.out.println("删除了一个用户");
    }
    public void update(){
        System.out.println("修改了一个用户");
    }
    public void query(){
        System.out.println("查询了一个用户");
    }
}
~~~



前置事务：

~~~java
package study.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class Log implements MethodBeforeAdvice {

    // method: 要执行的目标对象的方法
    // args: 参数
    // target: 目标参数
    public void before(Method method, Object[] objects, Object target) throws Throwable {
        System.out.println(target.getClass().getName()+ "的" + method.getName() + "被执行了");

    }
}
~~~



后置事务：

~~~java
package study.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AfterLog implements AfterReturningAdvice {

    // returnValue: 返回值
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("执行了"+method.getName() + "方法, 返回结果为" + returnValue);
    }
}

~~~



xml：

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

<!--    注册 bean-->
    <bean id="userService" class="study.service.UserServiceImpl"/>
    <bean id="log" class="study.log.Log"/>
    <bean id="afterLog" class="study.log.AfterLog"/>

<!--    方式一: 使用原生Spring API接口实现AOP-->

<!--    配置AOP: 需要导入aop约束-->
    <aop:config>
<!--        需要一个接入点: 即需要在那个地方执行-->
<!--        expression: 表达式 execution(要执行的位置)-->
        <aop:pointcut id="pointcut" expression="execution(* study.service.UserServiceImpl.*(..))"/>

<!--        执行环绕增加-->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>
</beans>
~~~



测试代码：

~~~java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.service.UserService;
import study.service.UserServiceImpl;

public class MyClass {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //【重要点】动态代理 代理的是接口
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
}

~~~



方式二：使用自定义类来实现AOP【主要是定义切面】



自定义类：

~~~JAVA
package study.diy;

public class DiyPointCut {

    public void before()
    {
        System.out.println("----- 方法执行前 ------ ");
    }

    public void after()
    {
        System.out.println("----- 方法执行后 ------ ");
    }
}

~~~



修改后的XML：

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

<!--    注册 bean-->
    <bean id="userService" class="study.service.UserServiceImpl"/>

<!--    方式二：自定义类实现AOP-->
    <bean id="diy" class="study.diy.DiyPointCut"/>
    <aop:config>
<!--        自定义切面， ref:要引用的类-->
        <aop:aspect ref="diy">
<!--            切入点-->
            <aop:pointcut id="point" expression="execution(* study.service.UserServiceImpl.*(..))"/>

<!--            通知-->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>

</beans>
~~~





方式三：使用注解实现AOP



修改后的XML：

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">


<!--    方式三： 使用注解实现AOP-->
    <bean id="annotationPointCut" class="study.diy.AnnotationPointCut"/>
<!--    开启注解支持  JDK(默认 proxy-target-class="false") cglib (proxy-target-class="true") -->
    <aop:aspectj-autoproxy proxy-target-class="false" />

</beans>
~~~



实现注解的方法类：

~~~java
package study.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect // 标注这个类为切面
public class AnnotationPointCut {

    @Before("execution(* study.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("==== 方法执行前 ====");
    }

    @After("execution(* study.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("==== 方法执行后 ====");
    }

    // 在环绕增强中 我们可以给定一个参数来代表我们要获取处理切入的点
    @Around("execution(* study.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");

        Signature signature = jp.getSignature(); //获得签名
        System.out.println("signature:" + signature);

        // 执行方法
        Object proceed = jp.proceed();
        System.out.println(proceed);
        System.out.println("环绕后");

    }

}
~~~



## 14 整合Mybatis

步骤：

1. 导入相关jar包

   - junit
   - mybatis
   - mysql数据库
   - spring 相关的
   - aop 植入
   - mybatis-spring 【new】

   

   基本环境搭建：

   ~~~xml
       <dependencies>
           <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-webmvc</artifactId>
               <version>5.2.2.RELEASE</version>
           </dependency>
   
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.12</version>
           </dependency>
   
           <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
           <dependency>
               <groupId>org.aspectj</groupId>
               <artifactId>aspectjweaver</artifactId>
               <version>1.9.5</version>
           </dependency>
   
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>8.0.18</version>
           </dependency>
   
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-jdbc</artifactId>
               <version>5.2.2.RELEASE</version>
           </dependency>
   
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis</artifactId>
               <version>3.5.3</version>
           </dependency>
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis-spring</artifactId>
               <version>2.0.3</version>
           </dependency>
   
   ~~~

   

2. 、编制配置文件

3. 测试



### 14.1 回忆mybatis

1. 编写实体类
2. 编写核心配置文件
3. 编写接口
4. 编写Mapper.xml
5. 测试



### 14.2 整合Mybatis

要和 Spring 一起使用 MyBatis，需要在 Spring 应用上下文中定义至少两样东西：一个 `SqlSessionFactory` 和至少一个数据映射器类。

1. 编写数据源配置

   ~~~xml
       <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
           <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
           <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;userUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=UTC" />
           <property name="username" value="root" />
           <property name="password" value="123" />
        </bean>
   ~~~

   

2. sqlSessionFactory

   ~~~xml
       <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
           <property name="dataSource" ref="dataSource"/>
           <property name="configLocation" value="classpath:mybatisConfig.xml"/>
           <property name="mapperLocations" value="classpath:study/mapper/UserMapper.xml"/>
       </bean>
   ~~~

   

3. sqlSessionTemplate

   ~~~xml
       <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
           <constructor-arg index="0" ref="sqlSessionFactory"/>
       </bean>
   
       <bean id="userMapper" class="study.mapper.UserMapperIml">
           <property name="sqlSession" ref="sqlSession"/>
       </bean>
   ~~~

   

4. 需要给接口加实现类【】

   ~~~java
   public class UserMapperIml implements UserMapper{
   
   //    原来：我们的所有操作，都使用sqlSession在操作；
   //    现在：都是SqlSessionTemplate 来操作
       private SqlSessionTemplate sqlSession;
   
       public SqlSessionTemplate getSqlSession() {
           return sqlSession;
       }
   
       public void setSqlSession(SqlSessionTemplate sqlSession) {
           this.sqlSession = sqlSession;
       }
   
       public List<User> selectUser() {
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           return mapper.selectUser();
       }
   }
   ~~~

   

5. 将自己写的实现类，注入到Spring中，测试即可

   ~~~java
       // 集成到Spring后
       @Test
       public void test2(){
           ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springDao.xml");
           UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
           List<User> userList = userMapper.selectUser();
           for (User user : userList) {
               System.out.println(user);
           }
       }
   ~~~

   



## 15 声明式事务

### 15.1 回顾事务

- 把一组业务当成一个业务来做；要么都成功，要么都失败
- 事务在项目开发中，十分重要，设计到数据的一致性问题，不能马虎
- 确保完整性和一致性



事务的ACID原则

- 原子性（Atomicity）

- 一致性（Consistency）
  
  要么都被提交，要么都提交失败
  
- 隔离性（Isolation）

  - 多个业务可能操作同一个资源，防止数据损坏

- 持久性（Durability）

  - 事务一旦提交，无论系统发生什么问题，结果都不会再被影响，被持久化的写道存储器中

    

一个使用 MyBatis-Spring 的其中一个主要原因是它允许 MyBatis 参与到 Spring 的事务管理中。而不是给 MyBatis 创建一个新的专用事务管理器，MyBatis-Spring 借助了 Spring 中的 DataSourceTransactionManager 来实现事务管理。



15.2 Spring中的事务管理

- 声明式事务：AOP
- 编程式事务：需要在代码中，进行事务的管理



15.3 声明式事务

~~~xml
<!--    结合AOP 实现声明式事务植入-->
<!--    配置事务的类-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <!--        给那些方法配置事务-->
        <!--        配置事务的传播特性：new propagation=-->
        <tx:attributes>
            <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="delete" propagation="REQUIRED"/>
            <tx:method name="update" propagation="REQUIRED"/>
            <tx:method name="query" read-only="true"/>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>

<!--        配置事务切入-->
    <aop:config>
        <aop:pointcut id="txPointCut" expression="execution(* study.mapper.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
    </aop:config>
~~~



思考：

为什么需要事务？

- 如果不配置事务，可能存在数据提交不一致的情况
- 如果我们不在Spring中去配置声明式事务，我们就需要在代码中手动配置事务！
- 事务在项目开发中十分重要，这涉及到数据的一致性



