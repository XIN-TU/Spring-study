package study.reflection;

// 测试类什么时候会初始化
public class Test04 {
    static {
        System.out.println("main类被加载");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // 1.主动引用
//        Son son = new Son();

        // 反射也会产生主动引用
//        Class.forName("study.reflection.Son");

        //不会产生类的引用的方法
//        System.out.println(Son.b); //通过子类调用父类的静态变量，那么就不会初始化子类

//        Son[] array = new Son[5]; // 只是开辟了一片空间

        System.out.println(Son.M); // 引用常类不会触发此类的初始化 因为常类在连接阶段就存入了调用类的常量池中了

    }
}

class Father {
    static int b = 2;
    static {
        System.out.println("父类被加载");
    }

}

class Son extends Father{
    static {
        System.out.println("子类被加载");
        m = 300 ;
    }
    static int m = 100;
    static final int M = 1;
}
