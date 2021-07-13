package study.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 测试性能问题
public class Test08 {

    // 普通调用方法
    public static void test01(){
        User user = new User();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++)
        {
            user.getName();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("普通方式输出：" + (endTime-startTime) + "ms");
    }

    // 反射的方式调用
    public static void test02 () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        User user = new User();
        Class c1 = user.getClass();

        Method getName = c1.getDeclaredMethod("getName", null);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user,null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("反射的方式输出：" + (endTime-startTime) + "ms");
    }

    // 反射方式调用 关闭检测
    public static void test03 () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        User user = new User();
        Class c1 = user.getClass();

        Method getName = c1.getDeclaredMethod("getName", null);
        getName.setAccessible(true);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user,null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("反射方式以及 关闭检测输出：" + (endTime-startTime) + "ms");
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        test01();
        test02();
        test03();
    }
}
