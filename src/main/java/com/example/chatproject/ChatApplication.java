package com.example.chatproject;

import com.example.chatproject.models.Client;
import com.example.chatproject.models.MiObjeto;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatApplication.class.getResource("chatList-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        try {
            Client client = new Client("127.0.0.1", 49999);

            // Crea un objeto y conviértelo a JSON
            MiObjeto objeto = new MiObjeto("Ejemplo", 42);
            Gson gson = new Gson();

            String json = gson.toJson(objeto);

            // Envia el JSON al servidor
            client.sendCommand("Insertar", json);

            // Recibe la respuesta del servidor
            String respuestaServidor = client.receiveMessage();
            System.out.println("Respuesta del servidor: " + respuestaServidor);

            // Cierra la conexión con el servidor
            client.closeConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
        launch();
    }
}