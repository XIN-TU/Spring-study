package study.service;

import study.dao.UserDao;
import study.dao.UserDaoOracleImpl;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    // 利用set进行动态实现值的注入！
    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }

//    private UserDao userDao = new UserDaoOracleImpl(); // 程序控制创建对象

    public void getUser()
    {
        userDao.getUser();
    }
}
