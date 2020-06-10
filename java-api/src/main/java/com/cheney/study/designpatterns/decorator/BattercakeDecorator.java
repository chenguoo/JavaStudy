package com.cheney.study.designpatterns.decorator;

public class BattercakeDecorator extends Battercake{
    private final Battercake battercake;

    public BattercakeDecorator(Battercake battercake) {
        this.battercake = battercake;
    }

    protected String getMsg(){return battercake.getMsg();}

    public int getPrice(){return battercake.getPrice();}

}
