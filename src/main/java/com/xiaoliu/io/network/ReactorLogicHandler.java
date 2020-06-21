package com.xiaoliu.io.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReactorLogicHandler implements IReactorLogicHandler {

    private ILogicHandler _handler;

    private Map<SocketChannel,List<WriteBuffer>> _writeBuffers;

    public ReactorLogicHandler(ILogicHandler logicHandler)
    {
        _handler = logicHandler;
        _writeBuffers = new HashMap<>();
    }

    @Override
    public void onError(Exception err) {
        _handler.onError(err);
    }

    @Override
    public void onAccept(SocketChannel channel) {
        _handler.onAccept(channel);
    }

    @Override
    public void onRead(SocketChannel channel, ByteBuffer data) {
        _handler.onRead(channel,data);
    }

    @Override
    public void onWrite(SocketChannel channel,IReactor reactor) {
        if (_writeBuffers.containsKey(channel)) 
        {
            List<WriteBuffer> bufs = _writeBuffers.get(channel);
            Iterator<WriteBuffer> begin = bufs.iterator();
            WriteBuffer data = begin.next();
            try 
            {
                int r = channel.write(data.getBuffer());
                data.add(r);
                if(data.isComplete())
                {
                    _handler.onWriteCompletely(channel);
                    begin.remove();
                    if(!begin.hasNext())
                    {
                        reactor.disableWrite(channel);
                    }
                }
            } 
            catch (ClosedChannelException e) 
            {
                _handler.onClose(channel);
                bufs.clear();
                reactor.disableWrite(channel);
            }
            catch(IOException e)
            {
                _handler.onError(e);
                bufs.clear();
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
    }

    @Override
    public void postWrite(SocketChannel channel, ByteBuffer data) {
        if(_writeBuffers.containsKey(channel))
        {
            _writeBuffers.get(channel).add(new WriteBuffer(data));
            return;
        }
        List<WriteBuffer> buffers = new LinkedList<>();
        buffers.add(new WriteBuffer(data));
        _writeBuffers.put(channel,buffers);
    }
}