package com.chat.chatclient;

import java.io.*;
import java.net.*;

public class WriteThread {
    private DataOutputStream toServer;
    private ChatClient client;

    public WriteThread(Socket socket, ChatClient client) {
        this.client = client;

        try {
            toServer = new DataOutputStream(socket.getOutputStream());
            toServer.writeUTF(client.getUserName());
        } catch (IOException ex) {
            client.printToClient("\nError getting output stream: " + ex.getMessage() + "\n");
        }
    }

    public void updateServer(String text) {
        try {
            toServer.writeUTF(text);
            client.printToClient("\n[" + client.getUserName() + "]: " + text);
        } catch (IOException ex) {
            client.printToClient("\nError writing to server: " + ex.getMessage() + "\n");
        }
    }
}