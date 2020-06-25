package com.xiaoliu.io.network.servers;

import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

import com.xiaoliu.io.network.MultiThreadServer;

public class HttpTestServer extends MultiThreadServer {

    private AtomicInteger tag;

    public HttpTestServer(int threadNumber, int port) throws IOException {
        super(threadNumber, port);
        tag = new AtomicInteger(0);
    }
    
    @Override
    public void onRead(SocketChannel channel,ByteBuffer data)
    {
        ByteBuffer buf = ByteBuffer.wrap("HTTP/1.0 200 OK\r\nContent-Length: 11\r\n\r\nHello World".getBytes());
        writeTo(channel,buf);
    }

    @Override
    public void onWriteCompletely(SocketChannel channel)
    {
        System.out.println(tag.getAndIncrement());
        closeChannel(channel);
    }

    @Override
    public void onError(SocketChannel channel,Exception err)
    {
        err.printStackTrace();
        if(channel.isOpen())
        {
            closeChannel(channel);
        }
    }

    @Override
    public void onError(Exception err)
    {
        err.printStackTrace();
    }

    @Override
    public void onError(ServerSocketChannel channel,Exception err)
    {
        err.printStackTrace();
    }
}