package com.cheney.study.designpatterns.adapter;

/**
 * 说明:接口适配器(使用不多)
 * 这里可以实现N个国家的插座标准,到对应的国家旅游的时候,直接转换为对应国家的标准插座
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 14:18
 */
public class ImplementSocketAdapter implements GBSocketInterface, GermanySocketInterface {
  @Override
  public void powerWithThreeFlat() {
    System.out.println("国标:使用三项扁头插孔供电");
  }

  @Override
  public void powerWithTwoRound() {
    System.out.println("德国标准:使用两项圆头的插孔供电");
  }
}
