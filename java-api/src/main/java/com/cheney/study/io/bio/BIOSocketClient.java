package com.cheney.study.io.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * 说明：测试BIOSocketClient
 *
 * @author Cheney <br/>
 * modified by:
 * @version 1.0 <br/>
 * Created in 2017-09-30 16:45
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
