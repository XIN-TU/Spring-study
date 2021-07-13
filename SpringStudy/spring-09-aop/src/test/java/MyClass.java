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
