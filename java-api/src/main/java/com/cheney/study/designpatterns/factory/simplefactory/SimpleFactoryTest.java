package com.cheney.study.designpatterns.factory.simplefactory;


import com.cheney.study.designpatterns.factory.IPhone;
import com.cheney.study.designpatterns.factory.IPhone8;
import com.cheney.study.designpatterns.factory.IPhoneXR;

/**
 * Description: TODO
 *
 * @author Cheney
 * @date 2019/3/18/018 11:00
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {

        SimplePhoneFactory factory = new SimplePhoneFactory();
        IPhone iPhone = factory.create(IPhone8.class);
        iPhone.playMusic();
        iPhone = factory.create(IPhoneXR.class);
        iPhone.playMusic();

    }


}
