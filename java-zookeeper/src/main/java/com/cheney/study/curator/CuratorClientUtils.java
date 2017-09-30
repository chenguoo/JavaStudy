package com.cheney.study.curator;

import com.cheney.study.ZkConstants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class CuratorClientUtils {

    private static CuratorFramework curatorFramework;


    public static CuratorFramework getInstance(){
        curatorFramework= CuratorFrameworkFactory.
                newClient(ZkConstants.CONNECT_STRING,5000,5000,
                        new ExponentialBackoffRetry(1000,3));
        curatorFramework.start();
        return curatorFramework;
    }
}
