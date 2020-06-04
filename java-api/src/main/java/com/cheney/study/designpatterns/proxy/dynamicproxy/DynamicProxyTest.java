package com.cheney.study.designpatterns.proxy.dynamicproxy;

import com.cheney.study.designpatterns.proxy.IUserDao;
import com.cheney.study.designpatterns.proxy.UserDao;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 说明:
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 10:22
 */
public class DynamicProxyTest {

  public static void main(String[] args) throws IOException {
    // 目标对象
    IUserDao target = new UserDao();
    // 【原始的类型 class UserDao】
    System.out.println("原始类型:" + target.getClass());

    // 给目标对象，创建代理对象
    IUserDao proxy = (IUserDao) ProxyFactory.getProxyInstance(target);
    // class $Proxy0   内存中动态生成的代理对象
    System.out.println("代理类型:" + proxy.getClass());
    // 执行方法   【代理对象】
    proxy.save();

    //下面的方法可以把这个代理对象输出到本地磁盘中:
    byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{IUserDao.class});
    FileOutputStream os =new FileOutputStream("D://$Proxy0.class");
    os.write(bytes);
    os.close();


  }


}
