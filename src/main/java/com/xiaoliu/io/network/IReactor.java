package com.xiaoliu.io.network;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface IReactor {
    public void run();

    public void stop();

    public void register(SocketChannel channel);

    public void register(ServerSocketChannel channel);

    public void enableWrite(SocketChannel channel);

    public void disableWrite(SocketChannel channel);
}