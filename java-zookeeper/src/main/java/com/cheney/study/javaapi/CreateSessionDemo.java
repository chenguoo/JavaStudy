package com.cheney.study.javaapi;

import com.cheney.study.ZkConstants;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class CreateSessionDemo {
  private static CountDownLatch countDownLatch = new CountDownLatch(1);

  public static void main(String[] args) throws IOException, InterruptedException {
    ZooKeeper zooKeeper = new ZooKeeper(ZkConstants.CONNECT_STRING, 5000, new Watcher() {
      public void process(WatchedEvent watchedEvent) {
        //如果当前的连接状态是连接成功的，那么通过计数器去控制
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
          countDownLatch.countDown();
          System.out.println(watchedEvent.getState());
        }
      }
    });
    countDownLatch.await();
    System.out.println(zooKeeper.getState());
  }
}
