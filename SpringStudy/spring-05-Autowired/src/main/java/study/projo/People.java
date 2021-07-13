package study.projo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class People {
    //Spring 的注解自动装配
    // 如果显示定义了Autowired的required属性为false，说明这个对象可以为null，否则不允许为空
//    @Autowired(required = false)
//    @Qualifier(value = "cat11")
//    private Cat cat;
//    @Autowired
//    @Qualifier(value = "dog222")
//    private Dog dog;

    // java的注解自动装配

    @Resource(name = "cat111")
    private Cat cat;

    @Resource
    private Dog dog;

    private String name;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}
