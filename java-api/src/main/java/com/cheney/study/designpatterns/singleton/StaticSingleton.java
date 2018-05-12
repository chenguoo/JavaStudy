package com.cheney.study.designpatterns.singleton;

/**
 * 懒汉模式2：延迟加载，第一次使用时被创建,
 * 好处：1.当调用静态方法或者时静态成员时，不会被创建；
 *      2.没有加锁。
 */
public class StaticSingleton {

    private StaticSingleton() {
        System.out.println("StaticSingleton is create!");
    }

    /**
     * 静态内部类
     */
    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    /**
     * 只有调用这个方法时才会被创建实例对象
     * @return
     */
    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
