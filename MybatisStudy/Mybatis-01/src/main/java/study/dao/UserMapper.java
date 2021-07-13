package study.dao;

import study.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    // 查询全部用户
    List<User> getUserList();

    // 根据ID 查询用户
    User getUserById(int ID);

    // 增加一个用户
    int addUser(User user);

    // 修改用户
    int updateUser(User user);

    // 删除一个用户
    int deleteUser(int ID);

    // 万能的Map
    int addUser2(Map<String, Object> map);

    User getUserById2(Map<String,Object> map);

    // 模糊查询
    List<User> getUserLike(String value);

}
