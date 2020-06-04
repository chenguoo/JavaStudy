package com.cheney.study.designpatterns.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 说明: 创建动态代理对象,动态代理不需要实现接口,但是需要指定接口类型
 * <p>
 * 动态代理有以下特点:
 * <p>
 * 1.代理对象,不需要实现接口
 * <p>
 * 2.代理对象的生成,是利用JDK的API,动态的在内存中构建代理对象(需要我们指定创建代理对象/目标对象实现的接口的类型)
 * <p>
 * 3.动态代理也叫做:JDK代理,接口代理(必须实现接口才行)
 * <p>
 * <b>JDK中生成代理对象的API</b>
 * <p>
 * 代理类所在包:{@link java.lang.reflect.Proxy}
 * <p>
 * JDK实现代理只需要使用newProxyInstance方法,但是该方法需要接收三个参数,完整的写法是:
 * {@code static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h )}
 * <br>
 * 注意该方法是在Proxy类中是静态方法,且接收的三个参数依次为:
 * <p>
 * <pre>ClassLoader loader :</pre>指定当前目标对象使用类加载器,获取加载器的方法是固定的
 * <pre>Class<?>[] interfaces :</pre>目标对象实现的接口的类型,使用泛型方式确认类型
 * <pre>{@link InvocationHandler} h :</pre> 事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
 *
 * <p><p>
 * 代码示例:
 * 接口类IUserDao.java以及接口实现类,目标对象UserDao是一样的,没有做修改.
 * <p>
 * 在这个基础上,增加一个代理工厂类(CglibProxyFactory.java),
 * 将代理类写在这个地方,然后在测试类(需要使用到代理的代码)中先建立目标对象和代理对象的联系,然后调用代理对象的中同名方法
 * <p>
 * 总结:
 * <p>
 * 代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用JDK动态代理
 *
 * @author Cheney <br>
 * modified by :
 * @version 1.0 <br>
 * Created in 2017-10-27 10:19
 */
public class ProxyFactory {

/*    //维护一个目标对象
    private T target;

    public ProxyFactory(T target) {
        this.target = target;
    }*/


    //给目标对象生成代理对象
    public static Object getProxyInstance(Object target) {
        Class<?> clazz = target.getClass();
        return  Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getInterfaces(),
                (proxy, method, args) -> {//InvocationHandler
                    System.out.println("JDK动态代理:开始事务");
                    //执行目标对象方法
                    Object returnValue = method.invoke(target, args);
                    System.out.println("JDK动态代理:提交事务");
                    return returnValue;
                }
        );
    }
}
