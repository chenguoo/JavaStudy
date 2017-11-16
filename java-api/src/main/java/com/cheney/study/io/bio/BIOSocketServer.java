package com.cheney.study.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 说明：测试BIOSocketClient
 *
 * @author Cheney <br>
 * modified by:
 * @version 1.0 <br>
 * Created in 2017-09-27 10:41
 */
public class BIOSocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                //--------------数据处理  开始----------------
                new Thread(() -> {
                    PrintWriter writer = null;
                    BufferedReader reader = null;

                    try {
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));


                        String clientData = null;

                        System.out.println("服务器收到数据为:" + reader.readLine());
//                        while ((clientData = reader.readLine()) != null) {
//                            System.out.println("服务器收到数据为:" + clientData);
//                        }

                        writer.println("hello  收到了你的数据 ^_^");
                        writer.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {

                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (writer != null) {
                            writer.close();
                        }
                        if (socket != null) {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                //--------------数据处理  结束----------------
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
