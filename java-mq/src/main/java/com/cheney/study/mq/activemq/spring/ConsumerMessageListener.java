package com.cheney.study.mq.activemq.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Description: 消息监听器
 *
 * @author Cheney
 * @date 2019/6/15/015 11:14
 */
public class ConsumerMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("获取消息：" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
