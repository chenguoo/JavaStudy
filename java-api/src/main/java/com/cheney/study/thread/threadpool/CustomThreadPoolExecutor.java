package com.cheney.study.thread.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 说明：自定义线程池
 *
 * 线程池提供了两个优秀的方法，我会强烈建议重写即beforeexecute()和afterexecute()方法。
 * 他们提供了很好的处理在执行要执行runnable对象生命周期。让我们看看在我们的customthreadpoolexecutor以上方法。
 *
 * @author Cheney <br>
 * modified by:
 * @version 1.0 <br>
 * Created in 2017-09-29 17:32
 */
public class CustomThreadPoolExecutor extends ThreadPoolExecutor {

  public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                  long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }

  @Override
  protected void beforeExecute(Thread t, Runnable r) {
    super.beforeExecute(t, r);
    System.out.println("Perform beforeExecute() logic");
  }

  @Override
  protected void afterExecute(Runnable r, Throwable t) {
    super.afterExecute(r, t);
    if (t != null) {
      System.out.println("Perform exception handler logic");
    }
    System.out.println("Perform afterExecute() logic");
  }

}
