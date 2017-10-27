package com.cheney.study.designpatterns.adapter;

/**
 * 说明:
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 13:55
 */
public class GBSocket implements GBSocketInterface {
  @Override
  public void powerWithThreeFlat() {
    System.out.println("国标:使用三项扁头插孔供电");
  }
}
