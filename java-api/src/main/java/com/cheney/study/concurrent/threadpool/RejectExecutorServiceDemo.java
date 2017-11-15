package com.cheney.study.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * 任务过多，拒绝策略demo
 */
public class RejectExecutorServiceDemo {
    public static class MyTesk implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100);//每个任务执行100毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 说明：线程池中有5个线程，每10毫秒提交一个任务，但是每个任务需要执行100毫秒，提交的任务数大于可以用来执行的线程数，部分现在无法执行，
     * 这部分任务就会被拒绝执行，但是可以指定拒绝策略（RejectedExecutionHandler），并处理。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        MyTesk task = new MyTesk();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),//一次只能进入一个任务
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + " is discard!");
                    }
                });
        for (int i = 0 ;i<Integer.MAX_VALUE;i++) {
            executorService.submit(task);
            Thread.sleep(10);//10毫秒提交一个任务
        }
    }

}
