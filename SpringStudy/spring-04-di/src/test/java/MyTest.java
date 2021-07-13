import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.projo.Student;
import study.projo.User;

public class MyTest {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
//        System.out.println(student.getName());
        System.out.println(student.toString());

//        Student{
//            name='Xin',
//            address=Address{address='成都'},
//            books=[红楼梦, 西游记, 水浒传, 三国演义],
//            hobbys=[听歌, 敲代码, 看电影],
//            card={
//                身份证=1111111111,
//                银行卡=2222222222
//            },
//                games=[LOL, COC, BOB],
//                wife='null',
//                info={
//                学号=小明,
//                性别=man
//                }
//        }
    }

//    p 命名空间获取需要借助junit中的test来获取
    @Test
    public void test2()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("userBeans.xml");
        User user2 = context.getBean("user2",User.class);
        User user3 = context.getBean("user2",User.class);
        System.out.println(user2 == user3);
    }
}
