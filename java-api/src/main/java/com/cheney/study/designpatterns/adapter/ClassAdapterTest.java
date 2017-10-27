package com.cheney.study.designpatterns.adapter;

/**
 * 说明:对象适配器测试
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 13:44
 */
public class ClassAdapterTest {


  public static void main(String[] args) {

    //1.我去德国旅游，带去的充电器是国标的（可以将这里的GBSocket看成是充电器）和德国标准的适配器
    GermanySocketInterface classGermanySocketAdapter = new ClassGermanySocketAdapter();

    //2.来到德国后， 找到一家德国宾馆住下 (这个宾馆还是上面代码中的宾馆，使用的依然是德国标准的插口)
    GermanyHotel hotel = new GermanyHotel();

    //3.再将适配器的下端插入宾馆里的插座上
    hotel.setSocket(classGermanySocketAdapter);

    //4.可以在宾馆中使用适配器进行充电了
    hotel.charge();

  }
}
