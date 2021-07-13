import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import study.dao.StudentMapper;
import study.dao.TeacherMapper;
import study.pojo.Student;
import study.pojo.Teacher;
import study.utils.MybatisUtils;

import java.util.List;

public class MyTest {

    @Test
    public void getTeacherById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacherById = mapper.getTeacherById(1);
        System.out.println(teacherById);
        sqlSession.close();
    }

    @Test
    public void getStudents(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.getStudents();
        for (Student student : studentList) {
            System.out.println(student);
        }
        sqlSession.close();

    }
}
