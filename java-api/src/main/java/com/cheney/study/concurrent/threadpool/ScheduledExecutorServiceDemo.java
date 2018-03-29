package com.cheney.study.concurrent.threadpool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        //固定间隔执行任务：固定间隔包括了任务的执行时间，当任务执行时间大于固定间隔时，这当任务执行完后就立即执行下一次任务。
        /*scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 2, TimeUnit.SECONDS);
*/
        //固定延迟执行任务，任务完成后在等待固定延迟时间间隔后再执行下一个任务。（任务与任务有固定时间间隔）
        System.out.println("2秒(initialDelay)后开始执行：" + System.currentTimeMillis() / 1000);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "开始任务：" + System.currentTimeMillis() / 1000);
                //模拟任务执行不同的时间
                Thread.sleep(Math.abs(new Random().nextInt(10) * 1000));
                System.out.println(Thread.currentThread().getName() + "结束任务：" + System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 2, 2, TimeUnit.SECONDS);
    }

}
