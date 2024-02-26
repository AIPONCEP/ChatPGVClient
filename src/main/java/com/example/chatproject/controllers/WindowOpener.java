package com.example.chatproject.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowOpener {

    /**
     * Método para abrir una nueva ventana con el contenido cargado desde un archivo FXML.     *
     * @param newFxmlPath La ruta del archivo FXML que define la interfaz de usuario de la nueva ventana.
     * @param oldTitle La ruta del archivo FXML de la ventana actual que se cerrará.
     * @param title El título de la nueva ventana que se mostrará en la barra de título.
     */
    public static void openWindow(String newFxmlPath, Label oldTitle, String title) {
        try {
            // Crear una nueva escena
            Scene scene=new Scene(FXMLLoader.load(WindowOpener.class.getResource(newFxmlPath)));
            Stage newStage =  (Stage) oldTitle.getScene().getWindow();
            newStage.setScene(scene);

            // Establecer el título de la nueva ventana
            newStage.setTitle(title);

            // Mostrar la nueva ventana
            newStage.show();

        } catch (IOException e) {
            // Manejar cualquier excepción que pueda ocurrir al cargar el archivo FXML
            e.printStackTrace();
        }
    }
}
