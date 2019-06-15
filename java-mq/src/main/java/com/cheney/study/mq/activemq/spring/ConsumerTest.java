package com.cheney.study.mq.activemq.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description: 消费者的测试
 *
 * @author Cheney
 * @date 2019/6/15/015 11:14
 */
public class ConsumerTest {
    public static void main(String[] args) {
        //启动消费者
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("consumer.xml");
    }
}
