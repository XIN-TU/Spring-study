
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.dao.UserDaoImpl;
import study.dao.UserDaoMysqlImpl;
import study.dao.UserDaoOracleImpl;
import study.dao.UserDaoSqlServiceImpl;
import study.service.UserService;
import study.service.UserServiceImpl;


public class MyTest {
    public static void main(String[] args) {

        //用户实际调用的是业务层， dao层他们不需要接触
//        UserServiceImpl userService = new UserServiceImpl();
//        userService.setUserDao( new UserDaoSqlServiceImpl());
//        userService.getUser();

//        Spring操作
        // 获取ApplicationContext: 拿到Spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //容器在手，天下我有，需要什么，就直接get什么
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("UserServiceImpl");
        userServiceImpl.getUser();



    }
}
