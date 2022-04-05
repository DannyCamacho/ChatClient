package com.chat.chatclient;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.util.Objects;

public class ClientController {
    private ChatClient client;
    @FXML
    private Button connectButton, disconnectButton, sendButton;
    @FXML
    private TextField inputTextField, usernameTextField;
    @FXML
    private TextArea outputTextArea;
    @FXML
    private Label usernameLabel;

    public void onConnectClicked(MouseEvent mouseEvent) {
        if (!Objects.equals(usernameTextField.getText(), "")) {
            usernameLabel.setVisible(false);
            connectButton.setVisible(false);
            usernameTextField.setVisible(false);
            inputTextField.setDisable(false);
            outputTextArea.setDisable(false);
            sendButton.setDisable(false);
            disconnectButton.setDisable(false);

            //client = new ChatClient("192.168.1.174", 8000, this); // danny's laptop
            //client = new ChatClient("192.168.1.218", 8000, this); // danny's desktop
            client = new ChatClient("localhost", 8000, this); // same machine
            client.setUserName(usernameTextField.getText());
            client.execute();
        }
    }

    public void updateClient(String message) {
        outputTextArea.appendText(message);
    }

    public void onSendClicked(MouseEvent mouseEvent) {
        if (!Objects.equals(inputTextField.getText(), "")) {
            client.sendToServer(inputTextField.getText());
            inputTextField.setText("");
        }
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().getCode() == 10) {
            if (!Objects.equals(inputTextField.getText(), "")) {
                client.sendToServer(inputTextField.getText());
                inputTextField.setText("");
            }
        }
    }

    public void onDisconnectClicked(MouseEvent mouseEvent) {
        if (client != null) {
            client.sendToServer("<QUIT>");
        } Platform.exit();
    }
}