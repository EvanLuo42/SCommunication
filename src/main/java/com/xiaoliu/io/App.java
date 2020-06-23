package com.xiaoliu.io;

import com.xiaoliu.io.network.IServer;
import com.xiaoliu.io.network.servers.HttpTestServer;

public class App {
    public static void main(String[] args) {
        try 
        {
            IServer server = new HttpTestServer(Runtime.getRuntime().availableProcessors(), 8080);
            server.run();   
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}