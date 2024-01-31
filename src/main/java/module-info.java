module com.example.chatproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;


    opens com.example.chatproject to javafx.fxml;
    opens com.example.chatproject.models;
    exports com.example.chatproject;
    exports com.example.chatproject.controllers;
    exports com.example.chatproject.models;
    opens com.example.chatproject.controllers to javafx.fxml;
}