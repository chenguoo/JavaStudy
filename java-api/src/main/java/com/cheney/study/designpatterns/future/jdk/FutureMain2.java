package com.cheney.study.designpatterns.future.jdk;

import java.util.concurrent.*;

public class FutureMain2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //构造FutureTask
//        FutureTask<String> futureTask = new FutureTask<String>(new RealData("费时操作！ "));
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        //执行FutTask，相当于上例中的clent.request()发送请求
        //这里开启线程进行RealData的call()执行
        Future futureTask = executorService.submit(new RealData("费时操作！ "));//直接传入实现了Callable接口的耗时操作类

        System.out.println("请求完毕！");

        //这里依然可以做额外的数据操作，这里使用sleep代替其他业务逻辑的处理
        Thread.sleep(2000);

        //相当于data.getResult(),取得call()方法的返回值
        //如果此时call()方法没有执行完成，依然会等待
        System.out.println("数据：" + futureTask.get());
    }
}
