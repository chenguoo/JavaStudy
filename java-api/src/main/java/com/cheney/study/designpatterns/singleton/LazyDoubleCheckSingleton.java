package com.cheney.study.designpatterns.singleton;

/**
 * Description: 双重检查锁
 *
 * @author Cheney
 * @date 2019/3/18/018 15:45
 */
public class LazyDoubleCheckSingleton {
    private LazyDoubleCheckSingleton() {
        System.out.println("LazyLockSingleton is create!");
    }

    //volatile 解决指令重排序的问题
    private static volatile LazyDoubleCheckSingleton instance = null;

    /**
     * 多线程不高效，每次进入都会加锁
     */
    public static LazyDoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                instance = new LazyDoubleCheckSingleton();
            }
        }
        return instance;
    }
}
