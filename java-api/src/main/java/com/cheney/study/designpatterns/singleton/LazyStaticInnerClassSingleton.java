package com.cheney.study.designpatterns.singleton;

/**
 * 懒汉模式(静态内部类)：延迟加载，第一次使用时被创建,
 * 好处：1.当调用静态方法或者静态成员时，不会被创建；
 *      2.没有加锁。
 * 坏处:有可能被反射攻击
 */
public class LazyStaticInnerClassSingleton {

    private LazyStaticInnerClassSingleton() {
        //防止反射攻击
        if (SingletonHolder.instance != null) {
            throw new RuntimeException("不允许创建多个实例");
        }


        System.out.println("LazyStaticInnerClassSingleton is create!");
    }

    /**
     * 静态内部类
     */
    private static class SingletonHolder {
        private static final LazyStaticInnerClassSingleton instance = new LazyStaticInnerClassSingleton();
    }

    /**
     * 只有调用这个方法时才会被创建实例对象
     * @return
     */
    public static LazyStaticInnerClassSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
