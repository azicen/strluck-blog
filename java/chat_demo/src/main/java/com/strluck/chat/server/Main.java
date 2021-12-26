package com.strluck.chat.server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.run();

        System.out.print(
                "   ______      __         __  \n" +
                "  / __/ /_____/ /_ ______/ /__\n" +
                " _\\ \\/ __/ __/ / // / __/  '_/\n" +
                "/___/\\__/_/ /_/\\_,_/\\__/_/\\_\\ \n");

        System.out.println("聊天室服务器已启动");

        while(true) {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
