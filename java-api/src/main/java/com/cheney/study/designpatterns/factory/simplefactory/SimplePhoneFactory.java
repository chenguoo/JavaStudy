package com.cheney.study.designpatterns.factory.simplefactory;


import com.cheney.study.designpatterns.factory.IPhone;

/**
 * Description: TODO
 *
 * @author Cheney
 * @date 2019/3/18/018 10:55
 */
public class SimplePhoneFactory {

    /*
    public IPhone create(String name){
        if ("IPhone8".equalsIgnoreCase(name)) {
            return new IPhone8();
        } else if ("IPhoneXR".equalsIgnoreCase(name)) {
            return new IPhoneXR();
        }else{
            return null;
        }
    }*/
/*
    public IPhone create(String className) {
        try {
            if (!(null == className || "".equals(className))) {
                return (IPhone) Class.forName(className).newInstance();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    */
    public IPhone create(Class<? extends IPhone> clazz){
        try {
            if (null != clazz) {
                return clazz.newInstance();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
