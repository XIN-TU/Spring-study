package study.dao;

import org.apache.ibatis.annotations.*;
import study.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Select("select * from users")
    List<User> getUsers();

    //方法存在多个参数 所有的参数前面必须加上@Param注解
    @Select("select * from users where id = #{id2} ")
    User getUserById(@Param("id2") int id);
//    User getUserById(@Param("id") int id,@Param("name") String name);

    @Insert("insert into users(id,name,pwd) values (#{id},#{name},#{password})")
    int addUser(User user);

    @Update("update users set name=#{name},pwd=#{password} where id=#{id}")
    int updateUser(User user);

    @Delete("delete from users where id=#{id1}")
    int deleteUser(@Param("id1") int id);
}
