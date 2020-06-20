package com.xiaoliu.niotestserver;

import com.xiaoliu.niotestserver.threads.StartThread;

public class Main {
    public static Thread startThread;

    public static void main(String[] args) {
        open();

        close();
    }

    private static void close() {



        closeFinally();
    }

    private static void closeFinally() {

    }

    private static void open() {
        doPreparation();


    }

    private static void doPreparation() {
        new PrintStreamInitial();
        startThread = new StartThread();
        startThread.start();
    }

}
