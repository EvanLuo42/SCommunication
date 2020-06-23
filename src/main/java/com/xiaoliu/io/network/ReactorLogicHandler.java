package com.xiaoliu.io.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
            Iterator<ByteBuffer> begin = bufs.iterator();
            ByteBuffer data = begin.next();
            try {
                int r = channel.write(data);
                data.position(data.position() + r);
                if (!data.hasRemaining()) {
                    _handler.onWriteCompletely(channel);
                    begin.remove();
                    if (!begin.hasNext()) {
                        reactor.disableWrite(channel);
                    }
                }
            } catch (ClosedChannelException e) {
                onClose(channel);
                reactor.disableWrite(channel);
            } catch (IOException e) {
                _handler.onError(channel, e);
                cleanBuffer(channel);
                reactor.disableWrite(channel);
            }
            return;
        }
        reactor.disableWrite(channel);
        return;
    }

    @Override
    public void onClose(SocketChannel channel) {

        _handler.onClose(channel);
        cleanBuffer(channel);
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
}