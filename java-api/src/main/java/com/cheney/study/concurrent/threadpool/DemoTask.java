package com.cheney.study.concurrent.threadpool;


/**
 * 说明：测试任务类
 *
 * @author Cheney <br>
 * modified by:
 * @version 1.0 <br>
 * Created in 2017-09-27 10:41
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
