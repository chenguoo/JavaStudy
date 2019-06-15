package com.cheney.study.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TankMsgServer{
  private String              host;
  private int                 port;
  private Selector            selector;
  private ServerSocketChannel serverSocketChannel;
  private boolean             isRun = true;

  public TankMsgServer(String host,int port) {
    this.host = host;
    this.port = port;
    try {
      selector = Selector.open();
      serverSocketChannel = ServerSocketChannel.open();
      //绑定端口
      serverSocketChannel.socket().bind(new InetSocketAddress(host, port));
      //设置成非阻塞
      serverSocketChannel.configureBlocking(false);
      //开启监听...
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      System.out.println("Tank Server -> server is ranning...");

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public void start() {
    while (isRun) {
      try {
        selector.select(1000);
        //请求在nio中对应的一个key,内核通知给selecter.
        //selecter可以拿到各种通知Key
        Set<SelectionKey> selectedKeys = selector.selectedKeys();

        Iterator<SelectionKey> iterator = selectedKeys.iterator();
        SelectionKey selectionKey;
        while (iterator.hasNext()) {
          selectionKey = iterator.next();
          iterator.remove();
          //判断这个事件是否还有效
          if (selectionKey.isValid()) {

            //判断处理连接事件
            if (selectionKey.isAcceptable()) {
              //获取客户端连接通道
              ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey
                  .channel();
              //相应客户端,建立连接
              SocketChannel socketChannel = serverSocketChannel.accept();
              socketChannel.configureBlocking(false);
              //连接建立完成
              socketChannel.register(selector, SelectionKey.OP_READ);

            }

            //处理数据读取事件
            if (selectionKey.isReadable()) {
              SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
              ByteBuffer readBuffer = ByteBuffer.allocate(1024);
              int readLength = socketChannel.read(readBuffer);

              if (readLength > 0) {
                //为了保证tcp socket包完整.
                readBuffer.flip();
                //返回剩余可用长度,及实际的数据长度.
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);

                String requet = new String(bytes, "utf-8");
                System.out.println("Tank Server ->request body :" + requet);
                
                
              }
            }
          }
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
