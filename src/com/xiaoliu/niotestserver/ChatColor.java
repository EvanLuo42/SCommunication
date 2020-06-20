package com.xiaoliu.niotestserver;

public enum ChatColor {
    /**
     * 颜色字符
     */
    BLUE("\033[34;m"),
    GREEN("\033[32;m"),
    DARK_BLUE("\033[34;m"),
    DARK_RED("\033[31;m"),
    PURPLE("\033[35;m"),
    ORANGE("\033[33;m"),
    SHALLOW_DARK("\033[90;m"),
    PLACE_COLOR("\033[90;m"),
    SHALLOW_BLUE("\033[36;m"),
    DARK("\033[30;m"),
    SHALLOW_GREEN("\033[92;m"),
    SHALLOW_SHALLOW_BLUE("\033[97;m"),
    RED("\033[91;m"),
    PINK("\033[96;m"),
    YELLOW("\033[93;m"),
    WHITE("\033[37;m"),
    UNDERLINE("\033[4m"),
    RESET("\033[0m"),
    BOLD("\033[1m"),
    ;
    public String value;
    ChatColor(String s) {
        this.value = s;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
