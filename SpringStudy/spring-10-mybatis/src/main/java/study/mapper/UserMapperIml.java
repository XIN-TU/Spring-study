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
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
