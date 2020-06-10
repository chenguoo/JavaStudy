package com.cheney.study.designpatterns.decorator;

public class EggDecorator extends BattercakeDecorator {

    public EggDecorator(Battercake battercake) {
        super(battercake);
    }

    protected String getMsg() {
        return "鸡蛋 " + super.getMsg();
    }

    public int getPrice() {
        return super.getPrice() + 2;
    }

}
