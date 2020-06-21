package com.xiaoliu.io.network;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface IServer {
    public void run();

    public void stop();

    public void writeTo(SocketChannel channel,ByteBuffer data);
}