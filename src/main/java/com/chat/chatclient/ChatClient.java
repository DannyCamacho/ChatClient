package com.chat.chatclient;

import java.io.*;
import java.net.*;

public class ChatClient {
    private String hostname;
    private int port;
    private String userName;
    private ClientController controller;
    private WriteThread writeThread;

    public ChatClient(String hostname, int port, ClientController controller) {
        this.hostname = hostname;
        this.port = port;
        this.controller = controller;
    }

    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);
            controller.updateClient("Connected to the ChatServer");

            new ReadThread(socket, this).start();
            writeThread = new WriteThread(socket, this);
        } catch (UnknownHostException ex) {
            controller.updateClient("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            controller.updateClient("I/O Error: " + ex.getMessage());
        }
    }

    void printToClient(String message) {
        controller.updateClient(message);
    }

    void sendToServer(String message) {
        writeThread.updateServer(message);
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    String getUserName() {
        return this.userName;
    }
}