package com.cheney.study.designpatterns.factory;

/**
 * Description:
 *
 * @author Cheney
 * @date 2019/3/18/018 10:48
 */
public class IPhone8 implements IPhone{

    @Override
    public void playMusic() {
        System.out.println("Iphone8 开始播放音乐");
    }
}
