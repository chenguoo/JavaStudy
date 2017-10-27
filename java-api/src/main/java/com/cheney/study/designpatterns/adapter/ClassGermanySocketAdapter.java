package com.cheney.study.designpatterns.adapter;

/**
 * 说明:德国插座适配器(类适配器模式)(使用不多)
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 13:56
 */
public class ClassGermanySocketAdapter extends GBSocket implements GermanySocketInterface {

  @Override
  public void powerWithTwoRound() {
    System.out.println("德国适配器转换");
    super.powerWithThreeFlat();
  }
}
