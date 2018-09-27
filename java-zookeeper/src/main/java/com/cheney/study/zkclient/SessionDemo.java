package com.cheney.study.zkclient;

import com.cheney.study.ZkConstants;
import org.I0Itec.zkclient.ZkClient;

import java.io.*;


/**
 * @author Cheney
 */
public class SessionDemo {


    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(ZkConstants.CONNECT_STRING, 4000);

        System.out.println(zkClient + " - > success");

    }
}
