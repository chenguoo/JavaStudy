package com.cheney.study.netty5.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MultClient {
    /**
     * 服务类
     */
    private Bootstrap bootstrap = new Bootstrap();
    /**
     * 会话
     */
    private List<Channel> channels = new ArrayList<>();
    /**
     * 引用计数器
     */
    private final AtomicInteger index = new AtomicInteger();


    public void init(int count) {
        //worker
        NioEventLoopGroup worker = new NioEventLoopGroup();

        //设置线程池
        bootstrap.group(worker);
        //设置socket工厂
        bootstrap.channel(NioSocketChannel.class);
        //设置管道工厂
        bootstrap.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) {
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new StringEncoder());
                ch.pipeline().addLast(new ClientHandler());
            }
        });
        for (int i = 0; i < count; i++) {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 10101);
            channels.add(future.channel());
        }
    }


    public Channel nextChannel() {
        return getFirstActiveChannel(0);
    }

    private Channel getFirstActiveChannel(int count) {
        Channel channel = channels.get(Math.abs(index.getAndIncrement() % channels.size()));
        if (!channel.isActive()) {
            //重连
            reconnect(channel);

            if (count >= channels.size()) {
                throw new RuntimeException("no can use channel!");
            }
            channel = getFirstActiveChannel(count + 1);
        }

        return channel;
    }

    private void reconnect(Channel channel) {
        synchronized (channel) {
            if (channels.indexOf(channel) == -1) {
                return;
            }
            Channel newChannel = bootstrap.connect("127.0.0.1", 10101).channel();
            channels.set(channels.indexOf(channel), newChannel);
        }
    }
}
