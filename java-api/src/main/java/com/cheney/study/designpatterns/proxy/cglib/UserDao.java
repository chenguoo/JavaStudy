package com.cheney.study.designpatterns.proxy.cglib;

/**
 * 说明:目标对象,没有实现任何接口
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 10:52
 */
public class UserDao {

  public boolean save() {
    System.out.println("----已经保存数据!----");
    return true;
  }
}
