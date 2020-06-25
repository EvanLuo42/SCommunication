package com.xiaoliu.io.network.servers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.xiaoliu.io.network.MultiThreadServer;

//Echo Server
public class EchoServer extends MultiThreadServer {

    public EchoServer(int threadNumber, int port) throws IOException {
        super(threadNumber, port);
    }
    
    //数据到达
    @Override
    public void onRead(SocketChannel channel,ByteBuffer data)
    {
        writeTo(channel, data);
    }

    //数据写入完成
    @Override
    public void onWriteCompletely(SocketChannel channel)
    {

    }

    //框架出错
    @Override
    public void onError(Exception err)
    {

    }

    //客户端出错
    @Override
    public void onError(SocketChannel channel,Exception err)
    {

    }

    //服务器出错
    @Override
    public void onError(ServerSocketChannel channel,Exception err)
    {

    }

    //新连接到达
    @Override
    public void onAccept(SocketChannel channel)
    {
        //一定要调用这个
        super.onAccept(channel);
    }

    //客户端关闭
    @Override
    public void onClose(SocketChannel channel)
    {
        closeChannel(channel);
    }
}