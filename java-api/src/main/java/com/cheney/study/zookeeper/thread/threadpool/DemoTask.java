package com.cheney.study.zookeeper.thread.threadpool;

/**
 * @Description: 任务
 * @Date: Created in 2017-09-29 17:31
 * @Author: Cheney
 * @Modified by:
 */
public class DemoTask implements Runnable{
  private String name = null;

  public DemoTask(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Executing : " + name);
  }
}
