package com.example.chatproject;

import com.example.chatproject.models.Client;
import com.example.chatproject.models.MiObjeto;
import com.example.chatproject.models.objects.Message;
import com.example.chatproject.models.objects.User;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.chatproject.models.Client.*;

public class ChatApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
        /*
        insertarUsuario(new User(1,"EE","131",123131111));
        insertarMensaje(new Message(1, 2 ,3, "Test", "2024-02-25 10:15:00"));

         */
    }
}