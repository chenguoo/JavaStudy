package com.cheney.study.mq.activemq.spring;

/**
 * Description: 生产者的接口
 *
 * @author Cheney
 * @date 2019/6/15/015 11:09
 */
public interface ProduceService {
    void sendMessage(String msg);
}
