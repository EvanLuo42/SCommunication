package com.xiaoliu.io.network;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface ILogicHandler {
    public void onError(Exception err);

    public void onAccept(SocketChannel channel);

    public void onRead(SocketChannel channel,ByteBuffer data);

    public void onWriteCompletely(SocketChannel channel);

    public void onClose(SocketChannel channel);
}