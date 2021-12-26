package com.strluck.chat.client.view;

import com.strluck.chat.util.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.IOException;

public class View extends JFrame implements ActionListener {

    ClientModel clientModel;

    // 文本域
    private JTextArea msgList;
    // 滚动条
    private JScrollPane jsp;
    // 面板里面是文本框和按钮
    private JPanel jp;
    private JTextField msgInput;
    private JButton sendButton;

    BufferedWriter bw = null;

    public View(ClientModel cm) {
        clientModel = cm;
        // 初始化上面的属性
        msgList = new JTextArea();

        // 将文本域添加到滚动条中
        jsp = new JScrollPane(msgList);
        jp = new JPanel();
        msgInput = new JTextField(15);
        sendButton = new JButton("发送");

        // 把按钮和文本框添加到面板中
        jp.add(msgInput);
        jp.add(sendButton);

        // 把滚动条和面板添加到JFrame中去
        this.add(jsp, BorderLayout.CENTER);
        this.add(jp, BorderLayout.SOUTH);

        this.setTitle("STRLUCK 聊天室");
        this.setSize(500, 500);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        sendButton.addActionListener(this);

        // 添加监听事件
        clientModel.addMonitor(s -> {
            msgList.append(s + "\n");
            if ("#抖动".equals(s)) {
                jitter();
            }
        });

        // 回车点击事件
        msgInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    send();
                }
            }
        });

        init();
    }

    public void init() {
        msgList.append("发送\"#抖动\"可以抖动所有人的窗口哦\n");
    }

    public void send() {
        // 获取文本框中需要发送的内容
        String text = msgInput.getText();
        if (!"#抖动".equals(text)) {
            text = clientModel.getName() + ": " + text;
        }
        try {
            clientModel.send(text);
            msgInput.setText("");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        send();
    }

    public void jitter() {
        int x = this.getX();
        int y = this.getY();
        for (int i = 0; i < 20; i++) {
            if ((i & 1) == 0) {
                x += 3;
                y += 3;
            } else {
                x -= 3;
                y -= 3;
            }
            this.setLocation(x, y);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}