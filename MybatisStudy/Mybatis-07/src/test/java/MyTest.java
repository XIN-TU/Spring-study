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
    public void getTeacher(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacherList = mapper.getTeacher(1);
        System.out.println(teacherList);
        sqlSession.close();
    }

    @Test
    public void getTeacher2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacherList = mapper.getTeacher2(1);
        System.out.println(teacherList);
        sqlSession.close();
    }
}
