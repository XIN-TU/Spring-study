package study.pojo;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private int tid;

//    //多个学生对应一个老师
//    private Teacher teacher;
}
