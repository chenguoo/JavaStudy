package com.cheney.study.designpatterns.decorator;

public class SauageDecorator extends BattercakeDecorator {

    public SauageDecorator(Battercake battercake) {
        super(battercake);
    }

    protected String getMsg() {
        return "香肠 " + super.getMsg();
    }

    public int getPrice() {
        return super.getPrice() + 2;
    }

}
