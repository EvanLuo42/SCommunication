package com.xiaoliu.io.network;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface IReactorLogicHandler {
    public void onError(Exception err);

    public void onAccept(SocketChannel channel);

    public void onRead(SocketChannel channel,ByteBuffer data);

    public void onWrite(SocketChannel channel,IReactor reactor);

    public void onClose(SocketChannel channel);

    public void postWrite(SocketChannel channel,ByteBuffer data,IReactor reactor);
}