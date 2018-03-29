package com.cheney.study.netty3.heart;

import org.jboss.netty.channel.*;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloServerHandler extends IdleStateAwareChannelHandler implements ChannelHandler {
    @Override
    public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {
//        super.channelIdle(ctx, e);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss");
        System.out.println(e.getState() + " " + simpleDateFormat.format(new Date()));


        if (((IdleStateEvent) e).getState() == IdleState.ALL_IDLE) {
            System.out.println("踢玩家下线!");
            //关闭会话,踢玩家下线
            ChannelFuture write = ctx.getChannel().write("time out ,you will close!");
            write.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    ctx.getChannel().close();
                }
            });
        } else {
            ctx.sendUpstream(e);
        }


    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        super.messageReceived(ctx, e);


        System.out.println(e.getMessage());
    }

}
