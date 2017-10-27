package com.cheney.study.designpatterns.proxy.staticproxy;

import com.cheney.study.designpatterns.proxy.UserDao;

/**
 * 说明: 测试静态代理
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 10:08
 */
public class UserDaoStaticProxyTest {
  public static void main(String[] args) {
    //目标对象
    UserDao target = new UserDao();

    //代理对象,把目标对象传给代理对象,建立代理关系
    UserDaoStaticProxy proxy = new UserDaoStaticProxy(target);

    proxy.save();//执行的是代理的方法
  }
}
