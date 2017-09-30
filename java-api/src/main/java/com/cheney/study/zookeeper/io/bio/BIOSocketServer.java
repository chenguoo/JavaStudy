package com.cheney.study.zookeeper.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:
 * @Date: Created in 2017-09-27 10:41
 * @Author: Cheney
 * @Modified by:
 */
public class BIOSocketServer {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = null;

    try {
      serverSocket = new ServerSocket(8888);
      while(true){
        Socket socket = serverSocket.accept();
        new Thread(()->{
          try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String clientData = null;
            while ((clientData = reader.readLine())!=null) {
              System.out.println("服务器收到数据为:"+clientData);
            }

            writer.println("hello  收到了你的数据 ^_^");
            writer.flush();

          } catch (IOException e) {
            e.printStackTrace();
          }
        }).start();
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (serverSocket != null) {
        serverSocket.close();
      }
    }

  }
}
