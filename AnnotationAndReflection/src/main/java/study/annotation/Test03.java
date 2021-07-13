package study.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 自定义注解
public class Test03 {

    @MyAnnotation2(name = "Xin",id = 20)
    public void test(){}

    @MyAnnotation3("XIN") //如果自定注解中没default值，那么就必须要赋值
    public void test2(){}
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2{

    //注解的参数：参数类型+参数名（）
    String name() default "";
    int age() default  0;
    int id() default -1;
    String[] schools() default {"scujcc","scu"};

}

@Target({ElementType.TYPE,ElementType.METHOD}) //定义注解的使用范围 type是类 method是方法
@Retention(RetentionPolicy.RUNTIME) //定义注解在什么环节是有效的
@interface MyAnnotation3{
    String value();
}