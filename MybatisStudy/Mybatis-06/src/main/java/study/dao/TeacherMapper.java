package study.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import study.pojo.Teacher;

public interface TeacherMapper {


    Teacher getTeacherById(int id);
}
