package com.xiaoliu.niotestserver.threads;

import com.xiaoliu.niotestserver.constructors.Server;

import java.io.IOException;

public class StartThread extends Thread{
    public Server server;
    @Override
    public void run() {

        try {
            System.out.println("Creating Server");
            server = new Server(30001);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
