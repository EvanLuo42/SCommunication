package com.xiaoliu.io.network.servers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.xiaoliu.io.network.MultiThreadServer;

public class HttpTestServer extends MultiThreadServer {

    public HttpTestServer(int threadNumber, int port) throws IOException {
        super(threadNumber, port);
    }
    
    @Override
    public void onRead(SocketChannel channel,ByteBuffer data)
    {
        ByteBuffer buf = ByteBuffer.wrap("HTTP/1.0 200 OK\r\nContent-Length: 11\r\n\r\nHello World".getBytes());
        writeTo(channel,buf);
    }
}