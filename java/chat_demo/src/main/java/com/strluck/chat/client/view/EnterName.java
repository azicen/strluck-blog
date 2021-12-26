package com.strluck.chat.client.view;

import com.strluck.chat.util.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class EnterName extends JFrame implements ActionListener {

    ClientModel clientModel;

    private JPanel jp;
    private JTextField nameInput;
    private JButton button;

    public EnterName(ClientModel cm) {
        clientModel = cm;

        jp = new JPanel();
        JLabel jl = new JLabel("输入你的名字: ");
        nameInput = new JTextField(15);
        button = new JButton("确定");
        jp.add(jl);
        jp.add(nameInput);
        jp.add(button);

        this.add(jp, BorderLayout.SOUTH);

        this.setTitle("输入你的名字");
        this.setSize(350, 100);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        button.addActionListener(this);

        // 回车点击事件
        nameInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    setName();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setName();
    }

    public void setName() {
        String text = nameInput.getText();
        clientModel.setName(text);
        this.setVisible(false);
        try {
            clientModel.send("欢迎" + text + "加入strluck的聊天室");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
