package study.mapper;


import org.mybatis.spring.SqlSessionTemplate;
import study.pojo.User;

import java.util.List;

public class UserMapperIml implements UserMapper {

    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
