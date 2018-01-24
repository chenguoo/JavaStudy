package com.cheney.study.netty5.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start {

    public static void main(String[] args) {
        MultClient client = new MultClient();

        client.init(5);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("请输入:");
            try {
                String msg = bufferedReader.readLine();
                client.nextChannel().writeAndFlush(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
