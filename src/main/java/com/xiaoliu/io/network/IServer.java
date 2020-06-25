package com.xiaoliu.io.network;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

//服务器实现此接口
public interface IServer extends ILogicHandler {
    public void run();

    public void stop();

    public void writeTo(SocketChannel channel,ByteBuffer data);

    public void closeChannel(SocketChannel channel);
}