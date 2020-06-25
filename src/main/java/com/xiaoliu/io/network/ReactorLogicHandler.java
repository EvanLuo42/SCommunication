package com.xiaoliu.io.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ReactorLogicHandler implements IReactorLogicHandler {

    private ILogicHandler _handler;

    private Map<SocketChannel, List<ByteBuffer>> _writeBuffers;

    public ReactorLogicHandler(ILogicHandler logicHandler) {
        _handler = logicHandler;
        _writeBuffers = new HashMap<>();
    }

    @Override
    public void onError(SocketChannel channel, Exception err) {
        _handler.onError(channel, err);
    }

    @Override
    public void onAccept(SocketChannel channel) {
        _handler.onAccept(channel);
    }

    @Override
    public void onRead(SocketChannel channel, ByteBuffer data) {
        _handler.onRead(channel, data);
    }

    @Override
    public void onWrite(SocketChannel channel, IReactor reactor) {
        if (_writeBuffers.containsKey(channel)) {
            List<ByteBuffer> bufs = _writeBuffers.get(channel);
            ByteBuffer data = bufs.get(0);
            try {
                int r = channel.write(data);
                if(r < 0)
                {
                    throw new ClosedChannelException();
                }
                if (!data.hasRemaining()) {
                    bufs.remove(0);
                    if(bufs.isEmpty())
                    {
                        reactor.disableWrite(channel);
                    }
                    _handler.onWriteCompletely(channel);
                }
            } catch (ClosedChannelException e) {
                cleanBuffer(channel);
                reactor.disableWrite(channel);
                onClose(channel);
            } catch (IOException e) {
                cleanBuffer(channel);
                reactor.disableWrite(channel);
                _handler.onError(channel, e);
            }
            return;
        }
        reactor.disableWrite(channel);
        return;
    }

    @Override
    public void onClose(SocketChannel channel) {
        cleanBuffer(channel);
        _handler.onClose(channel);
    }

    @Override
    public void postWrite(SocketChannel channel, ByteBuffer data, IReactor reactor) {
        if (_writeBuffers.containsKey(channel)) {
            _writeBuffers.get(channel).add(data);
            reactor.enableWrite(channel);
            return;
        }
        List<ByteBuffer> buffers = new LinkedList<>();
        buffers.add(data);
        _writeBuffers.put(channel, buffers);
        reactor.enableWrite(channel);
    }

    private void cleanBuffer(SocketChannel channel) {
        if (_writeBuffers.containsKey(channel)) {
            _writeBuffers.remove(channel);
        }
    }

    @Override
    public void onError(ServerSocketChannel channel, Exception err) {
        _handler.onError(channel,err);
    }

    @Override
    public void onError(Exception err) {
        _handler.onError(err);
    }

    @Override
    public void closeChannel(SocketChannel channel) {
        cleanBuffer(channel);
        try 
        {
            channel.close();
        } 
        catch (Exception e)
        {
            onError(channel,e);
        }
    }
}