package com.cheney.study.designpatterns.proxy.cglib;

/**
 * 说明:
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 10:57
 */
public class CglibProxyTest {

  public static void main(String[] args) {
    //目标对象
    UserDao target = new UserDao();

    //代理对象
    UserDao proxy = (UserDao) new CglibProxyFactory(target).getProxyInstance();

    //执行代理对象的方法
    proxy.save();


  }
}
