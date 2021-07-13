package study.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import study.pojo.User;

import java.util.List;

public class UserMapperIml implements UserMapper{

//    原来：我们的所有操作，都使用sqlSession在操作；
//    现在：都是SqlSessionTemplate 来操作
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUser() {
        User user = new User(7, "TuXin", "123456");

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(user);
        mapper.deleteUser(5);
        return mapper.selectUser();
    }

    public int addUser(User User) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.addUser(User);
    }

    public int deleteUser(int id) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.deleteUser(id);
    }
}
