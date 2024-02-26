package com.example.chatproject.controllers;

import com.example.chatproject.models.objects.Message;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.example.chatproject.models.Client.insertarMensaje;

public class chatController implements Initializable {
    public Label label_userName;
    public TextArea chat_textArea;
    public TextField chat_textField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_userName.setText(chatListController.userSelect);
    }
    public void sendSms(MouseEvent mouseEvent) {
        // Obtener la fecha y hora actual
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        // Formatear la fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraFormateada = fechaHoraActual.format(formatter);

        Message mensaje = new Message(
                Integer.parseInt(LoginController.idUsuarioLogueado.substring(3).trim()),
                Integer.parseInt(chatListController.idSelect.substring(3).trim()),
                chat_textField.getText(),
                fechaHoraFormateada
        );

        insertarMensaje(mensaje);
    }
    public void back(MouseEvent mouseEvent) {
        WindowOpener.openWindow("/com/example/chatproject/chatList-view.fxml", label_userName,"Usuarios");
    }
}
