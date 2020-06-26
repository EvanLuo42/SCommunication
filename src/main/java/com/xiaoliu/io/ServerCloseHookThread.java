package com.xiaoliu.io;

import com.xiaoliu.io.network.IServer;

public class ServerCloseHookThread extends Thread {
    private IServer _server;

    private Runnable _after;

    public ServerCloseHookThread(IServer server)
    {
        super();
        _server = server;
    }

    public ServerCloseHookThread(IServer server,Runnable after)
    {
        super();
        _server = server;
        _after = after;
    }

    @Override
    public void run()
    {
        _server.stop();
        if(_after != null)
        {
            _after.run();
        }
    }
}