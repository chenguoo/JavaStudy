package com.cheney.study.designpatterns.decorator;

public class Test {

    public static void main(String[] args) {
        Battercake battercake = new BaseBattercake();
        // + 一个鸡蛋
        battercake = new EggDecorator(battercake);
        // * 一个香肠
        battercake = new SauageDecorator(battercake);

        System.out.println(battercake.getMsg()+", 价格："+battercake.getPrice());
    }
}
