package com.example.chatproject.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowOpener {

    /**
     * Método para abrir una nueva ventana con el contenido cargado desde un archivo FXML.
     *
     * @param fxmlPath La ruta del archivo FXML que define la interfaz de usuario de la ventana.
     * @param title    El título de la ventana que se mostrará en la barra de título.
     */
    public static void openWindow(String fxmlPath, String title) {
        try {
            // Cargar el archivo FXML
            FXMLLoader fxmlLoader = new FXMLLoader(WindowOpener.class.getResource(fxmlPath));
            Parent root = fxmlLoader.load();

            // Crear una nueva escena
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            // Establecer el título de la ventana
            stage.setTitle(title);

            // Mostrar la ventana
            stage.show();
        } catch (IOException e) {
            // Manejar cualquier excepción que pueda ocurrir al cargar el archivo FXML
            e.printStackTrace();
        }
    }
}
