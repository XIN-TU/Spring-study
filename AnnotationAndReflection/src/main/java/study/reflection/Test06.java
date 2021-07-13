package study.reflection;

import com.sun.javaws.IconUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 获得类的信息
public class Test06 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1 = Class.forName("study.reflection.User");

        // 获得类的名字
        System.out.println(c1.getName()); // 获得包名+类名
        System.out.println(c1.getSimpleName()); // 获得类名

        // 获得类的属性
        System.out.println("===========");
        Field[] fields = c1.getFields();// 只能找到public属性
//        for (Field field : fields){
//            System.out.println(field); // 无输出
//        }

        fields = c1.getDeclaredFields(); // 找到全部属性
        for (Field field : fields){
            System.out.println(field);
        }

        Field name = c1.getDeclaredField("name"); //获得指定属性的值
        System.out.println(name);

        // 获得类的方法
        System.out.println("===========");
//        Method[] methods = c1.getMethods(); // 获得本类及其父类的所有方法
//        for(Method method: methods){
//            System.out.println(method);
//        }
        Method[] methodsS = c1.getDeclaredMethods(); // 获得本类的所有方法
        for(Method method: methodsS){
            System.out.println(method);
        }

        // 获得指定的方法
        Method getName = c1.getMethod("getName", null);
        System.out.println(getName);
        Method setName = c1.getMethod("setName", String.class);
        System.out.println(setName);

        System.out.println("===========");
        Constructor[] constructors = c1.getConstructors(); // 获得本类的public方法
        for(Constructor constructor: constructors){
            System.out.println("getConstructors:"+constructor);
        }
        Constructor[] constructorsS = c1.getDeclaredConstructors(); // 获得本类的全部方法
        for(Constructor constructor: constructorsS){
            System.out.println("getDeclaredConstructors:"+constructor);
        }

        // 获得指定方法
        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        System.out.println(declaredConstructor);

    }
}

class User{
    private String name;
    private int id;
    private int age;

    public User() {
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}
