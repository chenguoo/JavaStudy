package com.cheney.study.designpatterns.future.jdk;

import java.util.concurrent.Callable;

public class RealData implements Callable {
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public Object call() throws Exception {
        //RealData的构造是很慢的，需要用户等待很久，这里使用了sleep来模拟
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            stringBuffer.append(para);
            try {
                Thread.sleep(100);//模拟一个很慢的操作过程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
}
