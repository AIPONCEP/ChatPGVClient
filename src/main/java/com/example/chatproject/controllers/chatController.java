package com.example.chatproject.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class chatController implements Initializable {
    public Label label_userName;
    public TextArea chat_textArea;
    public TextField chat_textField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_userName.setText(chatListController.userSelect);
    }
    public void sendSms(MouseEvent mouseEvent) {
    }
    public void back(MouseEvent mouseEvent) {
        WindowOpener.openWindow("/com/example/chatproject/chatList-view.fxml", "Usuarios");
    }
}
