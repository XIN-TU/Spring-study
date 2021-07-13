# Mybatis

## 相关环境

环境：

- JDK 1.8
- Mysql 5.7
- maven 3.6.1
- IDEA

回顾

- JDBC
- Mysql
- java基础
- Maven
- Junit



SSM 框架：查配置文件的最好方式：看官方文档：https://mybatis.org/mybatis-3/zh/index.html



## 1 简介

### 1.1 什么是Mybatis

- MyBatis 是一款优秀的**持久层框架**，
- 它支持定制化 SQL、存储过程以及高级映射。
- MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。
- MyBatis 可以使用简单的 XML 或注解来配置和映射原生类型、接口和 Java 的 POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。
- MyBatis 本是[apache](https://baike.baidu.com/item/apache/6265)的一个开源项目[iBatis](https://baike.baidu.com/item/iBatis), 
- 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。2013年11月迁移到Github



### 1.2 如何获得Mybatis？

- maven : https://mvnrepository.com/artifact/org.mybatis/mybatis 

- github： https://github.com/mybatis/mybatis-3/releases

- 中文文档:  https://mybatis.org/mybatis-3/zh/index.html

  

### 1.3 持久化

**数据持久化**

- 持久化就是将程序的数据在持久状态和瞬时状态转化的过程
- 内存：**断电即失**
- 数据库（jdbc），io文件持久化。
- 生活：冷藏，罐头



**为什么需要持久化？**

- 有一些对象，不能让他丢掉
- 内存太贵了



### 1.4 持久层

Dao层，Service层，Controller层...

- 完成持久化工作的代码块
- 层界限十分明显



### 1.5 为什么需要Mybatis？

- 帮助程序员将数据存入到数据库中

- 方便

- 传统的JDBC代码太复杂，简化，框架，自动化

- 不用Mybatis也可以。只是用了更加容易上手。技术没有高低之分

- 优点：

  - 简单易学

  - 灵活
  - 解除sql与程序代码的耦合：sql和代码的分离，提高了可维护性。
  - 提供映射标签，支持对象与数据库的orm字段关系映射
  - 提供对象关系映射标签，支持对象关系组建维护
  - 提供xml标签，支持编写动态sql。 
  - **使用的人多**



## 2 第一个Mybatis程序

思路:  搭建环境- > 导入Mybatis -> 编写代码 -> 测试

### 2.1 搭建环境

搭建数据库

~~~java
CREATE DATABASE mybatis;

USE mybatis;

CREATE TABLE users (
ID INT(20) NOT NULL PRIMARY KEY,
NAME VARCHAR(30) DEFAULT NULL,
PWD VARCHAR(30) DEFAULT NULL
)ENGINE= INNODB DEFAULT CHARSET=utf8;
    
INSERT INTO users (ID,NAME,PWD) VALUES 
(1,'张三','123456'),
(2,'李四','123456'),
(3,'涂鑫','123456')

~~~



新建项目

1. 新建一个普通maven项目
2. 删除src目录
3. 导入依赖
   - mysql驱动
   - mybatis
   - junit



### 2.2 创建一个模块

- 编写Mybatis核心配置文件

  

  ~~~xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  
  <!--configuration 核心配置文件-->
  <configuration>
      <environments default="development">
  
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;userUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=UTC"/>
                  <property name="username" value="root"/>
                  <property name="password" value="123"/>
              </dataSource>
          </environment>
      </environments>
  
  <!--    每一个Mapper.xml 都需要在Mybatis核心配置文件中注册！-->
      <mappers>
          <mapper resource="study/dao/UserMapper.xml"/>
      </mappers>
  
  </configuration>
  ~~~

  

- 编写mybatis工具类

  ~~~java
  public class MybatisUtils {
  
      private static  SqlSessionFactory sqlSessionFactory;
      static {
  
          try {
              // 使用Mybatis第一步: 获取sqlSessionFactory对象
              String resource = "org/mybatis/example/mybatis-config.xml";
              InputStream inputStream = Resources.getResourceAsStream(resource);
              sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
              // 既然有了 SqlSessionFactory，顾名思义，我们就可以从中获得 SqlSession 的实例了。
          // SqlSession 完全包含了面向数据库执行 SQL 命令所需的所有方法。你可以通过 SqlSession 实例来直接执行已映射的 SQL 语句。
      
          public static SqlSession getSqlSession() {
              SqlSession sqlSession = sqlSessionFactory.openSession();
              return sqlSession;
  
          }
  }
  ~~~

  

### 2.3 编写代码

- 实体类

  ~~~java
  // 实体类
  public class User {
      private int ID;
      private String NAME;
      private String PWD;
  
      public User() {
      }
  
      public User(int ID, String NAME, String PWD) {
          this.ID = ID;
          this.NAME = NAME;
          this.PWD = PWD;
      }
  
      public int getID() {
          return ID;
      }
  
      public void setID(int ID) {
          this.ID = ID;
      }
  
      public String getNAME() {
          return NAME;
      }
  
      public void setNAME(String NAME) {
          this.NAME = NAME;
      }
  
      public String getPWD() {
          return PWD;
      }
  
      public void setPWD(String PWD) {
          this.PWD = PWD;
      }
  
      @Override
      public String toString() {
          return "User{" +
                  "ID=" + ID +
                  ", NAME='" + NAME + '\'' +
                  ", PWD='" + PWD + '\'' +
                  '}';
      }
  }
  ~~~

  

- 

- Dao接口

  ~~~java
  package study.dao;
  
  import study.pojo.User;
  
  import java.util.List;
  
  public interface UserDao {
  
      List<User> getUserlist();
  }
  
  ~~~

  

- 接口实现类

  由原来的UserDaoImpl转变成一个Mapper配置文件

  ~~~java
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
  <!--namespace = 绑定一个对应的Dao/Mapper接口 -->
  <mapper namespace="study.dao.UserDao">
  
  <!--    select 查询语句 ： id：绑定方法的名字 resultType: 返回类型-->
      <select id="getUserList" resultType="study.pojo.User">
          select * from mybatis.users
      </select>
  
  </mapper>
  ~~~



### 2.4 测试

注意点：org.apache.ibatis.binding.BindingException: Type interface study.dao.UserDao is not known to the MapperRegistry.

**pom.xml:**

~~~java
<!--    在build中配置resources，来防止我们资源导出失败的问题 -->
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>

            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
~~~



- junit测试

  ~~~java
  package study.dao;
  
  import org.apache.ibatis.session.SqlSession;
  import org.junit.Test;
  import study.pojo.User;
  import study.utils.MybatisUtils;
  
  import java.util.List;
  
  public class UserDaoTest {
  
      @Test
      public void test(){
  
          // 第一步：获得sqlSession对象
          SqlSession sqlSession = MybatisUtils.getSqlSession();
       
         try{
             // 执行SQL
             //方式一：getMapper (更加推荐）
             UserDao mapper = sqlSession.getMapper(UserDao.class);
             List<User> userList = mapper.getUserList();
             
             // 方式二
  //        List<User> userList = sqlSession.selectList("study.dao.UserDao.getUserList")
  
             for (User user : userList) {
                 System.out.println(user);
             }
  
         }catch (Exception e) {
             System.out.println(e);
         }
         finally {
             //关闭sqlSession
             sqlSession.close();
         }
  
      }
  }
  
  ~~~

  - 测试中可能会遇到的问题

    1. 配置文件没有注册

    2. 绑定接口错误

    3. 方法名不对

    4. 返回类型不对

    5. Maven导出资源问题

       

  <img src="C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191215173848930.png" alt="image-20191215173848930"  />



整体步骤：

1. 配置环境，导入包，解决maven资源导出失败问题【pom.xml】
2. 编写mybatis核心配置文件，其中关于连接数据库相关操作代码，（Mysql 8.0 与Mysql5.0 代码语法有不同）并且使用Mapper 讲Mapper.xml文件注册在核心配置文件中【mybatis-config.xml】
3. 编写实体类【User】
4. 编写实体类Dao接口【UserDao】
5. 编写实体类方法，其中用mapper 选择相应接口的命名空间（相当于一个mapper绑定对应一个Dao/Mapper接口），然后使用sql语句 id为需绑定方法的名字， returnType为返回类型（使用别名前需要全路径）【UserMapper.xml】
6. 编写Mybatis配置类，其中在静态代码块中编写获取sqlSessionFactory对象，然后编写获取SqlSession的方法并返回。【MybatisUtils】
7. 测试类，其中首先获取调用Mybatis配置类中的接口获取sqlSession对象，然后只用getMapper（）获取对象，然后调用对象方法执行sql并返回，然后打印sql查询结果，最后官兵sqlSession



## 3 增删改查实现

### 3.1 namespace

namespace中的包名要和Dao/Mapper接口的包名一致



### 3.1 select

查询语句

- id： 就是对应的namespace中的方法名
- resultType：sql语句执行的返回值！
- parameterType：参数类型

1. 编写接口

   ~~~java
      User getUserById(int ID);
   ~~~

   

2. 编写相对应的Mapper中的sql语句

   ~~~xml
       <select id="getUserById" resultType="study.pojo.User" parameterType="int">
           select * from mybatis.users where ID = #{id}
        </select>
   ~~~

3. 测试

   ~~~java
       @Test    
   	public void getUserById(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           User userById = mapper.getUserById(3);
           System.out.println("result:" + userById);
           sqlSession.close();
       }
   ~~~

### 3.2 insert

1. 编写接口

   ~~~java
       // 增加一个用户
       int addUser(User user);
   ~~~

   

2. 编写相对应的Mapper中的sql语句

   ~~~xml
   <!--    对象中的属性可以直接取出来-->
       <insert id="addUser" parameterType="study.pojo.User">
           insert into mybatis.users (id,name,pwd) values (#{ID},#{NAME},#{PWD})
       </insert>
   ~~~

   

3. 测试

    **增删改 需要提交事务**

   ~~~java
       // 增删改 需要提交事务
       @Test
       public void addUser(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           int res = mapper.addUser(new User(5,"Xin","123456"));
           if (res > 0 ){
               System.out.println("insert successfully");
           }
           // 提交事务
           sqlSession.commit();
           sqlSession.close();
       }
   ~~~

   

### 3.3 update

1. 编写接口

   ~~~java
   // 修改用户
       int updateUser(User user);
   ~~~

   

2. 编写相对应的Mapper中的sql语句

   ~~~xml
       <update id="updateUser" parameterType="study.pojo.User" >
           update mybatis.users set NAME = #{NAME} , PWD = #{PWD} where ID=#{ID} ;
       </update>
   ~~~

   

3. 测试

   ~~~java
       @Test
       public void updateUser(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           mapper.updateUser(new User(3,"TU","789456"));
           sqlSession.commit();
           sqlSession.close();
       }
   ~~~

   

### 3.4 delete

1. 编写接口

   ~~~java
       int deleteUser(int ID);
   ~~~

   

2. 编写相对应的Mapper中的sql语句

   ~~~xml
   	 <delete id="deleteUser" parameterType="study.pojo.User">
           delete from mybatis.users where ID = #{ID};
       </delete>
   ~~~

   

3. 测试

   ~~~java
       @Test
       public void deleteUser(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           int res = mapper.deleteUser(5);
           if (res>0){
               System.out.println("delete successfully");
           }
           sqlSession.commit();
           sqlSession.close();
       }
   ~~~



**注意点: 增删改 需要提交事务**



### 3.5 分析错误

- 标签不要匹配出错

- resource绑定mapper，需要使用路径

- 程序配置文件必须符合规范

- NullPointerException，没有注册到资源

- 输出的xml文件中存在中文乱码问题

- maven资源没有导出问题

  

### 3.6 万能的Map

假设，我们的实体类，或者数据库中的表，字段或者参数过多，我们应当考虑使用Map！

~~~java
    // 万能的Map
    int addUser2(Map<String, Object> map);
~~~



~~~xml
<!--    传递map的key-->
    <insert id="addUser2" parameterType="map">
        insert into mybatis.users (id,name,pwd) values (#{userId},#{userName},#{userPassword})
    </insert>
</mapper>
~~~



~~~java
    @Test
    public void addUser2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // map是接口 HashMap是map的实现类 接口不能直接new
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId",4);
        map.put("userName","Xin");
        map.put("userPassword","11122233");
        int res = mapper.addUser2(map);
        if (res > 0 ){
            System.out.println("insert successfully");
        }
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

~~~



Map 传递参数，直接在sql中取出key即可【parameterType="map"】

对象传递传输，直接在sql中取对象的属性即可

【parameterType="study.pojo.User"】 

只有一个基本类型参数的情况下，可以直接在sql中取到

多个参数用**Map**或者**注解**



### 3.7 模糊查询

1. java代码执行的时候，传递通配符%%

   ~~~java
   List<User> userList = mapper.getUserLike("%李%"); 
   ~~~

   

2. 在sql拼接中使用通配符（更推荐，因为可以防止sql注入）

   ~~~xml
   select * from mybatis.users where NAME like "%"#{value}"%";
   ~~~



## 4 配置解析

### 4.1 核心配置文件

- mybatis.config.xml
  - MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。
- configuration（配置）
  - [properties（属性）](https://mybatis.org/mybatis-3/zh/configuration.html#properties)
  - [settings（设置）](https://mybatis.org/mybatis-3/zh/configuration.html#settings)
  - [typeAliases（类型别名）](https://mybatis.org/mybatis-3/zh/configuration.html#typeAliases)
  - [typeHandlers（类型处理器）](https://mybatis.org/mybatis-3/zh/configuration.html#typeHandlers)
  - [objectFactory（对象工厂）](https://mybatis.org/mybatis-3/zh/configuration.html#objectFactory)
  - [plugins（插件）](https://mybatis.org/mybatis-3/zh/configuration.html#plugins)
  - environments（环境配置）
    - environment（环境变量）
      - transactionManager（事务管理器）
      - dataSource（数据源）
  - [databaseIdProvider（数据库厂商标识）](https://mybatis.org/mybatis-3/zh/configuration.html#databaseIdProvider)
  - [mappers（映射器）](https://mybatis.org/mybatis-3/zh/configuration.html#mappers)



### 4.2 environments（环境配置）

- MyBatis 可以配置成适应多种环境

- 不过要记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。

  

学会配置多套运行环境

Mybatis默认的事务管理器就是JDBC， 连接池:POOLED



### 4.3 properties（属性）

我们可以通过使用properties属性来实现引用配置文件

这些属性都是可外部配置且可动态替换的，既可以在典型的 Java 属性文件中配置，亦可通过 properties 元素的子元素来传递。【db.properties】



1. 编写一个配置文件

   ~~~properties
   driver = com.mysql.cj.jdbc.Driver
   url = jdbc:mysql://localhost:3306/mybatis?useSSL=true&userUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
   username = root
   password = 123
   ~~~

2. 在核心配置文件中映入

~~~xml
<!--    引入外部配置文件 两种方式同时存在时，优先使用外部文件的方法 即第一种方式-->
<!--    方式一: -->
<!--    <properties resource="db.properties"/>-->
    <!--    方式二: -->
    <properties resource="db.properties">
        <property name="username" value="root"/>
        <property name="password" value="123"/>
    </properties>
~~~



- 可以直接引入外部文件

- 可以在其中增加一些属性配置

- 如果两个文件有同一字段，优先使用外部配置文件的字段

  

### 4.4 别名

- 类型别名是为 Java 类型设置一个短的名字。 
- 它只和 XML 配置有关，存在的意义仅在于用来减少类完全限定名的冗余。

~~~xml
<!--    可以给实体类起别名-->
    <typeAliases>
        <typeAlias type="study.pojo.User" alias="User"/>
    </typeAliases>
~~~



也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean.

扫描实体类的包，他的默认别名就是这个类的首字母小写

~~~xml
<!--    给实体类的包起别名 扫描实体类的包，他的默认别名就是这个类的首字母小写 -->
    <typeAliases>
        <package name="study.pojo" />
    </typeAliases>
    
~~~



在实体类比较少的时候，建议使用第一种

如果实体类十分多，建议使用第二种

第一种可以DIY别名，第二种不行

但是第二种可以通过注解在实体类上取别名来实现自定义别名



### 4.5 设置（setting）

这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。 

![image-20191216170421392](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191216170421392.png)

![image-20191216170446353](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191216170446353.png)

### 4.6 其他配置

- typeHandlers（类型处理器）

- objectFactory（对象工厂）

- plugins（插件

  - mybatis-generator-core

  - mybatis-plus

  - 通用mapper

    

### 4.7 映射器（mappers）

MapperRegistry: 注册绑定我们的Mapper文件

方式一： 使用resource文件绑定注册（推荐）

~~~xml
<!--    每一个Mapper.xml 都需要在Mybatis核心配置文件中注册！-->
    <mappers>
        <mapper resource="study/dao/UserMapper.xml"/>
    </mappers>
~~~

方式二： 使用class文件绑定注册

~~~java
    <mappers>
        <mapper class="study.dao.UserMapper" />
    </mappers>
~~~

注意点：

- 接口和他的Mapper配置文件必须要同名
- 接口和他的Mapper配置文件必须要在同一个包下

方式三：使用扫描包方式注入环境

~~~xml
    <mappers>
        <package name="study.dao"/>
    </mappers>
~~~

注意点：

- 接口和他的Mapper配置文件必须要同名
- 接口和他的Mapper配置文件必须要在同一个包下

### 4.8 作用域（scope）和生命周期

![image-20191216173141111](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191216173141111.png)

作用域 和 生命周期类 是至关重要的，因为错误的使用会导致非常严重的**并发问题**

**SqlSessionFactoryBuilder：**

- 一旦创建了SqlSessionFactory，就不再需要他了
- 局部变量

**SqlSessionFactory：**

- 说白了就可以想象为：数据库连接池
- SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，**没有任何理由丢弃它或重新创建另一个实例**。
- 因此 SqlSessionFactory 的最佳作用域是应用作用域。最简单的就是使用**单例模式**或者静态单例模式。

**SqlSession：**

- 连接到连接池的一个请求
- SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。 
- 用完之后需要赶紧关闭，否则资源被占用

![image-20191216191509154](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191216191509154.png)

这里面的每一个Mapper，就代表一个具体的业务！



## 5 解决属性名和字段名不一致的问题

### 5.1 问题

数据库中的字段：

![image-20191216192318562](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191216192318562.png)

新建一个项目，拷贝之前的，测试实体类字段不一致的情况

~~~java
public class User {
    private int id;
    private String name;
    private String password;
}
~~~

测试出现问题:

![image-20191216194324267](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191216194324267.png)



分析sql语句：

~~~xml
    // 代码中的sql语句：select * from mybatis.users where ID = #{id}
    // 类型处理器
    // 真实的sql语句:  select ID,NAME,PWD from mybatis.users where ID = #{id}
~~~



解决方法：

- 起别名

  ~~~xml
  select ID,NAME,PWD as password from mybatis.users where ID = #{id}
  ~~~

  

  ![image-20191216194626353](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191216194626353.png)



### 5.2 ResultMap

resultMap:结果集映射

~~~xml
id name pwd
id name password
~~~



~~~xml
<!--    结果集映射-->
    <resultMap id="UserMap" type="User">
<!--        column : 数据库中的字段 property：实体类中的属性-->
        <result column="ID" property="id" />
        <result column="NAME" property="name" />
        <result column="PWD" property="password" />
    </resultMap>
    <select id="getUserById" resultMap="UserMap" parameterType="int">
        select * from mybatis.users where ID = #{id}
     </select>

~~~



- `resultMap` 元素是 MyBatis 中最重要最强大的元素。

- ResultMap 的设计思想是，对于简单的语句根本不需要配置显式的结果映射，而对于复杂一点的语句只需要描述它们的关系就行了。

- `ResultMap` 最优秀的地方在于，虽然你已经对它相当了解了，但是根本就不需要显式地用到他们。

  

  ![image-20191216200859728](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191216200859728.png)



## 6 日志

### 6.1 日志工厂

如果一个数据库操作出现了异常，我们需要进行排错。日志就是最好的助手

曾经：sout，debug

现在：日志工厂

![image-20191216204524668](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191216204524668.png)

- SLF4J 
- LOG4J 【掌握】
- LOG4J2 
- JDK_LOGGING 
- COMMONS_LOGGING 
- STDOUT_LOGGING 【掌握】
- NO_LOGGING

在Mybatis中具体使用那一个日志实现，在设置中设定



**STDOUT_LOGGING 标准日志输出**

在Mybatis核心配置文件中，配置

~~~xml
    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
~~~

![image-20191216205418118](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191216205418118.png)



### 6.2 Log4j

**什么是Log4j：**

- Log4j是Apache的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是控制台、文件、GUI组件，
- 我们也可以控制每一条日志的输出格式；
- 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。
- 最令人感兴趣的就是，这些可以通过一个配置文件来灵活地进行配置，而不需要修改应用的代码。

1.  使用一个外部类，先导包

   ~~~xml
   <dependency>
       <groupId>log4j</groupId>
       <artifactId>log4j</artifactId>
       <version>1.2.17</version>
   </dependency>
   ~~~

   

2 log4j.properties

~~~properties
### 配置根 ###
log4j.rootLogger = debug,console ,fileAppender,dailyRollingFile,ROLLING_FILE

### 设置输出sql的级别，其中logger后面的内容全部为jar包中所包含的包名 ###
log4j.logger.org.apache=dubug
log4j.logger.java.sql.Connection=dubug
log4j.logger.java.sql.Statement=dubug
log4j.logger.java.sql.PreparedStatement=dubug
log4j.logger.java.sql.ResultSet=dubug

### 配置输出到控制台 ###
log4j.appender.console = org.apache.log4j.ConsoleAppender
log4j.appender.console.Target = System.out
log4j.appender.console.Threshold = DEBUG
log4j.appender.console.layout = org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern =  %d{ABSOLUTE} %5p %c{1}:%L - %m%n

### 配置输出到文件 ###
log4j.appender.fileAppender = org.apache.log4j.FileAppender
log4j.appender.fileAppender.File = ./logs/log.log
log4j.appender.fileAppender.Append = true
log4j.appender.fileAppender.Threshold = DEBUG
log4j.appender.fileAppender.layout = org.apache.log4j.PatternLayout
log4j.appender.fileAppender.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n

### 配置输出到文件，并且每天都创建一个文件 ###
log4j.appender.dailyRollingFile = org.apache.log4j.DailyRollingFileAppender
log4j.appender.dailyRollingFile.File = logs/log.log
log4j.appender.dailyRollingFile.Append = true
log4j.appender.dailyRollingFile.Threshold = DEBUG
log4j.appender.dailyRollingFile.layout = org.apache.log4j.PatternLayout
log4j.appender.dailyRollingFile.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n

### 配置输出到文件，且大小到达指定尺寸的时候产生一个新的文件 ###
log4j.appender.ROLLING_FILE=org.apache.log4j.RollingFileAppender 
log4j.appender.ROLLING_FILE.Threshold=ERROR 
log4j.appender.ROLLING_FILE.File=rolling.log 
log4j.appender.ROLLING_FILE.Append=true 
log4j.appender.ROLLING_FILE.MaxFileSize=10KB 
log4j.appender.ROLLING_FILE.MaxBackupIndex=1 
log4j.appender.ROLLING_FILE.layout=org.apache.log4j.PatternLayout 
log4j.appender.ROLLING_FILE.layout.ConversionPattern=[framework] %d - %c -%-4r [%t] %-5p %c %x - %m%n

~~~

3 配置log4j为日志的实现

~~~xml
    <settings>
        <setting name="logImpl" value="LOG4J"/>
    </settings>
~~~

4 Log4j的使用

- 直接测试运行

  ![image-20191216211844655](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191216211844655.png)



**简单使用**

1. 在要使用log4j类中导入类包

   ~~~java
   import org.apache.log4j.Logger;
   ~~~

2. 日志对象，参数为当前类的class

   ~~~java
   static Logger logger = Logger.getLogger(UserDaoTest.class);
   ~~~

3. 日志级别

   ~~~java
   logger.info("info:进入了log4j方法");
   logger.debug("info:进入了debug");
   logger.error("error: 进入了error");
   ~~~

   

### 7 分页

**思考：为什么要分页**

- 减少数据的处理量



### 7.1 使用Limit分页【重点】

~~~sql
语法：SELECT * FROM users limit startIndex, pageSize;
select * from users limit 3; #[0,n]
~~~



使用Mybatis实现分页，核心SQL

1. 接口

   ~~~java
       //分页
       List<User> getUserByLimit(Map<String,Integer> map);
   ~~~

   

2. Mapper.xml

   ~~~xml
   <!--    分页实现-->
       <resultMap id="limitMap" type="User">
           <result column="PWD" property="password"/>
       </resultMap>
       <select id="getUserByLimit" parameterType="map" resultMap="limitMap">
           select * from mybatis.users limit #{startIndex},#{pageSize};
       </select>
   ~~~

3. 测试

~~~java
    @Test
    public void getUserByLimit(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex",0);
        map.put("pageSize",4);
        List<User> userByLimit = mapper.getUserByLimit(map);
        for (User user : userByLimit) {
            System.out.println(user);
        }
        sqlSession.close();
    }
~~~



### 7.2 RowBounds分页

不再使用SQL实现分页

1. 接口

   ~~~java
       //分页2 RowsBounds
       List<User> getUserByRowBounds(Map<String,Integer> map);
   ~~~

2. Mapper.xml

   ~~~xml
       <!--    分页实现 BY RowBounds-->
       <resultMap id="rowMap" type="User">
           <result column="PWD" property="password"/>
       </resultMap>
       <select id="getUserByRowBounds"  resultMap="rowMap">
           select * from mybatis.users;
       </select>
   ~~~

3. 测试

   ~~~java
       @Test
       public void getUserByRowBounds(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   
           //通过RowBounds实现分页
           RowBounds rowBounds = new RowBounds(1,2);
           //通过java代码层面实现分类
           List<User> userList = sqlSession.selectList("study.dao.UserMapper.getUserByRowBounds",null,rowBounds);
           for (User user : userList) {
               System.out.println(user);
           }
       }
   ~~~



### 7.3 分页插件

Mybatis pageHelper：https://pagehelper.github.io/



## 8 使用注解开发

### 8.1 面向接口编程

- 大家之前都学过面向对象编程，也学过接口，但是在真正的开发中，很多时候我们会选择面向接口编程
- **根本原因：解耦，可拓展，提高复用，分层开发，上层不用管具体的实现，大家都遵循共同的标准，使得开发变得更加容易，规范性更好**
- 在一个面向对象的系统中，系统的各个功能是由许许多多的不同对象协作完成的。在这种情况下，各个对象内部是如何实现自己的，对系统设计人员讲就不那么重要了。
- 而各个对象之间的协作关系则成为系统设计的关键。小到不同类之间的通信，大到各个模块之间的交互，在系统设计之初都是要着重考虑的，这也是系统设计的主要工作内容。面向接口编程就是按照这种思想来编程

**关于接口的理解**

- 接口从更深层次的理解，应是定义（规范，约束）与实现（名实分离的原则）的分离。
- 接口的本身反映了系统设计人员对系统的抽象理解。
- 接口应有两类：
  - 第一类是对一个个体的抽象，它可对应为一个抽象体(abstract class)；
  - 第二类是对一个个体某一方面的抽象，即形成一个抽象面（interface）；
- 一个体有可能有多个抽象面。抽象体与抽象面是有区别的

**三个面向区别**

- 面向对象是指，我们考虑问题时，以对象为单位，考虑它的属性及方法

- 面向过程是指，我们考虑问题时，以一个具体的流程（事务过程）为单位，考虑它的实现

- 接口设计与非接口设计是针对复用技术而言的，与面向对象（过程）不是一个问题



### 8.2 使用注解开发

1. 注解 直接在接口上实现

   ~~~java
   public interface UserMapper {
   
       @Select("select * from users")
       List<User> getUsers();
   }
   ~~~

2. 需要再配置核心配置文件绑定接口

   ~~~xml
   <!--    绑定接口-->
       <mappers>
           <mapper class="study.dao.UserMapper"/>
       </mappers>
   ~~~

3.  测试

   ~~~java
       @Test
       public void test(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           // 底层主要应用反射
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           List<User> users = mapper.getUsers();
           for (User user : users) {
               System.out.println(user);
           }
           sqlSession.close();
       }
   ~~~



本质：反射机制实现

底层：动态代理

![image-20191218160803409](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191218160803409.png)



**Mybatis 详细的执行流程**

![20180507152553971.png](https://blog.kuangstudy.com/usr/uploads/2019/10/1461882138.png)



### 8.3 使用注解实现CRUD

我可以在工具类创建的时候 实现自动提交事务

~~~java
        public static SqlSession getSqlSession() {
            SqlSession sqlSession = sqlSessionFactory.openSession(true); //带参数true 实现事务自动提交
            return sqlSession;

        }
~~~



编写接口增加注解

~~~java
public interface UserMapper {

    @Select("select * from users")
    List<User> getUsers();

    //方法存在多个参数 所有的参数前面必须加上@Param注解
    @Select("select * from users where id = #{id2} ")
    User getUserById(@Param("id2") int id);
//    User getUserById(@Param("id") int id,@Param("name") String name);

    @Insert("insert into users(id,name,pwd) values (#{id},#{name},#{password})")
    int addUser(User user);

    @Update("update users set name=#{name},pwd=#{password} where id=#{id}")
    int updateUser(User user);

    @Delete("delete from users where id=#{id1}")
    int deleteUser(@Param("id1") int id);
}

~~~



测试类

【注意：我们必须要将接口绑定到核心配置文件中】



**关于@Param()注解**

- 基本类型的参数或者String类型需要加上
- 引用类型不需要加
- 如果只有一个基本类型可以忽略，但是建议大家都加上
- 我们在Sql中引用的就是这里的@Param中设定的属性名



**#{} 和${}区别**

- \#方式能够很大程度防止sql注入。
- $方式无法防止Sql注入。
- $方式一般用于传入数据库对象，例如传入表名.
- **一般能用#的就别用$.**



## 9 Lambok

~~~java
 Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
~~~



使用步骤：

1. 在IDEA中安装Lombok插件

2. 在项目中导入lombok的jar包

   ~~~xml
   <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
       <version>1.18.10</version>
       <scope>provided</scope>
   </dependency>
   ~~~

   

3. 在实体类加注解即可

   ~~~java
   // 实体类
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class User {
       private int id;
       private String name;
       private String password;
   
   }
   ~~~

   

~~~java
@Getter and @Setter
@FieldNameConstants
@ToString
@EqualsAndHashCode
@AllArgsConstructor, @RequiredArgsConstructor and @NoArgsConstructor
@Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger, @CustomLog
@Data
@Builder
@SuperBuilder
@Singular
@Delegate
@Value
@Accessors
@Wither
@With
@SneakyThrows
~~~



@Data: 生成无参构造，get，set，toString，hashcode，equals

```
@AllArgsConstructor 有参构造	
@NoArgsConstructor 无参构造
@Getter
@Setter
@ToString
@EqualsAndHashCode
```



## 10 多对一的处理【association】

![image-20191218194810297](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191218194810297.png)

- 多个学生，对应一个老师
- 对于学生这边而言，**关联**，多个学生，关联一个老师【多对一】
- 对于老师而言，**集合**，一个老师有很多学生【一对多】



SQL:

~~~SQL
CREATE TABLE `teacher` (
  `id` INT(10) NOT NULL,
  `name` VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO teacher(`id`, `name`) VALUES (1, '秦老师'); 

CREATE TABLE `student` (
  `id` INT(10) NOT NULL,
  `name` VARCHAR(30) DEFAULT NULL,
  `tid` INT(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fktid` (`tid`),
  CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8


INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('1', '小明', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('2', '小红', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('3', '小张', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('4', '小李', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('5', '小王', '1'); 
~~~

![1567059915539.png](https://blog.kuangstudy.com/usr/uploads/2019/10/70968825.png)





### 10.1 测试环境搭建

1. 导入lombok
2. 新建实体类 Teacher，student
3. 建立Mapper接口
4. 建立Mapper.xml文件
5. 在核心配置文件中绑定我们的Mapper接口或者文件【方式很多，随心选】
6. 测试查询是否成功



### 10.2 按照查询嵌套处理

~~~xml
<mapper namespace="study.dao.StudentMapper">

<!--    思路：1. 查询所有的学生信息
             2. 根据查询出来的学生的tid，寻找对应的老师 (子查询）-->
    <resultMap id="StudentTeacher" type="study.pojo.Student">
        <result property="id" column="id" />
        <result property="name" column="name" />
<!--        复杂的属性我们需要单独处理-->
<!--        如果是对象 我们用 association
            如果是集合 我们用 collection-->
        <association property="teacher" column="tid" javaType="study.pojo.Teacher" select="getTeacher"/>
    </resultMap>
    <select id="getStudents" resultMap="StudentTeacher">
        select * from mybatis.student;
    </select>

    <select id="getTeacher" resultType="study.pojo.Teacher">
        select * from mybatis.teacher where id=#{id}
    </select>

</mapper>
~~~



### 10.3 按照结果嵌套处理【推荐】

~~~xml
<!--    方式二： 按照结果嵌套处理-->
    <resultMap id="StudentTeacher2" type="study.pojo.Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <association property="teacher" javaType="study.pojo.Teacher">
            <result property="name" column="tname"/>
        </association>
    </resultMap>
    <select id="getStudents" resultMap="StudentTeacher2">
        select s.id sid,s.name sname,t.name tname
        from mybatis.student s,mybatis.teacher t
        where s.tid=t.id;
    </select>
~~~



### 10.4 回顾Mybatis 多对一的查询方式

- 子查询
- 联表查询



## 11 一对多处理

对老师而言，就是一对多的关系



### 11.1 环境搭建

1. 搭建环境 和刚才的一样

   **实体类**

   ~~~java
   @Data
   public class Teacher {
       private int id;
       private String name;
   
       // 一个老师拥有多个学生
       private List<Student> students;
   }
   ~~~

   ~~~java
   @Data
   public class Student {
       private int id;
       private String name;
   
       //多个学生对应一个老师
       private Teacher teacher;
   }
   ~~~

### 11.2 按照结果嵌套处理【推荐】

~~~xml
<!--    按照结果嵌套查询-->
    <resultMap id="TeacherStudent" type="study.pojo.Teacher">
        <result property="id" column="tid"/>
        <result property="name" column="tname"/>
<!--       复杂的属性我们需要单独处理
            如果是对象 我们用 association
            如果是集合 我们用 collection
            javaType="" 指定属性的类型
            集合中的泛型信息，我们使用ofType-->
        <collection property="students" ofType="study.pojo.Student">
            <result property="id" column="sid"/>
            <result property="name" column="sname"/>
            <result property="id" column="sid"/>
        </collection>
    </resultMap>
    <select id="getTeacher" resultMap="TeacherStudent">
        select s.id sid,s.name sname,t.name tname,t.id tid
        from student s, teacher t
        where s.tid = t.id and t.id = #{tid};
    </select>

~~~



### 11.3 按照查询嵌套处理

~~~xml
 <resultMap id="TeacherStudent2" type="study.pojo.Teacher">
        <result property="id" column="id"/>
        <result property="name" column="name"/>
        <collection property="students" javaType="ArrayList" ofType="study.pojo.Student" select="getStudentByTeacherId" column="id"/>
    </resultMap>

    <select id="getTeacher2" resultMap="TeacherStudent2">
        select * from mybatis.teacher where id=#{tid};
    </select>

    <select id="getStudentByTeacherId" resultType="study.pojo.Student">
        select * from mybatis.student where tid = #{tid}
    </select>
~~~



### 11.4  小结

1. 关联-association 【多对一】
2. 集合 collection 【一对多】
3. javaType  & ofType
   - JavaType 用来指定实体类中的属性的类型
   - ofType 用来指定映射到LIst或者集合中pojo类型，泛型中的约束类型！



注意点：

1. 保证sql的可读性，尽量保证通俗易懂
2. 注意一对多和多对一中，属性名和字段的问题
3. 如果问题不好排查错误，可以使用日志，建议使用Log4j



**面试高频**

- Mysql引擎
- InnoDB底层原理
- 索引
- 索引优化



## 12 动态SQL

 **什么是动态SQL：动态SQL就是指根据不同的条件生成不同的SQL语句**

利用动态SQL这一特性可以彻底摆脱这种痛苦

~~~xml
动态 SQL 元素和 JSTL 或基于类似 XML 的文本处理器相似。在 MyBatis 之前的版本中，有很多元素需要花时间了解。MyBatis 3 大大精简了元素种类，现在只需学习原来一半的元素便可。MyBatis 采用功能强大的基于 OGNL 的表达式来淘汰其它大部分元素。

if
choose (when, otherwise)
trim (where, set)
foreach
~~~



### 12.1 搭建环境

~~~sql
CREATE TABLE `blog` (
  `id` varchar(50) NOT NULL COMMENT '博客id',
  `title` varchar(100) NOT NULL COMMENT '博客标题',
  `author` varchar(30) NOT NULL COMMENT '博客作者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `views` int(30) NOT NULL COMMENT '浏览量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
~~~



创建一个基础工程

1. 导包

2. 编写配置文件

3. 编写实体类

4. 编写实体类对应Mapper接口和Mapper.xml文件

   实体类

   ~~~java
   @Data
   public class Blog {
       private int id;
       private String title;
       private String author;
       private Date createTime;
       private int views;
   }
   ~~~

   Mapper接口

   ~~~java
   ublic interface BlogMapper {
   
       // 插入数据
       int addBook(Blog blog);
   }
   ~~~

   Mapper.xml

   ~~~xml
   <mapper namespace="study.dao.BlogMapper">
   
       <insert id="addBook" parameterType="study.pojo.Blog">
           insert into mybatis.blog (id, title, author, create_time, views)
           values(#{id}, #{title}, #{author}, #{createTime}, #{views})
       </insert>
   </mapper>
   ~~~

   随机生成ID

   ~~~java
   public class IdUtiles {
   
       public static String getId(){
           return UUID.randomUUID().toString().replaceAll("-","");
       }
   
       @Test
       public void test(){
           System.out.println(IdUtiles.getId());
       }
   }
   ~~~

   测试

   ~~~java
       @Test
       public void addBook(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
   
           Blog blog = new Blog();
           blog.setId(IdUtiles.getId());
           blog.setTitle("Mybatis如此简单");
           blog.setAuthor("狂神说");
           blog.setCreateTime(new Date());
           blog.setViews(9999);
           mapper.addBook(blog);
   
           blog.setId(IdUtiles.getId());
           blog.setTitle("Java如此简单");
           mapper.addBook(blog);
   
           blog.setId(IdUtiles.getId());
           blog.setTitle("Spring如此简单");
           mapper.addBook(blog);
   
           blog.setId(IdUtiles.getId());
           blog.setTitle("微服务如此简单");
           mapper.addBook(blog);
   
           sqlSession.close();
       }
   ~~~

   

### 12.2 IF语句

实体类

~~~java
public interface BlogMapper {

    // 插入数据
    int addBook(Blog blog);

    // 查询博客
    List<Blog> queryBlogIf(Map map);
}
~~~

Mapper.xml

~~~xml

<mapper namespace="study.dao.BlogMapper">
    
    <select id="queryBlogIf" parameterType="map" resultType="study.pojo.Blog">
        select * from mybatis.blog where 1=1
        <if test="title != null">
            and title = #{title}
        </if>
        <if test="author != null">
            and author= #{author}
        </if>
    </select>
    
</mapper>
~~~

测试

~~~java
    @Test
    public void queryBlogIf() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap();
//        map.put("title","Java如此简单");
        map.put("author","狂神说");
        List<Blog> blogs = mapper.queryBlogIf(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }
~~~



### 12.3 choose (when, otherwise) 语句

~~~xml
 select * from mybatis.blog
        <where>
            <choose>
                <when test="title != null">
                    title = #{title}
                </when>
                <when test="author != null ">
                    and author =#{author}
                </when>
                <otherwise>
                    and views = #{views}
                </otherwise>
            </choose>
        </where>
~~~

相当于switch语句

### 12.4 trim, where, set 语句

**where语句**

~~~xml
<where>
    <if test="title != null">
        and title = #{title}
    </if>
    <if test="author != null">
        and author= #{author}
    </if>
</where>
~~~

~~~xml
where 元素只会在至少有一个子元素的条件返回 SQL 子句的情况下才去插入“WHERE”子句。而且，若语句的开头为“AND”或“OR”，where 元素也会将它们去除。
~~~



**set语句**

~~~xml
update mybatis.blog
<set>
    <if test="title != null">
        title = #{title},
    </if>
    <if test="author != null">
        author = #{author},
    </if>
</set>
where id= #{id}
~~~



所谓的动态sql，本质还是sql语句，只是我们可以在sql层面去执行一些逻辑代码（if，where，set，choose）



**Foreach**

~~~xml
<foreach item="item" index="index" collection="list"
      open="(" separator="," close=")">
        #{item}
  </foreach>
~~~



~~~xml
<!--
select * from mybatis.blog where 1=1 and id in (1,2,3);
我们现在传递一个万能的map，这map中可以存在一个集合-->
<select id="queryBlogForeach" parameterType="map" resultType="study.pojo.Blog">
    select * from mybatis.blog
    <where>
    <foreach collection="ids" item="id" open="and (" close=")" separator="or">
        id=#{id}
    </foreach>

    </where>
</select>
~~~



~~~java
@Test
public void queryBlogForeach(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
    HashMap map = new HashMap();
    ArrayList<Integer> ids = new ArrayList<Integer>();
    ids.add(1);
    ids.add(2);
    map.put("ids",ids);
    List<Blog> blogs = mapper.queryBlogForeach(map);
    for (Blog blog : blogs) {
        System.out.println(blog);
    }
    sqlSession.close();
}
~~~





**SQL片段**

有时候，我们可能会将一些功能的部分抽出来进行，方便公用

1. 使用sql标签抽取公共的部分

   ~~~xml
       <sql id="if-title-author">
           <if test="title != null">
               and title = #{title}
           </if>
           <if test="author != null">
               and author= #{author}
           </if>
       </sql>
   ~~~

   

2. 在需要使用的使用include标签引用即可

   ~~~xml
           select * from mybatis.blog
           <where>
               <include refid="if-title-author"></include>
           </where>
   
   ~~~

   

**注意事项：**

- 最好基于单表来定义SQL片段

- 不要存在where标签

  





**动态sql就是在拼接sql语句，我们只要保证sql的正确性，按照sql的格式，去排列组合就可以了**

建议:

- 现在Mysql中写出完整的sql，再对应的去修改成为我们的动态sql，从而实现通用即可





## 13 缓存【了解】

### 13.1 简介

~~~xml
查询：连接数据库，耗资源
	一次查询的结果，给他暂存在一个可以直接取到的地方--> 内存:缓存

我们再次查询相同数据的时候，直接走缓存，就不用再一次走数据库了
~~~

1.  什么是缓存【Cache】?
   - 存在内存中的临时数据
   - 将用户经常查询的数据放在缓存(内存)中，用户去查询数据就不用从磁盘上
   - （关系型数据库数据文件）查询，从缓存中查询，从而提高查询效率， 解决了高并发系统的性能问题
2. 为什么使用缓存？
   - 减少和数据库的交互次数，减少系统开销
3. 什么样的数据能使用缓存？
   - 经常查询并且不经常改变的数据
4. 什么样的数据不能使用缓存？
   - 不经常查询或者经常改变的数据



### 13.2 Mybatis缓存

- MyBatis包含一个非常强大的查询缓存特性，它可以非常方便地定制和配置缓存。缓存可以极大的提升查询效率。
- MyBatis系统中默认定义了两级缓存：**一级缓存**和**二级缓存**
  - 默认情况下，只有一级缓存开启。（SqlSession级别的缓存，也称为本地缓存）
  - 二级缓存需要手动开启和配置，他是基于namespace级别的缓存。
  - 为了提高扩展性，MyBatis定义了缓存接口Cache。我们可以通过实现Cache接口来自定义二级缓存



### 13.3 一级缓存

**一级缓存也叫本地缓存：**

- 与数据库同一次会话期间查询到的数据会放在本地缓存中。
- 以后如果需要获取相同的数据，直接从缓存中拿，没必须再去查询数据库；



**测试步骤：**

1. 开启日志
2. 测试在一个Session中查询两次相同记录
3. 查看日志输出

![image-20191222191857849](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191222191857849.png)



**缓存失效的情况：**

1. 查询不同的东西
2. 增删改操作，可能会改变原来的数据，所以必定会刷新缓存
3. 查询不同的Mapper.xml
4. 手动清除缓存



![image-20191222192127594](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191222192127594.png)



~~~java
    @Test
    public void queryUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(2);
        System.out.println(user);

        System.out.println("=============================");

//        int xin = mapper.updateUserById(new User(1,"xin","123"));
//        System.out.println(xin);
        sqlSession.clearCache();//手动清除缓存

        System.out.println("=============================");

        User user1 = mapper.queryUserById(1);
        System.out.println(user1);
        System.out.println(user==user1);

        sqlSession.close();
    }
}
~~~



**小结**：一级缓存是默认开启的，只在一次Sqlsession中生效，也就是拿到连接到关闭连接这个区间段

一级缓存相当于一个Map



### 13.4 二级缓存

- 二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存
- 基于namespace级别的缓存，一个名称空间，对应一个二级缓存；
- 工作机制
  - 一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存中；
  - 如果当前会话关闭了，这个会话对应的一级缓存就没了；但是我们想要的是，会话关闭了，一级缓存中的数据被保存到二级缓存中；
  - 新的会话查询信息，就可以从二级缓存中获取内容；
  - 不同的mapper查出的数据会放在自己对应的缓存（map）中；

![image-20191222194806708](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191222194806708.png)

![image-20191222194839166](C:\Users\Mick\AppData\Roaming\Typora\typora-user-images\image-20191222194839166.png)



**步骤**

1. 开启缓存

   ~~~xml
   <!--        显式的开启全局缓存-->
   <setting name="cacheEnabled" value="true"/>
   ~~~

2. 在要使用二级缓存的Mapper.xml中开启

   ~~~xml
   <!--    在当前Mapper.xml中使用二级缓存-->
       <cache/>
   ~~~

   也可以自定义参数

   ~~~xml
   <!--    在当前Mapper.xml中使用二级缓存-->
       <cache  eviction="FIFO"
               flushInterval="60000"
               size="512"
               readOnly="true" />
   ~~~

3. 测试

   1. 问题：我们需要将实体类序列化！！否则聚会报错

      ~~~xml
      Caused by: java.io.NotSerializableException: study.pojo.User
      ~~~

      ~~~java
      //解决：将实体类序列化
      public class User implements Serializable {}
      ~~~



**小结：**

- 只要开启了二级缓存，在有一个Mapper下就有效
- 所有的数据会先放在一级缓存中
- 只有当会话提交，或者关闭的时候，才会提交到二级缓存中



### 13.5 缓存原理

![1567092725457.png](https://blog.kuangstudy.com/usr/uploads/2019/10/3995451776.png)



**缓存顺序：**

1. 先看二级缓存中有没有
2. 再看一级缓存中有没有
3. 查询数据库



### 13.6 自定义缓存 ehcache

~~~xml
Ehcache是一种广泛使用的java分布式缓存，用于通用缓存；
~~~

要在程序中使用ehcache，需要导jar包

~~~xml
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.1.0</version>
</dependency>
~~~

在mapper.xml中使用对应的缓存即可

~~~xml
<cache type = “org.mybatis.caches.ehcache.EhcacheCache” /> 
~~~

编写ehcache.xml文件，如果在`加载时`未找到`/ehcache.xml`资源或出现问题，则将使用默认配置。

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
         updateCheck="false">
    <!--
       diskStore：为缓存路径，ehcache分为内存和磁盘两级，此属性定义磁盘的缓存位置。参数解释如下：
       user.home – 用户主目录
       user.dir  – 用户当前工作目录
       java.io.tmpdir – 默认临时文件路径
     -->
    <diskStore path="./tmpdir/Tmp_EhCache"/>

    <defaultCache
            eternal="false"
            maxElementsInMemory="10000"
            overflowToDisk="false"
            diskPersistent="false"
            timeToIdleSeconds="1800"
            timeToLiveSeconds="259200"
            memoryStoreEvictionPolicy="LRU"/>

    <cache
            name="cloud_user"
            eternal="false"
            maxElementsInMemory="5000"
            overflowToDisk="false"
            diskPersistent="false"
            timeToIdleSeconds="1800"
            timeToLiveSeconds="1800"
            memoryStoreEvictionPolicy="LRU"/>
    <!--
       defaultCache：默认缓存策略，当ehcache找不到定义的缓存时，则使用这个缓存策略。只能定义一个。
     -->
    <!--
      name:缓存名称。
      maxElementsInMemory:缓存最大数目
      maxElementsOnDisk：硬盘最大缓存个数。
      eternal:对象是否永久有效，一但设置了，timeout将不起作用。
      overflowToDisk:是否保存到磁盘，当系统宕机时
      timeToIdleSeconds:设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。
      timeToLiveSeconds:设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷大。
      diskPersistent：是否缓存虚拟机重启期数据 Whether the disk store persists between restarts of the Virtual Machine. The default value is false.
      diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。
      diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认是120秒。
      memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
      clearOnFlush：内存数量最大时是否清除。
      memoryStoreEvictionPolicy:可选策略有：LRU（最近最少使用，默认策略）、FIFO（先进先出）、LFU（最少访问次数）。
      FIFO，first in first out，这个是大家最熟的，先进先出。
      LFU， Less Frequently Used，就是上面例子中使用的策略，直白一点就是讲一直以来最少被使用的。如上面所讲，缓存的元素有一个hit属性，hit值最小的将会被清出缓存。
      LRU，Least Recently Used，最近最少使用的，缓存的元素有一个时间戳，当缓存容量满了，而又需要腾出地方来缓存新的元素的时候，那么现有缓存元素中时间戳离当前时间最远的元素将被清出缓存。
   -->

</ehcache>
~~~



**以后 基本上会在Redis数据库在做缓存**