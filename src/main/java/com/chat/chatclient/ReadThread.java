package com.chat.chatclient;

import java.io.*;
import java.net.*;

public class ReadThread extends Thread {
    private DataInputStream fromServer;
    private ChatClient client;

    public ReadThread(Socket socket, ChatClient client) {
        this.client = client;

        try {
            fromServer = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            client.printToClient("\nError getting input stream: " + ex.getMessage() + "\n");
        }
    }

    public void run() {
        while (true) {
            try {
                client.printToClient("\n" + fromServer.readUTF());
            } catch (IOException ex) {
                client.printToClient("\nError reading from server: " + ex.getMessage()+ "\n");
                break;
            }
        }
    }
}