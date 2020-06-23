package com.xiaoliu.io.network.servers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.xiaoliu.io.network.MultiThreadServer;

//Echo Server
public class EchoServer extends MultiThreadServer {

    public EchoServer(int threadNumber, int port) throws IOException {
        super(threadNumber, port);
    }
    
    @Override
    public void onRead(SocketChannel channel,ByteBuffer data)
    {
        writeTo(channel, data);
    }
}