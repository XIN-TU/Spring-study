package study.annotation;

import java.util.ArrayList;
import java.util.List;

public class Test01 extends Object{

    // @Override 重写的注解
    @Override
    public String toString() {
        return "Test01{}";
    }

    //Deprecated不推荐程序员使用，但是可以使用，或者存在更好的方式
    @Deprecated
    public static void  test(){
        System.out.println("Deprecated");
    }

//    @SuppressWarnings(value = "all")
//    public void test02{
//        List list = new ArrayList();
//    }

    public static void main(String[] args) {
        test();
    }
}
