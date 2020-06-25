package com.xiaoliu.io.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

//单线程服务器
public abstract class SingleThreadServer implements IServer {

    private IReactor _reactor;

    private ServerSocketChannel _serverChannel;

    public SingleThreadServer(SocketAddress address) throws IOException
    {
        _serverChannel = ServerSocketChannel.open();
        _serverChannel.socket().bind(address);
        _reactor = new Reactor(new ReactorLogicHandler(this));
        _reactor.register(_serverChannel);
    }

    public SingleThreadServer(int port) throws IOException
    {
        this(new InetSocketAddress(port));
    }

    @Override
    public void onError(Exception err) {
    }

    @Override
    public void onError(ServerSocketChannel channel, Exception err) {
    }

    @Override
    public void onError(SocketChannel channel, Exception err) {
        
    }

    @Override
    public void onAccept(SocketChannel channel) {
        _reactor.register(channel);
    }

    @Override
    public void onRead(SocketChannel channel, ByteBuffer data) {
    }

    @Override
    public void onWriteCompletely(SocketChannel channel) {
    }

    @Override
    public void onClose(SocketChannel channel) {
    }

    @Override
    public void run() {
        if(_reactor != null)
        {
            _reactor.run();
        }
    }

    @Override
    public void stop() {
        try 
        {
            if(_reactor != null)
            {
                _reactor.stop();
            }
            if(_serverChannel != null)
            {
                _serverChannel.close();
            }   
        } 
        catch (IOException e) 
        {
            onError(_serverChannel,e);
        }
    }

    @Override
    public void writeTo(SocketChannel channel, ByteBuffer data) {
        _reactor.postWrite(channel, data);
    }
    
    @Override
    public void closeChannel(SocketChannel channel)
    {
        _reactor.closeChannel(channel);
    }
}