package com.cheney.study.designpatterns.adapter;

/**
 * 说明:
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 14:24
 */
public class ImplementSocketTest {

  public static void main(String[] args) {

    //1.我去德国旅游，带去的充电器是万能充电器
    Object universalSocket = new ImplementSocketAdapter();

    //2.来到德国后， 找到一家德国宾馆住下 (这个宾馆还是上面代码中的宾馆，使用的依然是德国标准的插口)
    GermanyHotel hotel = new GermanyHotel();

    //3.由于我带的是万能充电器于是我就找到德国标准插口
    GermanySocketInterface socket =(GermanySocketInterface)universalSocket;

    //4.再将适配器的下端插入宾馆里的插座上
    hotel.setSocket(socket);
//    hotel.setSocket((GermanySocketInterface)universalSocket);

    //5.可以在宾馆中使用适配器进行充电了
    hotel.charge();

  }
}
