package com.xiaoliu.io.network;

import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface IReactor {
    public void run();

    public void stop();

    public void register(SocketChannel channel);

    public void register(ServerSocketChannel channel);

    public void enableWrite(SocketChannel channel,ByteBuffer data);

    public void disableWrite(SocketChannel channel);
}