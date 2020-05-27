package com.cheney.study.designpatterns.factory.factorymethod;


import com.cheney.study.designpatterns.factory.IPhone;
import com.cheney.study.designpatterns.factory.IPhoneXR;

/**
 * Description: TODO
 *
 * @author Cheney
 * @date 2019/3/18/018 11:22
 */
public class IPhoneXrFactory implements IPhoneFactory {
    @Override
    public IPhone create() {
        return new IPhoneXR();
    }
}
