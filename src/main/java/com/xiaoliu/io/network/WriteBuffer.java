package com.xiaoliu.io.network;

import java.nio.ByteBuffer;

public class WriteBuffer {
    private ByteBuffer _buf;
    private int _off;
    
    public WriteBuffer(ByteBuffer data)
    {
        _buf = data;
        _off = 0;
    }

    public WriteBuffer(ByteBuffer data,int off)
    {
        _buf = data;
        _off = off;
    }

    public void add(int n)
    {
        if(n > 0)
        {
            _off += n;
        }
    }

    public boolean isComplete()
    {
        return _off >= _buf.limit();
    }

    public ByteBuffer getBuffer()
    {
        return _buf;
    }
}