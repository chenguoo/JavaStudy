package com.cheney.study.designpatterns.factory.abstractfactory;

import com.cheney.study.designpatterns.factory.IPhone;

/**
 * Description: TODO
 *
 * @author Cheney
 * @date 2019/3/18/018 11:39
 */
public interface IPhoneFactory {

    IPhone createIPhone();//创建手机

    IPhoneLine createIPhoneLine();//创建数据连接线(假设数据线不一样)

    IPhoneBox createIPhoneBox();//创建包装盒
}
