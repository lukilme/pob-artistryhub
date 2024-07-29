package com.artistryhub.terminal;

import java.io.Console;

@SuppressWarnings("unused")

public final class TPrint {
    
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String WARNING_MESSAGE = "\033[1m\033[4m\033[40m\033[33m";
    private static final String DANGER_MESSAGE = "\033[0m\033[4m\033[30m\033[41m";
    private static final String SUCCESS_MESSAGE = "\033[0m\033[30m\033[42m";
    
    public static String padRight(String s, int n) {
         return String.format("%-" + n + "s", s);  
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);  
    }
    
    public static void warning(String message) {
        System.out.println(WARNING_MESSAGE + padRight(message, 30) + " ⚠" + ANSI_RESET);
    }
    
    public static void danger(String message) {
        System.out.println(DANGER_MESSAGE + padRight(message, 30) + " ❌" + ANSI_RESET);
    }
    
    public static void success(String message) {
        System.out.println(SUCCESS_MESSAGE + padRight(message, 30) + " ✔" + ANSI_RESET);
    }
    
    public static void main(String[] args) {
        int width = getTerminalWidth();
        int height = getTerminalHeight();
        
        System.out.println("Terminal width: " + width);
        System.out.println("Terminal height: " + height);
        
        warning("This is a warning");
        danger("This is a danger");
        success("This is a success");
    }

    public static int getTerminalWidth() {
        Console console = System.console();
        if (console != null) {
            String[] cmd = { "/bin/sh", "-c", "tput cols 2> /dev/tty" };
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
                java.io.InputStream in = process.getInputStream();
                byte[] b = new byte[in.available()];
                in.read(b);
                return Integer.parseInt(new String(b).trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 80; // Default width
    }

    public static int getTerminalHeight() {
        Console console = System.console();
        if (console != null) {
            String[] cmd = { "/bin/sh", "-c", "tput lines 2> /dev/tty" };
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
                java.io.InputStream in = process.getInputStream();
                byte[] b = new byte[in.available()];
                in.read(b);
                return Integer.parseInt(new String(b).trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 24; // Default height
    }
}