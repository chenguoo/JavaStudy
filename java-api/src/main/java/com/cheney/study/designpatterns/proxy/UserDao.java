package com.cheney.study.designpatterns.proxy;

/**
 * 说明:被代理对象(目标对象)
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 10:04
 */
public class UserDao implements IUserDao {
  @Override
  public void save() {
    System.out.println("----已经保存数据!----");
  }
}
