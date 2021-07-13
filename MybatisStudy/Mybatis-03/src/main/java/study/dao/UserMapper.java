package study.dao;

import study.pojo.User;

import java.util.List;

public interface UserMapper {


    // 根据ID 查询用户
    User getUserById(int ID);

}
