package com.cheney.study.designpatterns.adapter;

/**
 * 说明:德国插座
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 13:50
 */
public class GermanySocket implements GermanySocketInterface {
  @Override
  public void powerWithTwoRound() {
    System.out.println("德国标准:使用两项圆头的插孔供电");
  }
}
