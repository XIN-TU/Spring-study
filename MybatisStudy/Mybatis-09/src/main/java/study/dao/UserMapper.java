package study.dao;

import org.apache.ibatis.annotations.Param;
import study.pojo.User;

public interface UserMapper {

    User queryUserById(@Param(("id")) int i);

    int updateUserById(User user);
}
