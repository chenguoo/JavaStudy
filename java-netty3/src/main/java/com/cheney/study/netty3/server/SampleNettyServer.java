package com.cheney.study.netty3.server;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SampleNettyServer {

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();

                //接受数据编码为String
                pipeline.addLast("decoder", new StringDecoder());
                //发出数据编码为Byte Array
                pipeline.addLast("encoder",new StringEncoder());
                //业务Handler
                pipeline.addLast("helloHandler", new SampleServerHandler());
                return pipeline;
            }
        });

        bootstrap.bind(new InetSocketAddress(10101));
        System.out.println("start!");
    }
}
