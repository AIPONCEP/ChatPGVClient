package com.example.chatproject.controllers;

import com.example.chatproject.models.objects.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
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
import static com.example.chatproject.models.Client.recibirMensajes;

public class chatController implements Initializable {
    @FXML
    public Label label_userName;
    @FXML
    public TextArea chat_textArea;
    @FXML
    public TextField chat_textField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            // Inicia un hilo para recibir mensajes en tiempo real
            Thread thread = new Thread(this::recibirMensajesEnTiempoReal);
            thread.setDaemon(true); // Establece el hilo como demonio para que se detenga cuando la aplicación se cierre
            thread.start();

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

    // Método para simular la recepción de mensajes en tiempo real

    private String ultimoMensajeRecibido = "";

    private void recibirMensajesEnTiempoReal() {
        try {
            while (true) {
                String idUsuarioLogueado = LoginController.idUsuarioLogueado;
                int numeroUsuario = Integer.parseInt(idUsuarioLogueado.substring(idUsuarioLogueado.indexOf(':') + 1).trim());

                String idContactoSelect = chatListController.idSelect;
                int numeroContactoSelect = Integer.parseInt(idContactoSelect.substring(idContactoSelect.indexOf(':') + 1).trim());

                // Simulamos un mensaje recibido
                String mensajeRecibido = recibirMensajes(String.valueOf(numeroUsuario), String.valueOf(numeroContactoSelect));

                if (mensajeRecibido != null && !mensajeRecibido.equals(ultimoMensajeRecibido)) {
                    Platform.runLater(() -> chat_textArea.appendText(mensajeRecibido + "\n"));
                    ultimoMensajeRecibido = mensajeRecibido;
                }

                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
