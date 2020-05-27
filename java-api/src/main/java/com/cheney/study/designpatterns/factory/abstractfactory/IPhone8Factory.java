package com.cheney.study.designpatterns.factory.abstractfactory;


import com.cheney.study.designpatterns.factory.IPhone;
import com.cheney.study.designpatterns.factory.IPhone8;

/**
 * Description: TODO
 *
 * @author Cheney
 * @date 2019/3/18/018 11:45
 */
public class IPhone8Factory implements IPhoneFactory {
    @Override
    public IPhone createIPhone() {
        System.out.println("生产IPhone8");
        return new IPhone8();
    }

    @Override
    public IPhoneLine createIPhoneLine() {
        System.out.println("生产IPhone8的数据线(假设数据线不一样)");
        return new IPhone8Line();
    }

    @Override
    public IPhoneBox createIPhoneBox() {
        System.out.println("生产IPhone8的包装盒");
        return new IPhone8Box();
    }
}
