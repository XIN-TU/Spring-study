package study.reflection;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 通过反射，动态的创建对象
public class Test07 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获取Class对象
        Class c1 = Class.forName("study.reflection.User");

        // 构造一个对象
//        User user = (User) c1.newInstance(); // 本质上去调用了类的无参构造器
//        System.out.println(user);

        // 通过构造器创建对象
//        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
//        User user2 = (User) declaredConstructor.newInstance("XIn,", 001, 18);
//        System.out.println(user2);

        // 通过反射调用普通反射
        User user3 = (User) c1.newInstance();
        //通过反射获取一个方法
        Method setName = c1.getDeclaredMethod("setName", String.class);
        //invoke :激活的意思（对象，“方法的值”）
        setName.invoke(user3,"Tu");
        System.out.println(user3.getName());

        System.out.println("=================");
        User user4 = (User) c1.newInstance();
        Field name = c1.getDeclaredField("name");

        name.setAccessible(true); // 关闭程序的安全检测，使能使用反射访问私有属性 默认为false
        name.set(user4,"Xin"); //name为私有属性 不能直接访问
        System.out.println(user4.getName());

    }
}
