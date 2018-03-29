package com.cheney.study.netty3.heart;

import com.cheney.study.netty3.server.SampleServerHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HeartNettyServer {

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));

        final HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();

        bootstrap.setPipelineFactory(() -> {
            ChannelPipeline pipeline = Channels.pipeline();

            pipeline.addLast("idle", new IdleStateHandler(hashedWheelTimer,
                    5, 5, 10));
            //接受数据编码为String
            pipeline.addLast("decoder", new StringDecoder());
            //发出数据编码为Byte Array
            pipeline.addLast("encoder", new StringEncoder());
            //业务Handler
            pipeline.addLast("helloHandler", new HelloServerHandler());
            return pipeline;
        });

        bootstrap.bind(new InetSocketAddress(10101));
        System.out.println("start!");
    }
}
