package com.cheney.study.thread.threadpool;


import java.util.concurrent.*;

/**
 *demo启动器
 */
public class DemoExecutor {
  /**
   * demo启动器主方法
   * @param args 系统参数
   */
  public static void main(String[] args) {
    Integer threadCounter = 0;
    BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(50);

    CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor(10,
      20, 5000, TimeUnit.MILLISECONDS, blockingQueue);
    /**
     * 当加入的DemoTask超过ThreadPoolExecutor最大处理能力(maximumPoolSize:20)与队列(blockingQueue)最大允许数(capacity:50)时,
     * 可以预计在Adding DemoTask : 71时会发生;
     * 捕获RejectedExecutionException(拒绝执行异常):
     */
    executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
      /**
       * 捕获RejectedExecutionException,并等待一段时间后再加入队列中执行...
       * @param r
       * @param executor
       */
      @Override
      public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("DemoTask Rejected : "
          + ((DemoTask) r).getName());
        System.out.println("Waiting for a second !!");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Lets add another time : "
          + ((DemoTask) r).getName());
        executor.execute(r);
      }
    });

    // Let start all core threads initially
    //启动所有核心线程, 等待工作。这将覆盖仅在执行新任务时启动核心线程的默认策略。
    executor.prestartAllCoreThreads();
    //连续添加100个DemoTask到线程池中处理。
    while (true) {
      threadCounter++;
      // Adding threads one by one
      System.out.println("Adding DemoTask : " + threadCounter);
      executor.execute(new DemoTask(threadCounter.toString()));

      if (threadCounter == 100)
        break;
    }
  }
}
