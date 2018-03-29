package com.cheney.study.designpatterns.future.custom;

public class Client {

    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        //RealData构造很慢，所以这里在单独的线程中进行
        new Thread(() -> {
            RealData realData = new RealData(queryStr);
            futureData.setReadData(realData);
        }).start();
        return futureData;//FutureData被立即返回
    }

    public static void main(String[] args) {
        Client client = new Client();
        //这里会立即返回，因为得到的是FutureData而不是RealData
        Data data = client.request("费时操作... ");
        System.out.println("请求完毕！");
        try {
            //这里用sleep替代了对其他业务的逻辑处理消耗的时间；
            //在处理这些业务逻辑的过程中,RealData被创建，从而充分利用了等待时间
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使用真实的数据
        System.out.println("数据：" + data.getResult());
    }
}
