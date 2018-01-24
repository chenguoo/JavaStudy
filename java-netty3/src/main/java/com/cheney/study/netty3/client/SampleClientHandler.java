package com.cheney.study.netty3.client;

import org.jboss.netty.channel.*;

/**
 * @author Cheney
 */
public class SampleClientHandler extends SimpleChannelHandler {


    public SampleClientHandler() {
        super();
    }

    /**
     * 连接成功触发
     *
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }

    /**
     * 必须市已建立连接的用户断开连接时才会触发
     *
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    /**
     * 断开连接触发 无论连接是否成功都会调用关闭资源
     *
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }

    /**
     * 收到消息触发
     *
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("messageReceived");

        /*添加new StringDecoder()后可以直接接受String*/
        String s = (String) e.getMessage();

        System.out.println(s);

        super.messageReceived(ctx, e);
    }

    /**
     * 发生异常触发
     *
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }
}
