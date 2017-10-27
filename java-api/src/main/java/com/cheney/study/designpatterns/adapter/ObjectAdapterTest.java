package com.cheney.study.designpatterns.adapter;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;

/**
 * 说明:对象适配器测试
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 13:44
 */
public class ObjectAdapterTest {


  public static void main(String[] args) {

    //1.我去德国旅游，带去的充电器是国标的（可以将这里的GBSocket看成是充电器）
    GBSocketInterface gbSocket = new GBSocket();

    //2.来到德国后， 找到一家德国宾馆住下 (这个宾馆还是上面代码中的宾馆，使用的依然是德国标准的插口)
    GermanyHotel hotel = new GermanyHotel();

    //3.由于没法充电，我拿出随身带去的适配器，并且将我带来的充电器插在适配器的上端插孔中。
    // 这个上端插孔是符合国标的，我的充电器完全可以插进去。
    GermanySocketInterface objectGermanySocketAdapter = new ObjectGermanySocketAdapter(gbSocket);

    //4.再将适配器的下端插入宾馆里的插座上
    hotel.setSocket(objectGermanySocketAdapter);

    //5.可以在宾馆中使用适配器进行充电了
    hotel.charge();

  }
}
