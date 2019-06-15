package com.cheney.study.javaapi;

import com.cheney.study.ZkConstants;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 说明: zookeeper 基本增删改查
 * @author  Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-09-30 11:16
 */
public class ApiOperatorDemo implements Watcher {

  private static CountDownLatch countDownLatch = new CountDownLatch(1);
  private static ZooKeeper zookeeper;
  private static Stat stat = new Stat();

  public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
    zookeeper = new ZooKeeper(ZkConstants.CONNECT_STRING, 5000, new ApiOperatorDemo());
    countDownLatch.await();
        /*ACL acl=new ACL(ZooDefs.Perms.ALL,new Id("ip","192.168.11.129"));
        List<ACL> acls=new ArrayList<>();
        acls.add(acl);
//        zookeeper.create("/authTest","111".getBytes(),acls,CreateMode.PERSISTENT);
        zookeeper.getData("/authTest",true,new Stat());*/

    System.out.println(zookeeper.getState());

    String path = "/zk-persis-mic";

    //创建临时节点，会话结束后会清除
    String result = zookeeper.create(path, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    //zookeeper.getData("/node1",new ZkClientApiOperatorDemo(),stat); //增加一个
    System.out.println("创建临时节点成功：" + result);
    Stat stat = new Stat();
    //获取数据
    byte[] data = zookeeper.getData(path, null, stat);
    System.out.println(new String(data));

    //修改数据
    zookeeper.setData(path, "1".getBytes(), stat.getVersion());
    Thread.sleep(1000);
    //获取数据
    data = zookeeper.getData(path, null, stat);
    Thread.sleep(1000);
    System.out.println(new String(data));

    zookeeper.delete(path, stat.getVersion());

    //创建节点和子节点
    path = "/node_test";

    zookeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    TimeUnit.SECONDS.sleep(1);

    stat = zookeeper.exists(path + "/node1", true);
    if (stat == null) {//表示节点不存在
      zookeeper.create(path + "/node1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
      TimeUnit.SECONDS.sleep(1);
    }
    //修改子路径
    zookeeper.setData(path + "/node1", "321".getBytes(), -1);
    TimeUnit.SECONDS.sleep(1);


    //获取指定节点下的子节点
    List<String> children = zookeeper.getChildren(path, true);

    System.out.println("获取指定节点下的子节点:"+children);


    //删除节点
    zookeeper.delete(path + "/node1", -1);
    TimeUnit.SECONDS.sleep(2);

    zookeeper.close();
  }

  @Override
  public void process(WatchedEvent watchedEvent) {
    System.out.println("Watcher事件："+watchedEvent.getType());

    //如果当前的连接状态是连接成功的，那么通过计数器去控制
    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
      if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
        countDownLatch.countDown();
        System.out.println(watchedEvent.getState() + "-->" + watchedEvent.getType());
      } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
        try {
          System.out.println("数据变更触发路径：" + watchedEvent.getPath() + "->改变后的值：" +
            new String(zookeeper.getData(watchedEvent.getPath(), true, stat)));
        } catch (KeeperException | InterruptedException e) {
          e.printStackTrace();
        }
      } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {//子节点的数据变化会触发
        try {
          System.out.println("子节点数据变更：" + watchedEvent.getPath() + "->节点的值：" +
            new String(zookeeper.getData(watchedEvent.getPath(), true, stat)));
        } catch (KeeperException | InterruptedException e) {
          e.printStackTrace();
        }
      } else if (watchedEvent.getType() == Event.EventType.NodeCreated) {//创建子节点的时候会触发
        try {
          System.out.println("子节点创建路径：" + watchedEvent.getPath() + "->节点的值：" +
            new String(zookeeper.getData(watchedEvent.getPath(), true, stat)));
        } catch (KeeperException | InterruptedException e) {
          e.printStackTrace();
        }
      } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {//子节点删除会触发
        System.out.println("节点删除路径：" + watchedEvent.getPath());
      }

    }

  }
}
