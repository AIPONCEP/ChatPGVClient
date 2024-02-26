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
import java.util.Arrays;
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
        // Si el mensaje no esta vacio inserta el mensaje y setea el textfield a ""
        if (!mensaje.getTxt_Mensaje().isEmpty()){
            insertarMensaje(mensaje);
            chat_textField.setText("");
        }
    }
    public void back(MouseEvent mouseEvent) {
        WindowOpener.openWindow("/com/example/chatproject/chatList-view.fxml", label_userName,"Usuarios");
    }

    private String ultimoMensajeRecibido = "";

    /**
     * Método recibirMensajesEnTiempoReal
     * se usa para simular la recepción de mensajes en tiempo real
     */
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
                    // Limpiar el TextArea antes de agregar el nuevo mensaje
                    Platform.runLater(() -> chat_textArea.clear());

                    // Dividir la cadena en filas individuales
                    String[] filas = mensajeRecibido.substring(7).split(" nombre:");

                    // Iterar sobre cada fila e imprimir
                    for (String fila : filas) {
                        // Copiar el valor de la fila a una variable local final
                        final String filaFinal = fila.replace("txt_Mensaje:", ": ");
                        // Agregar la fila al TextArea
                        Platform.runLater(() -> chat_textArea.appendText(filaFinal + "\n"));
                    }
                    ultimoMensajeRecibido = mensajeRecibido;
                }
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
