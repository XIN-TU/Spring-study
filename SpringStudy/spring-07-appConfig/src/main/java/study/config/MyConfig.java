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
