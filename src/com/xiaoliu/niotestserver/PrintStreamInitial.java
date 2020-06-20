package com.xiaoliu.niotestserver;

import org.fusesource.jansi.AnsiConsole;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintStreamInitial {

    public PrintStreamInitial() {
        AnsiConsole.systemInstall();
        PrintStream outStream = new PrintStream(System.out) {
            @Override
            public void println(String text) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                Date time = new Date(System.currentTimeMillis());
                super.println("["+simpleDateFormat.format(time)+" "+ ChatColor.SHALLOW_GREEN+"INFO"+ChatColor.RESET+"]: "+text+ChatColor.RESET);
            }
        };
        PrintStream errorStream = new PrintStream(System.err) {
            @Override
            public void println(String text) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                Date time = new Date(System.currentTimeMillis());
                super.println("["+simpleDateFormat.format(time)+" "+ ChatColor.RED+"Error"+ChatColor.RESET+"]: "+text+ChatColor.RESET);
            }
        };
        System.setOut(outStream);
        System.setErr(errorStream);
    }

}
