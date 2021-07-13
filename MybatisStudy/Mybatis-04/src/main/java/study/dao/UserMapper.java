package study.dao;

import study.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {


    // 根据ID 查询用户
    User getUserById(int ID);

    //分页
    List<User> getUserByLimit(Map<String,Integer> map);

    //分页2 RowsBounds
    List<User> getUserByRowBounds(Map<String,Integer> map);
}
