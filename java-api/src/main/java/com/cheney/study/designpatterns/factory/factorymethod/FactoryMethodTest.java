package com.cheney.study.designpatterns.factory.factorymethod;


import com.cheney.study.designpatterns.factory.IPhone;

/**
 * Description: TODO
 *
 * @author Cheney
 * @date 2019/3/18/018 11:23
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        IPhoneFactory factory = new IPhone8Factory();
        IPhone iPhone = factory.create();
        iPhone.playMusic();

    }
}
