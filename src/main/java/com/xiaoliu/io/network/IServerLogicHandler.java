package com.xiaoliu.io.network;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

//处理服务器逻辑的类实现此接口
public interface IServerLogicHandler<TServer extends IServer> {
    public void onAccept(TServer server,SocketChannel channel);

    public void onRead(TServer server,SocketChannel channel,ByteBuffer data);

    public void onClose(TServer server,SocketChannel channel);

    public void onWriteCompletely(TServer server,SocketChannel channel);
}