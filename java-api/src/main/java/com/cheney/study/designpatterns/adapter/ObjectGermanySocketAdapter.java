package com.cheney.study.designpatterns.adapter;

/**
 * 说明:德国插座适配器(对象适配器模式)(经常使用)
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 13:56
 */
public class ObjectGermanySocketAdapter implements GermanySocketInterface {

  //组合新接口
  private GBSocketInterface gbSocket;

  /**
   * 在创建适配器对象时，必须传入一个新街口的实现类
   *
   * @param gbSocket
   */
  public ObjectGermanySocketAdapter(GBSocketInterface gbSocket) {
    this.gbSocket = gbSocket;
  }

  @Override
  public void powerWithTwoRound() {
    System.out.println("德国适配器转换");
    gbSocket.powerWithThreeFlat();
  }
}
