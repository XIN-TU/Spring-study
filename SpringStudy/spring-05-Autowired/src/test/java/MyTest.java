import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.projo.People;

public class MyTest {
    @Test
    public void test1()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        People people = context.getBean("people",People.class);
        people.getDog().shut();
        people.getCat().shut();
    }
}
