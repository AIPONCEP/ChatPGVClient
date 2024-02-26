package com.example.chatproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import static com.example.chatproject.models.Client.encontrarUsuario;

public class LoginController {
    @FXML
    public TextField name_TextField;
    @FXML
    public PasswordField pass_TextField;
    @FXML
    public Label nombreLabel;
    public static String idUsuarioLogueado;

    /**
     * Método singIn
     * comprueba que los textfiels no esten vacios y
     * si el método encontrarUsuario no deuvuelve null cambia a vista Usuarios y guardar datos en idUsuarioLogueado
     * @param mouseEvent
     */
    public void signIn(MouseEvent mouseEvent) {
        if (!name_TextField.getText().isEmpty() & !pass_TextField.getText().isEmpty()){
            if (encontrarUsuario(name_TextField.getText(), pass_TextField.getText()) != null){
                idUsuarioLogueado = encontrarUsuario(name_TextField.getText(), pass_TextField.getText());
                WindowOpener.openWindow("/com/example/chatproject/chatList-view.fxml", nombreLabel,"Usuarios");
            }
        }else {
            Alert.showAlert("Error", "Debe rellenar todos los campos.", javafx.scene.control.Alert.AlertType.ERROR);
        }
    }
    public void newAccount(MouseEvent mouseEvent) {
        WindowOpener.openWindow("/com/example/chatproject/register-view.fxml", nombreLabel,"Registro");
    }
}