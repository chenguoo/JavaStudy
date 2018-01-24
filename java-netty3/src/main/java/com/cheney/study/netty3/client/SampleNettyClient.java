package com.cheney.study.netty3.client;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试客户端连接
 */
public class SampleNettyClient {

    public static void main(String[] args) {
        //服务端

        ClientBootstrap clientBootstrap = new ClientBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //socket 工厂
        clientBootstrap.setFactory(new NioClientSocketChannelFactory(boss, worker));

        //管道工厂
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();

                //接受数据编码为String
                pipeline.addLast("decoder", new StringDecoder());
                //发出数据编码为Byte Array
                pipeline.addLast("encoder", new StringEncoder());
                //业务Handler
                pipeline.addLast("hiHandler", new SampleClientHandler());
                return pipeline;
            }
        });


        //连接服务器
        ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 10101));
        Channel channel = connect.getChannel();

        System.out.println("client start:");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入:");
            channel.write(scanner.next());
        }
    }
}
