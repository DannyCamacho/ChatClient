module com.chat.chatclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.chat.chatclient to javafx.fxml;
    exports com.chat.chatclient;
}