package study.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import study.pojo.User;
import study.utils.MybatisUtils;

import java.util.List;

public class UserDaoTest {

    @Test
    public void getUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(3);
        System.out.println("result:" + userById);
        sqlSession.close();
    }

    // 代码中的sql语句：select * from mybatis.users where ID = #{id}
    // 类型处理器
    // 真实的sql语句:   select ID,NAME,PWD from mybatis.users where ID = #{id}


}
