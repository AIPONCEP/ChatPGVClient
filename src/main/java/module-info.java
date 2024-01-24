module com.example.chatproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.chatproject to javafx.fxml;
    exports com.example.chatproject;
    exports com.example.chatproject.controllers;
    opens com.example.chatproject.controllers to javafx.fxml;
}