package com.example.chatproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import static com.example.chatproject.models.Client.encontrarUsuarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import static com.example.chatproject.models.Client.encontrarUsuarios;

public class chatListController {
    @FXML
    private Label Title;

    @FXML
    private ListView<String> usersList; // Especifica el tipo de elementos que contendrá el ListView
    //Me declaro la variable para guardar el nombre y pasarlo a la vista del chat
    public static String userSelect;
    public void initialize(){
        String usuarios = encontrarUsuarios();

        String[] lista = usuarios.substring(7).split("\\s+nombre:");

        ObservableList<String> items = FXCollections.observableArrayList(lista);

        // Asignar la lista observable al ListView
        usersList.setItems(items);

        // Configurar un evento de clic en el ListView
        usersList.setOnMouseClicked(event -> {
            // Obtener el valor seleccionado del ListView
            String selectedItem = usersList.getSelectionModel().getSelectedItem();

            // Verificar si hay un elemento seleccionado
            if (selectedItem != null) {
                // Imprimir el valor seleccionado en la consola
                System.out.println("Valor seleccionado: " + selectedItem);
                userSelect=selectedItem;
            }
        });
    }

    public void atrasClicked(){
        WindowOpener.openWindow("/com/example/chatproject/login-view.fxml", Title,"Login");
    }
    public void irAlChatClicked(){
        if(userSelect!=null){
            WindowOpener.openWindow("/com/example/chatproject/chat-view.fxml", Title,"Chat");
        }else {
            Alert.showAlert("Error", "Debe seleccionar un usuario", javafx.scene.control.Alert.AlertType.ERROR);
        }
    }
}


