package study.demo04;

import study.demo02.UserService;
import study.demo02.UserServiceImpl;



public class Client {

    public static void main(String[] args) {

        //真实角色
        UserServiceImpl userService = new UserServiceImpl();

        //代理角色: 现在没有
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //通过调用程序处理角色来处理我们要调用的接口对象
        pih.setTarget(userService); // 设置要代理的对象

//        Object proxy = (Object) pih.getProxy(); // 这里的proxy就是动态生成的，我们并没有写
        UserService proxy = (UserService) pih.getProxy();
        proxy.delete();
        proxy.add();
    }
}
