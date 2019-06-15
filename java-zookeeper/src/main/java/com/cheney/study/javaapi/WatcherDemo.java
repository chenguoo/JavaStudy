package com.cheney.study.javaapi;

import com.cheney.study.ZkConstants;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Description: TODO
 *
 * @author Cheney
 * @date 2018/9/27/027 14:42
 */
public class WatcherDemo {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(ZkConstants.CONNECT_STRING, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getType() + "->" + watchedEvent.getPath());
                //如果当前的连接状态是连接     成功的，那么通过计数器去控制
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                    System.out.println(watchedEvent.getState());
                }
            }
        });
        countDownLatch.await();
        System.out.println(zooKeeper.getState());
        zooKeeper.create("/zk-persis-mic", "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //通过exists绑定事件
        Stat stat = zooKeeper.exists("/zk-persis-mic", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getType() + "->" + watchedEvent.getPath());
                try {
                    zooKeeper.exists(watchedEvent.getPath(), true);//使用的是全局的watcher
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //通过修改的事务类型操作来触发监听事件
         stat = zooKeeper.setData("/zk-persis-mic", "2".getBytes(), stat.getVersion());

        TimeUnit.SECONDS.sleep(1);

        zooKeeper.delete("/zk-persis-mic", stat.getVersion());
        System.in.read();









        zooKeeper.close();
    }
}
