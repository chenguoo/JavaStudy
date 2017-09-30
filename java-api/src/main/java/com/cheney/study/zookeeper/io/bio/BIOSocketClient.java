package com.cheney.study.zookeeper.io.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Description:
 * @Date: Created in 2017-09-27 10:41
 * @Author: Cheney
 * @Modified by:
 */
public class BIOSocketClient {


  public static void main(String[] args) {
    try {
      Socket socket = new Socket("localhost",8888);
      PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
      writer.println("hello BIO!");
      writer.close();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
