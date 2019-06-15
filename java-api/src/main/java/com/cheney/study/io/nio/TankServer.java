package com.cheney.study.io.nio;

public class TankServer{

  public static void main(String[] args) {
    new TankMsgServer("localhost", 888).start();
  }

}
