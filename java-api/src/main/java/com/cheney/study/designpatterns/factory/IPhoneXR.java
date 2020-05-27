package com.cheney.study.designpatterns.factory;

/**
 * Description: 
 *
 * @author Cheney
 * @date 2019/3/18/018 10:48
 */
public class IPhoneXR implements IPhone{

    @Override
    public void playMusic() {
        System.out.println("IphoneXR 开始播放音乐");
    }
}
