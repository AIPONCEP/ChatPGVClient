package com.example.chatproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import static com.example.chatproject.models.DBconnection.ExecuteSelectSql;

public class chatListController {
    @FXML
    private ListView usersList;

    public void initialize(){
        usersList.getItems().addAll(
        );
    }
}
