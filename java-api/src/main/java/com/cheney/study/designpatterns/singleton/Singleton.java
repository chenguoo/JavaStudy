package com.cheney.study.designpatterns.singleton;

/**
 * 恶汉模式
 * 最简单的实现方式：
 * 问题:不能控制何时被创建，及没有使用到实例时也会被创建（如只获取STATUS时）
 */
public class Singleton {

    public static int STATUS = 1;

    private Singleton() {
        System.out.println("Singleton is create!");
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}
