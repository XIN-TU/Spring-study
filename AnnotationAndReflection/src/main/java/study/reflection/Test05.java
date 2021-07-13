package study.reflection;

public class Test05 {
    public static void main(String[] args) throws ClassNotFoundException {

        // 获取系统类的加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取系统类加载器的父类加载器--> 扩展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        //获取扩展类加载器的父类加载器-->根加载器（c/c++）
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        // 测试当前类是那个加载器加载的 --> 系统类加载器加载
        ClassLoader classLoader = Class.forName("study.reflection.Test05").getClassLoader();
        System.out.println(classLoader);

        // 测试JDK 内置的类时谁加载的 -- > 根加载器加载
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);

        // 如何获得系统类加载器可以加载的路径

        System.out.println(System.getProperty("java.class.path"));

        // 双亲委派机制：
         //
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\charsets.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\deploy.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\access-bridge-64.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\cldrdata.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\dnsns.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\jaccess.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\jfxrt.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\localedata.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\nashorn.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\sunec.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\sunjce_provider.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\sunmscapi.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\sunpkcs11.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\zipfs.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\javaws.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\jce.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\jfr.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\jfxswt.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\jsse.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\management-agent.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\plugin.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\resources.jar;
//        D:\Program Files\Java\jdk1.8.0_231\jre\lib\rt.jar;
//        D:\XUEXI\JAVA Spring\AnnotationAndReflection\target\classes;
//        D:\Program Files\JetBrains\IntelliJ IDEA 2019.3\lib\idea_rt.jar

    }
}
