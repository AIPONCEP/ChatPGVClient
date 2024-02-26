package com.example.chatproject.controllers;

import com.example.chatproject.models.objects.User;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.chatproject.models.Client.insertarMensaje;
import static com.example.chatproject.models.Client.insertarUsuario;

public class RegisterContoller {
    public TextField name_TextField;
    public PasswordField pass_TextField;
    public TextField tlf_TextField;
    public Label nombreLabel;

    public void signIn(MouseEvent mouseEvent) {
        WindowOpener.openWindow("/com/example/chatproject/login-view.fxml", nombreLabel,"Login");
    }
    /**
     * newAccount sirver para registrar a un nuevo usuario
     * se comprueba que:
     * los campos no estén en blanco en el formulario
     * el número de teléfono tenga 9 dígitos y sea numérico
     * la contraseña tenga al menos 8 caracteres, 1 mayúscula, 1 minúscula y un número entre ellos.
     * @param mouseEvent
     */
    public void newAccount(MouseEvent mouseEvent) {
        String nombre = name_TextField.getText();
        String pass = pass_TextField.getText();
        String telefono = tlf_TextField.getText();

        if (nombre.isEmpty() || pass.isEmpty() || telefono.isEmpty()) {
           Alert.showAlert("Error","Todos los campos son obligatorios.", javafx.scene.control.Alert.AlertType.ERROR);
        } else if (!telefono.matches("\\d{9}")) {
            Alert.showAlert("Error", "El número de teléfono debe tener 9 dígitos y solo se admiten datos numericos.", javafx.scene.control.Alert.AlertType.ERROR);
        } else if (!pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            Alert.showAlert("Error", "La contraseña debe tener al menos 8 caracteres, incluyendo al menos una letra mayúscula, una minúscula y un número.", javafx.scene.control.Alert.AlertType.ERROR);
        } else{
            User user = new User(nombre, pass, Integer.parseInt(telefono));
            insertarUsuario(user);
            name_TextField.setText("");
            pass_TextField.setText("");
            tlf_TextField.setText("");
        }
    }
}
