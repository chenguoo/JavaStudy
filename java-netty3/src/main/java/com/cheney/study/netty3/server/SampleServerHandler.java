package com.cheney.study.netty3.server;

import org.jboss.netty.channel.*;

public class SampleServerHandler extends SimpleChannelHandler {


    public SampleServerHandler() {
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
        /*
        ChannelBuffer message = (ChannelBuffer)e.getMessage();
        String s =  new String(message.array());*/

        /*添加new StringDecoder()后可以直接接受String*/
        String s = (String) e.getMessage();

        System.out.println("接收到消息:" + s);

        //回写数据
        /*ChannelBuffer channelBuffer = ChannelBuffers.copiedBuffer("hi".getBytes());
        ctx.getChannel().write(channelBuffer);*/

        //添加new StringEncoder()后可以直接返回String
        ctx.getChannel().write("嗨! 您好! 您输入的信息是:" + s);
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
