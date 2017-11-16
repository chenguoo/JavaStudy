package com.cheney.study.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * 说明：测试BIOSocketClient
 *
 * @author Cheney <br>
 * modified by:
 * @version 1.0 <br>
 * Created in 2017-09-30 16:45
 */
public class BIOSocketClient {


    public static void main(String[] args) {
        Socket client = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        try {
            client = new Socket("localhost", 8888);
            writer = new PrintWriter(client.getOutputStream(), true);

            writer.println("hello BIO!");
            writer.flush();

            String serverData = null;
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            System.out.println("客户端收到数据为:" + reader.readLine());
//            while ((serverData = reader.readLine()) != null) {
//                System.out.println("客户端收到数据为:" + serverData);
//            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
