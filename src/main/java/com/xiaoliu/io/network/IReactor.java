package com.xiaoliu.io.network;

import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface IReactor {
    //运行(请单线程调用)
    public void run();

    //停止
    public void stop();

    //注册SocketChannel
    public void register(SocketChannel channel);

    //注册ServerSocketChannel
    public void register(ServerSocketChannel channel);

    //启用写监听(不直接调用)
    public void enableWrite(SocketChannel channel);

    //停止写监听(不直接调用)
    public void disableWrite(SocketChannel channel);

    //投递写请求(call me!)
    public void postWrite(SocketChannel channel,ByteBuffer data);

    public void close(SocketChannel channel);
}