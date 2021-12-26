package com.strluck.chat.server;

import com.strluck.chat.util.ClientModel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// 实现一个简单的 一线程对一客户端 的服务器模型
// 不考虑线程持久化等问题
public class Server {

    List<ClientModel> socketList;

    ServerSocket serverSocket;

    private boolean isClose = false;

    public Server() {
        this.socketList = new ArrayList<>();
    }

    public void run() throws IOException {
        // 创建一个服务端的套接字
        serverSocket = new ServerSocket(8888);
        // 开一个线程去等待客户端链接
        new Thread(() -> {
            while (!isClose) {
                try {
                    // 这里会阻塞线程等待
                    Socket socket = serverSocket.accept();
                    ClientModel cm = new ClientModel(socket);
                    // 添加监听器，将收到的消息广播给所有客户端
                    cm.addMonitor(this::sendAll);
                    socketList.add(cm);
                    // ClientModel run 会启动一个新的线程去监听客户端消息，实现一对一线程模型
                    cm.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 广播
    public void sendAll(String msg) {
        System.out.println("sendAll: " + msg);
        socketList.forEach((s) -> {
            if (s.isClose()) {
                return;
            }
            try {
                s.send(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void close() throws IOException {
        isClose = true;
        serverSocket.close();
        socketList.clear();
    }
}
