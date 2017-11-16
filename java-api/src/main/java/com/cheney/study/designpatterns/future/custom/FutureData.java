package com.cheney.study.designpatterns.future.custom;

public class FutureData implements Data {
    protected RealData realData = null;//FutureData时RealData的包装类
    protected boolean isReady = false;

    public synchronized void setReadData(RealData readData) {
        if (isReady) {
            return;
        }
        this.realData = readData;
        isReady =true;
        super.notifyAll();//RealData已经被注入，通知getResult();
    }
    @Override
    public synchronized String getResult() { //会等待RealData构造完成
        while (!isReady) {
            try {
                super.wait();//一直等待，直到RealData被注入
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;//由RealData实现
    }
}
