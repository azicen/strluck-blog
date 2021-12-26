package com.strluck.chat.client;

import com.strluck.chat.client.view.EnterName;
import com.strluck.chat.client.view.View;
import com.strluck.chat.util.ClientModel;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        ClientModel cm = new ClientModel(socket);
        new View(cm);
        new EnterName(cm);
        cm.run();
    }
}
