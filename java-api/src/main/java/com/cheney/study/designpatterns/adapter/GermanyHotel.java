package com.cheney.study.designpatterns.adapter;

/**
 * 说明:德国宾馆
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 13:52
 */
public class GermanyHotel {
  //旅馆中有一个德标的插口
  private GermanySocketInterface germanySocket;

  public GermanyHotel() {
  }

  public GermanyHotel(GermanySocket germanySocket) {
    this.germanySocket = germanySocket;
  }

  public void setSocket(GermanySocketInterface socket) {
    germanySocket = socket;
  }

  /**
   * 旅馆中有一个充电的功能
   */
  public void charge() {
    //使用德标插口充电
    germanySocket.powerWithTwoRound();
  }


}
