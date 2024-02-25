package com.example.chatproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import static com.example.chatproject.models.Client.encontrarUsuario;

public class LoginController {
    @FXML
    public TextField name_TextField;

    @FXML
    public PasswordField pass_TextField;

    public String idUsuarioLogueado;

    public void signIn(MouseEvent mouseEvent) {

        if (!name_TextField.getText().isEmpty() & !pass_TextField.getText().isEmpty()){
            if (encontrarUsuario(name_TextField.getText(), pass_TextField.getText()) != null){
                idUsuarioLogueado = encontrarUsuario(name_TextField.getText(), pass_TextField.getText());
                System.out.println(idUsuarioLogueado);

                WindowOpener.openWindow("/com/example/chatproject/chatList-view.fxml", "Usuarios");
            }
        }

    }

    public void newAccount(MouseEvent mouseEvent) {
    }
}