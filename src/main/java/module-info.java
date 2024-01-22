module org.example.chatpgv {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.chatpgv to javafx.fxml;
    exports org.example.chatpgv;
}