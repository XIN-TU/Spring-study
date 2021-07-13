package study.projo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// 等价于 <bean id="user" class="study.projo.User"
// @Component 组件
@Component
@Scope("singleton")
public class User {
//    public String name = "Xin";

    //相当于 <property name = 'name' value = 'TuXin')
    @Value("TuXin")
    public String name;

    @Value("TuXin2")
    public void setName(String name) {
        this.name = name;
    }
}
