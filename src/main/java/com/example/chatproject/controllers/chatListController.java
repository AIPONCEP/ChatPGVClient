package com.example.chatproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

import static com.example.chatproject.models.Client.sendCommand;

public class chatListController {
    @FXML
    private ListView usersList;

    public void initialize(){
       /*
        String names = consultas("SELECT name FROM users");
        String[] list = names.split("\n");
        for (String element : list){
            usersList.getItems().addAll(
                    element.substring(5, element.length())
            );
        }
        */
    }
}
