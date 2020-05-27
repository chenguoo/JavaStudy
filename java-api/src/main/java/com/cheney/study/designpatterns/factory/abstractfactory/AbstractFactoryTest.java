package com.cheney.study.designpatterns.factory.abstractfactory;

/**
 * Description: TODO
 *
 * @author Cheney
 * @date 2019/3/18/018 11:49
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        IPhoneFactory factory = new IPhone8Factory();
        factory.createIPhone().playMusic();
        factory.createIPhoneBox();
        factory.createIPhoneLine();
    }
}
