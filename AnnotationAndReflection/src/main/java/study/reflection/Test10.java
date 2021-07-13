package study.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

// 练习反射操作注解
public class Test10 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class c1 = Class.forName("study.reflection.Student2");

        // 通过反射获得注解
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
                System.out.println("annotation"+annotation);
        }

        // 获得注解的value的值
        TableT tableT = (TableT) c1.getAnnotation(TableT.class);
        String value = tableT.value();
        System.out.println(value);

        //获得类指定的注解
        Field f = c1.getDeclaredField("name");
        FieldT annotation = f.getAnnotation(FieldT.class);
        System.out.println(annotation.columnName());
        System.out.println(annotation.length());
        System.out.println(annotation.type());


    }
}

@TableT("db_student")
class Student2{
    @FieldT(columnName = "db_id",type = "int",length = 10)
    private int id;
    @FieldT(columnName = "db_age",type = "int",length = 10)
    private int age;
    @FieldT(columnName = "db_name",type = "varchar",length = 3)
    private  String name;

    public Student2(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Student2() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableT{
    String value();
}

//属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldT{
    String columnName();
    String type();
    int length();
}