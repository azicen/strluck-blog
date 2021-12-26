package com.strluck.chat.util;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// 客户端简单封装
public class ClientModel {

    private String name = "";

    // 监听器列表
    private final List<Monitor> monitorList;
    // 套子节
    private Socket socket;
    // 读写流
    private BufferedReader br;
    private BufferedWriter bw;

    private boolean isClose = false;

    public ClientModel(Socket socket) throws IOException {
        monitorList = new ArrayList<>();
        this.socket = socket;
        // 获取socket通道的输入流(输入流的读取方式为一行一行的读取方式 ----> readLine())
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 获取通道的输入流(也是一行一行的写出  BufferedWriter ->newLine())
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void run() {
        // 开一个线程去等待消息
        new Thread(() -> {
            String line;
            while (!isClose) {
                line = null;
                try {
                    if ((line = br.readLine()) == null) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 收到消息，回调所有监听器
                for (Monitor m : monitorList) {
                    m.monitor(line);
                }
            }
        }).start();
    }

    public boolean isClose() {
        if (isClose != socket.isClosed()) {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return isClose;
    }

    public void close() throws IOException {
        isClose = true;
        socket.close();
        monitorList.clear();
    }

    public void addMonitor(Monitor m) {
        monitorList.add(m);
    }

    public void send(String msg) throws IOException {
        bw.write(msg);
        bw.newLine(); // 换行
        bw.flush();  // 刷新
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
