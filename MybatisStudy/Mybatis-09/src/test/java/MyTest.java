import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import study.dao.UserMapper;
import study.pojo.User;
import study.utils.MybatisUtils;

public class MyTest {

    @Test
    public void queryUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(2);
        System.out.println(user);

        System.out.println("=============================");

//        int xin = mapper.updateUserById(new User(1,"xin","123"));
//        System.out.println(xin);
        sqlSession.clearCache();//手动清除缓存

        System.out.println("=============================");

        User user1 = mapper.queryUserById(2);
        System.out.println(user1);
        System.out.println(user==user1);

        sqlSession.close();
    }

    @Test
    public void testCache(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserMapper mapper2 = sqlSession.getMapper(UserMapper.class);

        User user = mapper.queryUserById(2);
        System.out.println(user);

        User user1 = mapper2.queryUserById(2);
        System.out.println(user1);
        System.out.println(user==user1);

        sqlSession.close();
        sqlSession2.close();
    }
}
