package com.cheney.study.designpatterns.future.custom;

public class RealData {
    protected final String result;

    public RealData(String para) {
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
        result = stringBuffer.toString();
    }

    public String getResult() {
        return result;
    }
}
