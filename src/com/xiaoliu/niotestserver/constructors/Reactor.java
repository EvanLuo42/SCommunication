package com.xiaoliu.niotestserver.constructors;


import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Reactor {
    public boolean keepWhile;
    private final Selector selector;

    public Reactor(Selector selector) throws IOException {
        this.selector = selector;
        keepWhile = true;
        start();

    }

    public void stop() throws IOException {
        keepWhile = false;
        selector.close();
    }

    private void start() throws IOException {

        while (keepWhile) {

            runMethod();

        }

    }

    private void handler(SelectionKey key) {
        if (key.isAcceptable()) {
            System.out.println("Get a Acceptable Key");
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    private void runMethod() throws IOException {

        int eventNum = selector.select();
        System.out.println("Get a number from Client: "+eventNum);
        Set<SelectionKey> keySet = selector.selectedKeys();
        for (SelectionKey selectionKey : keySet) {
            handler(selectionKey);
            keySet.remove(selectionKey);
        }

    }

}
