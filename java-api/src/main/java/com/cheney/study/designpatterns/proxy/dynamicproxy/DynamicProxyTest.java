package com.cheney.study.designpatterns.proxy.dynamicproxy;

import com.cheney.study.designpatterns.proxy.IUserDao;
import com.cheney.study.designpatterns.proxy.UserDao;

/**
 * 说明:
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 10:22
 */
public class DynamicProxyTest {

  public static void main(String[] args) {
    // 目标对象
    IUserDao target = new UserDao();
    // 【原始的类型 class com.cheney.study.designpatterns.proxy.UserDao】
    System.out.println("原始类型:" + target.getClass());

    // 给目标对象，创建代理对象
    IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
    // class $Proxy0   内存中动态生成的代理对象
    System.out.println("代理类型:" + proxy.getClass());

    // 执行方法   【代理对象】
    proxy.save();
  }


}
