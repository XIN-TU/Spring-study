import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.mapper.UserMapper;
import study.pojo.User;

import java.util.List;

public class MyTest {

    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringDao.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        List<User> userList = userMapper.selectUser();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
