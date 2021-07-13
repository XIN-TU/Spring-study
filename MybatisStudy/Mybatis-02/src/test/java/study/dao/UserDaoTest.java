package study.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import study.pojo.User;
import study.utils.MybatisUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    @Test
    public void test(){

        // 第一步：获得sqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

       try{
           // 执行SQL
           //方式一：getMapper (更加推荐）
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           List<User> userList = mapper.getUserList();

           // 方式二
//        List<User> userList = sqlSession.selectList("study.dao.UserDao.getUserList");


           for (User user : userList) {
               System.out.println(user);
           }

       }catch (Exception e) {
           System.out.println(e);
       }
       finally {
           //关闭sqlSession
           sqlSession.close();
       }
    }

    @Test
    public void getUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(3);
        System.out.println("result:" + userById);
        sqlSession.close();
    }

    // 增删改 需要提交事务
    @Test
    public void addUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.addUser(new User(5,"Xin","123456"));
        if (res > 0 ){
            System.out.println("insert successfully");
        }
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser(new User(3,"TU","789456"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.deleteUser(5);
        if (res>0){
            System.out.println("delete successfully");
        }
        sqlSession.commit();
        sqlSession.close();
    }

}
