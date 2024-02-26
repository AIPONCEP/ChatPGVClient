package com.example.chatproject.controllers;
public class Alert {
    /**
     * Método showAlert
     * se utiliza para generar alertas
     * @param title
     * @param message
     * @param alertType
     */
    public static void showAlert(String title, String message, javafx.scene.control.Alert.AlertType alertType) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
