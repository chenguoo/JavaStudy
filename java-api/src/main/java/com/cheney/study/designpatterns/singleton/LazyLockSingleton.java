package com.cheney.study.designpatterns.singleton;

/**
 * 懒汉模式1：延迟加载，第一次使用时被创建
 * 问题:多线程时有问题，不高效
 */
public class LazyLockSingleton {

    private LazyLockSingleton() {
        System.out.println("LazyLockSingleton is create!");
    }

    private static LazyLockSingleton instance = null;

    /**
     * 多线程不高效，每次进入都会加锁
     * @return
     */
    public static synchronized LazyLockSingleton getInstance() {
        if (instance == null) {
            instance = new LazyLockSingleton();
        }
        return instance;
    }

}
