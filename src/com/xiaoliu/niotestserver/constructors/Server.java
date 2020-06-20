package com.xiaoliu.niotestserver.constructors;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Server {
    public final ServerSocketChannel serverSocketChannel;
    public final InetSocketAddress socketAddress;
    public final Selector selector;
    public final Reactor reactor;

    public Server(int port) throws IOException {
        System.out.println("Creating ServerSocketChannel");
        serverSocketChannel = ServerSocketChannel.open();
        socketAddress = new InetSocketAddress(port);
        serverSocketChannel.bind(socketAddress);
        selector = Selector.open();
        registerServer();
        reactor = new Reactor(selector);
    }

    private void registerServer() throws IOException {
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
}
