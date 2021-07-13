import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import study.dao.UserMapper;
import study.pojo.User;
import study.utils.MybatisUtils;

import java.util.List;

public class UserMapperTest {

    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 底层主要应用反射
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUsers();
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void getUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User tu = mapper.getUserById(3);
        System.out.println(tu);
        sqlSession.close();
    }

    @Test
    public void addUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int xin = mapper.addUser(new User(6, "XIN", "123456"));
        if (xin>0){
            System.out.println("add successfully");
        }
        sqlSession.close();
    }

    @Test
    public void updateUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int xin = mapper.updateUser(new User(5, "xin", "789456"));
        if (xin>0){
            System.out.println("update successfully");
        }
        sqlSession.close();
    }

    @Test
    public void deleteUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.deleteUser(6);
        if(i>0){
            System.out.println("delete successfully");
        }
        sqlSession.close();
    }
}
