package com.xiaoliu.io;

import com.xiaoliu.io.network.IServer;
import com.xiaoliu.io.network.servers.HttpTestServer;

public class App {
    public static void main(String[] args) {
        try 
        {
            IServer server = new HttpTestServer(Runtime.getRuntime().availableProcessors(), 8080);
            System.out.printf("Server listening on %d with %d threads\n",8080,Runtime.getRuntime().availableProcessors());
            Runtime.getRuntime().addShutdownHook(new ServerCloseHookThread(server,()->
            {
                System.out.println("Server closed");
            }));
            server.run();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}