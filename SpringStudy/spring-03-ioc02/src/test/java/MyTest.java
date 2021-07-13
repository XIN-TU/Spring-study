import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.projo.User;

public class MyTest {
    public static void main(String[] args) {
//        User user = new User();

        // Spring 容器相当于一个婚介所，所有用户都在其中注册了（相当于被创建了对象）
        // 需要使用时 直接get就行
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) context.getBean("userA");
        user.show();

    }
}
