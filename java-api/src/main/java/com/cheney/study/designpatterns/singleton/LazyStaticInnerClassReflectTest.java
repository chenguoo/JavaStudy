package com.cheney.study.designpatterns.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Description: 静态内部类单例模式被反射攻击测试
 *
 * @author Cheney
 * @date 2019/3/18/018 16:08
 */
public class LazyStaticInnerClassReflectTest {
    public static void main(String[] args) {
        try {
            Class clazz = LazyStaticInnerClassSingleton.class;
            Constructor constructor = clazz.getDeclaredConstructor(null);
            constructor.setAccessible(true);//强吻

            Object o1 = constructor.newInstance();

            Object o2 = LazyStaticInnerClassSingleton.getInstance();
            System.out.println(o1 == o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
