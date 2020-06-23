package com.xiaoliu.io.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//多线程服务器
public abstract class MultiThreadServer implements IServer {

    private IReactor _mainReactor;

    private List<IReactor> _reactors;

    private ExecutorService _threadPool;

    private ServerSocketChannel _serverChannel;

    public MultiThreadServer(int threadNumber,SocketAddress address) throws IOException
    {
        _serverChannel = ServerSocketChannel.open();
        _serverChannel.socket().bind(address,65335);
        _mainReactor = new Reactor(new ReactorLogicHandler(this));
        if(threadNumber > 1)
        {
            _reactors = new ArrayList<>();
            threadNumber -= 1;
            for(int i = 0 ;i < threadNumber;i++)
            {
                _reactors.add(new Reactor(new ReactorLogicHandler(this)));
            }
            _threadPool = Executors.newFixedThreadPool(threadNumber);
        }
        _mainReactor.register(_serverChannel);
    }

    public MultiThreadServer(int threadNumber,int port) throws IOException
    {
        this(threadNumber,new InetSocketAddress(port));
    }

    protected IReactor dispathReactor(SocketChannel channel)
    {
        if(_reactors != null)
        {
            int hash = channel.hashCode();
            if(hash < 0)
            {
                hash = -hash;
            }
            int pos = hash % (_reactors.size() + 1);
            if(pos < _reactors.size())
            {
                return _reactors.get(pos);
            }
        }
        return _mainReactor;
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
        IReactor reactor = dispathReactor(channel);
        reactor.register(channel);
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
        if(_reactors != null && _threadPool != null)
        {
            for (IReactor reactor : _reactors) {
                _threadPool.submit(()->
                {
                    reactor.run();
                });
            }
        }
        if(_mainReactor != null)
        {
            _mainReactor.run();
        }
    }

    @Override
    public void stop() {
        if(_mainReactor != null)
        {
            _mainReactor.stop();
        }
        if (_reactors != null) {
            for (IReactor r : _reactors) {
                r.stop();
            }
        }
        try 
        {
            if (_serverChannel != null) 
            {
                _serverChannel.close();
            }   
        } 
        catch (IOException e) 
        {
            onError(e);
        }
    }

    @Override
    public void writeTo(SocketChannel channel, ByteBuffer data) {
        IReactor reactor = dispathReactor(channel);
        reactor.postWrite(channel, data);
    }
    
}