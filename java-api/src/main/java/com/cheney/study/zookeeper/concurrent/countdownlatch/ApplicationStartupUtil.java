package com.cheney.study.zookeeper.concurrent.countdownlatch;

import com.cheney.study.zookeeper.concurrent.countdownlatch.service.BaseHealthChecker;
import com.cheney.study.zookeeper.concurrent.countdownlatch.service.CacheHealthChecker;
import com.cheney.study.zookeeper.concurrent.countdownlatch.service.DatabaseHealthChecker;
import com.cheney.study.zookeeper.concurrent.countdownlatch.service.NetworkHealthChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ApplicationStartupUtil {
  private static List<BaseHealthChecker> _services;
  private static CountDownLatch _latch;

  private ApplicationStartupUtil() {
  }

  private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();


  public static ApplicationStartupUtil getInstance() {
    return INSTANCE;
  }

  public static boolean checkExternalServices() throws Exception {
    _latch = new CountDownLatch(3);
    _services = new ArrayList<>();
    _services.add(new NetworkHealthChecker(_latch));
    _services.add(new CacheHealthChecker(_latch));
    _services.add(new DatabaseHealthChecker(_latch));

    Executor executor = Executors.newFixedThreadPool(_services.size());

    for (final BaseHealthChecker v : _services) {
      executor.execute(v);
    }

    _latch.await();

    for (final BaseHealthChecker v : _services) {
      if (!v.isServiceUp()) {
        return false;
      }
    }
    return true;
  }


}
